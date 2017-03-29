package com.example.lyj1996.main;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.lyj1996.wing1.R;
import com.example.lyj1996.constants.Constants;

public class WelcomeActivity extends AppCompatActivity {

    private static final int LOWBRIGHTNESS = -230;
    private static final double everycut = 5;
    private boolean isFirstIn = false;
    private Bitmap bmp;
    private ImageView iv;
    private int brightness = 70;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        bmp = BitmapFactory.decodeResource(this.getBaseContext().getResources(),
                R.drawable.welcome_page);
        iv = (ImageView) this.findViewById(R.id.image_welcome);

        init();

        /*

        // Handler构造器
        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case DECREASE:
                        brightness -= everycut * 1.05;
                        brightChanged(brightness, bmp, iv);
                        break;
                    case GO_INDEX:
                        goIndex();
                        break;
                    case GO_GUIDE:
                        goGuide();
                        break;
                }
                super.handleMessage(msg);
            }
        };

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    mHandler.sendEmptyMessage(DECREASE);// Handler发出请求
                    if (brightness < LOWBRIGHTNESS) {
                        init();
                        break;
                    }
                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        */
    }

    private void init() {
        // 读取SharedPreferences中需要的数据
        // 使用SharedPreferences来记录程序的使用次数
        SharedPreferences preferences = getSharedPreferences(
                Constants.SHAREDPREFERENCES_NAME, MODE_PRIVATE);
        // 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
        isFirstIn = preferences.getBoolean("isFirstIn", true);
        // 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
        if (!isFirstIn) {
            // 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity
//            mHandler.sendEmptyMessageDelayed(GO_INDEX, KEEP_INDEX_TIME);
            goIndex();
        } else {
//            mHandler.sendEmptyMessageDelayed(GO_GUIDE, KEEP_INDEX_TIME);
            goGuide();
        }
    }

    private void goIndex() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goGuide() {
        Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
        startActivity(intent);
        finish();
    }

    public void brightChanged(int brightness, Bitmap bitmap,
                              ImageView iv) {
        int imgHeight = bitmap.getHeight();
        int imgWidth = bitmap.getWidth();
        Bitmap bmp = Bitmap.createBitmap(imgWidth, imgHeight, Bitmap.Config.ARGB_8888);
        ColorMatrix cMatrix = new ColorMatrix();
        cMatrix.set(
                new float[]{
                        1, 0, 0, 0, brightness, // 改变亮度
                        0, 1, 0, 0, brightness,
                        0, 0, 1, 0, brightness,
                        0, 0, 0, 1, 0
                });

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));

        Canvas canvas = new Canvas(bmp);
        // 在Canvas上绘制一个已经存在的Bitmap。这样，dstBitmap就和srcBitmap一摸一样了
        canvas.drawBitmap(bitmap, 0, 0, paint);
        iv.setImageBitmap(bmp);
    }

}

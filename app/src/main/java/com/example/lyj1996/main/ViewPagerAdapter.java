package com.example.lyj1996.main;



        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.v4.view.PagerAdapter;
        import android.support.v4.view.ViewPager;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.example.lyj1996.main.MainActivity;
        import com.example.lyj1996.wing1.R;
        import com.example.lyj1996.constants.Constants;

        import java.util.List;

/**
 * Created by G on 2017/03/21.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private List<View> views;
    private Activity activity;

    public ViewPagerAdapter(List<View> views, Activity activity) {
        this.views = views;
        this.activity = activity;
    }

    // 销毁position位置的界面
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(views.get(position));
    }

    //获得当前界面数
    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        }
        return 0;
    }

    //初始化position位置的界面
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(views.get(position), 0);
        //判断是否到达最后一页
        if (position == views.size() - 1) {
            TextView startBtn = (TextView) container
                    .findViewById(R.id.start_btn);
            startBtn.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 设置已经引导
                            setNoGuide();
                            goIndex();

                        }
                    });
        }
        return views.get(position);
    }

    private void goIndex() {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    //设置下次启动不用再次引导
    private void setNoGuide() {
        SharedPreferences preferences = activity.getSharedPreferences(
                Constants.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        // 存入数据
        editor.putBoolean("isFirstIn", false);
        // 提交修改
        editor.apply();
    }

    //判断是否由对象生成界面
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }
}


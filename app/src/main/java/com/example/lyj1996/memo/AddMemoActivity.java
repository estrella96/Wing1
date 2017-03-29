package com.example.lyj1996.memo;

import android.content.Intent;
import java.text.SimpleDateFormat;
//import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lyj1996.wing1.R;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;

public class AddMemoActivity extends AppCompatActivity {

    private Button btn_back;
    private Button btn_save;
    private TextView memo_time;
    private EditText memo_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmemo);
        memo_time=(TextView)findViewById(R.id.addmemo_time);
        memo_content=(EditText) findViewById(R.id.addmemo_content);

        //设置备忘录创建时间
        setMemoTime();

        btn_back = (Button) findViewById(R.id.addmemo_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_save = (Button) findViewById(R.id.addmeno_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = memo_content.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(AddMemoActivity.this, "文本内容不能为空！", Toast.LENGTH_LONG).show();
                } else {
                    String time = memo_time.getText().toString();
                    content = time + ":" + content + "#";
                    boolean flag = saveMemo(content);   //将数据存储到memo_data文件中
                    if (flag == true) {
                        Toast.makeText(AddMemoActivity.this, "添加成功！", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(AddMemoActivity.this, "添加失败！", Toast.LENGTH_LONG).show();
                    }
                    finish();
                    Intent intent = new Intent(AddMemoActivity.this, MemoActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * 设置备忘录创建时间
     */
    private void setMemoTime() {
        Calendar calendar = Calendar.getInstance();
        String created = calendar.get(Calendar.YEAR) + "年"
                + (calendar.get(Calendar.MONTH) + 1) + "月"//从0计算
                + calendar.get(Calendar.DAY_OF_MONTH) + "日";
        memo_time.setText(created);
    }

    /**
     * 将数据存储到memo_data文件中
     */
    private boolean saveMemo(String content) {
        boolean flag = true;
        FileOutputStream out = null;
        BufferedOutputStream writer = null;
        try {
            out = openFileOutput("memo_data", MODE_APPEND);
            writer = new BufferedOutputStream(out);
            byte[] buffer = content.getBytes();
            writer.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }
}




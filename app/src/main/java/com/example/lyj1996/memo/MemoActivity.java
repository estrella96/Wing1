package com.example.lyj1996.memo;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.lyj1996.wing1.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;



public class MemoActivity extends AppCompatActivity
{
    private Button btn_back;
    private  Button btn_add;
    private ListView listView;
    private  List<Map<String,String>> memolist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String memo_content=loadMemo();
        if (TextUtils.isEmpty(memo_content))
        {
            setContentView(R.layout.activity_memo_empty);
        }
        else {
            setContentView(R.layout.activity_memo_main);
            initMemoList(memo_content);
            listView=(ListView)findViewById(R.id.memo_listview);
            listView = (ListView) findViewById(R.id.memo_listview);
            SimpleAdapter adapter = new SimpleAdapter(MemoActivity.this, memolist, R.layout.memo_list,
                    new String[]{"memoTime", "memoContent"}, new int[]{R.id.memo_item_time, R.id.memo_item_content});
            listView.setAdapter(adapter);
        }
        btn_back = (Button) findViewById(R.id.memo_bnback);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_add = (Button) findViewById(R.id.memo_bnadd);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MemoActivity.this,"添加备忘录信息！",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MemoActivity.this, AddMemoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private String loadMemo() {
        byte[] buffer = new byte[1024];
        FileInputStream in = null;
        BufferedInputStream reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("memo_data");
            reader = new BufferedInputStream(in);
            while (true) {
                int byteRead = in.read(buffer); //从文件读数据给字节数组
                if (byteRead == -1) //在文件尾，无数据可读
                    break;  //退出循环
                String s = new String(buffer, 0, byteRead);
                content.append(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d("memo content:", "  " + content.toString());
        return content.toString();
    }

    //初始化数据
    private void initMemoList(String content) {
        memolist = new ArrayList<Map<String, String>>();
        String[] aItem = content.split("#");

        for (int i = 0; i < aItem.length; i++) {
            Map<String, String> item = new HashMap<String, String>();
            String[] data = aItem[i].split(":");
            item.put("memoTime", data[0]);
            item.put("memoContent", data[1]);
            memolist.add(item);
        }
    }
}

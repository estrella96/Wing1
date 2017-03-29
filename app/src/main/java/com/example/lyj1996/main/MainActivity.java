package com.example.lyj1996.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.lyj1996.memo.MemoActivity;
import com.example.lyj1996.wing1.R;
import com.example.lyj1996.calculator.CalculatorActivity;
import com.example.lyj1996.help.HelpActivity;
import com.example.lyj1996.phonebook.PhoneBookActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<MainItem> itemList = new ArrayList<MainItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMainItem(); //初始化数据
        MainItemAdapter adapter = new MainItemAdapter(MainActivity.this, R.layout.main_item, itemList);
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainItem item = itemList.get(position);
                showNewActivity(item.getName());
            }
        });
    }

    private void initMainItem() {
        MainItem item_1 = new MainItem("记账本", R.drawable.account_book);
        itemList.add(item_1);
        MainItem item_2 = new MainItem("备忘录", R.drawable.memo_info);
        itemList.add(item_2);
        MainItem item_3 = new MainItem("计算器", R.drawable.calculator);
        itemList.add(item_3);
        MainItem item_4 = new MainItem("通讯录", R.drawable.phone_book);
        itemList.add(item_4);
        MainItem item_5 = new MainItem("天气", R.drawable.weather_info);
        itemList.add(item_5);
        MainItem item_6 = new MainItem("关于", R.drawable.help_info);
        itemList.add(item_6);
    }

    private void showNewActivity(String name){
        if(name.equals("通讯录")){
            Intent intent = new Intent(MainActivity.this, PhoneBookActivity.class);
            startActivity(intent);
        }
        else if(name.equals("计算器")){
            Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
            startActivity(intent);
        }
        else if(name.equals("关于")){
            Intent intent = new Intent(MainActivity.this, HelpActivity.class);
            startActivity(intent);
        }
        else if(name.equals("天气")){
//intent 和category完全符合才可以打开活动
            Intent intent = new Intent("com.example.activitytest.ACTION_START");
            intent.addCategory("com.example.activitytest.MY_CATEGORY");
//            startActivity(intent);
        }
        else if(name.equals("备忘录")){
            Intent intent = new Intent(MainActivity.this, MemoActivity.class);
            startActivity(intent); }
    }
}

package com.example.lyj1996.phonebook;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lyj1996.wing1.R;

import java.util.ArrayList;
import java.util.List;



public class PhoneBookActivity extends AppCompatActivity {

    private Button btn_back;
    private List<Linkman> linkmanList=new ArrayList<Linkman>();
    private PhonebookItemAdapter phonebookItemAdapter;
    private TextView del_ok;//完成按钮
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);
        initLinkmanList();

        phonebookItemAdapter=new PhonebookItemAdapter(PhoneBookActivity.this,R.layout.phonebook_item,linkmanList);
        ListView listView=(ListView) findViewById(R.id.phone_listview);
        listView.setAdapter(phonebookItemAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public  void onItemClick(AdapterView<?>parent,View view,int position,long id){
                Linkman linkman=linkmanList.get(position);
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+"027"+linkman.getPhoneNumber()));
                startActivity(intent);
            }
        });
        btn_back = (Button) findViewById(R.id.phonebook_bnback);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        del_ok=(TextView)findViewById(R.id.phonebook_delok);
        del_ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ArrayList<Linkman> delList = phonebookItemAdapter.getDelList();
                if (delList.size() > 0) {
                    for (Linkman linkman : delList) {
                        linkmanList.remove(linkman);
                    }
                }
                phonebookItemAdapter.setDel(false);
                del_ok.setVisibility(View.GONE);
                showMenu();
            }
        });




    }
    private void initLinkmanList() {
        Linkman item_1 = new Linkman("人事科", "87651462", "");
        linkmanList.add(item_1);
        Linkman item_2 = new Linkman("工资科", "87651466", "");
        linkmanList.add(item_2);
        Linkman item_3 = new Linkman("教师工作办公室", "87651526", "");
        linkmanList.add(item_3);
        Linkman item_4 = new Linkman("职称工作办公室", "87869147", "");
        linkmanList.add(item_4);
        Linkman item_5 = new Linkman("基础研究办公室", "87214969", "");
        linkmanList.add(item_5);
        Linkman item_6 = new Linkman("成果管理办公室", "87218140", "");
        linkmanList.add(item_6);
        Linkman item_7 = new Linkman("科技开发部", "87651485", "");
        linkmanList.add(item_7);
        Linkman item_8 = new Linkman("教学研究办公室", "87850017", "");
        linkmanList.add(item_8);
        Linkman item_9 = new Linkman("教务管理办公室", "87850027", "");
        linkmanList.add(item_9);
        Linkman item_10 = new Linkman("考务管理中心", "87850509", "");
        linkmanList.add(item_10);
        Linkman item_11 = new Linkman("教师教学发展中心", "87859099", "");
        linkmanList.add(item_11);
        Linkman item_12 = new Linkman("培养处教学管理科", "87651477", "");
        linkmanList.add(item_12);
        Linkman item_13 = new Linkman("留学生管理科", "87855613", "");
        linkmanList.add(item_13);
        Linkman item_14 = new Linkman("学位办学位管理科", "87651521", "");
        linkmanList.add(item_14);
        Linkman item_15 = new Linkman("余区办公室", "86558526", "");
        linkmanList.add(item_15);
        Linkman item_16 = new Linkman("科研经费管理科", "87671337", "");
        linkmanList.add(item_16);
        Linkman item_17 = new Linkman("人员经费管理科", "87859047", "");
        linkmanList.add(item_17);
        Linkman item_18 = new Linkman("马区会计核算科", "87651502", "");
        linkmanList.add(item_18);
        Linkman item_19 = new Linkman("余区会计核算科", "86534332", "");
        linkmanList.add(item_19);
        Linkman item_20 = new Linkman("公共卫生科", "87864894", "");
        linkmanList.add(item_20);
        Linkman item_21 = new Linkman("治安科", "87392065", "");
        linkmanList.add(item_21);
        Linkman item_22 = new Linkman("户政室", "87398030", "");
        linkmanList.add(item_22);
        Linkman item_23 = new Linkman("校园交通与公共秩序管理中心", "87394516", "");
        linkmanList.add(item_23);
        Linkman item_24 = new Linkman("马区综合服务中心", "87864689", "");
        linkmanList.add(item_24);
        Linkman item_25 = new Linkman("余区后勤综合办公室", "86868802", "");
        linkmanList.add(item_25);
    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        this.menu=menu;
        getMenuInflater().inflate(R.menu.phonebook_menu,menu);
        return true;
    }

    @Override

    public  boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add_phonebook_item:
                Toast.makeText(PhoneBookActivity.this,"新增联系人信息！",Toast.LENGTH_LONG).show();
                break;
            case R.id.del_phonebook_item:
                phonebookItemAdapter.setDel(true);
                del_ok.setVisibility(View.VISIBLE);
                hiddenMenu();
        }
        return  true;
    }

    private  void hiddenMenu(){
        if (null!=menu){
            for(int i=0;i<menu.size();i++)
            {
                menu.getItem(i).setVisible(false);
                menu.getItem(i).setEnabled(false);
            }
        }
    }
    private  void showMenu()
    {
        if (null!=menu){
            for (int i=0;i<menu.size();i++){
                menu .getItem(i).setVisible(true);
                menu.getItem(i).setEnabled(true);
            }
        }
    }





}

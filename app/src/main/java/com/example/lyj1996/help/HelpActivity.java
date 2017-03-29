package com.example.lyj1996.help;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lyj1996.wing1.R;

public class HelpActivity extends AppCompatActivity {
 private Button bn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        bn_back=(Button)this.findViewById(R.id.layout_help_bn_back);
        bn_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                finish();
               // Toast.makeText(HelpActivity.this,"返回到上一层!",Toast.LENGTH_LONG).show();
            }
        });
    }
}

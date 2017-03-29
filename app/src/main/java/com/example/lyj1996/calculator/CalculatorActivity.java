package com.example.lyj1996.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.lyj1996.wing1.R;

public class CalculatorActivity extends AppCompatActivity {
 private Button bn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        bn_back=(Button)this.findViewById(R.id.calculator_bnback);
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

package com.example.kitri.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv = (TextView)findViewById(R.id.textView);
        Intent intent = getIntent();
//        String str = intent.getStringExtra("str");
//        int age = intent.getIntExtra("age",0);
//        tv.setText(str+" : "+age);
        Member m = (Member)intent.getSerializableExtra("m");
        tv.setText("name:"+m.getName()+"\nage: "+m.getAge());
    }
}

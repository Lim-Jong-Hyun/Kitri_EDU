package com.example.kitri.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main4Activity extends AppCompatActivity {
    private EditText str;
    private final static String MY_ACTION2 = "com.example.kitri.myapplication.MY_ACTION2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        str = (EditText)findViewById(R.id.editText4);
        MyReceiver3 mr3 = new MyReceiver3(this);
        registerReceiver(mr3, new IntentFilter(MY_ACTION2));
    }
    public void onSend(View view){
        String s= str.getText().toString();
        Intent intent = new Intent(MY_ACTION2);
        intent.putExtra("str", s);
        sendBroadcast(intent);
    }
}

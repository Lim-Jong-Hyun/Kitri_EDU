package com.example.kitri.myapp2;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    private String str="";
    private EditText id;
    private EditText pwd;
    private TextView result;
    private String gen="";
    private String[] hob= {"","",""};
    private RadioButton r1;
    private RadioButton r2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        id =(EditText)findViewById(R.id.id);
        pwd =(EditText)findViewById(R.id.pwd);
        result = (TextView)findViewById(R.id.r);
        r1 = (RadioButton)findViewById(R.id.radioButton5);
        r2 = (RadioButton)findViewById(R.id.radioButton6);

    }

    public void onGenderClick(View v){
        RadioButton rb =null;
        switch (v.getId()) {
            case R.id.radioButton5:
                r2.setChecked(false);
                rb = r1;
                break;
            case R.id.radioButton6:
                r1.setChecked(false);
                rb = r2;
                break;
        }
        gen = rb.getText().toString();
    }

    public void onHobbyClick(View v){
        CheckBox cb = (CheckBox)v;
        String val = cb.getText().toString();
        if(cb.isChecked()){
            for(int i=0;i<hob.length;i++){
                if(hob[i].equals("")){
                    hob[i] =val;
                    break;
                }
            }
        }else{
            for(int i=0;i<hob.length;i++){
                if(hob[i].equals(val)){
                    hob[i]="";
                }
            }
        }
    }

    public void onJoinClick(View v){
        String ids = id.getText().toString();
        String pwds = pwd.getText().toString();
        str = "id:"+ids;
        str +="\npwd:"+pwds;
        str +="\ngender:"+gen;
        str +="\nhobby:";
        for(String s:hob){
            str +=s;
        }
        result.setText(str);
    }
}

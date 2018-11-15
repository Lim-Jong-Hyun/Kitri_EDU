package com.example.kitri.myapp1108;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onWrite (View view){
        //프리퍼런스파일오픈 - 쓰기에 사용할 에디터 생성 - 파일에 문자열 저장
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name","aaa");
        editor.putInt("age",12);
        editor.commit();
    }
    public void onRead(View view){
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        String name=pref.getString("name","");
        int age = pref.getInt("age",0);
        Toast.makeText(this,"name:"+name+"/age:"+age,Toast.LENGTH_SHORT).show();
    }
}

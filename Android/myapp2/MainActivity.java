package com.example.kitri.myapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //엑티비티의 부모를 먼저호출해줌
        setContentView(R.layout.activity_main); //레이아웃 붙이기
        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
        et = (EditText)findViewById(R.id.editText);
        tv = (TextView)findViewById(R.id.myText);

        System.out.println("msg1");
        Log.i("myTag","info");
        Log.w("myTag","warn");
        Log.e("myTag","error");
        Log.d("myTag","debug");
    }
    void btnOnClick(View view){//view는 상위클래스로 업캐스팅해서 이벤트가 발생한 객체를 받아오는 것
        Button b = (Button) view; //버튼 객채를 쓰려면 다운캐스팅을 해서 사용
        String str= b.getText().toString();
        String str2 = et.getText().toString();
        tv.setText(str2);
        Toast.makeText(this, str2, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(this, "onStop()", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "onDestory()", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onreStart()", Toast.LENGTH_SHORT).show();
    }
}

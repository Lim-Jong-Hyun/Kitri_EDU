package com.example.kitri.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {
    private EditText name;
    private EditText age;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        name =(EditText)findViewById(R.id.editText2);
        age =(EditText)findViewById(R.id.editText3);
        intent = getIntent();
        Member m = (Member)intent.getSerializableExtra("m");
        name.setText(m.getName());
        age.setText(m.getAge()+"");
    }
    public void onEdit(View view){
        Member m = new Member();
        m.setName(name.getText().toString());
        m.setAge(Integer.parseInt(age.getText().toString()));
        intent.putExtra("m",m);
        setResult(RESULT_OK,intent); //현재 액티비티의 결괔코드 작성
        finish(); //현재 액티비티 종료. 이전 페이지로 돌아감
    }
}

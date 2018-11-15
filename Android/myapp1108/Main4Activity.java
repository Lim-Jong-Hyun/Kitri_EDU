package com.example.kitri.myapp1108;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main4Activity extends AppCompatActivity {
    private EditText title;
    private EditText memo;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_dialog_layout);
        title =(EditText)findViewById(R.id.editText3);
        memo =(EditText)findViewById(R.id.editText5);
        intent = getIntent();
        Memo m = (Memo)intent.getSerializableExtra("m");
        title.setText(m.getTitle());
        memo.setText(m.getContent());
    }
    public void onSave(View view){
        Memo m = new Memo();
        m.setTitle(title.getText().toString());
        m.setContent(memo.getText().toString());
        intent.putExtra("m",m);
        setResult(RESULT_OK,intent); //현재 액티비티의 결괔코드 작성
        finish(); //현재 액티비티 종료. 이전 페이지로 돌아감
    }
    public void onCancel(View view){
        setResult(RESULT_CANCELED,intent); //현재 액티비티의 결괔코드 작성
        finish(); //현재 액티비티 종료. 이전 페이지로 돌아감
    }
}

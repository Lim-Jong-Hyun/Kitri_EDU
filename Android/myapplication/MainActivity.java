package com.example.kitri.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private EditText et2;
    //리시버에서 사용할 액션명
    private final static String MY_ACTION ="com.example.kitri.myapplication.MY_ACTION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewById(R.id.age);

        //묵시적으로 활성화할 백그라운드(서비스, 리시버)
        //컴포넌트는 매니페스트에 등록할 수 없다. 객체를 직접 작성하여 context에 등록. 인텐트 필터도 같이 등록
        MyReceiver2 mr2 = new MyReceiver2();
        this.registerReceiver(mr2,new IntentFilter(MY_ACTION));
    }

    public void onBtnClick(View view){
        String str =et.getText().toString();
        //2를 활성화할 인텐트 생성
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        Member m = new Member();
        m.setName(str);
        m.setAge(Integer.parseInt(et2.getText().toString()));
        intent.putExtra("str",str);
        intent.putExtra("m",m);
        //인텐트가 지목한 액티비티 시작
        startActivity(intent);

    }
    public void onBtn2Click(View view){
        Intent intent = new Intent(MainActivity.this, Main3Activity.class);
        Member m = new Member();
        m.setName(et.getText().toString());
        m.setAge(Integer.parseInt(et2.getText().toString()));
        intent.putExtra("m",m);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    Member x = (Member)data.getSerializableExtra("m");
                    et.setText(x.getName());
                    et2.setText(x.getAge()+"");
                }else{
                    Toast.makeText(MainActivity.this,"비정상처리",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void onBtn3Click(View view){
        //명시적으로 리시버활성화
        Intent intent = new Intent(this, MyReceiver.class);
        Member m = new Member();
        m.setName(et.getText().toString());
        m.setAge(Integer.parseInt(et2.getText().toString()));
        intent.putExtra("m",m);
        sendBroadcast(intent);
    }
    public void onBtn4Click(View view){
        //묵시적으로 리시버 활성화
        Intent intent = new Intent(MainActivity.MY_ACTION);
        Member m = new Member();
        m.setName(et.getText().toString());
        m.setAge(Integer.parseInt(et2.getText().toString()));
        intent.putExtra("m",m);
        sendBroadcast(intent);

    }
}

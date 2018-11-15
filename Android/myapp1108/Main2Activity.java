package com.example.kitri.myapp1108;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main2Activity extends AppCompatActivity {
    private EditText et;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et = (EditText)findViewById(R.id.editText2);
        tv = (TextView)findViewById(R.id.textView2);
    }
    public void onSave(View view){
        FileOutputStream fos = null;
        try {
            //파일 쓰기모드
            fos = openFileOutput("test.txt",MODE_APPEND);
            fos.write(et.getText().toString().getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void onRead(View view){
        try{
            //파일 읽기모드
            FileInputStream fis = openFileInput("test.txt");
            StringBuffer sbuf = new StringBuffer();
            byte[] buf = new byte[40];
            //파일 끝 만날때까지 40파이트씩 읽어서 buf에 저장
            while((fis.read(buf,0,40))!=-1){
                //string으로 변환 스트링 버퍼에 저장
                String str = new String(buf);
                sbuf.append(str);

                //파일에 남은 데이터가 40바이트 보다 작으면 buf배열에
                //복사했을때 끝 부분에 쓰레기 값이 남는다
                //이를 처리하기; 위해 배열 전체를 공백문자로 처리학 파일에서 읽은 데이터를 쓴다
                if(fis.available()<40){
                    //arrays.fill() 배열을 지정한 값으로 채움
                    //param1 처리할 배열
                    //param2 시작 인덱스
                    //param3 끝 인덱스
                    //param4 배열을 채울 데이터
                    Arrays.fill(buf,0,40,(byte) ' ');
                }
            }
            fis.close();
            tv.setText(sbuf);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

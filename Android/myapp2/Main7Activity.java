package com.example.kitri.myapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Main7Activity extends AppCompatActivity {
    private LinearLayout linear;
    private Spinner spin;
    private String[] list = {"red", "green","blue"};
    private int[] list2 = {R.color.red, R.color.green, R.color.blue};
    private ArrayAdapter<String> aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        linear = (LinearLayout)findViewById(R.id.linear1);
        spin = (Spinner)findViewById(R.id.spinner);
        aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,list);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        //어댑터가 바뀌는 이벤트리스너를 소스인 스핀에 붙이는거다
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //선택한 항목의 뷰 위치 아이디 다줌
                Toast.makeText(Main7Activity.this,list[position],Toast.LENGTH_SHORT).show();
                linear.setBackgroundResource(list2[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}

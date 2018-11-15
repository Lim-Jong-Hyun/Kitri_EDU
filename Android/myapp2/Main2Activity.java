package com.example.kitri.myapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText et;
    int num1=-1;
    int num2=-1;
    String op="";
    int result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et = (EditText)findViewById(R.id.display);

    }
    void onNumClick(View v){
        Button b = (Button) v;
        int num  = Integer.parseInt(b.getText().toString());
        if(et.getText().equals("") && b.getText().toString().equals("0")){
        }else {
            et.append(b.getText().toString());
        }
    }
    void onOpClick(View v){
        Button b = (Button) v;
        if(b.getText().toString().equals("C")){
            num1 =-1;
            et.setText("");
            return;
        }
        if(!et.getText().equals("")) {
            num1 = Integer.parseInt(et.getText().toString());
            op = b.getText().toString();
            et.setText("");
        }
    }

    void onResultClick(View v) {
        if (num1 != -1 && !op.equals("")) {

            int num2 = Integer.parseInt(et.getText().toString());

            if (op.equals("+")) {
                result = num1 + num2;
                Toast.makeText(this,op,Toast.LENGTH_LONG);
                Log.d("num1", String.valueOf(num1));
                Log.d("num2", String.valueOf(num2));
            } else if (op.equals("-")) {
                result = num1 - num2;
            } else if (op.equals("*")) {
                result = num1 * num2;
            } else if (op.equals("/")) {
                result = num1 / num2;
            }
        }
        et.setText(String.valueOf(result));
        num1 = result;
        op="";
    }


}

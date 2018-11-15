package com.example.kitri.myapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        tv = (TextView)findViewById(R.id.textView2);
        registerForContextMenu(tv); // tv에 컨텍스트메뉴 붙임
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"add");
        menu.add(0,2,0,"edit");
        menu.add(0,3,0,"del");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case 1:
                //this가 해당 이벤트로 넘어가는 경우있기때문에 클래스.this하면 명확함
                Toast.makeText(Main4Activity.this,"add menu click", Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(Main4Activity.this,"edit menu click", Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(Main4Activity.this,"del menu click", Toast.LENGTH_LONG).show();
                break;

        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,1,0,"edit");
        menu.add(0,2,0,"del");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case 1:
                Toast.makeText(Main4Activity.this,"edit",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(Main4Activity.this,"del",Toast.LENGTH_SHORT).show();
                break;
        }


        return super.onContextItemSelected(item);
    }
}

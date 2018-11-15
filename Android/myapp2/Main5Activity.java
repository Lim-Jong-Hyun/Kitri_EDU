package com.example.kitri.myapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity {
    private EditText et;
    private ListView lv;
    private Button btn;
    private ArrayList<String> list;
    private ArrayAdapter<String> aa;
    private int idx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        btn = (Button)findViewById(R.id.button3);
        et = (EditText)findViewById(R.id.editText3);
        lv = (ListView)findViewById(R.id.lv1);
        list = new ArrayList<>();
        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        lv.setAdapter(aa); //리스트뷰에 어레이 어댑터 연결
        registerForContextMenu(lv); // tv에 컨텍스트메뉴 붙임
    }

    public void onAdd(View view){
        if(btn.getText().equals("ADD")) {
            String str = et.getText().toString();
            et.setText("");
            list.add(str);
            aa.notifyDataSetChanged(); //데이터가 바뀌면 화면을 갱신해준다
        }else if(btn.getText().equals("EDIT")) {
            String str = et.getText().toString();
            et.setText("");
            list.set(idx,str);
            aa.notifyDataSetChanged();
            btn.setText("ADD");
        }
    }

    public void edit(int idx, String str){
        list.set(idx, str);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,1,0,"edit");
        menu.add(0,2,0,"del");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        idx = info.position;
        switch(item.getItemId()){
            case 1:
                btn.setText("EDIT");
                et.setText(list.get(idx));
                break;
            case 2:
                list.remove(idx);
                aa.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }
}

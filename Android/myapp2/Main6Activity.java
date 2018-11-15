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

import com.example.kitri.myapp2.model.Member;

import java.util.ArrayList;

public class Main6Activity extends AppCompatActivity {
    private EditText name;
    private EditText tel;
    private ListView lv;
    private Member m;
    private ArrayList<Member> list;
    private ArrayAdapter<Member> aa;
    private int idx;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        name = (EditText)findViewById(R.id.name);
        tel = (EditText)findViewById(R.id.tel);
        lv = (ListView)findViewById(R.id.lv);
        btn = (Button)findViewById(R.id.save);
        list = new ArrayList<>();
        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        lv.setAdapter(aa); //리스트뷰에 어레이 어댑터 연결
        registerForContextMenu(lv);
    }

    public void onAdd(View view){
        if(btn.getText().equals("SAVE")) {
            String n = name.getText().toString();
            String t = tel.getText().toString();
            if(n.equals("") || t.equals("")){
                Toast.makeText(Main6Activity.this, "fill name, tell", Toast.LENGTH_SHORT).show();
                return;
            }
            m = new Member(n,t);
            name.setText("");
            tel.setText("");
            list.add(m);
        }else if(btn.getText().equals("EDIT")) {
            String n = name.getText().toString();
            String t = tel.getText().toString();
            if(n.equals("") || t.equals("")){
                Toast.makeText(Main6Activity.this, "fill name, tell", Toast.LENGTH_SHORT).show();
                return;
            }
            m = new Member(n,t);
            name.setText("");
            tel.setText("");
            list.set(idx,m);
            btn.setText("SAVE");
        }
        aa.notifyDataSetChanged();
    }
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
                name.setText(list.get(idx).getName());
                tel.setText(list.get(idx).getTel());
                break;
            case 2:
                list.remove(idx);
                aa.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }


}

package com.example.kitri.myapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kitri.myapp2.model.Member;
import com.example.kitri.myapp2.model.MemberAdapter;

import java.util.ArrayList;


public class Main8Activity extends AppCompatActivity {
    private EditText name;
    private EditText tel;
    private ListView lv;
    private ArrayList<Member> list;
    private MemberAdapter memberAdapter;
    private Spinner spin;
    private String[] arr = {"a.jpg","b.jpg","c.jpg","d.jpg"};
    private int[] arr_res ={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d};
    private ArrayAdapter<String> aa;
    private int i_res = -1;
    private LinearLayout ll;
    private int idx;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        name = (EditText)findViewById(R.id.editText6);
        tel = (EditText)findViewById(R.id.editText5);
        lv = (ListView)findViewById(R.id.phone_list);
        spin = (Spinner)findViewById(R.id.spinner2);
        btn = (Button)findViewById(R.id.button5);
        list = new ArrayList<>();
        memberAdapter = new MemberAdapter(this,R.layout.phone_item, list);
        lv.setAdapter(memberAdapter);
        aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arr);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        ll = (LinearLayout)findViewById(R.id.ll);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    i_res = arr_res[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        registerForContextMenu(lv);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"추가");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                ll.setVisibility(View.VISIBLE);
                break;
        }
        return true;
    }

    public void onSave(View view){
        if(btn.getText().equals("SAVE")) {
            String n = name.getText().toString();
            String t = tel.getText().toString();
            Member m = new Member(n, t);
            if (i_res != -1) {
                m.setImg_res(i_res);
            }
            list.add(m);
            i_res = -1;
            memberAdapter.notifyDataSetChanged();
            name.setText("");
            tel.setText("");
            spin.setSelection(0);
            ll.setVisibility(View.GONE);
        }else if(btn.getText().equals("EDIT")){

            String n = name.getText().toString();
            String t = tel.getText().toString();
            Member m = new Member(n, t);
            if (i_res != -1) {
                m.setImg_res(i_res);
            }
            list.set(idx,m);
            i_res=-1;
            memberAdapter.notifyDataSetChanged();
            name.setText("");
            tel.setText("");
            spin.setSelection(0);
            ll.setVisibility(View.GONE);
            btn.setText("SAVE");

        }

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
                spin.setSelection(list.get(idx).getImg_res());
                ll.setVisibility(View.VISIBLE);
                break;
            case 2:
                list.remove(idx);
                ll.setVisibility(View.GONE);
                memberAdapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }


}

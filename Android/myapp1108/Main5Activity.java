package com.example.kitri.myapp1108;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity {
    private EditText et;
    private ListView nameList;
    private ArrayList<Member> list;
    private ArrayAdapter<Member> adapter;
    private Cursor cursor;
    private MyDBAdapter dbAdapter;
    private int idx;
    private boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        et = (EditText)findViewById(R.id.editText);
        nameList = (ListView) findViewById(R.id.nameList);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        nameList.setAdapter(adapter);
        dbAdapter = new MyDBAdapter(this);
        makeList();
        registerForContextMenu(nameList);
    }
    public void makeList(){
        dbAdapter.open();
        cursor = dbAdapter.getAll();
        list.clear();
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String n = cursor.getString(1);
                list.add(new Member(id, n));
            }while(cursor.moveToNext());
            adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(this,"no data", Toast.LENGTH_SHORT).show();
        }
        dbAdapter.close();
    }
    public void onSave(View view){
        String n = et.getText().toString();
        dbAdapter.open();
        if(flag){
            dbAdapter.updateData(idx, n);
            flag = false;
        }else {
            dbAdapter.insertData(n);
        }
        dbAdapter.close();
        makeList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0,1,0,"edit");
        menu.add(0,2,0,"del");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Member m =list.get(info.position);
        idx = m.getId();
        switch (item.getItemId()){
            case 1:
                et.setText(m.getName());
                flag = true;
                break;
            case 2:
                dbAdapter.open();
                dbAdapter.removeData(idx);
                dbAdapter.close();
                makeList();
                break;
        }
        return true;
    }


}

package com.example.kitri.myapp1112_3;

import android.content.ContentUris;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.database.Cursor;
import android.net.Uri;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private EditText name;
    private EditText email;
    private ListView lv;
    private ArrayAdapter<Member> adapter;
    private ArrayList<Member> list;
    private int id;
    private boolean flag;
    public static final Uri CONTENT_URI = Uri
            .parse("content://com.example.kitri.myapp1112_3.myProvider");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);
        name = (EditText) findViewById(R.id.editText);
        email = (EditText) findViewById(R.id.editText2);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);
        makeList();
    }

    public void onSave(View view){
        String n = name.getText().toString();
        String e = email.getText().toString();
        if(n==null || n.equals("") || e==null || e.equals("")){
            Toast.makeText(this,"fill the name & email",Toast.LENGTH_SHORT).show();
            return;
        }
        ContentValues cv = new ContentValues();
        cv.put("name",n);
        cv.put("email",e);
        if(flag){
            getContentResolver().update(CONTENT_URI,cv,"_id="+id,null);
            flag=false;
        }else{
            getContentResolver().insert(CONTENT_URI,cv);
        }
        makeList();
    }

    public void makeList() {
        list.clear();
        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(new Member(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)));
            } while (cursor.moveToNext());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,1,0,"edit");
        menu.add(0,2,0,"delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        id = list.get(menuInfo.position).getId();
        switch (item.getItemId()) {
            case 1:
                editForm(id);
                break;
            case 2:
                delete();
                break;
        }
        makeList();
        return true;
    }

    public void editForm(int idx){
        Member m = list.get(idx);
        name.setText(m.getName());
        email.setText(m.getEmail());
        flag = true;
    }
    public void delete(){
        Uri uri = ContentUris.withAppendedId(CONTENT_URI, id);
        getContentResolver().delete(uri,null,null);
        makeList();
    }
}


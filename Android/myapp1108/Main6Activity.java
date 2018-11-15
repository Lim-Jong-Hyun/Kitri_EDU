package com.example.kitri.myapp1108;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main6Activity extends AppCompatActivity {
    private ListView lv;
    private ArrayList<Memo> list;
    private ArrayAdapter<Memo> adapter;
    private int selectedId;
    private MemoDBAdapter dbAdapter;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        lv = (ListView)findViewById(R.id.memoList);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDetail(position);
            }
        });
        registerForContextMenu(lv);
        dbAdapter = new MemoDBAdapter(this);
        makeList();
    }
    public void makeList(){
        list.clear();
        dbAdapter.open();
        cursor = dbAdapter.getAll();
        if(cursor.moveToFirst()){
            do{
                int i = cursor.getInt(0);
                String t = cursor.getString(1);
                String c = cursor.getString(2);
                list.add(new Memo(i, t, c));
            }while (cursor.moveToNext());
            adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }
        dbAdapter.close();
    }
    public void showDetail(int pos){
        String str = "";
        Memo m = list.get(pos);
        int id = m.getId();
        dbAdapter.open();
        cursor = dbAdapter.getMemo(id);
        if(cursor.moveToFirst()){
            String t = cursor.getString(1);
            String c = cursor.getString(2);
            str = "title:"+t+"\ncontent:"+c;
        }else{
            str = "no data";
        }
        dbAdapter.close();
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage(str)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void onStop(View view){
        finish();
    }
    public void showWriteDiolog(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.write_dialog_layout);
        Button cancel = (Button)dialog.findViewById(R.id.button8);
        Button save = (Button)dialog.findViewById(R.id.button7);
        final EditText title = (EditText)dialog.findViewById(R.id.editText3);
        final EditText memo = (EditText)dialog.findViewById(R.id.editText5);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title_s = title.getText().toString();
                String memo_s = memo.getText().toString();
                if(memo_s.equals("")||memo_s==null){
                    Toast.makeText(Main6Activity.this,
                            "내용을 입력하라", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(title_s.equals("")||title_s==null){
                    title_s = memo_s.substring(0, 10);
                }
                //db에저장
                dbAdapter.open();
                dbAdapter.insertData(new Memo(0, title_s, memo_s));
                dbAdapter.close();
                makeList();
                dialog.cancel();
            }
        });
        dialog.create();
        dialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "메모작성");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                showWriteDiolog();
                break;
        }
        return true;
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
        int selectedIdx = info.position;
        selectedIdx = list.get(selectedIdx).getId();
        switch(item.getItemId()){
            case 1:
                Intent intent = new Intent(this, Main4Activity.class);
                intent.putExtra("m",list.get(selectedIdx));
                startActivityForResult(intent,1);
                break;
            case 2:
                dbAdapter.open();
                dbAdapter.removeData(selectedId);
                dbAdapter.close();
                makeList();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    Memo m = (Memo)data.getSerializableExtra("m");
                    m.setId(selectedId);
                    dbAdapter.open();
                    dbAdapter.updateData(m);
                    dbAdapter.close();
                    makeList();
                }else{

                }
                break;
        }
    }
}

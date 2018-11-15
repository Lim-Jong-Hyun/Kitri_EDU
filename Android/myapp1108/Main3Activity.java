package com.example.kitri.myapp1108;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    private ListView lv;
    private ArrayList<Memo> list;
    private ArrayAdapter<Memo> adapter;
    private int idx;
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
        File fileDir = getFilesDir();
        String path = fileDir.getAbsolutePath();
        File newFile = new File(path+"/memo.dat");
        if(!newFile.exists()){
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        registerForContextMenu(lv);
        init();
    }

    private void showDetail(int pos) {
        Memo m = list.get(pos);
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("title:"+m.getTitle()+"\ncontent:"+m.getContent())
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void init(){
        list.clear();
        try {
            FileInputStream fis = openFileInput("memo.dat");
            ObjectInputStream os = new ObjectInputStream(fis);
            Memo m = null;
            while((m = (Memo)os.readObject())!=null){
                list.add(m);
                Toast.makeText(this, "read:"+m.getTitle(), Toast.LENGTH_SHORT).show();
            }
            adapter.notifyDataSetChanged();
            os.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void onStop(View view){
        try {
            FileOutputStream fos = openFileOutput("memo.dat", 0);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            for(Memo m:list){
                os.writeObject(m);
                Toast.makeText(this, "write:"+m.getTitle(), Toast.LENGTH_SHORT).show();
            }
            os.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finish();
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
                    Toast.makeText(Main3Activity.this,
                            "내용을 입력하라", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(title_s.equals("")||title_s==null){
                    title_s = memo_s.substring(0, 10);
                }
                list.add(new Memo(title_s, memo_s));
                adapter.notifyDataSetChanged();
                dialog.cancel();
            }
        });
        dialog.create();
        dialog.show();
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
                Intent intent = new Intent(this, Main4Activity.class);
                Memo m = list.get(idx);
                intent.putExtra("m",m);
                startActivityForResult(intent,1);
                break;
            case 2:
                list.remove(idx);
                adapter.notifyDataSetChanged();
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
                    Memo mm = (Memo)data.getSerializableExtra("m");
                    list.set(idx,mm);
                    adapter.notifyDataSetChanged();
                }else{

                }
                break;
        }
    }
}

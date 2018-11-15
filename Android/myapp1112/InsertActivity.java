package com.example.kitri.myapp1112;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class InsertActivity extends Activity {
    private EditText et1;
    private EditText et2;
    private RadioButton type1;
    private RadioButton type2;
    private RadioButton type3;
    private RadioButton type4;
    private String label="";
    private String tel="";
    private int type=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        et1=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        type1=(RadioButton)findViewById(R.id.radioButton);
        type2=(RadioButton)findViewById(R.id.radioButton2);
        type3=(RadioButton)findViewById(R.id.radioButton3);
        type4=(RadioButton)findViewById(R.id.radioButton4);

        OnClickListener li=new OnClickListener(){

            //�ڵ鷯
            @Override
            public void onClick(View v) {
                RadioButton r=(RadioButton)v;

                if(r==type1){
                    type=1;
                }else if(r==type2){
                    type=2;
                }else if(r==type3){
                    type=3;
                }else{
                    type=0;
                }

            }

        };

        type1.setOnClickListener(li);
        type2.setOnClickListener(li);
        type3.setOnClickListener(li);
        type4.setOnClickListener(li);

    }

    public void ok(View view){
        label=et1.getText().toString();
        tel=et2.getText().toString();

        if(label.equals("")|| tel.equals("")){
            Toast.makeText(getApplicationContext(),
                    "input the label and number", Toast.LENGTH_SHORT).show();
            return;
        }

        insert();

        Intent intent=getIntent();
        setResult(RESULT_OK, intent);
        finish();

    }


    @Override
    protected void onResume() {
        super.onResume();
        label="";
        tel="";
        type=0;
        et1.setText("");
        et2.setText("");
    }

    public void insert(){

        ContentValues values = new ContentValues();

        String contactId = label;

        values.put(RawContacts.CONTACT_ID, contactId);

        Uri contactUri = getContentResolver().insert(
                RawContacts.CONTENT_URI, values);

        long contactId_l = ContentUris.parseId(contactUri);

        values.clear();

        values.put(Data.RAW_CONTACT_ID, contactId_l);

        values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);

        values.put(Phone.NUMBER, tel);
        values.put(Phone.TYPE, type);

        values.put(Phone.LABEL, label);

        getContentResolver().insert(Data.CONTENT_URI, values);
    }

}

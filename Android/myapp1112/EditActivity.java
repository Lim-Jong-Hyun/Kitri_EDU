package com.example.kitri.myapp1112;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class EditActivity extends Activity {
    private EditText et1;
    private EditText et2;
    private RadioButton type1;
    private RadioButton type2;
    private RadioButton type3;
    private RadioButton type4;
    private Button edit;
    private String label = "";
    private String tel = "";
    private int type = 0;
    private Cursor c;
    private Person p;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        et1=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        type1=(RadioButton)findViewById(R.id.radioButton);
        type2=(RadioButton)findViewById(R.id.radioButton2);
        type3=(RadioButton)findViewById(R.id.radioButton3);
        type4=(RadioButton)findViewById(R.id.radioButton4);

        //�� ��Ƽ��Ƽ�� Ȱ��ȭ�ϱ� ���� ���� ����Ʈ�� ���޹���.
        intent = getIntent();

        //����Ʈ�� ����Ʈ�� ���� idŰ ���� ����. �̴� ������ ���� id�� ��Ÿ��
        String id = intent.getStringExtra("id");

        //DB���� id�� ������ ���� ã���� ����Ʈ ���ι��̴����� ��û�ϴ� �˻� �޼���
        //ȣ��. ��ȯ������ �˻� ��� �����͸� Person ��ü ���·� �޴´�.
        p = search(id);

        //�˻� ����� �Է�â�� ����Ѵ�.
        //�� �˻� ��� p�� ���̺�, ��ȭ��ȣ, ��ȭŸ���� ����Ʈ �ؽ�Ʈ�� ���� ��ư��
        //����Ѵ�.
        et1.setText(p.getLabel());
        et2.setText(p.getTel());
        switch(p.getType()){
            case 1:
                type1.setChecked(true);
                break;
            case 2:
                type2.setChecked(true);
                break;
            case 3:
                type3.setChecked(true);
                break;
            default:
                type4.setChecked(true);

        }

        OnClickListener li = new OnClickListener() {

            @Override
            public void onClick(View v) {
                RadioButton r = (RadioButton) v;
                if (r == type1) {
                    type = 1;
                } else if (r == type2) {
                    type = 2;
                } else if (r == type3) {
                    type = 3;
                } else {
                    type = 0;
                }

            }

        };

        type1.setOnClickListener(li);
        type2.setOnClickListener(li);
        type3.setOnClickListener(li);
        type4.setOnClickListener(li);

    }

    //���� ��ư�� Ŭ���ϸ�
    public void edit(View view){
        label = et1.getText().toString();
        tel = et2.getText().toString();

        //���̺��̳� ��ȭ��ȣ�� �����̸� �޼��� ����
        if (label.equals("") || tel.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "input the label and number", Toast.LENGTH_SHORT).show();
            return;
        }

        //���̺�� ��ȭ��ȣ�� �����ԷµǾ� �ִٸ� ����ó�� �޼��� ȣ��
        update();

        //����Ʈ�� ����ڵ� �����ϰ�
        setResult(RESULT_OK, intent);

        //��Ƽ��Ƽ ����
        finish();

    }

    public void update() {
        String where = "Data._ID=?";
        String id = p.getId();
        String[] args = new String[]{id};

        ContentValues values = new ContentValues();

        // ��ȭ��ȣ ����
        values.put(Phone.NUMBER, tel);

        // ��ȭ ����(����ȭ, �����, ����) ����
        values.put(Phone.TYPE, type);

        // ����� �ĺ� ���̺� ����
        values.put(Phone.LABEL, label);

        //����Ʈ ���ι����Ϳ� ������ ���� ��û
        getContentResolver().update(Data.CONTENT_URI, values, where, args);
    }

    //�˻� �޼���
    public Person search(String id) {

        //����Ʈ ������� ����Ʈ ���ι��̴��� �Ķ���� id�� ������ ���� �˻��ϵ���
        //��û. �˻� ����� Ŀ�����·� �޴´�.
        c = getContentResolver().query(Data.CONTENT_URI,
                new String[] { Data._ID, Phone.NUMBER, Phone.TYPE,	Phone.LABEL },
                "Data._ID=?", new String[]{id}, null);

        String number = null, label = null;
        int type = 0;

        // Cursor�� ���� ��ġ�� �̵��� �����͸� �Ѷ��ξ� �д´�
        if (c.moveToFirst()) {
            do {

                // ���� ������ �����͵� �� ��ȭ��ȣ, ��ȭ����, ���̺��� �д´�
                // ���� number�� type�÷��� ���� �д´�
                number = c.getString(c.getColumnIndex(Phone.NUMBER));
                type = c.getShort((c.getColumnIndex(Phone.TYPE)));

                // ���̺� �д´�
                label = c.getString(c.getColumnIndex(Phone.LABEL));

                //Ŀ������ ���� �����ͷ� Person��ü ����
                Person p = new Person(id, label, number, type);
                return p;

                // Cursor�� �����Ͱ� �����ϸ� ���� �������� �̵�
            } while (c.moveToNext());
        }

        //�˻� ����� ������ null ��ȯ.
        return null;
    }

}

package com.example.kitri.myapp2.model;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kitri.myapp2.R;

import java.util.ArrayList;
import java.util.List;

public class MemberAdapter extends ArrayAdapter {
    private ArrayList<Member> list;
    private Context context;
    private int res;
    public MemberAdapter( Context context, int resource, List objects) {
        super(context, resource, objects);
        list = (ArrayList<Member>)objects;
        this.context = context;
        res = resource;
    }

    //infalter는 xml, 뷰를 객체화 시킨다
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(res, null);
        }
        final TextView name = (TextView)convertView.findViewById(R.id.textView3);
        final TextView tel = (TextView)convertView.findViewById(R.id.textView5);
        final ImageView img = (ImageView)convertView.findViewById(R.id.imageView2);
        final Button call = (Button)convertView.findViewById(R.id.call);
        final Button sms =(Button)convertView.findViewById(R.id.sms);
        call.setFocusable(false);
        sms.setFocusable(false);
        final Member m = list.get(position);
        name.setText(m.getName());
        tel.setText(m.getTel());
        if(m.getImg_res()!=0){
            img.setImageResource(m.getImg_res());
            img.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+m.getTel()));
                context.startActivity(intent);
            }
        });
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentName cn = new ComponentName("com.example.kitri.app1107",
                        "com.example.kitri.app1107.Main3Activity");
                Intent intent = new Intent("com.example.kitri.app1107.sms");
                intent.putExtra("phone",m.getTel());
                intent.setComponent(cn);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}

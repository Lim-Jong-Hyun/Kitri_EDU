package com.example.kitri.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Member b = (Member)intent.getSerializableExtra("m");
        Toast.makeText(context, "reciever2/name:"+b.getName()+"/age:"+b.getAge(),Toast.LENGTH_LONG).show();
    }
}

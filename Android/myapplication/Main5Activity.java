package com.example.kitri.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {
    private ProgressBar pb;
    private MyService.MyBinder myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        pb = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void onStart(View view) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);

    }

    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            pb.setProgress(myBinder.getVolume());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void onBind(View view) {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, sc, Context.BIND_AUTO_CREATE);
    }

    public void onUnbind(View view) {
        unbindService(sc);
        myBinder = null;
    }

    public void onStop(View view) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    //바인드객체를 끊는다고 커넥트객체가 널이되는것이아니다. 코드작성해야함
    public void onUp(View view) {
        if (myBinder == null) {
            Toast.makeText(this, "바인드 먼저", Toast.LENGTH_SHORT);
            return;
        } else {
            int newVolume = myBinder.volumeUp();
            pb.setProgress(newVolume);
        }
    }

    public void onDown(View view) {
        if (myBinder == null) {
            Toast.makeText(this, "바인드 먼저", Toast.LENGTH_SHORT);
            return;
        } else {
            int newVolume = myBinder.volumeDown();
            pb.setProgress(newVolume);
        }
    }
}

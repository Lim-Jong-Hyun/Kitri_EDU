package com.example.kitri.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

    private MyBinder myBinder;
    private int num;
    public class MyBinder extends Binder {
        public int volume =50;

        public int getNum(){
            return num;
        }
        public int volumeUp(){
            volume+=10;
            if(volume>100){
                volume=100;
            }
            return volume;
        }
        public int volumeDown(){
            volume -=10;
            if(volume<0){
                volume=0;
            }
            return volume;
        }
        public int getVolume(){
            return volume;
        }
    }
    public MyService() {
        myBinder = new MyBinder();
    }

    //IBINDER라는 인터페이스의 구현은 binder에 되어있음 그래서 binder를 상속받은 객체를 던져도됨
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Toast.makeText(this,"onBind()",Toast.LENGTH_SHORT).show();
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"onCreate()",Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        num = 100;
        Toast.makeText(this,"onStartCommand()",Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"onDestroy()",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this,"onUnbind()",Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);

    }

}

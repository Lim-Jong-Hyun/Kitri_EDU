package com.example.kitri.myapp2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class Main10Activity extends AppCompatActivity {
    private GridView gv;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        gv = (GridView)findViewById(R.id.gv);
        imageAdapter = new ImageAdapter(this);
        gv.setAdapter(imageAdapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean flag = (boolean)imageAdapter.getItem(position);
                if(!flag){
                    imageAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    private class ImageAdapter extends BaseAdapter {
        private Context context;
        private boolean flag = false;
        private int po1 =-1;

        public ImageAdapter(Context c){
            context = c;
        }
        int[] idata = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,R.drawable.e,
                        R.drawable.f,R.drawable.g,R.drawable.h};
        //데이터 개수
        @Override
        public int getCount() {
            return idata.length;
        }
        //파라메터가 지저한 항목객체 반환
        @Override
        public Object getItem(int position) {
            int tmp =0;
            if(flag){
                if(position%3==0){
                    if(position+1==po1 || position+3==po1 || position-3==po1){
                        tmp=idata[po1];
                        idata[po1]  = idata[position];
                        idata[position] =tmp;
                        flag = false;
                    }
                }else if(position%3==1){
                    if(position+1==po1 || position+3==po1 || position-3==po1 ||position-1==po1){
                        tmp=idata[po1];
                        idata[po1]  = idata[position];
                        idata[position] =tmp;
                        flag = false;
                    }
                }else if(position%3==2){
                    if(position-1==po1 || position+3==po1 || position-3==po1){
                        tmp=idata[po1];
                        idata[po1]  = idata[position];
                        idata[position] =tmp;
                        flag = false;
                    }
                }

            }else{
                po1=position;
                flag=true;
            }
            return flag;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if(convertView ==null){
                imageView = new ImageView(context);

                imageView.setLayoutParams(new GridView.LayoutParams(200,200));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8,8,8,8);
            }else{
                imageView = (ImageView)convertView;
            }
            imageView.setImageResource(idata[position]);
            return imageView;
        }
    }

}


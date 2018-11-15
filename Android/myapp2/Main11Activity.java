package com.example.kitri.myapp2;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class Main11Activity extends AppCompatActivity {
    private GridView gv;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        gv = (GridView) findViewById(R.id.gv2);
        imageAdapter = new ImageAdapter(this);
        gv.setAdapter(imageAdapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    showMyDialog(position);
            }
        });
    }

    public void showMyDialog(int pos){

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        TextView title = (TextView)dialog.findViewById(R.id.title);
        TextView detail =(TextView)dialog.findViewById(R.id.detail);
        ImageView iv = (ImageView) dialog.findViewById(R.id.bigimg);
        Button b = (Button)dialog.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.setTitle("영화 상세페이지");
        title.setText((pos+1)+"번 영화");
        detail.setText((pos+1)+"번 영화 상세내용");
        int imgRes =(int)imageAdapter.getItem(pos);
        iv.setImageResource(imgRes);
        dialog.create();
        dialog.show();
    }


    class ImageAdapter extends BaseAdapter {
        private Context context;

        public ImageAdapter(Context c){
            context = c;
        }
        int[] idata = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};
        //데이터 개수
        @Override
        public int getCount() {
            return idata.length;
        }
        //파라메터가 지저한 항목객체 반환
        @Override
        public Object getItem(int position) {
            return idata[position];
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

package com.example.kitri.myapp2.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class MemberDBAdapter {
        private static final String DB = "memberDB.db";
        private static final String DB_TABLE = "membertable";
        private static final String ID = "_id";//primary key
        private static final String NAME = "name";
        private static final String TEL ="tel";
        private static final String IMG_RES ="img_res";
        private static final int DB_VERS = 1;//db 파일 버전

        private SQLiteDatabase mdb;
        private final Context context;
        private MemberDBAdapter.MyHelper mHelper;

        public MemberDBAdapter(Context context) {
            this.context = context;
            mHelper = new MemberDBAdapter.MyHelper(context, DB, null, DB_VERS);
        }

        public void open() throws SQLiteException {
            try {
                mdb = mHelper.getWritableDatabase();//db파일을 읽고쓰기 모드로 오픈
            } catch (SQLiteException ex) {
                mdb = mHelper.getReadableDatabase();//db파일을 읽기 모드로 오픈
            }
        }

        public void close() {
            mdb.close();//오픈했던 db파일을 닫는다
        }
        public long insertData(Member m) {
            ContentValues cv = new ContentValues();
            cv.put(NAME, m.getName());
            cv.put(TEL, m.getTel());
            cv.put(IMG_RES,m.getImg_res());
            return mdb.insert(DB_TABLE, null, cv);
        }
        public int removeData(long index) {

            return mdb.delete(DB_TABLE, ID + "=" + index, null);
        }

        public int updateData(Member m) {
            String where = ID + " = " + m.getId();
            ContentValues cv = new ContentValues();
            cv.put(NAME, m.getName());
            cv.put(TEL, m.getTel());
            cv.put(IMG_RES,m.getImg_res());
            return mdb.update(DB_TABLE, cv, where, null);
        }
        public Cursor getAll() {
            return mdb.query(DB_TABLE, new String[] { ID, NAME,TEL,IMG_RES}, null, null, null, null, null);
        }
        public Cursor getMemo(long id){
            return mdb.query(DB_TABLE, new String[] { ID, NAME,TEL,IMG_RES },
                    ID +"="+id, null, null,
                    null, null);
        }
        private static class MyHelper extends SQLiteOpenHelper {
            private static final String DB_CREATE = "create table " + DB_TABLE +
                    " (" + ID +
                    " integer primary key autoincrement, " + NAME +
                    " text not null, "+ TEL +" text not null, "+IMG_RES+" integer)";
            public MyHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
                super(context, name, factory, version);
            }
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(DB_CREATE);
            }
            @Override
            public void onUpgrade(SQLiteDatabase db, int oVers, int nVers) {
                db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
                onCreate(db);
            }
        }

}
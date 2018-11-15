package com.example.kitri.myapp1108;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBAdapter {
    private static final String DB = "MyDB.db";
    private static final String DB_TABLE = "MyTable";
    private static final String ID = "_id"; //_id를 꼭사용 프라이머리키
    private static final String NAME = "name";
    private static final int DB_VERS = 1;

    private SQLiteDatabase mdb;
    private final Context context;
    private MyHelper mHelper;

    public MyDBAdapter(Context context) {
        this.context = context;
        mHelper = new MyHelper(context, DB, null, DB_VERS);
    }

    public void open() throws SQLiteException {
        try {
            mdb = mHelper.getWritableDatabase(); //디비를 읽고 쓰기모드로 오픈
        } catch (SQLiteException ex) {
            mdb = mHelper.getReadableDatabase(); //디비파일을 읽기모드로 오픈
        }
    }

    public void close() {

        mdb.close();
    }
    public long insertData(String name) {

        ContentValues cv = new ContentValues();

        cv.put(NAME, name);

        return mdb.insert(DB_TABLE, null, cv);
    }

    public int removeData(long index) {

        return mdb.delete(DB_TABLE, ID + "=" + index, null);
    }

    public int updateData(long index, String name) {

        String where = ID + " = " + index;

        ContentValues cv = new ContentValues();

        cv.put("name", name);

        return mdb.update(DB_TABLE, cv, where, null);
    }

    public Cursor getAll() {
        return mdb.query(DB_TABLE, new String[] { ID, NAME}, null, null, null, null, null);
    }

    private static class MyHelper extends SQLiteOpenHelper {

        private static final String DB_CREATE = "create table " + DB_TABLE + " (" + ID +
                " integer primary key autoincrement, " + NAME + " text not null );";

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

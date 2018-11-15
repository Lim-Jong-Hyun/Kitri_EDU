package com.example.kitri.myapp1112_2;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class MyContentProvider extends ContentProvider {
    //DB���ϸ�, ���̺��, DB���� ������ ����� ��� ����
    public static final String DB_NAME = "memberdata.db";
    public static final String DB_TABLE = "member";
    public static final int DB_VERS = 1;

    //�÷��� ��� ����
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";

    //�÷� �ε��� ��� ����
    public static final int ID_COLUMN = 0;
    public static final int NAME_COLUMN = 1;
    public static final int EMAIL_COLUMN = 2;

    private SQLiteDatabase db;
    private Context context;
    private myDBHelper dbHelper;

    //���� ���ø����̼� ������ URI��  ����� �� �ֵ��� URI�� ����� ����
    public static final Uri CONTENT_URI = Uri
            .parse("content://com.example.kitri.myapp1112_2.myProvider");

    //UriMatcher���� URI�� ���¸� ������ �� ����� ��� ����
    private static final int DATAS = 1;
    private static final int DATA_ID = 2;

    //����Ʈ ������� �����ϴ� URI�� ���¸� ���� �ִ� UriMatcher ����
    private static final UriMatcher myURIMatcher;

    //UriMatcher ��ü ���� �� �� ����� �� URI ���
    static {
        myURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        myURIMatcher.addURI("com.example.kitri.myapp1112_2.myProvider", "email", DATAS);
        myURIMatcher.addURI("com.example.kitri.myapp1112_2.myProvider", "email/#", DATA_ID);
    }


    public MyContentProvider() {
    }

    //����Ʈ ���ι��̴��� �������ڸ��� ȣ��Ǵ� onCreate()���� ����� DB Helper Ŭ��
    //�� ��ü �����ϰ� DB ����. �������� ���µǸ� true, �ƴϸ� false ��ȯ
    @Override
    public boolean onCreate() {
        context = getContext();
        dbHelper = new myDBHelper(context, DB_NAME, null, DB_VERS);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
        return (db == null) ? false : true;
    }

    //URI ���¿� ���� MIME ��ȯ
    //URI�� �������̸� ������ MIME�� URI�� �������̸� ������ MIME ��ȯ
    @Override
    public String getType(Uri uri) {
        switch (myURIMatcher.match(uri)) {
            case DATAS:
                return "vnd.android.cursor.dir/vnd.example.usr.app12.myProvider";
            case DATA_ID:
                return "vnd.android.cursor.item/vnd.example.usr.app12.myProvider";
            default:
                return null;
        }
    }

    //ContentResolver.query()�� ȣ���ϸ� ȣ��Ǵ� �޼���� �˻��� ����.
    //����� cursor ��ȯ
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(DB_TABLE);

        //URI ���°� ���� �������̶�� �� URI ���� ID�� �ִٸ� where���� �̸� �߰�
        if (myURIMatcher.match(uri) == DATA_ID) {
            String id = uri.getPathSegments().get(1);
            qb.appendWhere(KEY_ID + "=" + id);
        }

        //�˻�
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, null);
        return c;
    }

    //ContentResolver.insert()�� ȣ���ϸ� ȣ��Ǵ� �޼���� �� �� �߰��ϰ� ���� �߰���
    //�׸��� URI�� ��ȯ
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //�Ķ���ͷ� ���� ContentValues�� ���̺� �߰�.
        //insert() �޼���� �߰��� �׸��� id�� ��ȯ
        long rowID = db.insert(DB_TABLE, null, values);

        //���������� �����Ͱ� �߰� �Ǿ����� ���� �߰��� �׸��� URI�� ��ȯ�Ѵ�.
        if (rowID > 0) {

            //�� �׸��� URI ����
            Uri uri_id = ContentUris.withAppendedId(CONTENT_URI, rowID);

            //URI ���� �����Ͱ� ����Ǿ����� �����ʿ� �˸�
            getContext().getContentResolver().notifyChange(uri_id, null);
            return uri;
        }
        return null;
    }

    //ContentResolver.update()�� ȣ���ϸ� ȣ��Ǵ� �޼���. ����ó���� �� �� ��ȯ
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int cnt;
        //URI ���� ��
        switch (myURIMatcher.match(uri)) {

            //URI�� �������̸� �ٷ� ���� ����
            case DATAS:
                cnt = db.update(DB_TABLE, values, selection, selectionArgs);
                break;

            //URI�� �������̸� where���� id�� ������ �߰��Ͽ� ���� ����
            case DATA_ID:
                String id = uri.getPathSegments().get(1);
                cnt = db.update(DB_TABLE, values, KEY_ID + "="	+ id
                        + (!TextUtils.isEmpty(selection) ? " AND ("
                        + selection + ')' : ""), selectionArgs);
                break;
            default:
                return 0;
        }
        //URI ���� �����Ͱ� ����Ǿ����� �����ʿ� �˸�
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    //ContentResolver.delete()�� ȣ���ϸ� ȣ��Ǵ� �޼���. ����ó���� �� �� ��ȯ
    @Override
    public int delete(Uri arg0, String arg1, String[] arg2) {
        int cnt;

        //URI ���� ��
        switch (myURIMatcher.match(arg0)) {

            //URI�� �������̸� �ٷ� ���� ����
            case DATAS:
                cnt = db.delete(DB_TABLE, arg1, arg2);
                break;

            //URI�� �������̸� where���� id�� ������ �߰��Ͽ� ���� ����
            case DATA_ID:
                String id = arg0.getPathSegments().get(1);
                cnt = db.delete(DB_TABLE, KEY_ID + "="	+ id
                        + (!TextUtils.isEmpty(arg1) ? " AND (" + arg1 + ')' : ""), arg2);

                break;
            default:
                return 0;
        }
        //URI ���� �����Ͱ� ����Ǿ����� �����ʿ� �˸�
        getContext().getContentResolver().notifyChange(arg0, null);

        return cnt;
    }

    //DB helper Ŭ���� ����
    public static class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
            super(context, name, factory, version);
        }

        private static final String DB_CREATE = "create table " + DB_TABLE
                + " (" + KEY_ID + " integer primary key autoincrement, "
                + KEY_NAME + " text not null," + KEY_EMAIL
                + " text not null );";

        //db ���� ������ ���̺� ����
        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DB_CREATE);
        }

        //db ���� ������ �ٸ��� ���̺� ���� �� �ٽ� ����
        @Override
        public void onUpgrade(SQLiteDatabase _db, int _oldVersion,
                              int _newVersion) {
            _db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(_db);
        }
    }

}

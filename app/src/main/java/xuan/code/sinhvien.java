package xuan.code;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sinhvien extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbsvdemo";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "sinhvien";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRST = "hosv";
    private static final String KEY_CLASS = "lop";
    private static final String KEY_LAST = "tensv";

    public sinhvien(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME +
                "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_FIRST + " TEXT, " +
                KEY_LAST + " TEXT, " +
                KEY_CLASS + " TEXT" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void taosv(String ho, String ten, String lop) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues noidung = new ContentValues();
        noidung.put(KEY_FIRST, ho);
        noidung.put(KEY_LAST, ten);
        noidung.put(KEY_CLASS, lop);

        String nullColumnHack = null;
        db.insert(TABLE_NAME, nullColumnHack, noidung);
    }

    public Cursor getAllsv() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor con_tro = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return con_tro;
    }

    public boolean xoasv(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs = {id};
        int rowsAffected = db.delete(TABLE_NAME, KEY_ID + " = ?", whereArgs);

        if (rowsAffected > 0) {
            db.execSQL("VACUUM");
            return true;
        } else {
            return false;
        }
    }



    public void xoaTatCaSinhVien() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean capnhatsv(String id,String ho, String ten, String lop) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST, ho);
        values.put(KEY_LAST, ten);
        values.put(KEY_CLASS, lop);
        String whereClause =KEY_ID + "= ?";
        String[] whereArgs = {id};
        int rowsAffected = db.update(TABLE_NAME, values, whereClause, whereArgs);
        return rowsAffected > 0;
    }


    public Cursor timsv(String ten) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_FIRST + " LIKE '%" + ten + "%'";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }
}

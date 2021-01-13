package com.example.mybensin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private final Context context;
    private static  final String DATABASE_NAME ="historyku";
    private static  final int DATABASE_VERSION=1;

    private static final String TABLE_NAME="my_history";
    private static final String COLUMN_ID ="_id";
    private static final String COLUMN_JARAK="jarak_temp";
    private static final String COLUMN_EFISIEN="efisiensi";
    private static final String COLUMN_BENSIN="jbensin";
    private static final String COLUMN_COST="costfuel";
    private static final String COLUMN_HAVE="needfuel";

    public MyDatabaseHelper(@Nullable Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query= "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_JARAK + " DOUBLE, " +
                        COLUMN_EFISIEN + " DOUBLE, " +
                        COLUMN_BENSIN + " TEXT, " +
                        COLUMN_COST + " DOUBLE, " +
                        COLUMN_HAVE + " DOUBLE); ";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);
    }
    void addriwayat(double distan , double efisien , String bensin , double hargab , double butuhb)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_JARAK,distan);
        cv.put(COLUMN_EFISIEN,efisien);
        cv.put(COLUMN_BENSIN,bensin);
        cv.put(COLUMN_COST,hargab);
        cv.put(COLUMN_HAVE,butuhb);
        long result =db.insert(TABLE_NAME,null,cv);

        if(result == -1 )
        {
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context,"sucess",Toast.LENGTH_SHORT).show();

    }

    Cursor readAlldata()
    {
        String query= "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor= null;
        if(db !=null)
        {
          cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    void deleteAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_NAME);

    }
}

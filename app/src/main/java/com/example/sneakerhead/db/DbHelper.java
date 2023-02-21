package com.example.sneakerhead.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static  final int DATABASE_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, "zapatilla.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE zapas (id  INTEGER PRIMARY KEY AUTOINCREMENT, modelo TEXT , marca TEXT  , talla TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE  zapas");
    }
}

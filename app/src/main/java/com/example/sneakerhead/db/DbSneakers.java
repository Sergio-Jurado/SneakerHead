package com.example.sneakerhead.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.sneakerhead.entidades.Sneakers;

import java.util.ArrayList;

public class DbSneakers extends DbHelper {

    Context context;

    public DbSneakers(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarSneakers(String modelo, String marca, String talla) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("modelo", modelo);
            values.put("marca", marca);
            values.put("talla", talla);

            id = db.insert("zapas", null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Sneakers> mostrarSneakers() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Sneakers> listaSneakers = new ArrayList<>();
        Sneakers sneaker;
        Cursor cursorSneakers;

        cursorSneakers = db.rawQuery("SELECT * FROM zapas", null, null);

        if (cursorSneakers.moveToFirst()) {
            do {
                sneaker = new Sneakers();
                sneaker.setId(cursorSneakers.getInt(0));
                sneaker.setModelo(cursorSneakers.getString(1));
                sneaker.setMarca(cursorSneakers.getString(2));
                sneaker.setTalla(cursorSneakers.getString(3));
                listaSneakers.add(sneaker);
            } while (cursorSneakers.moveToNext());
        }

        cursorSneakers.close();

        return listaSneakers;
    }

    public Sneakers verSneaker(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Sneakers sneaker = null;
        Cursor cursorSneakers;

        cursorSneakers = db.rawQuery("SELECT * FROM zapas WHERE id = " + id + " LIMIT 1", null);

        if (cursorSneakers.moveToFirst()) {
            sneaker = new Sneakers();
            sneaker.setId(cursorSneakers.getInt(0));
            sneaker.setModelo(cursorSneakers.getString(1));
            sneaker.setMarca(cursorSneakers.getString(2));
            sneaker.setTalla(cursorSneakers.getString(3));
        }

        cursorSneakers.close();

        return sneaker;
    }

    public boolean editarSneaker(int id, String modelo, String marca, String talla) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE zapas SET modelo = '" + modelo + "', marca = '" + marca + "', talla = '" + talla + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarSneaker(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM zapas WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}


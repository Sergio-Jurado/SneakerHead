package com.example.sneakerhead.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.sneakerhead.entidades.Usuario;

import java.util.ArrayList;

public class DbUsuario extends DbHelper{
    Context context;
    ArrayList<Usuario> lista;

    public DbUsuario(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarUsuario(String nombre, String usuario, String password){
        long id = 0;

        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values  = new ContentValues();
            values.put("nombre", nombre);
            values.put("usuario", usuario);
            values.put("password", password);

            id = db.insert("user", null, values);

        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<Usuario> selectUsuario(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<Usuario> lista = new ArrayList<>();
        lista.clear();

        Cursor cursorUsuario;
        Usuario user = null;

        cursorUsuario = db.rawQuery("SELECT * FROM user", null);
        if (cursorUsuario.moveToFirst()){
            user = new Usuario();
            user.setNombre(cursorUsuario.getString(0));
            user.setUsuario(cursorUsuario.getString(1));
            user.getPassword(cursorUsuario.getString(2));
            lista.add(user);
        }
        cursorUsuario.close();

    return lista;
    }


    public boolean comprobar(String usuario, String password){
        boolean comprueba = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursorComprobar;
        cursorComprobar = db.rawQuery("SELECT * FROM user WHERE usuario = \""+usuario+ "\"", null);
        if (cursorComprobar.moveToNext()){

            if(cursorComprobar.getString(2).equals(usuario) && cursorComprobar.getString(3).equals(password)){
                comprueba = true;
                System.out.println("True");
            } else {
                comprueba = false;
                System.out.println("False");

            }
        }

        return comprueba;
    }



}



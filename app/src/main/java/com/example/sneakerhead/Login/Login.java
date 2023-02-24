package com.example.sneakerhead.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sneakerhead.R;
import com.example.sneakerhead.ShowAllSneaker;
import com.example.sneakerhead.ShowSneakers;
import com.example.sneakerhead.db.DbHelper;
import com.example.sneakerhead.db.DbUsuario;

public class Login extends AppCompatActivity {
    EditText txtUsuario, txtPassword;
    Button btnLogin, btnsSignIn;
    Context context;
    DbUsuario dbUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword = findViewById(R.id.txtPassword);
        dbUsuario = new DbUsuario(this);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean comprueba = dbUsuario.comprobar(txtUsuario.getText().toString(), txtPassword.getText().toString());
                if (comprueba){
                    Intent intent = new Intent(view.getContext(), ShowSneakers.class);
                    startActivity(intent);
                }
            }
        });


        btnsSignIn = findViewById(R.id.btnSignIn);
        btnsSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignIn.class);
                startActivity(intent);
            }
        });
    }

}
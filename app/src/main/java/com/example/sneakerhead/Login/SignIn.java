package com.example.sneakerhead.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sneakerhead.AddSneaker;
import com.example.sneakerhead.R;
import com.example.sneakerhead.db.DbUsuario;

public class SignIn extends AppCompatActivity {
    EditText txtSetUsuario, txtSetPassword, txtSetName;
    Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setTitle("Añadir Usuario");

        txtSetName = findViewById(R.id.txtSetNombre);
        txtSetUsuario = findViewById(R.id.txtSetUsuario);
        txtSetPassword = findViewById(R.id.txtSetPassword);

        btnCrear = findViewById(R.id.btnCreate);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtSetName.getText().toString().equals("") && !txtSetUsuario.getText().toString().equals("") && !txtSetPassword.getText().toString().equals("")){
                    DbUsuario dbUsuario = new DbUsuario(SignIn.this);
                    System.out.println(txtSetName.getText().toString());
                    long id = dbUsuario.insertarUsuario(txtSetName.getText().toString(), txtSetUsuario.getText().toString(),txtSetPassword.getText().toString());

                    if (id>0){
                        Toast.makeText(SignIn.this, "USUARIO CREADO", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignIn.this, Login.class);
                    } else{
                        Toast.makeText(SignIn.this, "ERROR AL CREAR EL USUARIO", Toast.LENGTH_LONG).show();

                    }
                } else{
                    Toast.makeText(SignIn.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
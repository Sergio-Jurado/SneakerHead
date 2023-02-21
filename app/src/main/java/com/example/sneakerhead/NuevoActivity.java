package com.example.sneakerhead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sneakerhead.db.DbSneakers;

public class NuevoActivity extends AppCompatActivity {
    EditText txtModelo, txtMarca, txtTalla;

    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtModelo =findViewById(R.id.txtModelo);
        txtMarca = findViewById(R.id.txtMarca);
        txtTalla =findViewById(R.id.txtTalla);

        btnGuardar = findViewById(R.id.btnEdit);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtModelo.getText().toString().equals("") && !txtMarca.getText().toString().equals("") && !txtTalla.getText().toString().equals("")) {

                    DbSneakers dbSneakers = new DbSneakers(NuevoActivity.this);
                    long id = dbSneakers.insertarSneakers(txtModelo.getText().toString(), txtMarca.getText().toString(), txtTalla.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        clean();
                        showSneaker();
                    } else {
                        Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void clean() {
        txtModelo.setText("");
        txtMarca.setText("");
        txtTalla.setText("");
    }

    private void showSneaker(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
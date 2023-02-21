package com.example.sneakerhead;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sneakerhead.db.DbSneakers;
import com.example.sneakerhead.entidades.Sneakers;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditSneaker extends AppCompatActivity {

    EditText txtModelo, txtMarca, txtTalla;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Sneakers sneakers;
    int id = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        setTitle("Editar Sneaker");

        txtModelo = findViewById(R.id.txtModelo);
        txtMarca = findViewById(R.id.txtMarca);
        txtTalla = findViewById(R.id.txtTalla);

        btnGuarda = findViewById(R.id.btnGuarda);

        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        final DbSneakers dbSneakers = new DbSneakers(EditSneaker.this);
        sneakers = dbSneakers.verSneaker(id);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = 0;
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        if (sneakers != null) {
            txtModelo.setText(sneakers.getModelo());
            txtMarca.setText(sneakers.getMarca());
            txtTalla.setText(sneakers.getTalla());
        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtModelo.getText().toString().equals("") && !txtMarca.getText().toString().equals("") && !txtTalla.getText().toString().equals("")) {
                    correcto = dbSneakers.editarSneaker(id, txtModelo.getText().toString(), txtMarca.getText().toString(), txtTalla.getText().toString());

                    if(correcto){
                        Toast.makeText(EditSneaker.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditSneaker.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditSneaker.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, ShowSneakers.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}

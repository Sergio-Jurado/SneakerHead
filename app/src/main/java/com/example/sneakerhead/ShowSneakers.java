package com.example.sneakerhead;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.sneakerhead.Login.Login;
import com.example.sneakerhead.adaptadores.ListaSneakersAdapter;
import com.example.sneakerhead.db.DbSneakers;
import com.example.sneakerhead.entidades.Sneakers;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ShowSneakers extends AppCompatActivity {

    RecyclerView listaSneakers;
    ArrayList<Sneakers> listaArraySneakers;
    FloatingActionButton fabNuevo;
    ListaSneakersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaSneakers = findViewById(R.id.listaSneakers);
        setTitle("Sneaker");
        fabNuevo = findViewById(R.id.favNuevo);
        listaSneakers.setLayoutManager(new LinearLayoutManager(this));

        DbSneakers dbSneakers = new DbSneakers(ShowSneakers.this);

        listaArraySneakers = new ArrayList<>();

        adapter = new ListaSneakersAdapter(dbSneakers.mostrarSneakers());
        listaSneakers.setAdapter(adapter);

        fabNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {addSneaker();}
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                addSneaker();
                return true;
            case R.id.Login:
                Intent intent = new Intent(ShowSneakers.this, Login.class);
                startActivity(intent);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addSneaker(){
        Intent intent = new Intent(this, AddSneaker.class);
        startActivity(intent);
    }
}
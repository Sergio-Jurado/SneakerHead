package com.example.sneakerhead.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sneakerhead.R;
import com.example.sneakerhead.VerActivity;
import com.example.sneakerhead.entidades.Sneakers;

import java.util.ArrayList;

public class ListaSneakersAdapter extends RecyclerView.Adapter<ListaSneakersAdapter.SneakerViewHolder> {

    ArrayList<Sneakers> listaSneakers;
    ArrayList<Sneakers> listaOriginal;

    public ListaSneakersAdapter(ArrayList<Sneakers> listaSneakers) {
        this.listaSneakers = listaSneakers;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaSneakers);
    }

    @NonNull
    @Override
    public ListaSneakersAdapter.SneakerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_sneaker, null, false);
        return new SneakerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaSneakersAdapter.SneakerViewHolder holder, int position) {
        holder.txtViewMarca.setText(listaSneakers.get(position).getMarca());
        holder.txtViewModelo.setText(listaSneakers.get(position).getModelo());
        holder.txtViewTalla.setText(listaSneakers.get(position).getTalla());
    }


    @Override
    public int getItemCount() {
        return listaSneakers.size();
    }

    public class SneakerViewHolder extends RecyclerView.ViewHolder {

        TextView txtViewModelo, txtViewMarca,  txtViewTalla;

        public SneakerViewHolder(@NonNull View itemView) {
            super(itemView);

            txtViewModelo = itemView.findViewById(R.id.viewModelo);
            txtViewMarca = itemView.findViewById(R.id.viewMarca);
            txtViewTalla = itemView.findViewById(R.id.viewTalla);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaSneakers.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}

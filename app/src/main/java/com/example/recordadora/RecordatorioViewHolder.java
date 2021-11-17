package com.example.recordadora;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class RecordatorioViewHolder extends RecyclerView.ViewHolder {
    int lugar;
    public TextView tiempo,conteido;

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }

    public TextView getTiempo() {
        return tiempo;
    }

    public void setTiempo(TextView tiempo) {
        this.tiempo = tiempo;
    }


    public RecordatorioViewHolder(@NonNull View itemView) {
        super(itemView);
        conteido = itemView.findViewById(R.id.textViewtText);
        tiempo= itemView.findViewById(R.id.textViewFecha);
    }

}

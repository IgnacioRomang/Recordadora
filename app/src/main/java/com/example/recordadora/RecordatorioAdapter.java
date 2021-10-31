package com.example.recordadora;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordatorioAdapter extends RecyclerView.Adapter<RecordatorioViewHolder> implements View.OnClickListener {
    private View.OnClickListener listener;
    private List<RecordatorioModel> listRec;

    public RecordatorioAdapter(List<RecordatorioModel> listRec) {
        this.listRec = listRec;
    }

    public void add(RecordatorioModel e){
        if(listRec==null){
            listRec= new ArrayList<RecordatorioModel>();
        }
        listRec.add(e);
    }
    @NonNull
    @Override
    public RecordatorioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.filas_recordatorio, viewGroup, false);
        RecordatorioViewHolder rec = new RecordatorioViewHolder(vista);
        rec.setLugar(i);
        vista.setOnClickListener(this);
        return rec;
    }

    @Override
    public void onBindViewHolder(@NonNull RecordatorioViewHolder recordatorioViewHolder, int i) {
        recordatorioViewHolder.tiempo.setTag(i);
        RecordatorioModel rec = listRec.get(i);
        String fech;
        SimpleDateFormat formatoF = new SimpleDateFormat("dd/MM/yyyy - hh:mm");
        fech = formatoF.format(rec.getFecha());
        recordatorioViewHolder.tiempo.setText("Fecha: " +fech);
        recordatorioViewHolder.titulo.setText(rec.getTitulo().toString());
        recordatorioViewHolder.conteido.setText("  "+rec.getTexto().toString());
    }

    @Override
    public int getItemCount() {
        return listRec.size();
    }

    @Override
    public void onClick(View view) {
        if (this.listener != null) {
            this.listener.onClick(view);
        }
    }
}

package com.example.recordadora;

import android.os.AsyncTask;

import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.Executor;

public class RecordatorioRepository {
    private final RecordatorioDataSource datasource;
    public RecordatorioRepository(final RecordatorioDataSource datasource) {
        this.datasource = datasource;
    }
    // Metodos que recuperan los recordatorios usando el data source

    public void traerRecordatorios(RecordatorioDataSource.RecuperarRecordatorioCallback callback){
        // TODO: 04/11/2021 Cargarlos en la lista, y enviar Alarm.
        Runnable r = new Runnable() {
            @Override
            public void run() {
                datasource.recuperarRecordatorios(callback);
            }
        };
        r.run();
    }
    public void guardarRecordatorio(RecordatorioDataSource.GuardarRecordatorioCallback callback,RecordatorioModel reco){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                datasource.guardarRecordatorio(reco,callback);
            }
        };
        r.run();
    }

}

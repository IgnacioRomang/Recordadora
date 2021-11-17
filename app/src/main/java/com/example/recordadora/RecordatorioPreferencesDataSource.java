package com.example.recordadora;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RecordatorioPreferencesDataSource implements RecordatorioDataSource {
    private final SharedPreferences sharedPreferences;
    RecordatorioPreferencesDataSource(final Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
    // Implementación de los metodos de la interface
    @Override
    public void guardarRecordatorio(RecordatorioModel recordatorio, GuardarRecordatorioCallback callback) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String key = String.valueOf(recordatorio.hashCode());
        try {
            editor.putString(key,recordatorio.toJSON().toString());
            editor.commit();
        }catch (Exception e){

        }
        Boolean result= sharedPreferences.contains(key);
        callback.resultado(result);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void recuperarRecordatorios(RecuperarRecordatorioCallback callback) {
        List<RecordatorioModel> resultado= new ArrayList<>();
        Set<String> lista= sharedPreferences.getAll().keySet();
        boolean restBool= false;
        if(!lista.isEmpty()){
            restBool= true;
            resultado= new ArrayList<>();
            RecordatorioModel nuevo;
            JSONObject nuvJson;
            for (String i: lista ) {
                try {
                    nuvJson= new JSONObject(sharedPreferences.getString(i,null));
                    nuevo= new RecordatorioModel(nuvJson);
                    resultado.add(nuevo);
                }catch (Exception e){}
            }
        }
        callback.resultado(restBool,resultado);
    }
}

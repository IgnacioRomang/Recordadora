package com.example.recordadora;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordatorioAPIRestDataSource implements RecordatorioDataSource {
    private RetroCalls serv;
    public RecordatorioAPIRestDataSource(String user,String pass){
        serv = RetrofitServ.createService(RetroCalls.class,user,pass);
    }
    @Override
    public void guardarRecordatorio(RecordatorioModel recordatorio, GuardarRecordatorioCallback callback) {
        Runnable n = new Runnable() {
            @Override
            public void run() {
                Call<RecordatorioModel> result= serv.saveRecordatorio(recordatorio);
                result.enqueue(new Callback<RecordatorioModel>() {
                    @Override
                    public void onResponse(Call<RecordatorioModel> call, Response<RecordatorioModel> response) {
                        if (response.isSuccessful()) {
                            callback.resultado(true);
                        } else {
                            callback.resultado(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<RecordatorioModel> call, Throwable t) {
                        callback.resultado(false);
                    }
                });
            }
        };
        n.run();
    }

    @Override
    public void recuperarRecordatorios(RecuperarRecordatorioCallback callback) {
        Runnable n = new Runnable() {
            @Override
            public void run() {
                Call<List<RecordatorioModel>> result=serv.getRecordatorios();
                result.enqueue(new Callback<List<RecordatorioModel>>() {
                    @Override
                    public void onResponse(Call<List<RecordatorioModel>> call, Response<List<RecordatorioModel>> response) {
                        if (response.isSuccessful()) {
                            callback.resultado(true, response.body());
                        } else {
                            callback.resultado(false, new ArrayList<RecordatorioModel>());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<RecordatorioModel>> call, Throwable t) {
                        Log.w("dev: ", t.toString());
                        callback.resultado(false, new ArrayList<RecordatorioModel>());
                    }
                });
            }
        };
        n.run();
    }
}

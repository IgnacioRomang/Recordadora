package com.example.recordadora;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetroCalls {
    @GET("recordatorio/")
    Call<List<RecordatorioModel>> getRecordatorios();
    @POST("recordatorio/")
    Call<RecordatorioModel> saveRecordatorio(@Body RecordatorioModel p);
}

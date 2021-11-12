package com.example.recordadora;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import java.util.List;

@Database(entities = {RecordatorioModel.class}, version = 1)
@TypeConverters({DataConverter.class})
public abstract class RecordatorioRoomDataSource extends RoomDatabase implements RecordatorioDataSource{
    public abstract DaoRecorder recordarorioDao();
    public static String DB_NAME ="basese";
    private static volatile RecordatorioRoomDataSource INSTANCE;

    public RecordatorioRoomDataSource(){};

    public static synchronized RecordatorioRoomDataSource getInstance(Context ctx){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(ctx, RecordatorioRoomDataSource.class, DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return  INSTANCE;
    }
    @Override
    public void guardarRecordatorio(RecordatorioModel recordatorio, GuardarRecordatorioCallback callback){
        long rowId = INSTANCE.recordarorioDao().insert(recordatorio);
        boolean result=false;
        if(rowId != OnConflictStrategy.IGNORE || rowId != OnConflictStrategy.FAIL|| rowId != OnConflictStrategy.ABORT){
            result=true;
        }
        callback.resultado(result);
    }

    @Override
    public void recuperarRecordatorios(RecuperarRecordatorioCallback callback){
        List<RecordatorioModel> resultado = INSTANCE.recordarorioDao().getAll();
        boolean result= false;
        if(!resultado.isEmpty() || resultado!= null){
            result=true;
        }
        callback.resultado(result,resultado);
    }

    @Override
    public void clearAllTables() {

    }
}
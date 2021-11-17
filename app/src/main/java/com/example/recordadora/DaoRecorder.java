package com.example.recordadora;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DaoRecorder {
    // TODO: 07/11/2021 HACE DAO PLZ NACHO DE FUTURO
    @Query("SELECT * FROM recordatorio")
    List<RecordatorioModel> getAll();

    @Insert
    void insertAll(RecordatorioModel... reco);

    @Insert(onConflict = IGNORE)
    Long insert(RecordatorioModel reco);

    @Delete
    void delete(RecordatorioModel reco);
}

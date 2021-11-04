package com.example.recordadora;

import java.util.List;

public interface RecordatorioDataSource {


    interface GuardarRecordatorioCallback {
        void resultado(final boolean exito);
    }

    interface RecuperarRecordatorioCallback {
        void resultado(final boolean exito, final List<RecordatorioModel> recordatorios);
    }

    void guardarRecordatorio(final RecordatorioModel recordatorio, final GuardarRecordatorioCallback callback);
    void recuperarRecordatorios(final RecuperarRecordatorioCallback callback);
}

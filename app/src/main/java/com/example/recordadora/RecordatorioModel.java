package com.example.recordadora;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Objects;
@Entity(tableName = "recordatorio")
public class RecordatorioModel implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String mensaje;
    @TypeConverters({DataConverter.class})
    private Date fecha;

    @Ignore
    public  RecordatorioModel() {
        super();
    }
    @Ignore
    protected RecordatorioModel(Parcel in) {
        this.mensaje = in.readString();
        this.fecha = new Date(in.readLong());
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( this.mensaje);
        dest.writeLong(this.fecha.getTime());
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static final Creator<RecordatorioModel> CREATOR = new Creator<RecordatorioModel>() {
        @Override
        public RecordatorioModel createFromParcel(Parcel in) {
            return new RecordatorioModel(in);
        }

        @Override
        public RecordatorioModel[] newArray(int size) {
            return new RecordatorioModel[size];
        }
    };


    public RecordatorioModel(final String mensaje, final Date fecha) {
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(final String mensaje) {
        this.mensaje = mensaje;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(final Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !getClass().equals(other.getClass())) {
            return false;
        }
        final RecordatorioModel that = (RecordatorioModel) other;
        return Objects.equals(this.mensaje, that.mensaje) && Objects.equals(this.fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mensaje) + Objects.hash(fecha);
    }

    @Ignore
    public  RecordatorioModel(JSONObject json) {
        try {
            this.mensaje = json.getString("mensaje");
            this.fecha = new Date(json.getLong("fecha"));
        }catch (Exception e){

        }
    }
    public JSONObject toJSON() throws JSONException {
        JSONObject resultado= new JSONObject();
        resultado.put("mensaje",this.mensaje);
        resultado.put("fecha",this.fecha.getTime());
        return resultado;
    }
    @Override
    public int describeContents() {
        return 0;
    }

}
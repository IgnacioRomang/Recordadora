package com.example.recordadora;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
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
    private String titulo;
    private String texto;
    @TypeConverters({DataConverter.class})
    private Date fecha;

    @Ignore
    public  RecordatorioModel() {
        super();
    }
    @Ignore
    protected RecordatorioModel(Parcel in) {
        this.texto = in.readString();
        this.fecha = new Date(in.readLong());
        this.titulo = in.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( this.texto);
        dest.writeLong(this.fecha.getTime());
        dest.writeString( this.titulo);
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public RecordatorioModel(final String texto, final String titulo, final Date fecha) {
        this.texto = texto;
        this.fecha = fecha;
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }
    public void setTexto(final String texto) {
        this.texto = texto;
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
        return Objects.equals(this.texto, that.texto) && Objects.equals(this.fecha, that.fecha) && Objects.equals(this.titulo, that.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(texto) + Objects.hash(fecha) + Objects.hash(titulo);
    }

    @Ignore
    public  RecordatorioModel(JSONObject json) {
        try {
            this.texto = json.getString("text");
            this.fecha = new Date(json.getLong("date"));
            this.titulo = json.getString("titu");
        }catch (Exception e){

        }
    }
    public JSONObject toJSON() throws JSONException {
        JSONObject resultado= new JSONObject();
        resultado.put("titu",this.titulo);
        resultado.put("text",this.texto);
        resultado.put("date",this.fecha.getTime());

        return resultado;
    }
    @Override
    public int describeContents() {
        return 0;
    }

}
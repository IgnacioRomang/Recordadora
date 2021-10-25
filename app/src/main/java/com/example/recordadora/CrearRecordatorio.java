package com.example.recordadora;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class CrearRecordatorio extends AppCompatActivity {
    private TextInputEditText titulo, desc,fecha,hora;
    //private TextInputLayout tituloLay, descLay;
    private DatePickerDialog date;
    private MaterialButton aceptar,fecha_s,hora_s;
    private TimePickerDialog timeP;
    private Calendar calendario;
    //------------------------------------------

    //------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_recordatorio);
        titulo = findViewById(R.id.editTextTitulo);
        fecha = findViewById(R.id.editTextDate);
        hora = findViewById(R.id.editTextTime);
        fecha_s = findViewById(R.id.dateselect);
        hora_s = findViewById(R.id.datetime);
        //tituloLay = findViewById(R.id.textInputLayoutTitulo);
        desc = findViewById(R.id.editTextDesc);
        //descLay = findViewById(R.id.textInputLayoutDesc);
        aceptar = findViewById(R.id.recordarbotton);
        titulo.setText("");
        desc.setText("");
        calendario = Calendar.getInstance();

        // Widget de hora lo creao
        date = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fecha.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
            }
        }, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH));

        //set los horarios por defecto
        timeP = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hora.setText(String.valueOf(hourOfDay)+":"+String.valueOf(minute));
            }
        },calendario.get(Calendar.HOUR_OF_DAY),calendario.get(Calendar.MINUTE),false);

        fecha_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date.show();
            }
        });
        hora_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeP.show();
            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok=false;
                String mensaje;
                Intent siguiente= new Intent();
                //TODO realizar la creación de la alarma y guardarla pasando a la vista de visualización

                if(ok){
                    mensaje= getString(R.string.r_toast_ok);
                    //definir el intent
                }
                else{
                    mensaje= getString(R.string.r_toast_sad);

                }
                Toast.makeText(v.getContext(),mensaje, Toast.LENGTH_LONG).show();
                startActivity(siguiente);
            }
        });
    }
}
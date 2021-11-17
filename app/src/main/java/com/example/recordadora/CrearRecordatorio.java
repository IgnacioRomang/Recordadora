package com.example.recordadora;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CrearRecordatorio extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText  desc;
    private TextView tiempoText;
    private DatePickerDialog date;
    private MaterialButton fecha_s,hora_s;
    private FloatingActionButton aceptar;
    private TimePickerDialog timeP;
    private Calendar calendario;
    private Date selec;

    private void updateFecha(){
        String fech;
        SimpleDateFormat formatoF = new SimpleDateFormat("dd/MM/yyyy - hh:mm");
        fech = formatoF.format(calendario.getTime());
        tiempoText.setText("Fecha: " +fech);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_recordatorio);
        fecha_s = findViewById(R.id.dateselect);
        hora_s = findViewById(R.id.datetime);
        tiempoText = findViewById(R.id.tiempoText);
        desc = findViewById(R.id.editTextDesc);
        aceptar = findViewById(R.id.recordarbotton);
        desc.setText("");
        calendario = Calendar.getInstance();
        updateFecha();
        ActionBar m = getSupportActionBar();
        m.setDisplayHomeAsUpEnabled(true);

        // Widget de hora lo creao
        date = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendario.set(year,month,dayOfMonth);
                updateFecha();
            }
        }, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH));

        //set los horarios por defecto
        timeP = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Date n = calendario.getTime();
                n.setHours(hourOfDay);
                n.setMinutes(minute);
                calendario.setTime(n);
                updateFecha();
            }
        },calendario.get(Calendar.HOUR_OF_DAY),calendario.get(Calendar.MINUTE),false);
        fecha_s.setOnClickListener(this);
        hora_s.setOnClickListener(this);
        aceptar.setOnClickListener(this);
        //------------------------------------

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recordarbotton:

                if(desc.getText().toString().isEmpty()){
                    Toast.makeText(v.getContext(),getString(R.string.r_error_cargar), Toast.LENGTH_LONG).show();
                }
                else{
                    Intent siguiente= new Intent(CrearRecordatorio.this,MainActivity.class);

                    RecordatorioModel nuevo = new RecordatorioModel();
                    nuevo.setMensaje(desc.getText().toString());
                    nuevo.setFecha(calendario.getTime());

                    siguiente.putExtra("r_parcel",nuevo);
                    setResult(Activity.RESULT_OK,siguiente);
                }
                finish();
                break;
            case R.id.dateselect:
                date.show();
                break;
            case R.id.datetime:
                timeP.show();
                break;
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(Activity.RESULT_OK,null);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_OK,null);
        finish();
    }

}
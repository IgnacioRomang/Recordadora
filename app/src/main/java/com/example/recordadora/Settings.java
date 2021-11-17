package com.example.recordadora;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

public class Settings extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    private  SharedPreferences sharedPreferences;
    private static String NAME = "nevilleSettings";
    private Switch noti;
    private Spinner song;
    private ArrayAdapter<CharSequence> spnstring;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        noti = findViewById(R.id.notific);
        song = findViewById(R.id.spinner);
        spnstring = ArrayAdapter.createFromResource(this, R.array.sonidos, R.layout.support_simple_spinner_dropdown_item);
        song.setAdapter(spnstring);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        sharedPreferences = getSharedPreferences(NAME,0);
        if(sharedPreferences.contains("notif")){
            noti.setChecked(sharedPreferences.getBoolean("notif",true));
        }
        else{
            editor = sharedPreferences.edit();
            noti.setChecked(true);
            editor.putBoolean("notif",true);
            editor.commit();
        }
        if(sharedPreferences.contains("song")){
            song.setSelection(sharedPreferences.getInt("song",1));
        }
        else{
            editor = sharedPreferences.edit();
            editor.putInt("song",0);
            editor.commit();
        }
        noti.setOnCheckedChangeListener(this);
        song.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editor = sharedPreferences.edit();
                editor.putInt("song",position);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        editor = sharedPreferences.edit();
        editor.putBoolean("notif",isChecked);
        editor.commit();
    }
}
package com.example.recordadora;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Settings extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    private  SharedPreferences sharedPreferences;
    private static String NAME = "nevilleSettings";
    private Switch noti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        noti = findViewById(R.id.notific);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        sharedPreferences = getSharedPreferences(NAME,0);
        if(sharedPreferences.contains("notif")){
            noti.setChecked(sharedPreferences.getBoolean("notif",true));
        }
        else{
            SharedPreferences.Editor editor = sharedPreferences.edit();
            noti.setChecked(true);
            editor.putBoolean("notif",true);
            editor.commit();
        }
        noti.setOnCheckedChangeListener(this);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent sett = new Intent(Settings.this,MainActivity.class);
                startActivity(sett);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("notif",isChecked);
        editor.commit();
    }
}
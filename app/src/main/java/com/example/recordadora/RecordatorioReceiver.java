package com.example.recordadora;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import java.util.Random;

public class RecordatorioReceiver extends BroadcastReceiver {
    public static String RECORDATORIO = "com.example.tp3.RECORDATORIO";
    public static String CANNAL_ID = "21496";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(RECORDATORIO)){
            //---------------------------
            String titulo,texto;
            titulo = intent.getExtras().getString("not_titulo");
            texto = intent.getExtras().getString("not_texto");
            NotificationCompat.Builder mBuilder;
            //-----------------------------
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mBuilder = new NotificationCompat.Builder(context,CANNAL_ID);
            }
            else{
                mBuilder = new NotificationCompat.Builder(context);
            }
            //-----------------------------
            mBuilder.setSmallIcon(R.drawable.ic_baseline_alarm_24)
                    .setContentTitle(titulo)
                    .setContentText(texto)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
            //----------------------------
            NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            Random notification_id = new Random();
            notificationManager.notify(notification_id.nextInt(100), mBuilder.build());
        }
    }
}

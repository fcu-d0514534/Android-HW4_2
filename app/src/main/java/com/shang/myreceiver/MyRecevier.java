package com.shang.myreceiver;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Shang on 2017/4/18.
 */
public class MyRecevier extends BroadcastReceiver{

    static int id=70000;
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("MSG");
        Intent newintent = new Intent();
        newintent.putExtra("MSG",msg);
        newintent.setClass(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, newintent,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notify = newNotification(context, pendingIntent, "(New) Broadcast is received.",msg);
        NotificationManager notificationManager =(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id++, notify);
    }

    @SuppressLint("NewApi")
    private Notification newNotification(
            Context context, PendingIntent pi,
            String title, String msg) {
        Notification.Builder builder =
                new Notification.Builder(context);

        builder.setContentTitle(title);
        builder.setContentText(msg);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pi);
        builder.setTicker(msg);
        builder.setWhen(System.currentTimeMillis());
        Notification notify = builder.build();
        return notify;
    }

}

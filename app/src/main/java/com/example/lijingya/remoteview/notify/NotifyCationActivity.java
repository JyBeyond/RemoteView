package com.example.lijingya.remoteview.notify;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.lijingya.remoteview.R;


/**
 * @description: this is NotifyCationActivity
 * @author: lijingya
 * @email: lijingya@91118.com
 * @createDate: 2018/5/25
 * @company: 杭州天音
 */
public class NotifyCationActivity extends Activity implements View.OnClickListener {

    private Button btnNotify, btnSysNotify;
    private final int requestCode = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        btnNotify = findViewById(R.id.btn_notify);
        btnSysNotify = findViewById(R.id.btn_sys_notify);
        btnNotify.setOnClickListener(this);
        btnSysNotify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_notify:
                NotificationCompat.Builder builder1 = new NotificationCompat.Builder(this, Notification.CATEGORY_PROMO)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setTicker("helloWord")
                        .setWhen(System.currentTimeMillis());
                Intent mIntent = new Intent(this, NotifyDetailActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                Notification notification = builder1.build();
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_define_notify);
                remoteViews.setTextViewText(R.id.tv_title, "nihao");
                remoteViews.setTextViewText(R.id.tv_content, "nihao");
                remoteViews.setOnClickPendingIntent(R.id.open, pendingIntent);
                notification.contentView = remoteViews;
                notification.contentIntent = pendingIntent;
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1, notification);
                break;
            case R.id.btn_sys_notify:
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Notification.CATEGORY_PROMO)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setTicker("helloWord")
                        .setWhen(System.currentTimeMillis());
                Intent mIntent1 = new Intent(this, NotifyDetailActivity.class);
                PendingIntent pendingIntent1 = PendingIntent.getActivity(this, requestCode, mIntent1, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent1);
                Notification notification1 = builder.build();
                NotificationManager notificationManager1 = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager1.notify(1, notification1);
                break;
        }
    }
}

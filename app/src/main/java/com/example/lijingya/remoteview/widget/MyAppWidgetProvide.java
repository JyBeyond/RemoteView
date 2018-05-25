package com.example.lijingya.remoteview.widget;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.lijingya.remoteview.R;


/**
 * @description: this is MyAppWidgetProvide
 * @author: lijingya
 * @email: lijingya@91118.com
 * @createDate: 2018/5/25
 * @company: 杭州天音
 */
public class MyAppWidgetProvide extends AppWidgetProvider {

    public static final String CLICK_ACTION = "com.example.lijingya.remoteview";

    @Override
    public void onReceive(final Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction() == CLICK_ACTION) {
            Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap srcbBit = BitmapFactory.decodeResource(context.getResources(), R.mipmap.bg_clear);
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                    for (int i = 0; i < 37; i++) {
                        float degree = (i * 10) % 360;
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
                        remoteViews.setImageViewBitmap(R.id.imageView, rotateBitmap(context, srcbBit, degree));
                        Intent mIntent = new Intent();
                        mIntent.setAction(CLICK_ACTION);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, mIntent, 0);
                        remoteViews.setOnClickPendingIntent(R.id.imageView, pendingIntent);
                        appWidgetManager.updateAppWidget(new ComponentName(context, MyAppWidgetProvide.class), remoteViews);
                        SystemClock.sleep(300);
                    }
                }
            }).start();
        }
    }

    private Bitmap rotateBitmap(Context context, Bitmap srcbBit, float degree) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);
        Bitmap bitmap = Bitmap.createBitmap(srcbBit, 0, 0, srcbBit.getWidth(), srcbBit.getHeight(), matrix, true);
        return bitmap;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        int counter = appWidgetIds.length;
        for (int i = 0; i < counter; i++) {
            onWidgetUpdate(context, appWidgetManager, appWidgetIds[ i ]);
        }
    }

    private void onWidgetUpdate(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        Intent mIntent = new Intent();
        mIntent.setAction(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, mIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.imageView, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }
}

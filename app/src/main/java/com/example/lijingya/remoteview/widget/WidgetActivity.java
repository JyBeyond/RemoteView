package com.example.lijingya.remoteview.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @description: this is WidgetActivity
 * @author: lijingya
 * @email: lijingya@91118.com
 * @createDate: 2018/5/25
 * @company: 杭州天音
 */
public class WidgetActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent mIntent = new Intent(this, MyAppWidgetProvide.class);
        mIntent.setAction("com.example.lijingya.remoteview");
        sendBroadcast(mIntent);
    }
}

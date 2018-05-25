package com.example.lijingya.remoteview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lijingya.remoteview.notify.NotifyCationActivity;
import com.example.lijingya.remoteview.widget.WidgetActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button notify, widget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notify = findViewById(R.id.notify);
        notify.setOnClickListener(this);
        widget = findViewById(R.id.widget);
        widget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notify:
                startActivity(new Intent(this, NotifyCationActivity.class));
                break;
            case R.id.widget:
                startActivity(new Intent(this, WidgetActivity.class));
                break;
        }
    }
}

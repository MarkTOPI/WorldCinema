package com.example.worldcinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goFeed(View view) {
        Intent intent = new Intent(this, Feed.class);
        startActivity(intent);
        finish();
    }

    public void goReg(View view) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
        finish();
    }

}
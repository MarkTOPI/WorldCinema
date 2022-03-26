package com.example.worldcinema.newCollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.worldcinema.R;

public class newCollections extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_collections);
    }

    public void goBack(View view) {
        finish();
    }

    public void goIcon(View view) {

    }
}
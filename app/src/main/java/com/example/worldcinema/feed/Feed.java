package com.example.worldcinema.feed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.worldcinema.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Feed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,  R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}
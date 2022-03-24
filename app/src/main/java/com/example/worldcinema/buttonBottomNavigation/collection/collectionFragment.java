package com.example.worldcinema.buttonBottomNavigation.collection;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.worldcinema.R;

import com.example.worldcinema.newCollection.newCollections;

public class collectionFragment extends Fragment {

    private LinearLayout linearLayout;
    private ImageButton imageButton;

    public collectionFragment() {
        // Required empty public constructor
    }

    public static collectionFragment newInstance() {
        collectionFragment fragment = new collectionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        imageButton = view.findViewById(R.id.buttonPlus);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), newCollections.class));
            }
        });
        return view;
    }
}
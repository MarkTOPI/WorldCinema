package com.example.worldcinema.buttonBottomNavigation.profile;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.worldcinema.MainActivity;
import com.example.worldcinema.R;
import com.example.worldcinema.network.ProfileHandler;
import com.example.worldcinema.network.models.UserInfoResponse;
import com.example.worldcinema.network.service.ApiProfileService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {
    private SharedPreferences sharedPreferences;
    private String TAG = "Привет!";
    private String token;
    private Button button;
    ApiProfileService service = ProfileHandler.getInstance().getProfileService();
    TextView txtFirstName, txtLastName, txtEmail;
    ImageView imgAvatar;


    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getContext().getSharedPreferences("token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        txtFirstName = view.findViewById(R.id.userNameText);
        txtLastName = view.findViewById(R.id.userLastNameText);
        txtEmail = view.findViewById(R.id.userEmailText);
        view.findViewById(R.id.buttonExit).setOnClickListener(v -> {
            sharedPreferences= getContext().getSharedPreferences("token",Context.MODE_PRIVATE);
            sharedPreferences.edit().remove("token").commit();
            startActivity(new Intent(getContext(), MainActivity.class));
        });
        AsyncTask.execute(() -> {
            service.getData(token).enqueue(new Callback<List<UserInfoResponse>>() {
                @Override
                public void onResponse(Call<List<UserInfoResponse>> call, Response<List<UserInfoResponse>> response) {
                    txtFirstName.setText(response.body().get(0).getFirstName());
                    txtLastName.setText(response.body().get(0).getLastName());
                    txtEmail.setText(response.body().get(0).getEmail());
                }

                @Override
                public void onFailure(Call<List<UserInfoResponse>> call, Throwable t) {
                    Log.d(TAG, "onFailure: Что-то пошло не так");
                }
            });
        });

        return view;
    }
}
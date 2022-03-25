package com.example.worldcinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.worldcinema.feed.Feed;
import com.example.worldcinema.network.ApiHandler;
import com.example.worldcinema.network.ErrorUtils;
import com.example.worldcinema.network.models.LoginBody;
import com.example.worldcinema.network.models.LoginResponse;
import com.example.worldcinema.network.service.ApiService;
import com.example.worldcinema.registration.Registration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private String token;
    private static final String TAG = "MainActivity";
    EditText editEmail, editPassword;

    ApiService service = ApiHandler.getInstance().getService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editor = getSharedPreferences("token", MODE_PRIVATE).edit();
        preferences = getSharedPreferences("token", MODE_PRIVATE);
        token = preferences.getString("token", "");
        if(token != ""){
            goMainPage();
        }
        initializeViews();
    }

    private void initializeViews() {
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);

        findViewById(R.id.buttonAuth).setOnClickListener(view -> {
            goFeed();
        });
    }

    private void goFeed() {
        AsyncTask.execute(() -> {
            service.goFeed(getLoginData()).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Авторизация успешна! Твой токен: " + response.body().getToken(), Toast.LENGTH_LONG).show();
                        editor.putString("token", response.body().getToken()).apply();
                        goMainPage();
                    } else if (response.code() == 400) {
                        String serviceErrorMessage = ErrorUtils.parseError(response).message();
                        Toast.makeText(getApplicationContext(), serviceErrorMessage, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Произошла ошибка!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    public void goMainPage() {
        Intent intent = new Intent(MainActivity.this, Feed.class);
        startActivity(intent);
        finish();
    }

    private LoginBody getLoginData() {
        return new LoginBody(editEmail.getText().toString(), editPassword.getText().toString());
    }

    public void goReg(View view) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
        finish();
    }
}
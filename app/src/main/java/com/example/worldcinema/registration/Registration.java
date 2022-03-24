package com.example.worldcinema.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.worldcinema.MainActivity;
import com.example.worldcinema.R;
import com.example.worldcinema.network.ApiHandler;
import com.example.worldcinema.network.ErrorUtils;
import com.example.worldcinema.network.models.RegisterBody;
import com.example.worldcinema.network.models.RegistrationResponse;
import com.example.worldcinema.network.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {

    private static final String TAG = "Registration";

    EditText editName, editSurname, editEmail, editPassword;

    ApiService service = ApiHandler.getInstance().getService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initializeViews();
    }

    private void initializeViews() {
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editName = findViewById(R.id.editName);
        editSurname = findViewById(R.id.editSurname);

        findViewById(R.id.buttonRegistration).setOnClickListener(view -> {
            goRegistration();
        });
    }

    private void goRegistration() {
        AsyncTask.execute(() -> {
            service.goRegistration(getRegistrationData()).enqueue(new Callback<RegistrationResponse>() {
                @Override
                public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(Registration.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        Log.d(TAG, "onResponse: " + getApplicationContext());
                        Toast.makeText(getApplicationContext(), "Регистрация успешна!", Toast.LENGTH_LONG).show();
                    } else if (response.code() == 400) {
                        String serviceErrorMessage = ErrorUtils.parseError(response).message();
                        Toast.makeText(getApplicationContext(), serviceErrorMessage, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Произошла ошибка!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    private RegisterBody getRegistrationData() {
        return new RegisterBody(editEmail.getText().toString(), editPassword.getText().toString(), editName.getText().toString(), editSurname.getText().toString());
    }

    public void goLogin(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
package com.example.retrofitcrud;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//import net.simplifiedcoding.retrofitandroidtutorial.R;
//import net.simplifiedcoding.retrofitandroidtutorial.api.RetrofitClient;
//import net.simplifiedcoding.retrofitandroidtutorial.models.LoginResponse;
//import net.simplifiedcoding.retrofitandroidtutorial.storage.SharedPrefManager;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
}
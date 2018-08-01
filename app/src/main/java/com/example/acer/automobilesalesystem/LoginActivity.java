package com.example.acer.automobilesalesystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.automobilesalesystem.models.LoginResponseModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ViewPager viewPager;
    private Button button;
    private EditText etname;
    private EditText etpassword;
    private TextView tvRegisterLink;
    private ArrayList<LoginResponseModel> userList;
    private MySharedPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_login);
        //viewPager=(ViewPager)findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);


//-------------------------retrofit for Fetching JSON from URL----------------------------


        button = (Button) findViewById(R.id.blogin);
        etname = (EditText) findViewById(R.id.etname);
        etpassword = (EditText) findViewById(R.id.etpassword);
        tvRegisterLink = (TextView) findViewById(R.id.tvregister);
        preference = new MySharedPreference(this);
        if (preference.getBoolValues("isLoggedIn") == false) {

            getTempUsers();
            setupClickListeners();
        }else{
            openActivity2();
        }


    }

    private void getTempUsers() {
        ApiService apiService = RetrofitSingleton.getApiService();
        Call<ArrayList<LoginResponseModel>> call = apiService.getUserList();
        call.enqueue(new Callback<ArrayList<LoginResponseModel>>() {
            @Override
            public void onResponse(Call<ArrayList<LoginResponseModel>> call, Response<ArrayList<LoginResponseModel>> response) {
                if (response.isSuccessful()) {
                    userList = response.body();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<LoginResponseModel>> call, Throwable t) {

            }
        });
    }

    private void setupClickListeners() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean validFields = validateUser();
                if (validFields) {
                    preference.setKeyValues("isLoggedIn", true);
                    Intent firstPage = new Intent(LoginActivity.this, frontpageactivity.class);
                    startActivity(firstPage);


                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_LONG).show();
                    etpassword.setError("Invalid Password");
                }
            }
        });

        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(registerIntent);
            }
        });
    }

    private boolean validateUser() {
        boolean notFound = false;
        if (!TextUtils.isEmpty(etname.getText().toString().trim()) && !TextUtils.isEmpty(etpassword.getText().toString().trim())) {
            for (LoginResponseModel users : this.userList) {
                if (users.username.equalsIgnoreCase(etname.getText().toString().trim())) {
                    if (users.username.equalsIgnoreCase(etpassword.getText().toString().trim())) {
                        notFound = true;
                    }
                }
            }
        }
        return notFound;
    }

    public void openActivity2() {
        Intent intent = new Intent(LoginActivity.this, frontpageactivity.class);
        startActivity(intent);
    }
}

package com.example.acer.automobilesalesystem;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    ViewPager viewPager;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


       setContentView(R.layout.activity_login);
        //viewPager=(ViewPager)findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(this);




        button = (Button) findViewById(R.id.blogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });


        final EditText etname=(EditText) findViewById(R.id.etname);
        final EditText etpassword=(EditText) findViewById(R.id.etpassword);

        final Button blogin=(Button) findViewById(R.id.blogin);
        final TextView RegisterLink=(TextView) findViewById(R.id.tvregister);

        RegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent=new Intent(LoginActivity.this,SignupActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
    }
    public void openActivity2(){
        Intent intent=new Intent(LoginActivity.this,frontpageactivity.class);
        startActivity(intent);
    }
}

package com.example.acer.automobilesalesystem;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_signup);
        final EditText etname=(EditText) findViewById(R.id.etname);
        final EditText etemail=(EditText) findViewById(R.id.etemail);
        final EditText etpassword=(EditText) findViewById(R.id.etpassword);
        final EditText etconfirmpassword=(EditText) findViewById(R.id.etconfirmpassword);
        final EditText etnumber=(EditText) findViewById(R.id.etnumber);


        final Button bregister=(Button) findViewById(R.id.bregister);

        final TextView LoginLink=(TextView) findViewById(R.id.tvlogin);

        LoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent=new Intent(SignupActivity.this,LoginActivity.class);
                SignupActivity.this.startActivity(loginIntent);
            }
        });
    }
}

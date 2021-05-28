package com.example.tunisairapp.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.tunisairapp.R;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    Button btnValidLogin;
    /*ImageView logo_img;
    TextView logo_title, logo_subtitle;*/
    private static final Pattern PASSWORED_Pattern = Pattern.compile("^" + ".{4,}" +"$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*logo_img = findViewById(R.id.app_logo);
        logo_title = findViewById(R.id.logo_name);
        logo_subtitle = findViewById(R.id.slogan_name);*/

        btnValidLogin = findViewById(R.id.btn_ValidLogin);
        btnValidLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }

    public boolean checkEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();

        //return Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }
    public boolean chekpassword(String password){
        if(PASSWORED_Pattern.matcher(password).matches())
        {
            return true;
        }
        else return false;
    }
}
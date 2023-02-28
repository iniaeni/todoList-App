package com.example.aproditeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreenActivity extends AppCompatActivity {

    EditText mail,password;
    CheckBox remember;
    Button btn_login;
    SharedPreferences sharedPreferences;
    boolean isRemembered = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        mail = findViewById(R.id.yourmail);
        password =findViewById(R.id.yourpw);
        remember =findViewById(R.id.CheckBox);
        btn_login = findViewById(R.id.btn_save);

        sharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE);

        isRemembered = sharedPreferences.getBoolean("CHECKBOX", false);
        //value is true we move dashboard
        if (isRemembered){
            Intent intent = new Intent(LoginScreenActivity.this, DashboardScreenActivity.class);
            startActivity(intent);
            finish();
        }

       btn_login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(mail.getText().toString().length()==0){
                   mail.setError("Masukan Email Terlebih dahulu !");
               }else if(password.getText().toString().length()==0){
                   password.setError("Masukan Password !");
               }else{

                   String email = mail.getText().toString();
                   String pw = password.getText().toString();
                   boolean cheked = remember.isChecked();

                   SharedPreferences.Editor editor = sharedPreferences.edit();
                   editor.putString("EMAIL", email);
                   editor.putString("PASSWORD", pw);
                   editor.putBoolean("CHECKBOX", cheked);
                   editor.apply();

                   Toast.makeText(LoginScreenActivity.this, "Berhasil Login",Toast.LENGTH_SHORT).show();

                   Intent intent = new Intent(LoginScreenActivity.this, HomeScreenFragment.class);
                   startActivity(intent);
                   finish();
               }

           }
       });
    }
}
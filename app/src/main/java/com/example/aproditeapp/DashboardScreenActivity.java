package com.example.aproditeapp;

import static com.example.aproditeapp.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aproditeapp.databinding.ActivityDashboardScreenBinding;



public abstract class DashboardScreenActivity extends AppCompatActivity{

    ActivityDashboardScreenBinding binding;

    TextView aMail;
    Button btn_logout;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeScreenFragment());

        binding.bottomNav.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case id.home:
                    replaceFragment(new HomeScreenFragment());
                    break;
                case id.list:
                    replaceFragment(new ListScreenFragment());
                    break;
                case id.profile:
                    replaceFragment(new ProfileScreenFragment());
                    break;
            }



            return true;
        });





        aMail = findViewById(id.email_user);
        btn_logout = findViewById(id.logout);

        preferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE);

        String email = preferences.getString("EMAIL", "");
        aMail.setText(email);

//logout to login
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(DashboardScreenActivity.this, LoginScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();



    }

}
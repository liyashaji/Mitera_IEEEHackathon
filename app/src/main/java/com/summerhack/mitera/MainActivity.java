package com.summerhack.mitera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.summerhack.mitera.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Button buLogin;
    FirebaseAuth mAuth;
    EditText etUsername,etPass;
    TextView createAcc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        buLogin = binding.buLogin;
        etUsername = binding.etUserName;
        etPass  = binding.etPassword;
        buLogin.setOnClickListener(v -> {
            mAuth.signInWithEmailAndPassword(etUsername.getText().toString(),etPass.getText().toString())
                            .addOnSuccessListener(authResult -> {
                                startActivity(new Intent(this,HomeActivity.class));
                                finish();

                            });

        });
        createAcc = binding.createAcc;
        createAcc.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), NewUserActivity.class));
            finish();
        });
    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

        FirebaseUser currentUser = mAuth.getCurrentUser();
       if(currentUser!=null){
           startActivity(new Intent(this,HomeActivity.class));
           finish();
       }
    }
}
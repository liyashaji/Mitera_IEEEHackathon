package com.summerhack.mitera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.summerhack.mitera.Model.User;
import com.summerhack.mitera.databinding.ActivityNewUserBinding;

import java.util.UUID;

public class NewUserActivity extends AppCompatActivity {
    ActivityNewUserBinding binding;
    private String name,phone,email,address,age,pass;
    private String id;
    FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buCreate.setOnClickListener(v -> {
            name = binding.etnameCr.getText().toString();
            phone = binding.etphoneCr.getText().toString();
            address = binding.etaddressCr.getText().toString();
            email = binding.etemailCr.getText().toString();
            age = binding.etAge.getText().toString();
            pass = binding.etpassCr.getText().toString();
            mauth = FirebaseAuth.getInstance();
            mauth
                    .createUserWithEmailAndPassword(email,pass)
                    .addOnSuccessListener(authResult -> {
                        mauth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(
                                succes->{

                                    FirebaseUser currentUser = succes.getUser();

                                    id = currentUser.getUid();
                                    User newuser = new User(name,phone,address,email,id,age);
                                    FirebaseDatabase.getInstance().getReference("Users").child(id).setValue(newuser)
                                            .addOnSuccessListener(unused -> {
                                                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                                finish();
                                            });
                                }
                        );

                    });
        });
    }
}
package com.summerhack.mitera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.summerhack.mitera.Model.User;
import com.summerhack.mitera.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    DatabaseReference dbref;
    FirebaseAuth mauth;
    User curuser;
    Button busym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mauth = FirebaseAuth.getInstance();
        dbref = FirebaseDatabase.getInstance().getReference("Users")
                .child(mauth.getCurrentUser().getUid().toString());
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               curuser = snapshot.getValue(User.class);
               populate();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"error loading database",Toast.LENGTH_SHORT).show();
            }
        });
        busym = binding.busym;
        busym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SymptomsActivity.class));
            }
        });

    }

    public  void populate(){
        binding.tvname.setText(curuser.getName());
        binding.tvAge.setText(curuser.getAge());
        binding.tvEmail.setText(curuser.getEmail());
        binding.tvPhone.setText(curuser.getPhone());
        binding.tvAddress.setText(curuser.getAddress());
    }
}
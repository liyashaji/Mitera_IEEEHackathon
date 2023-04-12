package com.summerhack.mitera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.summerhack.mitera.databinding.ActivitySymptomsBinding;

public class SymptomsActivity extends AppCompatActivity {
    ActivitySymptomsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding  = ActivitySymptomsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}
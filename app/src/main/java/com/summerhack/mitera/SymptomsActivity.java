package com.summerhack.mitera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.summerhack.mitera.Adapters.ItemAdapter;
import com.summerhack.mitera.Model.Symptom;
import com.summerhack.mitera.databinding.ActivitySymptomsBinding;

import java.util.ArrayList;
import java.util.List;

public class SymptomsActivity extends AppCompatActivity {
    ActivitySymptomsBinding binding;
    ItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding  = ActivitySymptomsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        List<Symptom> listsym = new ArrayList<>();
        listsym.add(new Symptom("aa","desdd","ds"));

        adapter  = new ItemAdapter(listsym,getApplicationContext());
        binding.rvSym.setAdapter(adapter);
        binding.rvSym.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

       adapter.setOnDeleteItemClickListener(new ItemAdapter.OnDeleteItemClickListener() {


           @Override
            public void onDeleteItemClick(int position) {
                // Remove the item from the list at the clicked position
                listsym.remove(position);

                // Notify the adapter that the item has been removed
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, listsym.size());
            }
        });

    }
}
package com.summerhack.mitera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.summerhack.mitera.Adapters.ItemAdapter;
import com.summerhack.mitera.Model.Symptom;
import com.summerhack.mitera.databinding.ActivitySymptomsBinding;

import java.util.ArrayList;
import java.util.List;

public class SymptomsActivity extends AppCompatActivity {
    ActivitySymptomsBinding binding;
    List<Symptom> listsym;
    ItemAdapter adapter;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding  = ActivitySymptomsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        listsym = new ArrayList<>();
        mauth = FirebaseAuth.getInstance();

       FirebaseDatabase.getInstance().getReference("Symptoms")
                .child(mauth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {

                        for(DataSnapshot data:dataSnapshot.getChildren()){
                            Symptom symptom = data.getValue(Symptom.class);
                            symptom.setUid(data.getKey());
                            listsym.add(symptom);
                        }
                                           }
                }).addOnFailureListener(
                        task-> {
                            Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                        });

        adapter  = new ItemAdapter(listsym,getApplicationContext());
        binding.rvSym.setAdapter(adapter);
        binding.rvSym.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.bureload.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listsym.clear();
                        FirebaseDatabase.getInstance().getReference("Symptoms")
                                .child(mauth.getCurrentUser().getUid()).get()
                                .addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                                    @Override
                                    public void onSuccess(DataSnapshot dataSnapshot) {

                                        for(DataSnapshot data:dataSnapshot.getChildren()){
                                            Symptom symptom = data.getValue(Symptom.class);
                                            symptom.setUid(data.getKey());
                                            listsym.add(symptom);
                                        }
                                        adapter.notifyDataSetChanged();
                                    }
                                }).addOnFailureListener(
                                        task-> {
                                            Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                                        });
                    }
                }
        );
       adapter.setOnDeleteItemClickListener(new ItemAdapter.OnDeleteItemClickListener() {


           @Override
            public void onDeleteItemClick(int position) {
                // Remove the item from the list at the clicked position
                //listsym.remove(position);
                FirebaseDatabase.getInstance()
                        .getReference("Symptoms").child(mauth.getCurrentUser().getUid())
                                .child(listsym.get(position).getUid()).removeValue().addOnSuccessListener(
                                        complete->{
                                            Toast.makeText(getApplicationContext(),"please refresh",Toast.LENGTH_SHORT).show();
                                        }
                        );
                // Notify the adapter that the item has been removed
                //adapter.notifyItemRemoved(position);
               // adapter.notifyItemRangeChanged(0, listsym.size());
            }
        });
        FloatingActionButton newbu = binding.bunewAction;
        newbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new NewUserFragment().show(getSupportFragmentManager(),"popup");
            }
        });
    }
}
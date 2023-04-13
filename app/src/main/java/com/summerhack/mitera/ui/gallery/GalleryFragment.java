package com.summerhack.mitera.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.summerhack.mitera.Adapters.MyAdapter;
import com.summerhack.mitera.MainActivity;
import com.summerhack.mitera.Model.Chat;
import com.summerhack.mitera.databinding.FragmentGalleryBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    FirebaseAuth mauth;
    EditText inputmessage;
    AppCompatImageView busend;
    List<Chat> chats;
    MyAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chats = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Chat")
                .get().addOnSuccessListener(
                        dataSnapshot -> {
                            for (DataSnapshot data: dataSnapshot.getChildren()
                            ) {
                                Chat chat = data.getValue(Chat.class);
                                System.out.println(chat.toString());
                                Log.d("fetch",chat.toString());
                                chats.add(chat);
                            }
                        }
                ).addOnFailureListener(test-> {

                    Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
                });

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mauth = FirebaseAuth.getInstance();

        inputmessage = binding.inputMessage;
        busend = binding.busend;
        setListener();
        //final TextView textView = binding.textGallery;
        //galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

         adapter= new MyAdapter(chats,getContext(),mauth.getCurrentUser());
        binding.chatRecyclerView.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
        binding.chatRecyclerView.setAdapter(adapter);


        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setListener(){
        busend.setOnClickListener(
                new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {
                        String msg = inputmessage.getText().toString();
                        Chat chat = new Chat(msg, Calendar.getInstance().getTime(),mauth.getCurrentUser().getUid());
                        chats.add(chat);
                        adapter.notifyItemChanged(chats.size()-1);
                        if(!msg.equals("")){
                            FirebaseDatabase.getInstance()
                                    .getReference("Chat")
                                    .push().setValue(
                                            chat
                                    ).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            inputmessage.setText("");
                                        }
                                    });
                        }
                    }
                }
        );
    }
}
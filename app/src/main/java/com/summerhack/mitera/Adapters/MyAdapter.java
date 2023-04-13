package com.summerhack.mitera.Adapters;



import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.summerhack.mitera.Model.Chat;
import com.summerhack.mitera.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Chat> items;
    private Context context;
    private String id;
    FirebaseUser currentUser;

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(FirebaseUser currentUser) {
        this.currentUser = currentUser;
    }

    public void setId(){
        this.id = id;
    }

    public MyAdapter(List<Chat> items, Context context,FirebaseUser currentUser) {
        this.items = items;
        this.context = context;
        this.currentUser = currentUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_container_sent_message, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chat item = items.get(position);
        holder.titleTextView.setText(item.getId());
        holder.descTextView.setText(item.getMsg());
        if(item.getId().equals(currentUser.getUid())){
            holder.itemView.setPadding(100,0,0,0);

        }

        else {
            holder.itemView.setPadding(0,0,0,0);

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tvNameChat);
            descTextView = itemView.findViewById(R.id.tvNameMsg);
        }
    }
}

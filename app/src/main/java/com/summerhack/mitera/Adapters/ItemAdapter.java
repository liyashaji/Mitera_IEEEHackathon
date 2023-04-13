package com.summerhack.mitera.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.summerhack.mitera.Model.Symptom;
import com.summerhack.mitera.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Symptom> itemList;
    private Context context;
    private OnDeleteItemClickListener onDeleteItemClickListener;

    public ItemAdapter(List<Symptom> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    public void setOnDeleteItemClickListener(OnDeleteItemClickListener onDeleteItemClickListener) {
        this.onDeleteItemClickListener = onDeleteItemClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.symptom_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Symptom item = itemList.get(position);
        holder.titleTextView.setText(item.getTitle());
        holder.descTextView.setText(item.getDesc());
        holder.deleteButton.setOnClickListener(v -> {
            if (onDeleteItemClickListener != null) {
                onDeleteItemClickListener.onDeleteItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descTextView;
        Button deleteButton;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.sym_title);
            descTextView = itemView.findViewById(R.id.sym_desc);
            deleteButton = itemView.findViewById(R.id.busymdel);
        }
    }

    public interface OnDeleteItemClickListener {
        void onDeleteItemClick(int position);
    }
}

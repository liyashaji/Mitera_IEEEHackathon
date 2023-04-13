package com.summerhack.mitera.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.summerhack.mitera.Model.SC;
import com.summerhack.mitera.R;
import com.summerhack.mitera.WaitFragment;

import java.util.List;

public class SelfAdapter extends RecyclerView.Adapter<SelfAdapter.ViewHolder> {

    private List<SC> items;
    private Context context;

    public SelfAdapter(List<SC> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.selfcareitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SC item = items.get(position);
        holder.titleTextView.setText(item.getName());
        holder.descTextView.setText(item.getDesc());
        holder.cons.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"more content soon!",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descTextView;
        ConstraintLayout cons;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tvselfcarename);
            descTextView = itemView.findViewById(R.id.tvselfcaredesc);
            cons = itemView.findViewById(R.id.consselfcare);
        }
    }
}

package com.example.seedlinghabittracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{
    private ArrayList<StreakItem> streakList;
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public ImageView streakImage;
        public TextView streakTitle;
        public TextView streakLength;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            streakImage = itemView.findViewById(R.id.streakImage);
            streakTitle = itemView.findViewById(R.id.streakTitle);
            streakLength = itemView.findViewById(R.id.streakLength);
        }
    }
    public RecyclerViewAdapter(ArrayList<StreakItem> exampleList)
    {
        streakList = exampleList;
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.streakcardview, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        StreakItem currentItem = streakList.get(position);
        holder.streakImage.setImageResource(currentItem.getImageResource());
        holder.streakLength.setText(currentItem.getHabitLength());
        holder.streakTitle.setText(currentItem.getHabitTitle());
    }

    @Override
    public int getItemCount() {
        return streakList.size();
    }
}

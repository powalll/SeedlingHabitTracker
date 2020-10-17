package com.example.seedlinghabittracker;

import android.content.Context;
import android.content.Intent;
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
    private sendInfo mSendInfo;
    public interface sendInfo{
        void delete(int position);
    }
    public void setSendInfo(sendInfo sendInfo)
    {
        mSendInfo = sendInfo;
    }
    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView streakImage;
        public ImageView streakDelete;
        public TextView streakTitle;
        public TextView streakLength;
        public ImageView streakCountImage;
        public TextView streakCount;

        public RecyclerViewHolder(@NonNull View itemView, final sendInfo SendInfo) {
            super(itemView);
            streakImage = itemView.findViewById(R.id.streakImage);
            streakTitle = itemView.findViewById(R.id.streakTitle);
            streakLength = itemView.findViewById(R.id.streakLength);
            streakDelete = itemView.findViewById(R.id.streakDelete);
            streakCountImage = itemView.findViewById(R.id.streakCountImage);
            streakCount = itemView.findViewById(R.id.streakCount);
            streakDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(SendInfo != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            SendInfo.delete(position);
                        }
                    }
                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent DetailIntent = new Intent(view.getContext(), DetailActivity.class);
            DetailIntent.putExtra("DetailTitle", streakTitle.getText().toString());
            DetailIntent.putExtra("DetailStreakCount", streakLength.getText().toString());
            view.getContext().startActivity(DetailIntent);
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
        return new RecyclerViewHolder(v, mSendInfo);
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

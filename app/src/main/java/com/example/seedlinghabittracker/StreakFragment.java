package com.example.seedlinghabittracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StreakFragment extends Fragment {
    private RecyclerView streakRecyclerView;
    private RecyclerView.Adapter streakRecyclerViewAdapter;
    private RecyclerView.LayoutManager streakRecyclerViewLayoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_streak, container, false);
        //streak recyclerview code
        ArrayList<StreakItem> streakItems = new ArrayList<>();
        streakItems.add(new StreakItem(R.drawable.ic__home_24, "Title","One Day"));
        streakRecyclerView = view.findViewById(R.id.streakRecyclerView);
        streakRecyclerView.setHasFixedSize(true);
        streakRecyclerViewLayoutManager = new LinearLayoutManager(view.getContext());
        streakRecyclerViewAdapter = new RecyclerViewAdapter(streakItems);

        streakRecyclerView.setLayoutManager(streakRecyclerViewLayoutManager);
        streakRecyclerView.setAdapter(streakRecyclerViewAdapter);
        return view;
    }
}

package com.example.seedlinghabittracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StreakFragment extends Fragment implements AddStreakDialogue.onInputSelected{
    private RecyclerView streakRecyclerView;
    private RecyclerView.Adapter streakRecyclerViewAdapter;
    private RecyclerView.LayoutManager streakRecyclerViewLayoutManager;
    private FloatingActionButton addStreakButton;
    private ArrayList<StreakItem> streakItems;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_streak, container, false);
        //streak recyclerview code
        streakItems = new ArrayList<>();
        streakItems.add(new StreakItem(R.drawable.ic__home_24, "Title","One Day"));
        streakRecyclerView = view.findViewById(R.id.streakRecyclerView);
        streakRecyclerView.setHasFixedSize(true);
        streakRecyclerViewLayoutManager = new LinearLayoutManager(view.getContext());
        streakRecyclerViewAdapter = new RecyclerViewAdapter(streakItems);

        streakRecyclerView.setLayoutManager(streakRecyclerViewLayoutManager);
        streakRecyclerView.setAdapter(streakRecyclerViewAdapter);
        //FAB code
        addStreakButton = (FloatingActionButton) view.findViewById(R.id.addStreakButton);
        addStreakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
                //dialog fragment -> fragment code
                getDialogueFragmentInfo();
            }
        });

        return view;
    }

    public void openDialog()
    {
        AddStreakDialogue addStreakDialogue = new AddStreakDialogue();
        addStreakDialogue.show(getFragmentManager(), "add streak dialogue");
        addStreakDialogue.dismiss();
    }
    public void getDialogueFragmentInfo()
    {
        AddStreakDialogue dialog = new AddStreakDialogue();
        dialog.setTargetFragment(this, 0);
        dialog.show(getFragmentManager(), "myCustomDialog");
    }

    @Override
    public void sendInput(String input, String input2) {
        streakItems.add(new StreakItem(R.drawable.ic_plus_sign_24, input, input2));
        streakRecyclerViewAdapter.notifyDataSetChanged();
    }
}

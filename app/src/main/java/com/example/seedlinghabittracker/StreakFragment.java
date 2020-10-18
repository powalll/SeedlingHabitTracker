package com.example.seedlinghabittracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class StreakFragment extends Fragment implements AddStreakDialogue.onInputSelected {
    private RecyclerView streakRecyclerView;
    private RecyclerViewAdapter streakRecyclerViewAdapter;
    private RecyclerView.LayoutManager streakRecyclerViewLayoutManager;
    private FloatingActionButton addStreakButton;
    private ArrayList<StreakItem> streakItems;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_streak, container, false);
        //streak recyclerview code
        loadData();
        streakRecyclerView = view.findViewById(R.id.streakRecyclerView);
        streakRecyclerViewLayoutManager = new LinearLayoutManager(view.getContext());
        setStreakRecyclerView();
        //FAB code
        addStreakButton = (FloatingActionButton) view.findViewById(R.id.addStreakButton);
        createFABClick();
        //Item touch helper
        createItemTouchHelper();

        return view;
    }

    private void saveData()
    {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(streakItems);
        editor.putString("streakItems", json);
        editor.apply();
    }

    private void loadData()
    {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("streakItems", null);
        Type type = new TypeToken<ArrayList<StreakItem>>() {}.getType();
        streakItems = gson.fromJson(json, type);
        if(streakItems == null)
        {
            streakItems = new ArrayList<>();
        }
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
        streakItems.add(new StreakItem(R.drawable.ic_plus_sign_24, input, input2, 0));
        streakRecyclerViewAdapter.notifyDataSetChanged();
        saveData();
    }

    public void createFABClick()
    {
        addStreakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
                //dialog fragment -> fragment code
                getDialogueFragmentInfo();
            }
        });
    }

    public void setStreakRecyclerView()
    {
        streakRecyclerView.setHasFixedSize(true);
        streakRecyclerViewAdapter = new RecyclerViewAdapter(streakItems);
        streakRecyclerView.setLayoutManager(streakRecyclerViewLayoutManager);
        streakRecyclerView.setAdapter(streakRecyclerViewAdapter);
        streakRecyclerViewAdapter.setSendInfo(new RecyclerViewAdapter.sendInfo(){
            @Override
            public void delete(int position) {
                streakItems.remove(position);
                streakRecyclerViewAdapter.notifyItemRemoved(position);
                saveData();
            }
        });
    }

    public void createItemTouchHelper()
    {
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT, 0) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(streakItems, from, to);
                streakRecyclerViewAdapter.notifyItemMoved(from, to);
                saveData();
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        helper.attachToRecyclerView(streakRecyclerView);
    }

}

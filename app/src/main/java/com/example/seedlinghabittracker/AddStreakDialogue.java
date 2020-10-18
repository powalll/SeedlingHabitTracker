package com.example.seedlinghabittracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddStreakDialogue extends AppCompatDialogFragment{
    private EditText habitName;
    private EditText habitFrequency;
    private TextView radioGroupTextView;
    private RadioGroup radioGroup;
    private RadioButton dailyButton;
    private RadioButton everyOtherDayButton;
    private RadioButton weeklyButton;
    public interface onInputSelected{
        void sendInput(String name, String frequency);
    }
    public static final String TAG = "myCustomDialog";
    public onInputSelected inputSelected;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.addstreakdialogue, null);
        habitName = view.findViewById(R.id.enterHabitNameEditText);
        dailyButton = view.findViewById(R.id.dailyRadiobutton);
        everyOtherDayButton = view.findViewById(R.id.everyOtherDayRadiobutton);
        weeklyButton = view.findViewById(R.id.WeeklyRadiobutton);
        builder.setView(view)
                .setTitle("Create A New Habit")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sHabitName = habitName.getText().toString();
                        String sHabitFrequency;
                        if(dailyButton.isChecked())
                        {
                            sHabitFrequency = "Daily";
                        }
                        else if(everyOtherDayButton.isChecked())
                        {
                            sHabitFrequency = "Every other day";
                        }
                        else
                        {
                            sHabitFrequency = "Weekly";
                        }
                        try {
                            onInputSelected onInputSelected = (AddStreakDialogue.onInputSelected) getTargetFragment();
                            onInputSelected.sendInput(sHabitName, sHabitFrequency);
                        }catch(ClassCastException e)
                        {
                            Log.d("hi","ht" );
                        }
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            inputSelected = (onInputSelected) getTargetFragment();
        } catch(ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage());
        }
    }
}

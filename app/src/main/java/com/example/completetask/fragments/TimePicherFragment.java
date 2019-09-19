package com.example.completetask.fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.example.completetask.R;

import java.sql.Time;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimePicherFragment extends DiaLogFragment {
    private final String EXTRA_TASK_TIME = "extratasktime";
    private TimePicker mTimePicker;

    public static TimePicherFragment newInstance() {

        Bundle args = new Bundle();

        TimePicherFragment fragment = new TimePicherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public String getEXTRA_TASK_TIME() {
        return EXTRA_TASK_TIME;
    }

    public TimePicherFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_time_picher, null, false);
        mTimePicker = view.findViewById(R.id.time_picher);
        return new AlertDialog.Builder(getActivity())
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String time = extractTime();
                        sendResult(time);
                    }
                })
                .setNegativeButton(R.string.cancle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setView(view)
                .create();
    }

    private String extractTime() {
        int hour = mTimePicker.getCurrentHour();
        int minute = mTimePicker.getCurrentMinute();
        String timeString = String.format("%02d/%02d", hour, minute);
        return timeString;
    }

    private void sendResult(String time) {
        Fragment fragment = getTargetFragment();
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TASK_TIME, time);
        fragment.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }

}

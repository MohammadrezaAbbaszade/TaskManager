package com.example.completetask.fragments;


import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;

import com.example.completetask.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DiaLogFragment {
    private final String EXTRA_TASK_DATE = "com.example.completetask.fragments.task date";
    private DatePicker mDatePicker;
    private Date mDate;

    public static DatePickerFragment newInstance() {

        Bundle args = new Bundle();

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public String getEXTRA_TASK_DATE() {
        return EXTRA_TASK_DATE;
    }

    public DatePickerFragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_date_picker, null, false);
       mDatePicker=view.findViewById(R.id.datepicker);
        return new AlertDialog.Builder(getActivity())
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String date = extractDate();
                        sendResult(date);
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

    private String extractDate() {
        int year = mDatePicker.getYear();
        int monthOfYear = mDatePicker.getMonth();
        int dayOfMonth = mDatePicker.getDayOfMonth();
        Date date=new Date(year,monthOfYear,dayOfMonth);
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy a" , Locale.US);
        String dateString =formatter.format(date);
        return dateString;
    }
    private void sendResult(String date) {
        Fragment fragment = getTargetFragment();
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TASK_DATE, date);
        fragment.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }


}

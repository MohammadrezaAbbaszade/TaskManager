package com.example.completetask.fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.completetask.R;
import com.example.completetask.model.Doing;
import com.example.completetask.model.Done;
import com.example.completetask.model.ToDo;
import com.google.android.material.button.MaterialButton;

import java.util.Date;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiaLogFragment extends DialogFragment {
    private static final String ID_OF_USER = "com.example.completetask.fragments.id of user";
    private static final String TITLE_AND_DISCRIPTION = "com.example.completetask.title and discription";
    private static final String DATE_FROM_DATEPICKER = "com.example.completetask.date from datepicker";
    private static final String FRAGMENTS_INDEX = "fragments index";
    private final String DATE_PICKER_FRAGMENT = "com.example.completetask.datepickerfragmenttag";
    private final int REQUEST_FOR_DATEPICKER = 0;
    private final String TIME_PICKER_TAG = "com.example.completetask.timepickertag";
    private final int REQUEST_FOR_TIMEPICKER = 1;
    private final String TIME_FROM_TIMEPICKER = "com.example.completetask.timefromtimepicker";
    private final String CHECKBOXES_STATE = "checkBoxesSstate";
    private EditText mTtitleEditText;
    private EditText mDiscriptionEditText;
    private Button mDateButton;
    private Button mTimeButton;
    private String date;
    private String time;
    private CheckBox mCheckBoxToDo;
    private CheckBox mCheckBoxDone;
    private CheckBox mCheckBoxDoing;
    int indexOfFragments;


    public static String getDateFromDatepicker() {
        return DATE_FROM_DATEPICKER;
    }

    public static String getTitleAndDiscription() {
        return TITLE_AND_DISCRIPTION;
    }

    public String getTIME_FROM_TIMEPICKER() {
        return TIME_FROM_TIMEPICKER;
    }

    public String getCHECKBOXES_STATE() {
        return CHECKBOXES_STATE;
    }

    public static DiaLogFragment newInstance() {

        Bundle args = new Bundle();
        DiaLogFragment fragment = new DiaLogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DiaLogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dia_log, null, false);
        init(view);
        mCheckBoxDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (mCheckBoxToDo.isChecked()) {
                    mCheckBoxToDo.setChecked(false);
                    mCheckBoxDone.setChecked(isChecked);
                } else if (mCheckBoxDoing.isChecked()) {
                    mCheckBoxDoing.setChecked(false);
                    mCheckBoxDone.setChecked(isChecked);
                }else {
                    mCheckBoxDone.setChecked(isChecked);
                }
            }
        });
        mCheckBoxDoing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (mCheckBoxDone.isChecked()) {
                        mCheckBoxDone.setChecked(false);
                        mCheckBoxDoing.setChecked(isChecked);
                    }else if(mCheckBoxToDo.isChecked())
                    {
                        mCheckBoxToDo.setChecked(false);
                        mCheckBoxDoing.setChecked(isChecked);
                    }else {
                        mCheckBoxDoing.setChecked(isChecked);
                    }
            }
        });
        mCheckBoxToDo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (mCheckBoxDone.isChecked()) {
                        mCheckBoxDone.setChecked(false);
                        mCheckBoxToDo.setChecked(isChecked);


                    }else if(mCheckBoxDoing.isChecked()) {
                        mCheckBoxDoing.setChecked(false);
                        mCheckBoxToDo.setChecked(isChecked);
                    }else {
                        mCheckBoxToDo.setChecked(isChecked);
                    }
            }
        });
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance();
                datePickerFragment.setTargetFragment(DiaLogFragment.this, REQUEST_FOR_DATEPICKER);
                datePickerFragment.show(getFragmentManager(), DATE_PICKER_FRAGMENT);
            }
        });
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePicherFragment timePicker = TimePicherFragment.newInstance();
                timePicker.setTargetFragment(DiaLogFragment.this, REQUEST_FOR_TIMEPICKER);
                timePicker.show(getFragmentManager(), TIME_PICKER_TAG);
            }
        });
        return new AlertDialog.Builder(getActivity())
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean[] taskState = {mCheckBoxDoing.isChecked(), mCheckBoxDone.isChecked(), mCheckBoxToDo.isChecked()};
                        String titleEditText = mTtitleEditText.getText().toString();
                        String discriptionEditText = mDiscriptionEditText.getText().toString();
                        if (titleEditText.equals("") || discriptionEditText.equals("") || date == null) {
                            Toast.makeText(getActivity(), "You Must Complete Eeach Field!", Toast.LENGTH_LONG).show();
                        } else {
                            String[] forExtra = {titleEditText, discriptionEditText};
                            if (!titleEditText.equals("") && !discriptionEditText.equals("") && date != null) {
                                sendResult(forExtra,taskState, date, time);
                            }
                        }
                    }
                })
                .setNegativeButton(R.string.cancle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                })
                .setView(view)
                .create();

    }

    private void init(View view) {
        mTtitleEditText = view.findViewById(R.id.dialog_title);
        mDiscriptionEditText = view.findViewById(R.id.dialog_discription);
        mDateButton = view.findViewById(R.id.dialog_date);
        mTimeButton = view.findViewById(R.id.dialog_time);
        mCheckBoxToDo = view.findViewById(R.id.dialog_checkbox_todo);
        mCheckBoxDoing = view.findViewById(R.id.dialog_checkbox_doing);
        mCheckBoxDone = view.findViewById(R.id.dialog_checkbox_done);

    }

    private void sendResult(String[] forExtra,boolean[] taskState, String date, String time) {
        Fragment fragment = getTargetFragment();
        Intent intent = new Intent();
        intent.putExtra(CHECKBOXES_STATE,taskState);
        intent.putExtra(TITLE_AND_DISCRIPTION, forExtra);
        intent.putExtra(DATE_FROM_DATEPICKER, date);
        intent.putExtra(TIME_FROM_TIMEPICKER, time);
        fragment.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == REQUEST_FOR_DATEPICKER) {
            date = data.getStringExtra(DatePickerFragment.newInstance().getEXTRA_TASK_DATE());
            mDateButton.setText(date);
        }
        if (requestCode == REQUEST_FOR_TIMEPICKER) {
            time = data.getStringExtra(TimePicherFragment.newInstance().getEXTRA_TASK_TIME());
            mTimeButton.setText(time);

        }
    }
}

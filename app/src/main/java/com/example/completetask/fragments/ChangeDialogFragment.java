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

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.completetask.R;
import com.example.completetask.activities.FragmentsMainActivity;
import com.example.completetask.model.Doing;
import com.example.completetask.model.DoingListsRepository;
import com.example.completetask.model.Done;
import com.example.completetask.model.DoneListsRepository;
import com.example.completetask.model.ToDo;
import com.example.completetask.model.ToDoListsRepository;

import java.util.Date;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeDialogFragment extends DiaLogFragment {
    private static final String TITLE_FROM_FRAGMENTS = "com.example.completetask.fragments.titleFromToDoFragment";
    private static final String DISCRIPTION_FROM_FRAGMENTS = "com.example.completetask.fragments.discriptionFromToDoFragment";
    private static final int REQUEST_FOR_DATEPICKER = 0;
    private static final String DATE_PICKER_FRAGMENT = "com.example.completetask.fragments.datepickerfragmenttag";
    private static final String DATE_FROM_DATEPICKER = "com.example.completetask.fragments.datefrompickerfragmenttag";
    private static final String TITLE_AND_DISCRIPTION_FROM_CHANGE_FRAGMENT = "com.example.completetask.fragments.titleanddiscriptionfromchangefragment";
    private static final String ID_OF_TASKS = "com.example.completetask.fragments.indexoffragments";
    private static final String USER_OF_USER = "com.example.completetask.fragments.usernamrofusr";
    private static final String INDEX_OF_FRAGMENTS = "com.example.completetask.fragments.index of fragments";
    private final String TIME_PICKER_TAG = "com.example.completetask.fragments.timepickertag";
    private static final String DATE = "com.example.completetask.fragments.date";
    private static final String TIME = "com.example.completetask.fragments.time";
    private final int REQUEST_FOR_TIMEPICKER = 1;
    private EditText mTtitleEditText;
    private EditText mDiscriptionEditText;
    private Button mDateButton;
    private Button mTimeButton;
    private Button mSaveButton;
    private Button mEditButton;
    private Button mDeleteButton;
    private CheckBox mCheckBoxToDo;
    private CheckBox mCheckBoxDone;
    private CheckBox mCheckBoxDoing;
    private String date;
    private String time;
    private ToDo mToDo;
    private Doing mDoing;
    private Done mDone;
    private int indexOfFragments;

    public static ChangeDialogFragment newInstance(String titleFromFragments, String discriptionFromFragments
            , String date, String time, String userOfUser, UUID idOfTasks, int indexOfFragments) {

        Bundle args = new Bundle();
        args.putString(TITLE_FROM_FRAGMENTS, titleFromFragments);
        args.putString(DISCRIPTION_FROM_FRAGMENTS, discriptionFromFragments);
        args.putSerializable(ID_OF_TASKS, idOfTasks);
        args.putString(USER_OF_USER, userOfUser);
        args.putInt(INDEX_OF_FRAGMENTS, indexOfFragments);
        args.putString(DATE, date);
        args.putString(TIME, time);
        ChangeDialogFragment fragment = new ChangeDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static String getDateFromDatepicker() {
        return DATE_FROM_DATEPICKER;
    }

    public static String getTitleAndDiscriptionFromChangeFragment() {
        return TITLE_AND_DISCRIPTION_FROM_CHANGE_FRAGMENT;
    }

    public ChangeDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        UUID idOfTasks = (UUID) getArguments().getSerializable(ID_OF_TASKS);
        date = getArguments().getString(DATE);
        time = getArguments().getString(TIME);
        indexOfFragments = getArguments().getInt(INDEX_OF_FRAGMENTS);
        if (indexOfFragments == 1) {
            mToDo = ToDoListsRepository.getInstance().getToDo(idOfTasks);
            mCheckBoxToDo.setChecked(mToDo.isToDo());
            mCheckBoxDoing.setChecked(mToDo.isDoing());
            mCheckBoxDone.setChecked(mToDo.isDone());
            mCheckBoxToDo.setEnabled(false);
            mCheckBoxDoing.setEnabled(false);
            mCheckBoxDone.setEnabled(false);
        } else if (indexOfFragments == 2) {
            mDone = DoneListsRepository.getInstance().getDone(idOfTasks);
            mCheckBoxToDo.setChecked(mDone.isToDo());
            mCheckBoxDoing.setChecked(mDone.isDoing());
            mCheckBoxDone.setChecked(mDone.isDone());
            mCheckBoxToDo.setEnabled(false);
            mCheckBoxDoing.setEnabled(false);
            mCheckBoxDone.setEnabled(false);
        } else {
            mDoing = DoingListsRepository.getInstance().getDoing(idOfTasks);
            mCheckBoxToDo.setChecked(mDoing.isToDo());
            mCheckBoxDoing.setChecked(mDoing.isDoing());
            mCheckBoxDone.setChecked(mDoing.isDone());
            mCheckBoxToDo.setEnabled(false);
            mCheckBoxDoing.setEnabled(false);
            mCheckBoxDone.setEnabled(false);
        }
        mTimeButton.setText(time);
        mDateButton.setText(date);
        mTtitleEditText.setText(getArguments().getString(TITLE_FROM_FRAGMENTS));
        mDiscriptionEditText.setText(getArguments().getString(DISCRIPTION_FROM_FRAGMENTS));
        mDateButton.setEnabled(false);
        mTtitleEditText.setEnabled(false);
        mDiscriptionEditText.setEnabled(false);
        mTimeButton.setEnabled(false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_change_dialog, null, false);
        init(view);
        final AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
        mTtitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (indexOfFragments == 1) {
                    mToDo.setTitle(charSequence.toString());
                } else if (indexOfFragments == 2) {
                    mDone.setTitle(charSequence.toString());
                } else {
                    mDoing.setTitle(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mDiscriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (indexOfFragments == 1) {
                    mToDo.setDiscriptin(charSequence.toString());
                } else if (indexOfFragments == 2) {
                    mDone.setDiscriptin(charSequence.toString());
                } else {
                    mDoing.setDiscriptin(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDateButton.setEnabled(true);
                mTtitleEditText.setEnabled(true);
                mDiscriptionEditText.setEnabled(true);
                mTimeButton.setEnabled(true);
                mCheckBoxToDo.setEnabled(true);
                mCheckBoxDoing.setEnabled(true);
                mCheckBoxDone.setEnabled(true);
            }
        });
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleEditText = mTtitleEditText.getText().toString();
                String discriptionEditText = mDiscriptionEditText.getText().toString();
                if (titleEditText.equals("") || discriptionEditText.equals("")) {
                    Toast.makeText(getActivity(), "You Must Complete Eeach Field!", Toast.LENGTH_LONG).show();
                } else {
                    setObjectsOfFragments();
                    sendResult();
                }

                alertDialog.dismiss();
            }
        });
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance();
                datePickerFragment.setTargetFragment(ChangeDialogFragment.this, REQUEST_FOR_DATEPICKER);
                datePickerFragment.show(getFragmentManager(), DATE_PICKER_FRAGMENT);
            }
        });
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indexOfFragments == 1) {
                    deleteReposiory(1);
                } else if (indexOfFragments == 2) {
                    deleteReposiory(2);
                } else {
                    deleteReposiory(3);
                }
                sendResult();
                alertDialog.dismiss();
            }
        });
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePicherFragment timePicker = TimePicherFragment.newInstance();
                timePicker.setTargetFragment(ChangeDialogFragment.this, REQUEST_FOR_TIMEPICKER);
                timePicker.show(getFragmentManager(), TIME_PICKER_TAG);
            }
        });
        mCheckBoxDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (indexOfFragments == 1) {
                    mToDo.setDone(isChecked);
                    if (mCheckBoxToDo.isChecked()){
                        mCheckBoxToDo.setChecked(false);

                    }else if(mCheckBoxDoing.isChecked())
                    {
                        mCheckBoxDoing.setChecked(false);
                    }
                    mCheckBoxDone.setChecked(isChecked);
                } else if (indexOfFragments == 2) {
                    mDone.setDone(isChecked);
                    if (mCheckBoxToDo.isChecked()){
                        mCheckBoxToDo.setChecked(false);

                    }else if(mCheckBoxDoing.isChecked())
                    {
                        mCheckBoxDoing.setChecked(false);
                    }
                    mCheckBoxDone.setChecked(isChecked);
                } else {
                    mDoing.setDone(isChecked);
                    if (mCheckBoxToDo.isChecked()){
                        mCheckBoxToDo.setChecked(false);

                    }else if(mCheckBoxDoing.isChecked())
                    {
                        mCheckBoxDoing.setChecked(false);
                    }
                    mCheckBoxDone.setChecked(isChecked);
                }
            }
        });
        mCheckBoxDoing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (indexOfFragments == 1) {
                    mToDo.setDoing(isChecked);
                    if (mCheckBoxDone.isChecked()) {
                        mCheckBoxDone.setChecked(false);
                    }else if(mCheckBoxToDo.isChecked())
                    {
                        mCheckBoxToDo.setChecked(false);
                    }
                   mCheckBoxDoing.setChecked(isChecked);
                } else if (indexOfFragments == 2) {
                    mDone.setDoing(isChecked);
                    if (mCheckBoxDone.isChecked()) {
                        mCheckBoxDone.setChecked(false);
                    }else if(mCheckBoxToDo.isChecked())
                    {
                        mCheckBoxToDo.setChecked(false);
                    }
                    mCheckBoxDoing.setChecked(isChecked);
                } else {
                    mDoing.setDone(isChecked);
                    if (mCheckBoxDone.isChecked()) {
                        mCheckBoxDone.setChecked(false);
                    }else if(mCheckBoxToDo.isChecked())
                    {
                        mCheckBoxToDo.setChecked(false);
                    }
                    mCheckBoxDoing.setChecked(isChecked);
                }
            }
        });
        mCheckBoxToDo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (indexOfFragments == 1) {
                    mToDo.setToDo(isChecked);
                    if (mCheckBoxDone.isChecked()) {
                        mCheckBoxDone.setChecked(false);

                    }else if(mCheckBoxDoing.isChecked())
                    {
                        mCheckBoxDoing.setChecked(false);
                    }
                    mCheckBoxToDo.setChecked(isChecked);
                } else if (indexOfFragments == 2) {
                    mDone.setToDo(isChecked);
                    if (mCheckBoxDone.isChecked()) {
                        mCheckBoxDone.setChecked(false);

                    }else if(mCheckBoxDoing.isChecked())
                    {
                        mCheckBoxDoing.setChecked(false);
                    }
                    mCheckBoxToDo.setChecked(isChecked);
                } else {
                    mDoing.setToDo(isChecked);
                    if (mCheckBoxDone.isChecked()) {
                        mCheckBoxDone.setChecked(false);

                    }else if(mCheckBoxDoing.isChecked())
                    {
                        mCheckBoxDoing.setChecked(false);
                    }
                    mCheckBoxToDo.setChecked(isChecked);
                }
            }
        });
        return alertDialog;
    }

    private void setObjectsOfFragments() {
        if (indexOfFragments == 1) {
            mToDo.setTime(time);
            mToDo.setDate(date);
            updateReposiory(1);
        } else if (indexOfFragments == 2) {
            mDone.setTime(time);
            mDone.setDate(date);
            updateReposiory(2);
        } else {
            mDoing.setTime(time);
            mDoing.setDate(date);
            updateReposiory(3);
        }
    }

    private void init(View view) {
        mTtitleEditText = view.findViewById(R.id.change_dialog_title);
        mDiscriptionEditText = view.findViewById(R.id.change_dialog_discription);
        mDateButton = view.findViewById(R.id.change_dialog_date);
        mTimeButton = view.findViewById(R.id.change_dialog_time);
        mSaveButton = view.findViewById(R.id.change_dialog_button_save);
        mEditButton = view.findViewById(R.id.change_dialog_button_edit);
        mDeleteButton = view.findViewById(R.id.change_dialog_button_delete);
        mCheckBoxToDo = view.findViewById(R.id.change_dialog_checkbox_todo);
        mCheckBoxDoing = view.findViewById(R.id.change_dialog_checkbox_doing);
        mCheckBoxDone = view.findViewById(R.id.change_dialog_checkbox_done);
    }

    private void sendResult() {
        Fragment fragment = getTargetFragment();
        Intent intent = new Intent();
        fragment.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }

    private void deleteReposiory(int indexOfFragments) {
        if (indexOfFragments == 1) {
            try {
                ToDoListsRepository.getInstance().deleteToDo(mToDo);
            } catch (Exception e) {
            }
        } else if (indexOfFragments == 2) {
            try {
                DoneListsRepository.getInstance().deleteDone(mDone);
            } catch (Exception e) {
            }
        } else {
            try {
                DoingListsRepository.getInstance().deleteDoing(mDoing);
            } catch (Exception e) {
            }
        }
    }

    private void updateReposiory(int indexOfFragments) {
        if (indexOfFragments == 1) {
            try {
                ToDoListsRepository.getInstance().updateToDo(mToDo);
            } catch (Exception e) {
            }
        } else if (indexOfFragments == 2) {
            try {
                DoneListsRepository.getInstance().updateDone(mDone);
            } catch (Exception e) {
            }
        } else {
            try {
                DoingListsRepository.getInstance().updateDoing(mDoing);
            } catch (Exception e) {
            }
        }
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




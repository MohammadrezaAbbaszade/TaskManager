package com.example.completetask.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.completetask.R;
import com.example.completetask.model.Doing;
import com.example.completetask.model.DoingListsRepository;
import com.example.completetask.model.Done;
import com.example.completetask.model.DoneListsRepository;
import com.example.completetask.model.ToDo;
import com.example.completetask.model.ToDoListsRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoneListFragment extends Fragment {
    private final String DIALOG_FRAGMENT_TAG = "com.example.completetask.fragments.dialogFragmentTag";
    private final int REQUEST_FOR_DIALOGFRAGMENT = 1;
    private FloatingActionButton mFloatingActionButton;
    private static final String USERNAME_OF_USER = "com.example.completetask.fragments.id of user";
    private final String CHANGE_DIALOG_FRAGMENT_TAG = "com.example.completetask.fragments.changedialogfragment";
    private final int REQUEST_CODE_FOR_CHANGE_FRAGMENT = 2;
    private List<Done> mDoneList;
    private Done mDone;
    private RecyclerView mRecyclerView;
    String userNameOfUser;
    private DoneAdaptor doneAdaptor;
    private TextView mEmptyText;
    public static DoneListFragment newInstance(String usernameOfUser) {

        Bundle args = new Bundle();
        args.putString(USERNAME_OF_USER, usernameOfUser);
        DoneListFragment fragment = new DoneListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DoneListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userNameOfUser = getArguments().getString(USERNAME_OF_USER);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_menu:
                ShowMsgDialog(getActivity());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_done_list, container, false);
        init(view);
        creatRecycler();
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLogFragment diaLogFragment = DiaLogFragment.newInstance();
                diaLogFragment.setTargetFragment(DoneListFragment.this, REQUEST_FOR_DIALOGFRAGMENT);
                diaLogFragment.show(getFragmentManager(), DIALOG_FRAGMENT_TAG);
            }
        });
        return view;
    }

    private class DoneHolder extends RecyclerView.ViewHolder {
        private TextView mItemTitleTextView;
        private TextView mItemDiscriptionTextView;
        private TextView mItemDateTextView;
        private TextView mItemShapeTextView;
        private Done mDone;

        public DoneHolder(@NonNull View itemView) {
            super(itemView);
            mItemTitleTextView = itemView.findViewById(R.id.item_title);
            mItemDiscriptionTextView = itemView.findViewById(R.id.item_discription);
            mItemDateTextView = itemView.findViewById(R.id.item_date);
            mItemShapeTextView = itemView.findViewById(R.id.item_shape_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ChangeDialogFragment changeDialogFragment = ChangeDialogFragment.newInstance(mDone.getTitle()
                            , mDone.getDiscriptin(), mDone.getDate(), mDone.getTime(), userNameOfUser, mDone.getUUID(), 2);
                    changeDialogFragment.setTargetFragment(DoneListFragment.this, REQUEST_CODE_FOR_CHANGE_FRAGMENT);
                    changeDialogFragment.show(getFragmentManager(), CHANGE_DIALOG_FRAGMENT_TAG);

                }
            });
        }

        public void bind(Done done) {
            Character shapeText=done.getTitle().charAt(0);
            String stringForShapeText=shapeText.toString();
            mItemTitleTextView.setText(done.getTitle());
            mItemDiscriptionTextView.setText(done.getDiscriptin());
            mItemShapeTextView.setText(stringForShapeText);
            mItemDateTextView.setText(done.getDate());
            mDone = done;
        }
    }

    private class DoneAdaptor extends RecyclerView.Adapter<DoneHolder> {
        private List<Done> mDones;

        public DoneAdaptor(List<Done> dones) {
            mDones = dones;
        }


        @NonNull
        @Override
        public DoneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_items, parent, false);
            DoneHolder doneHolder = new DoneHolder(view);
            return doneHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull DoneHolder holder, int position) {
            holder.bind(mDoneList.get(position));
        }

        @Override
        public int getItemCount() {
            if (mDoneList.size() != 0)
                return mDoneList.size();
            return 0;
        }
    }

    private void init(View view) {
        mFloatingActionButton = view.findViewById(R.id.done_fab);
        mRecyclerView = view.findViewById(R.id.done_recycler);
        mEmptyText = view.findViewById(R.id.empty_indonefragment);
    }

    private void creatRecycler() {
        mDoneList = DoneListsRepository.getInstance(getContext()).getDones(userNameOfUser);
        if (mDoneList.size() == 0) {
            mEmptyText.setVisibility(View.VISIBLE);
        } else {
            mEmptyText.setVisibility(View.GONE);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        doneAdaptor = new DoneAdaptor(mDoneList);
        mRecyclerView.setAdapter(doneAdaptor);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String[] valueFromDiaLogFragment;
        String dateFromDialogFragment;
        String timeFromDialogFragment;
        boolean[] taskState;
        if (resultCode != Activity.RESULT_OK || data == null)
            return;

        if (requestCode == REQUEST_FOR_DIALOGFRAGMENT) {
            valueFromDiaLogFragment = data.getStringArrayExtra(DiaLogFragment.getTitleAndDiscription());
            dateFromDialogFragment =  data.getStringExtra(DiaLogFragment.getDateFromDatepicker());
            timeFromDialogFragment=data.getStringExtra(DiaLogFragment.newInstance().getTIME_FROM_TIMEPICKER());
            taskState = data.getBooleanArrayExtra(DiaLogFragment.newInstance().getCHECKBOXES_STATE());
            setToDoClass(valueFromDiaLogFragment,taskState, dateFromDialogFragment,timeFromDialogFragment);
        }
        if (requestCode == REQUEST_CODE_FOR_CHANGE_FRAGMENT) {
            creatRecycler();
        }
    }
    private void setToDoClass(String[] valueFromDiaLogFragment, boolean[] taskState, String dateFromDialogFragment
            ,String timeFromDialogFragment) {
       mDone = new Done();
        if (valueFromDiaLogFragment != null) {
            mDone.setTitle(valueFromDiaLogFragment[0]);
            mDone.setDiscriptin(valueFromDiaLogFragment[1]);
            mDone.setUserName(userNameOfUser);
        }
        if (dateFromDialogFragment != null&&timeFromDialogFragment!=null) {
            mDone.setDate(dateFromDialogFragment);
            mDone.setTime(timeFromDialogFragment);
        }
        if (taskState != null) {
            mDone.setDoing(taskState[0]);
            mDone.setDone(taskState[1]);
            mDone.setToDo(taskState[2]);
        }
        DoneListsRepository.getInstance(getContext()).insertDone(mDone);
        creatRecycler();
    }
    public void ShowMsgDialog(Context self) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(self);
        dlgAlert.setMessage("Are you Sure?");
        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                try {
                    List<Doing> mDoingList = DoingListsRepository.getInstance(getContext()).getDoings(userNameOfUser);
                    List<Done> mDoneList = DoneListsRepository.getInstance(getContext()).getDones(userNameOfUser);
                    List<ToDo> mToDoList = ToDoListsRepository.getInstance(getContext()).getToDoes(userNameOfUser);
                    ToDoListsRepository.getInstance(getContext()).deleteToDoes(mToDoList);
                    DoingListsRepository.getInstance(getContext()).deleteDoings(mDoingList);
                    DoneListsRepository.getInstance(getContext()).deleteDones(mDoneList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                creatRecycler();
            }
        });
        dlgAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
        dlgAlert.show();
    }
}

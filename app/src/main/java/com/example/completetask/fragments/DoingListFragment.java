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
public class DoingListFragment extends Fragment {
    private final String DIALOG_FRAGMENT_TAG = "com.example.completetask.fragments.dialogFragmentTag";
    private final int REQUEST_FOR_DIALOGFRAGMENT = 3;
    private final String CHANGE_DIALOG_FRAGMENT_TAG = "com.example.completetask.changedialogfragment";
    private final int REQUEST_CODE_FOR_CHANGE_FRAGMENT = 4;
    private FloatingActionButton mFloatingActionButton;
    private static final String USERNAME_OF_USER = "com.example.completetask.fragments.id of user";
    private List<Doing> mDoingList;
    private Doing mDoing;
    String userNameOfUser;
    private RecyclerView mRecyclerView;
    private DoingAdaptor doingAdaptor;
    private TextView mEmptyText;
    public static DoingListFragment newInstance(String userNameOfUser) {

        Bundle args = new Bundle();
        args.putString(USERNAME_OF_USER, userNameOfUser);
        DoingListFragment fragment = new DoingListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DoingListFragment() {
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
        View view = inflater.inflate(R.layout.fragment_doing_list, container, false);
        init(view);
        creatRecycler();
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLogFragment diaLogFragment = DiaLogFragment.newInstance();
                diaLogFragment.setTargetFragment(DoingListFragment.this, REQUEST_FOR_DIALOGFRAGMENT);
                diaLogFragment.show(getFragmentManager(), DIALOG_FRAGMENT_TAG);
            }
        });
        return view;
    }

    private void init(View view) {
        mFloatingActionButton = view.findViewById(R.id.doing_fab);
        mRecyclerView = view.findViewById(R.id.doing_recycler);
        mEmptyText = view.findViewById(R.id.empty_indoingfragment);

    }

    private class DoingHolder extends RecyclerView.ViewHolder {
        private TextView mItemTitleTextView;
        private TextView mItemDiscriptionTextView;
        private TextView mItemDateTextView;
        private TextView mItemShapeTextView;
        private Doing mDoing;

        public DoingHolder(@NonNull View itemView) {
            super(itemView);
            mItemTitleTextView = itemView.findViewById(R.id.item_title);
            mItemDiscriptionTextView = itemView.findViewById(R.id.item_discription);
            mItemDateTextView = itemView.findViewById(R.id.item_date);
            mItemShapeTextView = itemView.findViewById(R.id.item_shape_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ChangeDialogFragment changeDialogFragment = ChangeDialogFragment.newInstance(mDoing.getTitle()
                            , mDoing.getDiscriptin(), mDoing.getDate(), mDoing.getTime(), userNameOfUser, mDoing.getUUID(), 3);
                    changeDialogFragment.setTargetFragment(DoingListFragment.this, REQUEST_CODE_FOR_CHANGE_FRAGMENT);
                    changeDialogFragment.show(getFragmentManager(), CHANGE_DIALOG_FRAGMENT_TAG);

                }
            });
        }

        public void bind(Doing doing) {
            Character shapeText = doing.getTitle().charAt(0);
            String stringForShapeText = shapeText.toString();
            mItemTitleTextView.setText(doing.getTitle());
            mItemDiscriptionTextView.setText(doing.getDiscriptin());
            mItemShapeTextView.setText(stringForShapeText);
            mItemDateTextView.setText(doing.getDate());
            mDoing = doing;
        }
    }

    private class DoingAdaptor extends RecyclerView.Adapter<DoingHolder> {
        private List<Doing> mDoingList;

        public DoingAdaptor(List<Doing> doingList) {
            mDoingList = doingList;
        }


        @NonNull
        @Override
        public DoingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_items, parent, false);
            DoingHolder doingHolder = new DoingHolder(view);
            return doingHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull DoingHolder holder, int position) {
            holder.bind(mDoingList.get(position));
        }

        @Override
        public int getItemCount() {
            if (mDoingList.size() != 0)
                return mDoingList.size();


            return 0;
        }
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
            dateFromDialogFragment = data.getStringExtra(DiaLogFragment.getDateFromDatepicker());
            timeFromDialogFragment = data.getStringExtra(DiaLogFragment.newInstance().getTIME_FROM_TIMEPICKER());
            taskState = data.getBooleanArrayExtra(DiaLogFragment.newInstance().getCHECKBOXES_STATE());
            setToDoClass(valueFromDiaLogFragment, taskState, dateFromDialogFragment, timeFromDialogFragment);
        }
        if (requestCode == REQUEST_CODE_FOR_CHANGE_FRAGMENT) {
            creatRecycler();
        }
    }

    private void setToDoClass(String[] valueFromDiaLogFragment, boolean[] taskState, String dateFromDialogFragment
            , String timeFromDialogFragment) {
        mDoing = new Doing();
        if (valueFromDiaLogFragment != null) {
            mDoing.setTitle(valueFromDiaLogFragment[0]);
            mDoing.setDiscriptin(valueFromDiaLogFragment[1]);
            mDoing.setUserName(userNameOfUser);
        }
        if (dateFromDialogFragment != null && timeFromDialogFragment != null) {
            mDoing.setDate(dateFromDialogFragment);
            mDoing.setTime(timeFromDialogFragment);
        }
        if (taskState != null) {
            mDoing.setDoing(taskState[0]);
            mDoing.setDone(taskState[1]);
            mDoing.setToDo(taskState[2]);
        }
        DoingListsRepository.getInstance(getContext()).insertDoing(mDoing);
        creatRecycler();
    }

    private void creatRecycler() {
        mDoingList = DoingListsRepository.getInstance(getContext()).getDoings(userNameOfUser);
        if (mDoingList.size() == 0) {
            mEmptyText.setVisibility(View.VISIBLE);
        } else {
            mEmptyText.setVisibility(View.GONE);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        doingAdaptor = new DoingAdaptor(mDoingList);
        mRecyclerView.setAdapter(doingAdaptor);
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

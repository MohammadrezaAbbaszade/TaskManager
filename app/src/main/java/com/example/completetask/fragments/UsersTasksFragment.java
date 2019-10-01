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

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.completetask.R;
import com.example.completetask.model.Doing;
import com.example.completetask.model.DoingListsRepository;
import com.example.completetask.model.Done;
import com.example.completetask.model.DoneListsRepository;
import com.example.completetask.model.ToDo;
import com.example.completetask.model.ToDoListsRepository;
import com.example.completetask.model.User;
import com.example.completetask.model.UserRepository;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersTasksFragment extends Fragment {
    private final int REQUEST_CODE_FOR_CHANGE_FRAGMENT = 1;
    private final String CHANGE_DIALOG_FRAGMENT_TAG = "com.example.completetask.changedialogfragment";
    private List<ToDo> mToDoList;
    private List<Doing> mDoingList;
    private List<Done> mDoneList;
    private ImageView mEmptyText;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView2;
    private RecyclerView mRecyclerView3;
    private UserAdaptor toDoAdaptor;
    private UserAdaptor2 doingAdaptor2;
    private UserAdaptor3 doneAdaptor3;
    private static final String USERNAME = "userName";
    String userName;

    public static UsersTasksFragment newInstance(String userName) {

        Bundle args = new Bundle();
        args.putString(USERNAME, userName);
        UsersTasksFragment fragment = new UsersTasksFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public UsersTasksFragment() {
        // Required empty public constructor
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
            case R.id.log_out_menu:
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userName = getArguments().getString(USERNAME);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users_tasks, container, false);
        init(view);
        creatRecycler();
        creatRecycler2();
        creatRecycler3();
        checkEmptyImage();
        return view;
    }

    private class UserHolder extends RecyclerView.ViewHolder {
        private TextView mItemTitleTextView;
        private TextView mItemDiscriptionTextView;
        private TextView mItemDateTextView;
        private TextView mItemShapeTextView;
        private TextView mState;
        private ToDo mToDo;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            mItemTitleTextView = itemView.findViewById(R.id.item_name_user);
            mItemDateTextView = itemView.findViewById(R.id.item_date_user);
            mItemShapeTextView = itemView.findViewById(R.id.item_shape_text_user);
            mItemTitleTextView = itemView.findViewById(R.id.item_title);
            mItemDiscriptionTextView = itemView.findViewById(R.id.item_discription);
            mItemDateTextView = itemView.findViewById(R.id.item_date);
            mItemShapeTextView = itemView.findViewById(R.id.item_shape_text);
            mState = itemView.findViewById(R.id.item_state_cardview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ChangeDialogFragment changeDialogFragment = ChangeDialogFragment.newInstance(mToDo.getTitle()
                            , mToDo.getDiscriptin(), mToDo.getDate(), mToDo.getTime(), userName, mToDo.getId(), 1);
                    changeDialogFragment.setTargetFragment(UsersTasksFragment.this, REQUEST_CODE_FOR_CHANGE_FRAGMENT);
                    changeDialogFragment.show(getFragmentManager(), CHANGE_DIALOG_FRAGMENT_TAG);

                }
            });

        }

        public void bind(ToDo toDo) {
            Character shapeText = toDo.getTitle().charAt(0);
            String stringForShapeText = shapeText.toString();
            mItemTitleTextView.setText(toDo.getTitle());
            mItemDiscriptionTextView.setText(toDo.getDiscriptin());
            mItemShapeTextView.setText(stringForShapeText);
            mState.setText(R.string.todo);
            mItemDateTextView.setText(toDo.getDate() + "," + toDo.getTime());
            mToDo = toDo;
        }
    }

    private class UserHolder2 extends RecyclerView.ViewHolder {
        private TextView mItemTitleTextView;
        private TextView mItemDiscriptionTextView;
        private TextView mItemDateTextView;
        private TextView mItemShapeTextView;
        private TextView mState;
        private Doing mDoing;

        public UserHolder2(@NonNull View itemView) {
            super(itemView);
            mItemTitleTextView = itemView.findViewById(R.id.item_title);
            mItemDiscriptionTextView = itemView.findViewById(R.id.item_discription);
            mItemDateTextView = itemView.findViewById(R.id.item_date);
            mItemShapeTextView = itemView.findViewById(R.id.item_shape_text);
            mState = itemView.findViewById(R.id.item_state_cardview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ChangeDialogFragment changeDialogFragment = ChangeDialogFragment.newInstance(mDoing.getTitle()
                            , mDoing.getDiscriptin(), mDoing.getDate(), mDoing.getTime(), userName, mDoing.getUUID(), 3);
                    changeDialogFragment.setTargetFragment(UsersTasksFragment.this, REQUEST_CODE_FOR_CHANGE_FRAGMENT);
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
            mState.setText(R.string.doing);
            mItemDateTextView.setText(doing.getDate() + "," + doing.getTime());
            mDoing = doing;
        }
    }

    private class UserHolder3 extends RecyclerView.ViewHolder {
        private TextView mItemTitleTextView;
        private TextView mItemDiscriptionTextView;
        private TextView mItemDateTextView;
        private TextView mItemShapeTextView;
        private TextView mState;
        private Done mDone;

        public UserHolder3(@NonNull View itemView) {
            super(itemView);
            mItemTitleTextView = itemView.findViewById(R.id.item_title);
            mItemDiscriptionTextView = itemView.findViewById(R.id.item_discription);
            mItemDateTextView = itemView.findViewById(R.id.item_date);
            mItemShapeTextView = itemView.findViewById(R.id.item_shape_text);
            mState = itemView.findViewById(R.id.item_state_cardview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ChangeDialogFragment changeDialogFragment = ChangeDialogFragment.newInstance(mDone.getTitle()
                            , mDone.getDiscriptin(), mDone.getDate(), mDone.getTime(), userName, mDone.getUUID(), 2);
                    changeDialogFragment.setTargetFragment(UsersTasksFragment.this, REQUEST_CODE_FOR_CHANGE_FRAGMENT);
                    changeDialogFragment.show(getFragmentManager(), CHANGE_DIALOG_FRAGMENT_TAG);

                }
            });


        }

        public void bind(Done done) {
            Character shapeText = done.getTitle().charAt(0);
            String stringForShapeText = shapeText.toString();
            mItemTitleTextView.setText(done.getTitle());
            mItemDiscriptionTextView.setText(done.getDiscriptin());
            mItemShapeTextView.setText(stringForShapeText);
            mState.setText(R.string.done);
            mItemDateTextView.setText(done.getDate() + "," + done.getTime());
            mDone = done;
        }
    }

    private class UserAdaptor extends RecyclerView.Adapter<UserHolder> {
        private List<ToDo> mToDoList;

        public UserAdaptor(List<ToDo> toDoList) {
            mToDoList = toDoList;
        }

        @NonNull
        @Override
        public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_items, parent, false);
            UserHolder userHolder = new UserHolder(view);
            return userHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull UserHolder holder, int position) {
            holder.bind(mToDoList.get(position));
        }

        @Override
        public int getItemCount() {
            return mToDoList.size();
        }
    }

    private class UserAdaptor2 extends RecyclerView.Adapter<UserHolder2> {
        private List<Doing> mDoingList;

        public UserAdaptor2(List<Doing> doingList) {
            mDoingList = doingList;
        }

        @NonNull
        @Override
        public UserHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_items, parent, false);
            UserHolder2 userHolder2 = new UserHolder2(view);
            return userHolder2;
        }

        @Override
        public void onBindViewHolder(@NonNull UserHolder2 holder, int position) {
            holder.bind(mDoingList.get(position));
        }

        @Override
        public int getItemCount() {
            return mDoingList.size();
        }
    }

    private class UserAdaptor3 extends RecyclerView.Adapter<UserHolder3> {
        private List<Done> mDoneList;

        public UserAdaptor3(List<Done> doneList) {
            mDoneList = doneList;
        }

        @NonNull
        @Override
        public UserHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_items, parent, false);
            UserHolder3 userHolder3 = new UserHolder3(view);
            return userHolder3;
        }

        @Override
        public void onBindViewHolder(@NonNull UserHolder3 holder, int position) {
            holder.bind(mDoneList.get(position));
        }

        @Override
        public int getItemCount() {
            return mDoneList.size();
        }
    }

    private void init(View view) {
        mRecyclerView = view.findViewById(R.id.users_tasks_recycler);
        mRecyclerView2 = view.findViewById(R.id.users_tasks_recycler2);
        mRecyclerView3 = view.findViewById(R.id.users_tasks_recycler3);
        mEmptyText = view.findViewById(R.id.empty_users_tasks_fragment);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String[] valueFromDiaLogFragment;
        String dateFromDialogFragment;
        String timeFromDialogFragment;
        boolean[] taskState;
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == REQUEST_CODE_FOR_CHANGE_FRAGMENT) {
            creatRecycler();
            creatRecycler2();
            creatRecycler3();
            checkEmptyImage();
        }
    }

    private void checkEmptyImage() {
        if (mDoneList.size() == 0 && mDoingList.size() == 0 && mToDoList.size() == 0) {
            mEmptyText.setVisibility(View.VISIBLE);
        } else {
            mEmptyText.setVisibility(View.GONE);
        }
    }

    private void creatRecycler() {
        mToDoList = ToDoListsRepository.getInstance(getContext()).getToDoes(userName);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        toDoAdaptor = new UserAdaptor(mToDoList);
        mRecyclerView.setAdapter(toDoAdaptor);
    }

    private void creatRecycler2() {
        mDoingList = DoingListsRepository.getInstance(getContext()).getDoings(userName);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        doingAdaptor2 = new UserAdaptor2(mDoingList);
        mRecyclerView2.setAdapter(doingAdaptor2);
    }

    private void creatRecycler3() {
        mDoneList = DoneListsRepository.getInstance(getContext()).getDones(userName);
        mRecyclerView3.setLayoutManager(new LinearLayoutManager(getActivity()));
        doneAdaptor3 = new UserAdaptor3(mDoneList);
        mRecyclerView3.setAdapter(doneAdaptor3);
    }
    public void ShowMsgDialog(Context self) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(self);
        dlgAlert.setMessage("Are you Sure?");
        dlgAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                try {
                    List<ToDo> mToDoList = ToDoListsRepository.getInstance(getContext()).getToDoes(userName);
                    ToDoListsRepository.getInstance(getContext()).deleteToDoes(mToDoList);
                    List<Doing> mDoingList = DoingListsRepository.getInstance(getContext()).getDoings(userName);
                    DoingListsRepository.getInstance(getContext()).deleteDoings(mDoingList);
                    List<Done> mDoneList = DoneListsRepository.getInstance(getContext()).getDones(userName);
                    DoneListsRepository.getInstance(getContext()).deleteDones(mDoneList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                creatRecycler();
                creatRecycler2();
                creatRecycler3();
                checkEmptyImage();
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

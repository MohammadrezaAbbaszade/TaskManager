package com.example.completetask.fragments;


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
import com.example.completetask.activities.UsersTasksActivity;
import com.example.completetask.model.Admin;
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
public class UsersListFragment extends Fragment {
    private List<User> mUserList;
    private User mUser;
    private ImageView mEmptyText;
    private RecyclerView mRecyclerView;
    private UserAdaptor userAdaptor;

    public static UsersListFragment newInstance() {

        Bundle args = new Bundle();

        UsersListFragment fragment = new UsersListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public UsersListFragment() {
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
                ShowMsgDialog(getActivity(),mUserList);
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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_users, container, false);
        init(view);
        creatRecycler();

        return view;
    }

    private class UserHolder extends RecyclerView.ViewHolder {
        private TextView mItemTitleTextView;
        private TextView mItemDateTextView;
        private TextView mItemShapeTextView;
        private Button mDelete;
        private User mUser;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            mItemTitleTextView = itemView.findViewById(R.id.item_name_user);
            mItemDateTextView = itemView.findViewById(R.id.item_date_user);
            mItemShapeTextView = itemView.findViewById(R.id.item_shape_text_user);
            mDelete = itemView.findViewById(R.id.user_list_delete_button);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = UsersTasksActivity.newIntent(getContext(), mUser.getMUserName());
                    startActivity(intent);
                }
            });
            mDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowMsgDialog(getContext(), mUser);
                }
            });

        }

        public void bind(User user) {
            Character shapeText = user.getMUserName().charAt(0);
            String stringForShapeText = shapeText.toString();
            mItemTitleTextView.setText(user.getMUserName());
            mItemShapeTextView.setText(stringForShapeText);
            mItemDateTextView.setText(user.getMTimeRegister());
            mUser = user;
        }
    }

    private class UserAdaptor extends RecyclerView.Adapter<UserHolder> {
        private List<User> mUserList;

        public UserAdaptor(List<User> userList) {
            mUserList = userList;
        }

        @NonNull
        @Override
        public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_users, parent, false);
            UserHolder userHolder = new UserHolder(view);
            return userHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull UserHolder holder, int position) {
            holder.bind(mUserList.get(position));
        }

        @Override
        public int getItemCount() {
            return mUserList.size();
        }
    }

    private void init(View view) {
        mRecyclerView = view.findViewById(R.id.user_recycler);
        mEmptyText = view.findViewById(R.id.empty_in_users_fragment);

    }

    private void creatRecycler() {
        mUserList = UserRepository.getInstance().getmUsers();
        if (mUserList.size() == 0) {
            mEmptyText.setVisibility(View.VISIBLE);
        } else {
            mEmptyText.setVisibility(View.GONE);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        userAdaptor = new UserAdaptor(mUserList);
        mRecyclerView.setAdapter(userAdaptor);
    }

    public void ShowMsgDialog(Context self, final User mUser) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(self);
        dlgAlert.setMessage("Do you want To Delete This User?");
        dlgAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                try {
                    deleteTasks(mUser);
                    UserRepository.getInstance().deleteUser(mUser);
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
    public void ShowMsgDialog(Context self, final List<User> mUserList) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(self);
        dlgAlert.setMessage("Are You Sure?");
        dlgAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                try {
                    for(int i=0;i<mUserList.size();i++) {
                        deleteTasks(mUserList.get(i));
                    }
                    for(int i=0;i<mUserList.size();i++) {
                        UserRepository.getInstance().deleteUser(mUserList.get(i));
                    }

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
    private void deleteTasks(User mUser) throws Exception {
        List<ToDo> mToDoList = ToDoListsRepository.getInstance().getToDoes(mUser.getMUserName());
        if (mToDoList.size() != 0){
            ToDoListsRepository.getInstance().deleteToDoes(mToDoList);
    }

    List<Doing> mDoingList = DoingListsRepository.getInstance().getDoings(mUser.getMUserName());
       if(mDoingList.size()!=0)

    {
        DoingListsRepository.getInstance().deleteDoings(mDoingList);
    }

        List<Done> mDoneList = DoneListsRepository.getInstance().getDones(mUser.getMUserName());
        if(mDoneList.size()!=0)

        {
            DoneListsRepository.getInstance().deleteDones(mDoneList);
        }


}
}

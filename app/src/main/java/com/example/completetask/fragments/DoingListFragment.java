package com.example.completetask.fragments;


import android.app.Activity;
import android.app.SearchManager;
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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.completetask.R;
import com.example.completetask.model.Doing;
import com.example.completetask.model.DoingListsRepository;
import com.example.completetask.model.Done;
import com.example.completetask.model.DoneListsRepository;
import com.example.completetask.model.ToDo;
import com.example.completetask.model.ToDoListsRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
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
    private ImageView mEmptyText;
    private SearchView.OnQueryTextListener queryTextListener;
    private SearchView searchView;
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
    public void onResume() {
        super.onResume();
        creatRecycler();
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
        MenuItem searchItem = menu.findItem(R.id.search_menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    doingAdaptor.getFilter().filter(newText);

                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    doingAdaptor.getFilter().filter(query);

                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
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
            case R.id.search_menu:
                return true;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);

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
        private TextView mState;
        private Doing mDoing;

        public DoingHolder(@NonNull View itemView) {
            super(itemView);
            mItemTitleTextView = itemView.findViewById(R.id.item_title);
            mItemDiscriptionTextView = itemView.findViewById(R.id.item_discription);
            mItemDateTextView = itemView.findViewById(R.id.item_date);
            mItemShapeTextView = itemView.findViewById(R.id.item_shape_text);
            mState=itemView.findViewById(R.id.item_state_cardview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ChangeDialogFragment changeDialogFragment = ChangeDialogFragment.newInstance(mDoing.getMTitle()
                            , mDoing.getMDiscriptin(), mDoing.getMDate(), mDoing.getMTime(), userNameOfUser, mDoing.getMID(), 3);
                    changeDialogFragment.setTargetFragment(DoingListFragment.this, REQUEST_CODE_FOR_CHANGE_FRAGMENT);
                    changeDialogFragment.show(getFragmentManager(), CHANGE_DIALOG_FRAGMENT_TAG);

                }
            });
        }

        public void bind(Doing doing) {
            Character shapeText = doing.getMTitle().charAt(0);
            String stringForShapeText = shapeText.toString();
            mItemTitleTextView.setText(doing.getMTitle());
            mItemDiscriptionTextView.setText(doing.getMDiscriptin());
            mItemShapeTextView.setText(stringForShapeText);
            mState.setText(R.string.doing);
            mItemDateTextView.setText(doing.getMDate()+","+doing.getMTime());
            mDoing = doing;
        }
    }

    private class DoingAdaptor extends RecyclerView.Adapter<DoingHolder> implements Filterable {
        private List<Doing> mDoingList;
        private List<Doing> mDoingListFiltered;
        public DoingAdaptor(List<Doing> doingList) {
            mDoingList = doingList;
            mDoingListFiltered=doingList;
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
            holder.bind(mDoingListFiltered.get(position));
        }

        @Override
        public int getItemCount() {
            if (mDoingListFiltered.size() != 0)
                return mDoingListFiltered.size();


            return 0;
        }
        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    String charString = charSequence.toString();
                    if (charString.isEmpty()) {
                        mDoingListFiltered=mDoingList;
                    } else {
                        List<Doing> filteredList = new ArrayList<>();
                        for (Doing row : mDoingList) {

                            // name match condition. this might differ depending on your requirement
                            // here we are looking for name or phone number match
                            if (row.getMTitle().toLowerCase().contains(charString.toLowerCase()) || row.getMDiscriptin().contains(charSequence)||
                                    row.getMDate().toLowerCase().contains(charString.toLowerCase())||row.getMTime().toLowerCase().contains(charString.toLowerCase()) ) {
                                filteredList.add(row);
                            }
                        }

                        mDoingListFiltered = filteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mDoingListFiltered;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    mDoingListFiltered = (ArrayList<Doing>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
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
            setToDoClass(valueFromDiaLogFragment, dateFromDialogFragment, timeFromDialogFragment);
        }
        if (requestCode == REQUEST_CODE_FOR_CHANGE_FRAGMENT) {
            creatRecycler();
        }
    }

    private void setToDoClass(String[] valueFromDiaLogFragment, String dateFromDialogFragment
            , String timeFromDialogFragment) {
        mDoing = new Doing();
        if (valueFromDiaLogFragment != null) {
            mDoing.setMTitle(valueFromDiaLogFragment[0]);
            mDoing.setMDiscriptin(valueFromDiaLogFragment[1]);
            mDoing.setMUserName(userNameOfUser);
            mDoing.setMIsDoing(true);
        }
        if (dateFromDialogFragment != null && timeFromDialogFragment != null) {
            mDoing.setMDate(dateFromDialogFragment);
            mDoing.setMTime(timeFromDialogFragment);
        }
        DoingListsRepository.getInstance().insertDoing(mDoing);
        creatRecycler();
    }

    private void creatRecycler() {
        mDoingList = DoingListsRepository.getInstance().getDoings(userNameOfUser);
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
                    List<Doing> mDoingList = DoingListsRepository.getInstance().getDoings(userNameOfUser);
                    DoingListsRepository.getInstance().deleteDoings(mDoingList);
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

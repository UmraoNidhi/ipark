package com.iparksimple.app.Activity.ui.book;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iparksimple.app.Activity.HomeActivity;
import com.iparksimple.app.Activity.ui.Vehicles.VehiclesFragment;
import com.iparksimple.app.Adapter.ListAdapter;
import com.iparksimple.app.Fragment.Home_Map_fragment;
import com.iparksimple.app.R;

import java.util.ArrayList;


public class BookFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<String>mList;
    ListAdapter listAdapter;

    LinearLayout MapView;



    private BookViewModel bookViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bookViewModel =
                ViewModelProviders.of(this).get(BookViewModel.class);
        View root = inflater.inflate(R.layout.fragment_book, container, false);


        mList = new ArrayList<>();
        mList.add("1");
        mList.add("1");
        mList.add("1");
        mList.add("1");




        recyclerView = root.findViewById(R.id.Recycler_list);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        listAdapter = new ListAdapter(getContext(),mList);
        recyclerView.setAdapter(listAdapter);

        MapView = root.findViewById(R.id.MapView);
        MapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Home_Map_fragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });

        bookViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeActivity.bottomNavigationView.findViewById(R.id.nav_bottom).setVisibility(View.VISIBLE);
        HomeActivity.mMenu.findItem(R.id.action_search).setVisible(true);
    }
}
package com.iparksimple.app.Fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iparksimple.app.Activity.HomeActivity;
import com.iparksimple.app.Adapter.BookingHistoryAdapter;
import com.iparksimple.app.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    BookingHistoryAdapter bookingHistoryAdapter;
    ArrayList<String>mList =new ArrayList<>();



    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        mList.add("1");
        mList.add("1");
        mList.add("1");
        mList.add("1");


        recyclerView = view.findViewById(R.id.Recycler_list);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        bookingHistoryAdapter = new BookingHistoryAdapter(getContext(),mList);
        recyclerView.setAdapter(bookingHistoryAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeActivity.bottomNavigationView.findViewById(R.id.nav_bottom).setVisibility(View.GONE);
        HomeActivity.mMenu.findItem(R.id.action_search).setVisible(false);
    }
}

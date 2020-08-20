package com.iparksimple.app.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iparksimple.app.Adapter.BookingAdapter;
import com.iparksimple.app.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Upcoming_bookingFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<String>mUpcoming;
    BookingAdapter bookingAdapter;


    public Upcoming_bookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming_booking, container, false);

        mUpcoming = new ArrayList<>();
        mUpcoming.add("1");
        mUpcoming.add("1");
        mUpcoming.add("1");


        recyclerView = view.findViewById(R.id.recycler_upcoming);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        bookingAdapter = new BookingAdapter(getContext(),mUpcoming);
        recyclerView.setAdapter(bookingAdapter);



        return view;
    }

}

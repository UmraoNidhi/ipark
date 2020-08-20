package com.iparksimple.app.Fragment;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iparksimple.app.Activity.HomeActivity;
import com.iparksimple.app.Adapter.BookingAdapter;
import com.iparksimple.app.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Booking_historyFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    BookingAdapter bookingAdapter;
    ArrayList<String>booking;


    public Booking_historyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_booking_history, container, false);


       booking = new ArrayList<>();
       booking.add("1");
       booking.add("1");
       booking.add("1");
       booking.add("1");


       recyclerView = view.findViewById(R.id.Recycler_booking);
       recyclerView.setHasFixedSize(false);
       linearLayoutManager = new LinearLayoutManager(getContext());
       recyclerView.setLayoutManager(linearLayoutManager);
       bookingAdapter = new BookingAdapter(getContext(),booking);
       recyclerView.setAdapter(bookingAdapter);

       return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        if(activity instanceof HomeActivity){
            HomeActivity homeActivity = (HomeActivity) activity;
            homeActivity.setToolBar(false);
        }
    }
}

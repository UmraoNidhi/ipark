package com.iparksimple.app.Activity.ui.Airport;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iparksimple.app.Activity.HomeActivity;
import com.iparksimple.app.Adapter.BookingHistoryAdapter;
import com.iparksimple.app.R;

import java.util.ArrayList;

public class AirportFragment extends Fragment {

    private AirportViewModel airportViewModel;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    BookingHistoryAdapter bookingHistoryAdapter;
    ArrayList<String>BookingHistory;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        airportViewModel =
                ViewModelProviders.of(this).get(AirportViewModel.class);
        View root = inflater.inflate(R.layout.fragment_airport, container, false);

        BookingHistory = new ArrayList<>();
        BookingHistory.add("1");
        BookingHistory.add("1");
        BookingHistory.add("1");
        BookingHistory.add("1");


        recyclerView = root.findViewById(R.id.Recycler_Airport);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        bookingHistoryAdapter = new BookingHistoryAdapter(getContext(),BookingHistory);
        recyclerView.setAdapter(bookingHistoryAdapter);

        airportViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        HomeActivity.bottomNavigationView.findViewById(R.id.nav_bottom).setVisibility(View.GONE);
//        HomeActivity.mMenu.findItem(R.id.action_search).setVisible(false);

    }
}
package com.iparksimple.app.Activity.ui.Monthly;

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
import com.iparksimple.app.Adapter.BookingAdapter;
import com.iparksimple.app.Adapter.BookingHistoryAdapter;
import com.iparksimple.app.R;

import java.util.ArrayList;

public class MonthlyFragment extends Fragment {

    private MonthlyViewModel monthlyViewModel;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<String>MonthlyList;
    BookingHistoryAdapter bookingAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        monthlyViewModel =
                ViewModelProviders.of(this).get(MonthlyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_monthly, container, false);


        MonthlyList = new ArrayList<>();
        MonthlyList.add("1");
        MonthlyList.add("1");
        MonthlyList.add("1");
        MonthlyList.add("1");

        recyclerView = root.findViewById(R.id.Recycler_monthly);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        bookingAdapter = new BookingHistoryAdapter(getContext(),MonthlyList);
        recyclerView.setAdapter(bookingAdapter);


//        final TextView textView = root.findViewById(R.id.text_notifications);
        monthlyViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
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
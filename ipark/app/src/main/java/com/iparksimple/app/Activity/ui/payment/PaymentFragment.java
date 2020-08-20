package com.iparksimple.app.Activity.ui.payment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iparksimple.app.Activity.HomeActivity;
import com.iparksimple.app.Adapter.CardAdapter;
import com.iparksimple.app.R;

import java.util.ArrayList;


public class PaymentFragment extends Fragment {

    private PaymentViewModel paymentViewModel;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<String>CardList;
    CardAdapter cardAdapter;
    ImageView back;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        paymentViewModel =
                ViewModelProviders.of(this).get(PaymentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_payment, container, false);

        CardList = new ArrayList<>();
        CardList.add("1");
        CardList.add("1");



        recyclerView = root.findViewById(R.id.Recycler_card);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        cardAdapter = new CardAdapter(getContext(),CardList);
        recyclerView.setAdapter(cardAdapter);

        back = root.findViewById(R.id.Image_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        paymentViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeActivity.bottomNavigationView.findViewById(R.id.nav_bottom).setVisibility(View.GONE);
        HomeActivity.mMenu.findItem(R.id.action_search).setVisible(false);
    }
}
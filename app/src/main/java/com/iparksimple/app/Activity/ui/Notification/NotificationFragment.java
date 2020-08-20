package com.iparksimple.app.Activity.ui.Notification;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iparksimple.app.Activity.HomeActivity;
import com.iparksimple.app.Adapter.NotificationAdapter;
import com.iparksimple.app.R;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {

    private NotificationViewModel notificationViewModel;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    NotificationAdapter notificationAdapter;
    ArrayList<String>NotificationList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        notificationViewModel =
                ViewModelProviders.of(this).get(NotificationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notification, container, false);

        NotificationList = new ArrayList<>();
        NotificationList.add("1");
        NotificationList.add("1");
        NotificationList.add("1");
        NotificationList.add("1");



        recyclerView = root.findViewById(R.id.Notification);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        notificationAdapter = new NotificationAdapter(getContext(),NotificationList);
        recyclerView.setAdapter(notificationAdapter);


        notificationViewModel.getText().observe(getActivity(), new Observer<String>() {
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
        Activity activity = getActivity();
        if(activity instanceof HomeActivity){
            HomeActivity homeActivity = (HomeActivity) activity;
            homeActivity.setToolBar(false);
        }
    }
}
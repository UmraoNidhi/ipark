package com.iparksimple.app.Activity.ui.profile;

import android.content.Intent;
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
import com.iparksimple.app.Activity.ResetActivity;
import com.iparksimple.app.Adapter.CardAdapter;
import com.iparksimple.app.Adapter.VehicleAdapter;
import com.iparksimple.app.R;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    TextView textView;
    RecyclerView vehicle_details,Card_details;
    LinearLayoutManager VehicleLayoutManager,CardlayoutManager;
    CardAdapter cardAdapter;
    VehicleAdapter vehicleAdapter;
    ArrayList<String>CardList;
    ArrayList<String>VehicleList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
//        final TextView textView = root.findViewById(R.id.text_gallery);

        CardList = new ArrayList<>();
        CardList.add("1");
        CardList.add("1");
        CardList.add("1");

        VehicleList = new ArrayList<>();
        VehicleList.add("1");
        VehicleList.add("1");
        VehicleList.add("1");
        VehicleList.add("1");


        textView = root.findViewById(R.id.Change_password);
        vehicle_details = root.findViewById(R.id.Vehicle_detail);
        vehicle_details.setHasFixedSize(false);
        VehicleLayoutManager = new LinearLayoutManager(getContext());
        vehicle_details.setLayoutManager(VehicleLayoutManager);
        vehicleAdapter = new VehicleAdapter(getContext(),VehicleList);
        vehicle_details.setAdapter(vehicleAdapter);





        Card_details = root.findViewById(R.id.Card_detail);
        Card_details.setHasFixedSize(false);
        CardlayoutManager = new LinearLayoutManager(getContext());
        Card_details.setLayoutManager(CardlayoutManager);
        cardAdapter = new CardAdapter(getContext(),CardList);
        Card_details.setAdapter(cardAdapter);





        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ResetActivity.class);
                startActivity(intent);
            }
        });
        profileViewModel.getText().observe(getActivity(), new Observer<String>() {
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
        HomeActivity.bottomNavigationView.findViewById(R.id.nav_bottom).setVisibility(View.GONE);
        HomeActivity.mMenu.findItem(R.id.action_search).setVisible(false);
    }
}
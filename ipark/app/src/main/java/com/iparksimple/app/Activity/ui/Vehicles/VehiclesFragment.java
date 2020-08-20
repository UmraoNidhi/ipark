package com.iparksimple.app.Activity.ui.Vehicles;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.iparksimple.app.Activity.ui.payment.PaymentFragment;
import com.iparksimple.app.Adapter.VehicleAdapter;
import com.iparksimple.app.R;

import java.util.ArrayList;


public class VehiclesFragment extends Fragment {

    private VehicleViewModel vehicleViewModel;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    VehicleAdapter vehicleAdapter;
    ArrayList<String>mVehicleList;
    TextView textView;
    Dialog dialog;
    Button Continue;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vehicleViewModel =
                ViewModelProviders.of(this).get(VehicleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_vehicle, container, false);

        mVehicleList = new ArrayList<>();
        mVehicleList.add("1");
        mVehicleList.add("1");
        mVehicleList.add("1");

        textView = root.findViewById(R.id.text_add);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddDialog();

            }
        });


        recyclerView = root.findViewById(R.id.Recycler_vehicleList);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        vehicleAdapter = new VehicleAdapter(getContext(),mVehicleList);
        recyclerView.setAdapter(vehicleAdapter);

        Continue = root.findViewById(R.id.But_continue);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PaymentFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });






        vehicleViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }


    private void openAddDialog(){
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.add_vehicle);
        ImageView imageView = dialog.findViewById(R.id.Cross);
        dialog.show();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeActivity.bottomNavigationView.findViewById(R.id.nav_bottom).setVisibility(View.GONE);
        HomeActivity.mMenu.findItem(R.id.action_search).setVisible(false);
    }
}
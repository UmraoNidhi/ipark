package com.iparksimple.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.iparksimple.app.Activity.ui.Vehicles.VehiclesFragment;
import com.iparksimple.app.Adapter.DayListAdapter;
import com.iparksimple.app.R;

import java.util.ArrayList;

public class ParkingDetailActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<String>DayList;
    DayListAdapter dayListAdapter;
    Button Continue;
    RelativeLayout relativeLayout;
    Toolbar toolbar;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_detail);

        DayList = new ArrayList<>();
        DayList.add("1");
        DayList.add("1");
        DayList.add("1");
        DayList.add("1");

        toolbar = findViewById(R.id.Toolbar);
        recyclerView = findViewById(R.id.RecyclerList);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        dayListAdapter = new DayListAdapter(this,DayList);
        recyclerView.setAdapter(dayListAdapter);

        relativeLayout = findViewById(R.id.Relative);
        back = findViewById(R.id.Image_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Continue = findViewById(R.id.But_continue);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setVisibility(View.INVISIBLE);
                toolbar.setVisibility(View.GONE);
                Fragment fragment = new VehiclesFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });


    }
}

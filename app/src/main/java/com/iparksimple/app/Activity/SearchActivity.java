package com.iparksimple.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.iparksimple.app.Activity.ui.Airport.AirportFragment;
import com.iparksimple.app.Activity.ui.Monthly.MonthlyFragment;
import com.iparksimple.app.Activity.ui.daily.DailyFragment;
import com.iparksimple.app.Adapter.Pager;
import com.iparksimple.app.Fragment.Active_bookingFragment;
import com.iparksimple.app.Fragment.Booking_historyFragment;
import com.iparksimple.app.Fragment.ReportFragment;
import com.iparksimple.app.Fragment.Upcoming_bookingFragment;
import com.iparksimple.app.R;

public class SearchActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    ImageView back,Image_filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        back = findViewById(R.id.Image_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Image_filter = findViewById(R.id.Image_filter);
        Image_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this,FilterActivity.class);
                startActivity(intent);
            }
        });

        viewPager = findViewById(R.id.ViewPager);
        tabLayout = findViewById(R.id.Tab_layout);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {

        Pager adapter = new Pager(getSupportFragmentManager());
        adapter.addFragment(new ReportFragment(), "  Daily ");
        adapter.addFragment(new ReportFragment(), " Monthly");
        adapter.addFragment(new ReportFragment(),"Airport");
        viewPager.setAdapter(adapter);


    }
}

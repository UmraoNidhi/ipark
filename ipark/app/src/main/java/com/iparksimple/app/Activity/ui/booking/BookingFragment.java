package com.iparksimple.app.Activity.ui.booking;

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
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.iparksimple.app.Activity.HomeActivity;
import com.iparksimple.app.Adapter.Pager;
import com.iparksimple.app.Fragment.Active_bookingFragment;
import com.iparksimple.app.Fragment.Booking_historyFragment;
import com.iparksimple.app.Fragment.Upcoming_bookingFragment;
import com.iparksimple.app.R;


public class BookingFragment extends Fragment {

    private BookingViewModel BookingViewModel;
    ViewPager viewPager;
    TabLayout tabLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BookingViewModel =
                ViewModelProviders.of(this).get(BookingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_booking, container, false);


        viewPager = root.findViewById(R.id.ViewPager);
        tabLayout = root.findViewById(R.id.Tab_layout);
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


        BookingViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }

    private void setupViewPager(ViewPager viewPager) {

        Pager adapter = new Pager(getChildFragmentManager());
        adapter.addFragment(new Booking_historyFragment(), "  History ");
        adapter.addFragment(new Active_bookingFragment(), " Active");
        adapter.addFragment(new Upcoming_bookingFragment(),"Upcoming");
        viewPager.setAdapter(adapter);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeActivity.bottomNavigationView.findViewById(R.id.nav_bottom).setVisibility(View.GONE);
        HomeActivity.mMenu.findItem(R.id.action_search).setVisible(false);
    }
}
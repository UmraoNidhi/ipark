package com.iparksimple.app.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.iparksimple.app.Activity.ui.Airport.AirportFragment;
import com.iparksimple.app.Activity.ui.Monthly.MonthlyFragment;
import com.iparksimple.app.Activity.ui.daily.DailyFragment;
import com.iparksimple.app.Fragment.Active_bookingFragment;
import com.iparksimple.app.Fragment.Booking_historyFragment;
import com.iparksimple.app.Fragment.Upcoming_bookingFragment;

import java.util.ArrayList;
import java.util.List;

public class Pager extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public Pager(FragmentManager childFragmentManager) {
        super(childFragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DailyFragment DailyFragment = new DailyFragment();
                return DailyFragment;
            case 1:
                MonthlyFragment monthlyFragment = new MonthlyFragment();
                return monthlyFragment;
            case 2:
                AirportFragment AirportFragment = new AirportFragment();
                return AirportFragment;
            default:
                return null;
        }    }

    @Override
    public int getCount() {
        return mFragmentList.size() ;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
}

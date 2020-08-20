package com.iparksimple.app.Fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iparksimple.app.Activity.HomeActivity;
import com.iparksimple.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends Fragment {


    public ContactUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_us, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeActivity.bottomNavigationView.findViewById(R.id.nav_bottom).setVisibility(View.GONE);
        HomeActivity.mMenu.findItem(R.id.action_search).setVisible(false);
    }
}

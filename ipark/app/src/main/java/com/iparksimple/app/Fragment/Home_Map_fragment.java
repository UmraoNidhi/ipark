package com.iparksimple.app.Fragment;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.iparksimple.app.Activity.HomeActivity;
import com.iparksimple.app.Activity.LocationTracker;
import com.iparksimple.app.Activity.ui.book.BookFragment;
import com.iparksimple.app.Adapter.ViewpagerAdapter;
import com.iparksimple.app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Map_fragment extends Fragment implements LocationListener {

    GoogleMap MarkergoogleMap;
    ArrayList<LatLng> points = new ArrayList<LatLng>();
    LocationTracker locationTracker;
    ViewPager viewPager;
    ViewpagerAdapter viewpagerAdapter;
    private static int NUM_PAGES = 0;
    private static int currentPage = 0;
    FrameLayout frameLayout;
    List<String> slider = new ArrayList<>();
    LinearLayout linear_list,linear_map;



    public Home_Map_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home__map_fragment, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MarkergoogleMap = googleMap;
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

//                googleMap.setLatLngBoundsForCameraTarget(PACIFIC);
//                googleMap.getUiSettings().setZoomControlsEnabled(true);
//                googleMap.getUiSettings().setRotateGesturesEnabled(false);
//                googleMap.getUiSettings().setScrollGesturesEnabled(true);
//                googleMap.getUiSettings().setTiltGesturesEnabled(false);
//                googleMap.clear();



                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(6.5244, -3.3792))
                        .zoom(8)
                        .build();

                CameraUpdate camUpd = CameraUpdateFactory.newCameraPosition(googlePlex);
                googleMap.animateCamera(camUpd, 2000, null);

            }
        });

        slider = new ArrayList<>();
        slider.add("1");
        slider.add("1");
        slider.add("1");

        linear_list = view.findViewById(R.id.Linear_list);
        linear_map = view.findViewById(R.id.MapView);
        linear_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new BookFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });



        viewPager = view.findViewById(R.id.viewPager);

        NUM_PAGES = slider.size();
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);


//        viewpagerAdapter = new ViewpagerAdapter(getActivity(),slider);
//        viewPager.setAdapter(viewpagerAdapter);
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });



        return view;

    }

    private BitmapDescriptor bitmapDescriptorFromVector(FragmentActivity activity, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(activity, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        points.add(latLng);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        HomeActivity.bottomNavigationView.findViewById(R.id.nav_bottom).setVisibility(View.VISIBLE);
//        HomeActivity.mMenu.findItem(R.id.action_search).setVisible(true);
    }
}

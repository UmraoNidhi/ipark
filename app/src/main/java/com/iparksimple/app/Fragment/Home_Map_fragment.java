package com.iparksimple.app.Fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.iparksimple.app.Activity.ForgotActivity;
import com.iparksimple.app.Activity.HomeActivity;
import com.iparksimple.app.Activity.LocationTracker;
import com.iparksimple.app.Activity.ParkingDetailActivity;
import com.iparksimple.app.Activity.ResetActivity;
import com.iparksimple.app.Activity.ui.book.BookFragment;
import com.iparksimple.app.Activity.ui.daily.DailyFragment;
import com.iparksimple.app.Adapter.ViewpagerAdapter;
import com.iparksimple.app.Network_class.Network_state;
import com.iparksimple.app.R;
import com.iparksimple.app.services.ApiClient;
import com.iparksimple.app.services.ApiInterface;
import com.iparksimple.app.services.Result_forgot;
import com.iparksimple.app.services.Result_map;
import com.iparksimple.app.utils.Constants;
import com.iparksimple.app.utils.PreferenceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    LinearLayout linear_list,linear_map,sliderdots;
    HashMap<String,String>request_map ;
    String Latitude,Longitude,Address,Title,Detail,Name,Id,ParkingType;
    ApiInterface apiInterface;
    ArrayList<Result_map.Data.Lot>Parking_list = new ArrayList<>();
    private LatLngBounds.Builder builder = new LatLngBounds.Builder();
    private ImageView[] dots;
    ProgressBar progressBar;
    String Selected_view;




    public Home_Map_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home__map_fragment, container, false);


        HomeActivity.bottomNavigationView.setVisibility(View.VISIBLE);


        locationTracker = new LocationTracker(getContext());
        Latitude = Double.toString(locationTracker.getLongitude());
        Longitude =Double.toString(locationTracker.getLongitude());
        PreferenceUtil.setUserLatitude(getContext(),Latitude);
        PreferenceUtil.setUserLongitude(getContext(),Longitude);
        viewPager = view.findViewById(R.id.viewPager);
        sliderdots = view.findViewById(R.id.SliderDots);
        progressBar = view.findViewById(R.id.Progress_bar);
        Selected_view = Constants.PreferenceConstants.Selected_MAP;
        PreferenceUtil.setSelectedView(getContext(),Selected_view);
//        try {
//            ParkingType = getArguments().getString(Constants.PreferenceConstants.ParKingType);
//            Log.e("ParkingType",":"+ParkingType);
//
            if (Network_state.isNetworkAvailable(getContext())){
                    GetMapData();
                    progressBar.setVisibility(View.VISIBLE);
                }else {
                    GetMapData();
                    progressBar.setVisibility(View.VISIBLE);
                }
//
//            }else {
//                Toast.makeText(getContext(),"Please check your network connection",Toast.LENGTH_LONG).show();
//            }

//        }catch (Exception e){
//            e.printStackTrace();
//        }

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
                Selected_view = Constants.PreferenceConstants.Selected_LIST;
                PreferenceUtil.setSelectedView(getContext(),Selected_view);
                Fragment fragment = new DailyFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });









        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
//        linear_list.performClick();
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


    private void GetMapData(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        request_map = new HashMap<>();
//        request_map.put("latitude",Latitude);
//        request_map.put("longitude",Longitude);
//        request_map.put("type","monthly");
//        request_map.put("Distance","5");
        Call<Result_map>call = apiInterface.GetHomeData(Latitude,Longitude,"Daily","5");
        call.enqueue(new Callback<Result_map>() {
            @Override
            public void onResponse(Call<Result_map> call, Response<Result_map> response) {
                try {
                    progressBar.setVisibility(View.GONE);
                    boolean status = response.body().getStatus();
                    String message = response.body().getMessage();
                    Parking_list = response.body().getData().getLots();
                    if (Parking_list.size()>0){

                        for (int i=0;i<Parking_list.size();i++){
                            Latitude = Parking_list.get(i).getLatitude();
                            Longitude = Parking_list.get(i).getLongitude();
                            Address = Parking_list.get(i).getAddress();
                            Name = Parking_list.get(i).getName();
                            Id = Parking_list.get(i).getId();
                            builder.include(new LatLng(Double.parseDouble(Latitude),Double.parseDouble(Longitude)));
                            AddMarker();
                            MarkergoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                @Override
                                public void onInfoWindowClick(Marker marker) {
                                    Intent intent = new Intent(getContext(), ParkingDetailActivity.class);
                                    intent.putExtra(Constants.PreferenceConstants.ID,Id);

                                    startActivity(intent);
                                }
                            });

                        }

//                        NUM_PAGES = Parking_list.size();
//                        final Handler handler = new Handler();
//                        final Runnable Update = new Runnable() {
//                            public void run() {
//                                if (currentPage == NUM_PAGES) {
//                                    currentPage = 0;
//                                }
//                                viewPager.setCurrentItem(currentPage++, true);
//                            }
//                        };
//                        Timer swipeTimer = new Timer();
//                        swipeTimer.schedule(new TimerTask() {
//                            @Override
//                            public void run() {
//                                handler.post(Update);
//                            }
//                        }, 8000, 8000);


                        viewpagerAdapter = new ViewpagerAdapter(getActivity(),Parking_list);
                        viewPager.setAdapter(viewpagerAdapter);
                        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
//                                for (int a = 0; a < Parking_list.size(); a++) {
//                                    dots[a].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.white_circle));
//                                }
//
//                                dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.circle_fill_blue));



                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                        displayProductImage(Parking_list);
                        LatLngBounds bounds = builder.build();
                        try {
                            MarkergoogleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }



                    }else {

                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Result_map> call, Throwable t) {
                Log.e("exception",":"+t);

            }
        });
    }
    public void displayProductImage(List<Result_map.Data.Lot> slist) {
        try {
            if (sliderdots.getChildCount() > 0) {
                sliderdots.removeAllViews();
            }
            dots = new ImageView[slist.size()];

            for (int i = 0; i < slist.size(); i++) {
                dots[i] = new ImageView(getContext());
                dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.white_circle));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(25, 25);
                params.setMargins(8, 0, 8, 0);
                sliderdots.addView(dots[i], params);
            }
            dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.circle_fill_blue));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void AddMarker(){
        MarkergoogleMap.addMarker(new MarkerOptions()
                .position(new LatLng(Double.parseDouble(Latitude), Double.parseDouble(Longitude)))
                .title(Name).icon(bitmapDescriptorFromVector(getActivity(), R.drawable.map_icon)));
    }




}

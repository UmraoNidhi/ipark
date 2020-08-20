package com.iparksimple.app.Activity.ui.Monthly;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.iparksimple.app.Activity.HomeActivity;
import com.iparksimple.app.Activity.ParkingDetailActivity;
import com.iparksimple.app.Adapter.BookingAdapter;
import com.iparksimple.app.Adapter.BookingHistoryAdapter;
import com.iparksimple.app.Adapter.ListAdapter;
import com.iparksimple.app.Adapter.ViewpagerAdapter;
import com.iparksimple.app.Fragment.Home_Map_fragment;
import com.iparksimple.app.Network_class.Network_state;
import com.iparksimple.app.R;
import com.iparksimple.app.services.ApiClient;
import com.iparksimple.app.services.ApiInterface;
import com.iparksimple.app.services.Result_map;
import com.iparksimple.app.utils.Constants;
import com.iparksimple.app.utils.PreferenceUtil;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonthlyFragment extends Fragment {

    private MonthlyViewModel monthlyViewModel;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<String>MonthlyList;
    ListAdapter listAdapter;
    String Latitude,Longitude,Address,Name,Id;
    ArrayList<Result_map.Data.Lot>Parking_list = new ArrayList<>();
    LinearLayout linear_list,linear_map;
    ImageView image_map,image_list;
    TextView list_text,Map_text;
    String SelectedView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        monthlyViewModel =
                ViewModelProviders.of(this).get(MonthlyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_monthly, container, false);


        MonthlyList = new ArrayList<>();
        MonthlyList.add("1");
        MonthlyList.add("1");
        MonthlyList.add("1");
        MonthlyList.add("1");

        Latitude = PreferenceUtil.getUserLatitude(getContext());
        Longitude = PreferenceUtil.getUserLongitude(getContext());
        SelectedView = PreferenceUtil.getSelectedView(getContext());
        Log.e("Selectedview",":"+SelectedView);


        linear_list = root.findViewById(R.id.Linear_list);
        linear_map = root.findViewById(R.id.MapView);
        image_map = root.findViewById(R.id.Image_map);
        image_list = root.findViewById(R.id.Image_list);
        Map_text = root.findViewById(R.id.Text_map);
        list_text = root.findViewById(R.id.Text_list);

        recyclerView = root.findViewById(R.id.Recycler_monthly);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        if (Network_state.isNetworkAvailable(getContext())){
            GetMapData();
        }else {
            Toast.makeText(getContext(),"Please check your network connection",Toast.LENGTH_LONG).show();
        }


//        final TextView textView = root.findViewById(R.id.text_notifications);
        monthlyViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });



        linear_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_text.setTextColor(Color.BLACK);
                image_list.setImageResource(R.drawable.list_view);
                SelectedView = Constants.PreferenceConstants.Selected_MAP;
                PreferenceUtil.setSelectedView(getContext(),SelectedView);
                Fragment fragment = new Home_Map_fragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.PreferenceConstants.ParKingType,"Monthly");
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();

            }
        });

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        HomeActivity.bottomNavigationView.findViewById(R.id.nav_bottom).setVisibility(View.GONE);
//        HomeActivity.mMenu.findItem(R.id.action_search).setVisible(false);
    }

    private void GetMapData(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Result_map> call = apiInterface.GetHomeData(Latitude,Longitude,"monthly","5");
        call.enqueue(new Callback<Result_map>() {
            @Override
            public void onResponse(Call<Result_map> call, Response<Result_map> response) {
                try {
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
                        }

                        if (SelectedView.equalsIgnoreCase(Constants.PreferenceConstants.Selected_LIST)){
                            listAdapter = new ListAdapter(getContext(),Parking_list);
                            recyclerView.setAdapter(listAdapter);
                            SelectedView = Constants.PreferenceConstants.Selected_LIST;
                            PreferenceUtil.setSelectedView(getContext(),SelectedView);
                        }else {
                            list_text.setTextColor(Color.BLACK);
                            image_list.setImageResource(R.drawable.list_view);
                            SelectedView = Constants.PreferenceConstants.Selected_MAP;
                            PreferenceUtil.setSelectedView(getContext(),SelectedView);
                            Fragment fragment = new Home_Map_fragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.PreferenceConstants.ParKingType,"Monthly");
                            fragment.setArguments(bundle);
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container, fragment);
                            transaction.commit();

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



}
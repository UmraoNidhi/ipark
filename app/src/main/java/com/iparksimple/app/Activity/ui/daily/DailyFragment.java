package com.iparksimple.app.Activity.ui.daily;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
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

import com.iparksimple.app.Activity.HomeActivity;
import com.iparksimple.app.Adapter.BookingHistoryAdapter;
import com.iparksimple.app.Adapter.ListAdapter;
import com.iparksimple.app.Fragment.Home_Map_fragment;
import com.iparksimple.app.Network_class.Network_state;
import com.iparksimple.app.R;
import com.iparksimple.app.services.ApiClient;
import com.iparksimple.app.services.ApiInterface;
import com.iparksimple.app.services.Result_map;
import com.iparksimple.app.utils.Constants;
import com.iparksimple.app.utils.PreferenceUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyFragment extends Fragment {

    private DailyViewModel dailyViewModel;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<String>mDailyBooking;
    ListAdapter listAdapter;
    String Latitude,Longitude,Address,Name,Id;
    ArrayList<Result_map.Data.Lot>Parking_list = new ArrayList<>();
    LinearLayout linear_list,linear_map;
    ImageView image_map,image_list;
    TextView list_text,Map_text;
    String Selected_view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dailyViewModel =
                ViewModelProviders.of(this).get(DailyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_daily, container, false);

        mDailyBooking = new ArrayList<>();
        mDailyBooking.add("1");
        mDailyBooking.add("1");
        mDailyBooking.add("1");
        mDailyBooking.add("1");

        Latitude = PreferenceUtil.getUserLatitude(getContext());
        Longitude = PreferenceUtil.getUserLongitude(getContext());
        Selected_view = PreferenceUtil.getSelectedView(getContext());
        Log.e("SelectedView",":"+Selected_view);
        linear_list = root.findViewById(R.id.Linear_list);
        linear_map = root.findViewById(R.id.MapView);
        image_map = root.findViewById(R.id.Image_map);
        image_list = root.findViewById(R.id.Image_list);
        Map_text = root.findViewById(R.id.Text_map);
        list_text = root.findViewById(R.id.Text_list);


        recyclerView = root.findViewById(R.id.Recycler_Daily);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        if (Network_state.isNetworkAvailable(getContext())){
            GetMapData();
        }else {
            Toast.makeText(getContext(),"Please check your network connection",Toast.LENGTH_LONG).show();
        }


        dailyViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        linear_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_text.setTextColor(Color.BLACK);
                image_list.setImageResource(R.drawable.list_view);
                Selected_view = Constants.PreferenceConstants.Selected_MAP;
                PreferenceUtil.setSelectedView(getContext(),Selected_view);
                Fragment fragment = new Home_Map_fragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.PreferenceConstants.ParKingType,"Daily");
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

//        HomeActivity.bottomNavigationView.findViewById(R.id.nav_bottom).setVisibility(View.VISIBLE);
//        HomeActivity.mMenu.findItem(R.id.action_search).setVisible(false);
    }

    private void GetMapData(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Result_map> call = apiInterface.GetHomeData(Latitude,Longitude,"daily","5");
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
                        if (Selected_view.equalsIgnoreCase(Constants.PreferenceConstants.Selected_MAP)){
                            list_text.setTextColor(Color.BLACK);
                            image_list.setImageResource(R.drawable.list_view);
                            Selected_view = Constants.PreferenceConstants.Selected_MAP;
                            PreferenceUtil.setSelectedView(getContext(),Selected_view);
                            Fragment fragment = new Home_Map_fragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.PreferenceConstants.ParKingType,"Daily");
                            fragment.setArguments(bundle);
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container, fragment);
                            transaction.commit();
                        }else {
                            listAdapter = new ListAdapter(getContext(),Parking_list);
                            recyclerView.setAdapter(listAdapter);
                            Selected_view = Constants.PreferenceConstants.Selected_LIST;
                            PreferenceUtil.setSelectedView(getContext(),Selected_view);
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
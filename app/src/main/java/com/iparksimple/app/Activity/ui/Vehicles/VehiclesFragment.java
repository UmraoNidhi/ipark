//package com.iparksimple.app.Activity.ui.Vehicles;
//
//import android.app.Dialog;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.RadioButton;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProviders;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.material.textfield.TextInputEditText;
//import com.iparksimple.app.Activity.HomeActivity;
//import com.iparksimple.app.Activity.ui.payment.PaymentFragment;
//import com.iparksimple.app.Adapter.VehicleAdapter;
//import com.iparksimple.app.Adapter.VehicleListadapter;
//import com.iparksimple.app.Network_class.Network_state;
//import com.iparksimple.app.R;
//import com.iparksimple.app.services.ApiClient;
//import com.iparksimple.app.services.ApiInterface;
//import com.iparksimple.app.services.Result_addvehicle;
//import com.iparksimple.app.services.Result_vehicleList;
//import com.iparksimple.app.utils.PreferenceUtil;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//
//public class VehiclesFragment extends Fragment {
//
//    private VehicleViewModel vehicleViewModel;
//    RecyclerView recyclerView;
//    LinearLayoutManager linearLayoutManager;
//    VehicleListadapter vehicleAdapter;
//    ArrayList<String>mVehicleList;
//    TextView textView;
//    Dialog dialog;
//    Button Continue;
//    String Token,Color,PlateNumber,Model;
//    ImageView imageView;
//    ArrayList<Result_vehicleList.Datum>UserVehicleList;
//    HashMap<String,String> Vehicle_map = new HashMap<>();
//    ProgressBar progressBar;
//    TextInputEditText PlateNo,Car_Model,Car_Color;
//    RadioButton Add_anotherDetails;
//
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        vehicleViewModel =
//                ViewModelProviders.of(this).get(VehicleViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_vehicle, container, false);
//
//        mVehicleList = new ArrayList<>();
//        mVehicleList.add("1");
//        mVehicleList.add("1");
//        mVehicleList.add("1");
//
//        Token = PreferenceUtil.getAccessTokenFromLogin(getContext());
//
//        textView = root.findViewById(R.id.text_add);
//        progressBar = root.findViewById(R.id.Progress_bar);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openAddDialog();
//
//            }
//        });
//
//        if (Network_state.isNetworkAvailable(getContext())){
//            getVehicleList();
//        }else {
//            Toast.makeText(getContext(),"Please check your network connection",Toast.LENGTH_LONG).show();
//        }
//
//
//        recyclerView = root.findViewById(R.id.Recycler_vehicleList);
//        recyclerView.setHasFixedSize(false);
//        linearLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//
//        Continue = root.findViewById(R.id.But_continue);
//        imageView = root.findViewById(R.id.Image_back);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        Continue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new PaymentFragment();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container, fragment);
//                transaction.commit();
//            }
//        });
//
//
//
//
//
//
//        vehicleViewModel.getText().observe(getActivity(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//
//            }
//        });
//        return root;
//    }
//
//
//    private void openAddDialog(){
//        dialog = new Dialog(getContext());
//        dialog.setContentView(R.layout.add_vehicle);
//        dialog.setCancelable(false);
//        ImageView imageView = dialog.findViewById(R.id.Cross);
//        Button button = dialog.findViewById(R.id.Button_save);
//        Car_Color = dialog.findViewById(R.id.et_Color);
//        Car_Model = dialog.findViewById(R.id.et_Model_name);
//        PlateNo = dialog.findViewById(R.id.et_Lname);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Model = Car_Model.getText().toString();
//                PlateNumber = PlateNo.getText().toString();
//                Color = Car_Color.getText().toString();
//                Log.e("Model",":"+Model+"PlateNo"+PlateNumber+"Color"+Color);
//                if (!Model.isEmpty()){
//                    if (!PlateNumber.isEmpty()){
//                        if (!Color.isEmpty()){
//                            AddVehicle();
//                            progressBar.setVisibility(View.VISIBLE);
//                            dialog.dismiss();
//                        }else {
//                            Toast.makeText(getContext(),"Please add vehicle color",Toast.LENGTH_LONG).show();
//                        }
//                    }else {
//                        Toast.makeText(getContext(),"Please add vehicle licence number plate",Toast.LENGTH_LONG).show();
//
//                    }
//
//                }else {
//                    Toast.makeText(getContext(),"Please add vehicle model",Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//        });
//        dialog.show();
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        HomeActivity.bottomNavigationView.findViewById(R.id.nav_bottom).setVisibility(View.GONE);
//        HomeActivity.mMenu.findItem(R.id.action_search).setVisible(false);
//    }
//
//
//    private void getVehicleList(){
//        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Call<Result_vehicleList>call = apiInterface.VehicleList(Token);
//        call.enqueue(new Callback<Result_vehicleList>() {
//            @Override
//            public void onResponse(Call<Result_vehicleList> call, Response<Result_vehicleList> response) {
//                try {
//                    boolean status = response.body().getStatus();
//                    String Message = response.body().getMessage();
//                    if (status== true){
//                        UserVehicleList = response.body().getData();
//                        vehicleAdapter = new VehicleListadapter(getContext(),UserVehicleList);
//                        recyclerView.setAdapter(vehicleAdapter);
//                    }else {
//                        Toast.makeText(getContext(),Message,Toast.LENGTH_LONG).show();
//                    }
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Result_vehicleList> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void AddVehicle(){
//        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Vehicle_map.put("model",Model);
//        Vehicle_map.put("color",Color);
//        Vehicle_map.put("plate_number",PlateNumber);
//        Call<Result_addvehicle>call = apiInterface.Add_vehicle(Token,Vehicle_map);
//        call.enqueue(new Callback<Result_addvehicle>() {
//            @Override
//            public void onResponse(Call<Result_addvehicle> call, Response<Result_addvehicle> response) {
//                try {
//                    boolean status = response.body().getStatus();
//                    String Message = response.body().getMessage();
//                    if (status == true){
//                        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(getContext(),Message,Toast.LENGTH_LONG).show();
//                        getVehicleList();
//                    }
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Result_addvehicle> call, Throwable t) {
//
//            }
//        });
//    }
//
//}
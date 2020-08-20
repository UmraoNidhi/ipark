package com.iparksimple.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iparksimple.app.R;

import java.util.ArrayList;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.MyViewHolder> {
    private Context mcontext;
    private ArrayList<String>mVehicle;
    public VehicleAdapter(Context context, ArrayList<String> vehicleList) {
        this.mcontext = context;
        this.mVehicle = vehicleList;
    }

    @NonNull
    @Override
    public VehicleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mVehicle.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

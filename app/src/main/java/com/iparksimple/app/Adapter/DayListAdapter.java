package com.iparksimple.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iparksimple.app.Activity.ParkingDetailActivity;
import com.iparksimple.app.R;

import java.util.ArrayList;

public class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String>mDayList;
    public DayListAdapter(ParkingDetailActivity parkingDetailActivity, ArrayList<String> dayList) {
        this.context = parkingDetailActivity;
        this.mDayList = dayList;
    }

    @NonNull
    @Override
    public DayListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daylist,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DayListAdapter.MyViewHolder holder, int position) {
        holder.Days.setText(mDayList.get(position));

    }

    @Override
    public int getItemCount() {
        return mDayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Days,Time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Days = itemView.findViewById(R.id.Text_days);
            Time = itemView.findViewById(R.id.Text_date);
        }
    }
}

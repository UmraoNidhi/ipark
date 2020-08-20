package com.iparksimple.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iparksimple.app.R;

import java.util.ArrayList;

public class BookingHistoryAdapter extends RecyclerView.Adapter<BookingHistoryAdapter.MyViewHolder> {
    private Context mcontext;
    private ArrayList<String>mMonthly;

    public BookingHistoryAdapter(Context context, ArrayList<String> monthlyList) {
        this.mcontext = context;
        this.mMonthly = monthlyList;
    }

    @NonNull
    @Override
    public BookingHistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_history,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingHistoryAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mMonthly.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

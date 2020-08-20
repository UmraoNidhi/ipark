package com.iparksimple.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iparksimple.app.R;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {
    private Context mcontext;
    private ArrayList<String>mBooking;
    public BookingAdapter(Context context, ArrayList<String> booking) {
        this.mcontext = context;
        this.mBooking = booking;
    }

    @NonNull
    @Override
    public BookingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_list,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mBooking.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

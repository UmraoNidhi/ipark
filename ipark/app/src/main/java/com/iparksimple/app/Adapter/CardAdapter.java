package com.iparksimple.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iparksimple.app.R;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {
    private Context mcontext;
    private ArrayList<String>mCardList;



    public CardAdapter(Context context, ArrayList<String> card_details) {
        this.mcontext= context;
        this.mCardList= card_details;
    }

    @NonNull
    @Override
    public CardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mCardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

package com.iparksimple.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.iparksimple.app.Activity.ParkingDetailActivity;
import com.iparksimple.app.R;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private Context mcontext;
    private ArrayList<String>List;
    public ListAdapter(Context context, ArrayList<String> mList) {
        this.mcontext = context;
        this.List = mList;
    }

    @NonNull
    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.Car_list);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext, ParkingDetailActivity.class);
                    mcontext.startActivity(intent);
                }
            });
        }
    }
}

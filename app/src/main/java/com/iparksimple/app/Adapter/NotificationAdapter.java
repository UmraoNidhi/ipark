package com.iparksimple.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iparksimple.app.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.myViewHolder> {
    private Context mcontext;
    private ArrayList<String>mNotification;

    public NotificationAdapter(Context context, ArrayList<String> notificationList) {
        this.mcontext = context;
        this.mNotification = notificationList;
    }

    @NonNull
    @Override
    public NotificationAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification,parent,false);
        myViewHolder myViewHolder = new myViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.myViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mNotification.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

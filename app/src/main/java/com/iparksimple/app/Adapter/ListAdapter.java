package com.iparksimple.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iparksimple.app.Activity.ParkingDetailActivity;
import com.iparksimple.app.R;
import com.iparksimple.app.services.Result_map;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private Context mcontext;
    private ArrayList<Result_map.Data.Lot>List;
    double distance = 1.5;
    public ListAdapter(Context context, ArrayList<Result_map.Data.Lot> mList) {
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
        String imageUrl = List.get(position).getLotImage();
        String Title = List.get(position).getName();
        String Address = List.get(position).getAddress();
        String Price = List.get(position).getDailyPrice();
        String City = List.get(position).getCity();
        String Distance = List.get(position).getDistance();
        String FinalimageUrl = "http:"+imageUrl;
        Log.e("ImageUrl",":"+imageUrl);
//        if (imageUrl!=null){
//            Glide.with(mcontext).load(imageUrl).into(holder.imageView);
//        }
        Picasso.with(mcontext).load(FinalimageUrl).into(holder.imageView);




        holder.Name.setText(Title);
        holder.Price.setText(Price);
        holder.Address.setText(Address);
        holder.Location.setText(City);
        holder.Distance.setText(distance+position+" "+"mi");


    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imageView;
        TextView Name,Address,Price,Location,Distance;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.Car_list);
            imageView = itemView.findViewById(R.id.image);
            Name = itemView.findViewById(R.id.Text_name);
            Address = itemView.findViewById(R.id.Text_description);
            Location = itemView.findViewById(R.id.Location);
            Price = itemView.findViewById(R.id.Price);
            Distance = itemView.findViewById(R.id.Distance);

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

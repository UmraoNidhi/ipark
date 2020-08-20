package com.iparksimple.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iparksimple.app.R;
import com.iparksimple.app.services.Result_profile;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.MyViewHolder> {
    private Context mcontext;
    private ArrayList<Result_profile.Data.Vehicle>mVehicle;
    LinkedHashMap<Integer,ImageView> radioMap= new LinkedHashMap<>();

    public VehicleAdapter(Context context, ArrayList<Result_profile.Data.Vehicle> vehicleList) {
        this.mcontext = context;
        this.mVehicle = vehicleList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        radioMap.put(position,holder.radioImage);
        holder.tvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext, "RadioButton on Click", Toast.LENGTH_SHORT).show();
                int selectedPos = position;
                List<Integer> keyList = new ArrayList<>(radioMap.keySet());
                for(int i=0;i<keyList.size(); i++){
                    if(keyList.get(i)==selectedPos){
                        ImageView radioSel= radioMap.get(selectedPos);
                        radioSel.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.ic_radio_button_checked_black_24dp));
                    }else{
                        ImageView radioUnSel= radioMap.get(i);
                        radioUnSel.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.ic_radio_button_unchecked_black_24dp));
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mVehicle.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout rowLayout;
        RadioButton radioButton;
        TextView tvText;
        ImageButton radioImage;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            rowLayout = itemView.findViewById(R.id.row_layout);
            tvText = itemView.findViewById(R.id.Text);
            radioButton = itemView.findViewById(R.id.Radio_button);
            radioButton.setVisibility(View.GONE);
            radioImage = itemView.findViewById(R.id.iv_radioBtn);
//            itemView.setOnClickListener(this);
//            radioImage.setOnClickListener(this);
//            rowLayout.setOnClickListener(this);
//            tvText.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int selectedPos = getAdapterPosition();
            List<Integer> keyList = new ArrayList<>(radioMap.keySet());
            for(int i=0;i<keyList.size(); i++){
                if(keyList.get(i)==selectedPos){
                    ImageView radioSel= radioMap.get(selectedPos);
                    radioSel.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.ic_radio_button_checked_black_24dp));
                }else{
                    ImageView radioUnSel= radioMap.get(i);
                    radioUnSel.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.ic_radio_button_unchecked_black_24dp));
                }
            }
        }
    }
}

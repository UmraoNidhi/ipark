package com.iparksimple.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iparksimple.app.Activity.VehicleListActivity;
import com.iparksimple.app.R;
import com.iparksimple.app.services.Result_vehicleList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class VehicleListadapter extends RecyclerView.Adapter<VehicleListadapter.MyViewHolder> {
    private Context mcontext;
    private ArrayList<Result_vehicleList.Datum>mUserVehicleList;
    LinkedHashMap<Integer,ImageView> radioMap= new LinkedHashMap<>();
    SelectedPosition selectedPosition;
    String Selected;


    public VehicleListadapter(Context context, ArrayList<Result_vehicleList.Datum> vehicleList, VehicleListActivity vehicleListActivityClass) {
        this.mcontext = context;
        this.mUserVehicleList = vehicleList;
        this.selectedPosition = vehicleListActivityClass;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_list,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        radioMap.put(position,holder.radioImage);
        String Model = mUserVehicleList.get(position).getModel();
        String color = mUserVehicleList.get(position).getColor();
        String plateNo =mUserVehicleList.get(position).getPlateNumber();

        holder.details.setText(Model+","+" "+color+","+" "+plateNo);


    }

    @Override
    public int getItemCount() {
        return mUserVehicleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView details;
        RadioButton radioButton;
        ImageButton radioImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            details = itemView.findViewById(R.id.Text);
            radioButton = itemView.findViewById(R.id.Radio_button);
            radioButton.setVisibility(View.GONE);
            radioImage = itemView.findViewById(R.id.iv_radioBtn);
            radioImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int selectedPos = getAdapterPosition();
            List<Integer> keyList = new ArrayList<>(radioMap.keySet());
            for(int i=0;i<keyList.size(); i++){
                if(keyList.get(i)==selectedPos){
                    ImageView radioSel= radioMap.get(selectedPos);
                    radioSel.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.ic_radio_button_checked_black_24dp));
                    Selected = "Yes";
                }else{
                    Selected = "No";
                    ImageView radioUnSel= radioMap.get(i);
                    radioUnSel.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.ic_radio_button_unchecked_black_24dp));
                }
            }
            selectedPosition.getSelectedPosition(Selected);
        }
    }

    public interface SelectedPosition{
        void getSelectedPosition(String position);
    }

}

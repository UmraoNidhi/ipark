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

import com.iparksimple.app.Activity.ui.payment.PaymentFragment;
import com.iparksimple.app.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {
    private Context mcontext;
    private ArrayList<String>mCardList;
    LinkedHashMap<Integer, ImageView> radioMap= new LinkedHashMap<>();
    SelectPaymentInterface selectPaymentInterface;


    public CardAdapter(Context context, ArrayList<String> card_details, PaymentFragment fragment) {
        this.mcontext= context;
        this.mCardList= card_details;
        this.selectPaymentInterface = fragment;

    }

    public CardAdapter(Context context, ArrayList<String> cardList) {
        this.mcontext= context;
        this.mCardList= cardList;
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
    }

    @Override
    public int getItemCount() {
        return mCardList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageButton radioImage;
        TextView tvTitle;
        RadioButton radioButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.Text);
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
                    selectPaymentInterface.selectedCard(selectedPos);
                }else{
                    ImageView radioUnSel= radioMap.get(i);
                    radioUnSel.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.ic_radio_button_unchecked_black_24dp));
                }
            }
        }
    }
    public void clearRadioButtons() {
        List<Integer> keyList = new ArrayList<>(radioMap.keySet());
        for(int i=0;i<keyList.size(); i++){
            ImageView radioUnSel= radioMap.get(i);
            radioUnSel.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.ic_radio_button_unchecked_black_24dp));
        }
    }

    public interface SelectPaymentInterface{
        void selectedCard(int pos);
    }
}

package com.iparksimple.app.Activity.ui.payment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.iparksimple.app.Activity.HomeActivity;
import com.iparksimple.app.Activity.ThankuActivity;
import com.iparksimple.app.Adapter.CardAdapter;
import com.iparksimple.app.R;

import java.util.ArrayList;


public class PaymentFragment extends Fragment implements CardAdapter.SelectPaymentInterface {

    private PaymentViewModel paymentViewModel;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<String>CardList;
    CardAdapter cardAdapter;
    ImageView back;
    Button save,Continue;
    RadioButton radioButton;
    LinearLayout NewDetails,Selected_another,linear_summary;
    TextInputEditText CardNo,Card_holderName;
    TextInputEditText Date,Month,Year,CVV;
    int id = 1;
    Boolean isCardSelected= false, isAnotherCard=false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        paymentViewModel =
                ViewModelProviders.of(this).get(PaymentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_payment, container, false);

        CardList = new ArrayList<>();
        CardList.add("1");
        CardList.add("1");

        save = root.findViewById(R.id.But_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        Card_holderName = root.findViewById(R.id.et_fullname);
        CardNo = root.findViewById(R.id.et_CardNo);
        Month = root.findViewById(R.id.et_Month);
        CVV = root.findViewById(R.id.et_CVV);
        Year = root.findViewById(R.id.et_Year);
        radioButton = root.findViewById(R.id.Radio_another);
        radioButton.setId(id);
        NewDetails = root.findViewById(R.id.Add_newDetail);
        Continue = root.findViewById(R.id.But_continue);
        Selected_another = root.findViewById(R.id.Selected_another);
        linear_summary = root.findViewById(R.id.Linear_summary);



        recyclerView = root.findViewById(R.id.Recycler_card);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        cardAdapter = new CardAdapter(getContext(),CardList,PaymentFragment.this);
        recyclerView.setAdapter(cardAdapter);
        Log.e("Selected",":"+radioButton.isChecked());

        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButton.isChecked() == true){
                    isCardSelected=true;
                    isAnotherCard= true;
                    Selected_another.setVisibility(View.VISIBLE);
                    cardAdapter.clearRadioButtons();
                }else {
                    isCardSelected=false;
                    isAnotherCard= false;
                    Selected_another.setVisibility(View.GONE);

                }
            }
        });





        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCardSelected) {
                    if(isAnotherCard) {
                        if (Card_holderName.getText().toString().trim().isEmpty()) {
                            Toast.makeText(getActivity(), "Card Holder's name should not be empty!", Toast.LENGTH_SHORT).show();
                        } else if (CardNo.getText().toString().trim().isEmpty()) {
                            Toast.makeText(getActivity(), "Card Number should not be empty!", Toast.LENGTH_SHORT).show();
                        } else if (CardNo.getText().toString().trim().length() < 16 || CardNo.getText().toString().trim().length() > 16) {
                            Toast.makeText(getActivity(), "Please enter a valid Card Number!", Toast.LENGTH_SHORT).show();
                        } else if (CVV.getText().toString().trim().isEmpty()) {
                            Toast.makeText(getActivity(), "CVV should not be empty!", Toast.LENGTH_SHORT).show();
                        } else if(Year.getText().toString().trim().isEmpty()) {
                            Toast.makeText(getActivity(), "Year should not be empty!", Toast.LENGTH_SHORT).show();
                        }else if(Month.getText().toString().trim().isEmpty()){
                            Toast.makeText(getActivity(), "Month should not be empty!", Toast.LENGTH_SHORT).show();
                        }else{
                            Intent intent = new Intent(getContext(), ThankuActivity.class);
                            startActivity(intent);
                        }
                    }else{
                        Intent intent = new Intent(getContext(), ThankuActivity.class);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(getActivity(), "Please select a card to proceed payment!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        back = root.findViewById(R.id.Image_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        paymentViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeActivity.bottomNavigationView.findViewById(R.id.nav_bottom).setVisibility(View.GONE);
        HomeActivity.mMenu.findItem(R.id.action_search).setVisible(false);
    }

    @Override
    public void selectedCard(int pos) {
        isCardSelected=true;
        isAnotherCard= false;
        Selected_another.setVisibility(View.GONE);
        radioButton.setChecked(false);
    }
}
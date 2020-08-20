package com.iparksimple.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iparksimple.app.Adapter.DayListAdapter;
import com.iparksimple.app.R;
import com.iparksimple.app.services.ApiClient;
import com.iparksimple.app.services.ApiInterface;
import com.iparksimple.app.services.Result_LotsDetail;
import com.iparksimple.app.utils.Constants;
import com.iparksimple.app.utils.PreferenceUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;
public class ParkingDetailActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<String>DayList;
    DayListAdapter dayListAdapter;
    Button Continue;
    RelativeLayout relativeLayout;
    Toolbar toolbar;
    String Token,UserId,lot_id, currentDateTime, currentEndTime;
    TextView Title,Description,location,price,Monthly,Hourly,start_date, end_date;;
    ImageView back;
    ImageView imageView;
    DatePickerDialog.OnDateSetListener datePickerDialog;
    Calendar cal=Calendar.getInstance();
    boolean isDateFromSelected =false;
    final int START=1, END=2;
    LinearLayout linear_recurring;
    private SwitchDateTimeDialogFragment dateTimeFragment;
    private static final String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_detail);

        Token = PreferenceUtil.getAccessTokenFromLogin(this);
        UserId = PreferenceUtil.getUserId(this);

        DayList = new ArrayList<>();
        DayList.add("Mon");
        DayList.add("Tue");
        DayList.add("Wed");
        DayList.add("Thu");
        DayList.add("Fri");
        DayList.add("Sat");
        DayList.add("Sun");

        toolbar = findViewById(R.id.Toolbar);
        back = findViewById(R.id.Image_back);
        Continue = findViewById(R.id.But_continue);
        imageView = findViewById(R.id.ImageView);
        Title = findViewById(R.id.Name);
        Description = findViewById(R.id.Description);
        location = findViewById(R.id.location);
        Monthly = findViewById(R.id.Monthly);
        Hourly = findViewById(R.id.TextView_hourly);
        start_date = findViewById(R.id.tv_startDate);
        end_date = findViewById(R.id.tv_endDate);
        linear_recurring = findViewById(R.id.Linear_recurring);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm aa");

        currentDateTime= df.format(c.getTime());
        start_date.setText(currentDateTime.replace("am","AM").replace("pm","PM"));

        Calendar c2 = Calendar.getInstance();
        c2.add(Calendar.DATE,3);
        currentEndTime=df.format(c2.getTime());;
        end_date.setText(currentEndTime.replace("am","AM").replace("pm","PM"));

        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String endDate= end_date.getText().toString().trim();
                openDateTimePickerFragment(START,start_date, endDate);

            }
        });
        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startDate= start_date.getText().toString().trim();
                openDateTimePickerFragment(END,end_date,startDate);

            }
        });
        Hourly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Monthly.setBackgroundResource(R.color.white);
                Monthly.setTextColor(Color.BLACK);
               Hourly.setBackgroundResource(R.color.dark_blue);
               Hourly.setTextColor(Color.WHITE);
               linear_recurring.setVisibility(View.GONE);
            }
        });
        Monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hourly.setBackgroundResource(R.color.white);
                Hourly.setTextColor(Color.BLACK);
                Monthly.setBackgroundResource(R.color.dark_blue);
                Monthly.setTextColor(Color.WHITE);
                linear_recurring.setVisibility(View.VISIBLE);
            }
        });


        relativeLayout = findViewById(R.id.Relative);
        recyclerView = findViewById(R.id.RecyclerList);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        dayListAdapter = new DayListAdapter(this,DayList);
        recyclerView.setAdapter(dayListAdapter);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Token.isEmpty()){

                    Intent intent =new Intent(ParkingDetailActivity.this,VehicleListActivity.class);
                    startActivity(intent);
//                    Fragment fragment = new VehiclesFragment();
//                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.fragment_container, fragment);
//                    transaction.commit();
                }else {
                    Intent intent = new Intent(ParkingDetailActivity.this,LoginActivity.class);
                    intent.putExtra(Constants.PreferenceConstants.TYPE,"DetailPage");
                    startActivity(intent);
                }
            }
        });

    }

    private void openDateTimePickerFragment(int Type, final TextView tvDate, String dateToRestrict) {
        Date defaultDate= null,max_min_date= null;
        String defDate = tvDate.getText().toString().trim();
        if(!dateToRestrict.equalsIgnoreCase(currentDateTime)){
            SimpleDateFormat oldFormat = new SimpleDateFormat("d MMM yyyy HH:mm:aa");
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date d1 = oldFormat.parse(dateToRestrict);
                max_min_date = newFormat.parse(oldFormat.format(d1));
                Date d2 = oldFormat.parse(defDate);
                defaultDate = newFormat.parse(oldFormat.format(d2));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        dateTimeFragment = (SwitchDateTimeDialogFragment) getSupportFragmentManager().findFragmentByTag(TAG_DATETIME_FRAGMENT);
        if(dateTimeFragment == null) {
            dateTimeFragment = SwitchDateTimeDialogFragment.newInstance(
                    getString(R.string.label_datetime_dialog),
                    getString(android.R.string.ok),
                    getString(android.R.string.cancel)
// getString(R.string.clean) // Optional
            );
        }

// Optionally define a timezone
        dateTimeFragment.setTimeZone(TimeZone.getDefault());

        int YEAR = cal.get(Calendar.YEAR);
        int MONTH = cal.get(Calendar.MONTH);
        int DATE = cal.get(Calendar.DATE);
        int HOUR = cal.get(Calendar.HOUR);
        int MIN = cal.get(Calendar.MINUTE);

// Init format
        final SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:aa", java.util.Locale.getDefault());
// Assign unmodifiable values
        dateTimeFragment.set24HoursMode(false);
        dateTimeFragment.setHighlightAMPMSelection(true);
        if (Type == START) {
            if (max_min_date != null) {
                dateTimeFragment.setMaximumDateTime(max_min_date);
                dateTimeFragment.setMinimumDateTime(new GregorianCalendar(YEAR, MONTH, DATE).getTime());
            }else{
                dateTimeFragment.setMinimumDateTime(new GregorianCalendar(YEAR, MONTH, DATE).getTime());
                dateTimeFragment.setMaximumDateTime(new GregorianCalendar(YEAR + 5, MONTH, DATE).getTime());
            }
        } else {
            if (max_min_date != null) {
                Calendar endCal = Calendar.getInstance();
                endCal.setTime(max_min_date);
                endCal.add(endCal.get(HOUR),3);
                dateTimeFragment.setMinimumDateTime(endCal.getTime());
                dateTimeFragment.setMaximumDateTime(new GregorianCalendar(YEAR, MONTH, DATE).getTime());
            }else{
                dateTimeFragment.setMinimumDateTime(new GregorianCalendar(YEAR, MONTH, DATE+3).getTime());
                dateTimeFragment.setMaximumDateTime(new GregorianCalendar(YEAR + 5, MONTH, DATE).getTime());
            }
        }

// Define new day and month format
        try {
            dateTimeFragment.setSimpleDateMonthAndDayFormat(new SimpleDateFormat("MMMM dd", Locale.getDefault()));
        } catch (SwitchDateTimeDialogFragment.SimpleDateMonthAndDayFormatException e) {
            Log.e("ParkingDetailsExc", e.getMessage());
        }

// Setting Date and time
        dateTimeFragment.startAtCalendarView();

        if (defaultDate != null) {
            if(Type==START) {
                dateTimeFragment.setDefaultDateTime(defaultDate);
            }else{
                Calendar endCal = Calendar.getInstance();
                endCal.setTime(max_min_date);
                endCal.add(endCal.get(HOUR),3);
                dateTimeFragment.setDefaultDateTime(endCal.getTime());
            }
        } else {
            dateTimeFragment.setDefaultDateTime(new GregorianCalendar(YEAR, MONTH, DATE + 3, HOUR, MIN).getTime());
        }
        dateTimeFragment.show(getSupportFragmentManager(), TAG_DATETIME_FRAGMENT);


// Set listener for date
// Or use dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener() {
        dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonWithNeutralClickListener() {
            @Override
            public void onPositiveButtonClick(Date date) {
                String str = myDateFormat.format(date).replace("AM", "am").replace("PM","pm");
                tvDate.setText(str);
            }

            @Override
            public void onNegativeButtonClick(Date date) {
// Do nothing
            }

            @Override
            public void onNeutralButtonClick(Date date) {
// Optional if neutral button does'nt exists
                tvDate.setText("");
            }
        });
    }

    private String formatDateTime(String dateToRestrict) {
        String formattedDate="";
        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:aa");
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date oldDate= null;
        try {
            oldDate= oldFormat.parse(dateToRestrict);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Calendar c = Calendar.getInstance();
        return formattedDate;
    }

    private void getLotsDetails(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Result_LotsDetail>call = apiInterface.Lots_details(lot_id);
        call.enqueue(new Callback<Result_LotsDetail>() {
            @Override
            public void onResponse(Call<Result_LotsDetail> call, Response<Result_LotsDetail> response) {
                try {
                    boolean status = response.body().getStatus();


                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Result_LotsDetail> call, Throwable t) {

            }
        });
    }


}

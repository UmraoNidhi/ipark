package com.iparksimple.app.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.iparksimple.app.Activity.ui.Airport.AirportFragment;
import com.iparksimple.app.Activity.ui.Monthly.MonthlyFragment;
import com.iparksimple.app.Activity.ui.Notification.NotificationFragment;
import com.iparksimple.app.Activity.ui.Vehicles.VehiclesFragment;
import com.iparksimple.app.Activity.ui.book.BookFragment;
import com.iparksimple.app.Activity.ui.booking.BookingFragment;
import com.iparksimple.app.Activity.ui.daily.DailyFragment;
import com.iparksimple.app.Activity.ui.payment.PaymentFragment;
import com.iparksimple.app.Activity.ui.profile.ProfileFragment;
import com.iparksimple.app.Fragment.AboutUsFragment;
import com.iparksimple.app.Fragment.Booking_historyFragment;
import com.iparksimple.app.Fragment.ContactUsFragment;
import com.iparksimple.app.Fragment.Home_Map_fragment;
import com.iparksimple.app.Fragment.PrivacyFragment;
import com.iparksimple.app.Fragment.ReportFragment;
import com.iparksimple.app.R;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    public static BottomNavigationView bottomNavigationView;
    FragmentTransaction fragmentTransaction;
    String currentFrag, backStateName;
    public static Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        bottomNavigationView = findViewById(R.id.nav_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.left_menu);
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setBackgroundResource(R.color.white);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.left_menu);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);

            }
        });
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Fragment fragment = new Home_Map_fragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        mMenu = menu;
        return true;
    }

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.navigation_Daily:
                            selectedFragment = new DailyFragment();
//                            MenuItem menuItemMap= mMenu.findItem(R.id.Map_view);
//                            menuItemMap.setVisible(true);
//                            MenuItem menuItemList= mMenu.findItem(R.id.List_view);
//                            menuItemList.setVisible(false);
//                            MenuItem menuItem = mMenu.findItem(R.id.Change_password);
//                            menuItem.setVisible(false);
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).addToBackStack(null).commit();
                            setTitle("Hourly/Daily");
                            break;
                        case R.id.navigation_Monthly:
                            selectedFragment = new MonthlyFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).addToBackStack(null).commit();
                            setTitle("Monthly");
                            break;
                        case R.id.navigation_Airport:
                            selectedFragment = new AirportFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).addToBackStack(null).commit();
                            setTitle("Airport");
                            break;
                    }
                    return true;
                }
            };


    private void displaySelectedFragment(Fragment fragment, String backstack_name) {
        currentFrag = backstack_name;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, backstack_name);
        fragmentTransaction.addToBackStack(backstack_name);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_Book) {
            displaySelectedFragment(new BookFragment(), backStateName);
            backStateName = "Book";
            setTitle("Book");
        } else if (id == R.id.nav_Profile) {
            displaySelectedFragment(new ProfileFragment(), backStateName);
            backStateName = "Profile";
            setTitle(item.getTitle());
        } else if (id == R.id.nav_Booking) {
            displaySelectedFragment(new BookingFragment(), backStateName);
            backStateName = "Booking";
            setTitle(item.getTitle());
        } else if (id == R.id.nav_Notification) {
            displaySelectedFragment(new NotificationFragment(), backStateName);
            backStateName = "Notification";
            setTitle(item.getTitle());
//        }else if (id == R.id.nav_Notifications) {
//            displaySelectedFragment(new NotificationFragment(), backStateName);
//            backStateName = "Notification Fragment";
//            setTitle(item.getTitle());
        } else if (id == R.id.nav_Vehicle) {
            displaySelectedFragment(new VehiclesFragment(), backStateName);
            backStateName = "Vehicle";
            setTitle(item.getTitle());

        } else if (id == R.id.nav_Payment) {
            displaySelectedFragment(new PaymentFragment(), backStateName);
            backStateName = "Payment";
            setTitle(item.getTitle());
               }else if (id == R.id.Contact){
            displaySelectedFragment(new ContactUsFragment(),backStateName);
            backStateName = "Contact Us";
            setTitle(item.getTitle());

        }else if (id == R.id.About){
            displaySelectedFragment(new AboutUsFragment(),backStateName);
            backStateName = "About";
            setTitle(item.getTitle());

        }else if (id == R.id.Privacy){
            displaySelectedFragment(new PrivacyFragment(),backStateName);
            backStateName = "Privacy";
            setTitle(item.getTitle());

        }else if (id == R.id.Report){
            displaySelectedFragment(new ReportFragment(),backStateName);
            backStateName = "Report";
            setTitle(item.getTitle());
        }
        else if (id == R.id.Share){
            shareApp();

        }else if (id == R.id.nav_Logout){
            openLogoutConfirm();

        }
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ExitPopup(){
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.logout_popup);
        TextView text_yes=dialog.findViewById(R.id.TvExit);
        TextView text_No=dialog.findViewById(R.id.TvClose);
        TextView txtTitle = dialog.findViewById(R.id.txtSubTitle);
        txtTitle.setText("Alert");
        TextView txtSubTitle = dialog.findViewById(R.id.txtSubTitle);
        txtSubTitle.setText("Are you sure want to Exit?");
        text_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                dialog.dismiss();
            }
        });
        text_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void shareApp() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Hey I just found this interesting app about Warehouses"+"\n"+"http://algosoftech.in/demo/bigbox/";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "BOT");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));

    }

    private void openLogoutConfirm() {

        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.logout_popup);
        TextView text_yes=dialog.findViewById(R.id.TvExit);
        TextView text_No=dialog.findViewById(R.id.TvClose);
        TextView txtTitle = dialog.findViewById(R.id.txtSubTitle);
        txtTitle.setText("Alert");
        TextView txtSubTitle = dialog.findViewById(R.id.txtSubTitle);
        txtSubTitle.setText("Are you sure want to logout?");
        text_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!PreferenceUtil.getSaveLoginFlag(HomeActivity.this)) {
//                    PreferenceUtil.clearPreferenceObject(HomeActivity.this);
//                }
//                PreferenceUtil.setUserEmail(HomeActivity.this,"");
                dialog.dismiss();
                Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });
        text_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {

            getSupportFragmentManager().popBackStackImmediate();
        } else {
            ExitPopup();
        }
    }
}






package com.iparksimple.app.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;
import com.iparksimple.app.Activity.ui.Airport.AirportFragment;
import com.iparksimple.app.Activity.ui.Monthly.MonthlyFragment;
import com.iparksimple.app.Activity.ui.Notification.NotificationFragment;
import com.iparksimple.app.Activity.ui.booking.BookingFragment;
import com.iparksimple.app.Activity.ui.daily.DailyFragment;
import com.iparksimple.app.Activity.ui.profile.ProfileFragment;
import com.iparksimple.app.Fragment.AboutUsFragment;
import com.iparksimple.app.Fragment.ContactUsFragment;
import com.iparksimple.app.Fragment.Home_Map_fragment;
import com.iparksimple.app.Fragment.PrivacyFragment;
import com.iparksimple.app.Fragment.ReportFragment;
import com.iparksimple.app.R;
import com.iparksimple.app.utils.PreferenceUtil;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    public static BottomNavigationView bottomNavigationView;
    FragmentTransaction fragmentTransaction;
    String currentFrag, backStateName,Token,UserName;
    public static Menu mMenu;
    boolean isUserLoggedIn = false;
    TextView bt_login,bt_signup,header_userName;
    NavigationView navigationView;
    private static final int PERMISSION_REQUEST_CODE = 200;
    Boolean isFromBack=false;
    RelativeLayout searchLayout;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);


        // Login Token//
        Token = PreferenceUtil.getAccessTokenFromLogin(this);
        UserName = PreferenceUtil.getUserName(this);


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
        searchLayout= toolbar.findViewById(R.id.rl_searchview);
        searchLayout.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.left_menu);
//        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setBackgroundResource(R.color.white);
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setToolBar(true);
        ViewGroup actionBarLayout = (ViewGroup) HomeActivity.this.getLayoutInflater().inflate(R.layout.custom_title, null);
        ActionBar actionBar = HomeActivity.this.getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(actionBarLayout);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.custom_title);

        textView = findViewById(R.id.textviewactivityname);

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
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Fragment fragment = new Home_Map_fragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null).commit();
        View header = navigationView.getHeaderView(0);

        bt_login = header.findViewById(R.id.tv_nav_login);
        bt_signup = header.findViewById(R.id.tv_nav_signup);
        header_userName = header.findViewById(R.id.Text_name);
        header_userName.setText(toCamelCase(UserName));

        requestPermission();

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setToolBar(boolean b) {
        if(b) {
            searchLayout.setVisibility(View.VISIBLE);
        }else{
            searchLayout.setVisibility(View.GONE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        MenuItem searchMenuItem = menu.findItem( R.id.action_search );
//        searchMenuItem.expandActionView();
        searchMenuItem.setVisible(false);
        mMenu = menu;
        return true;
    }

    private void showNavItems(NavigationView navigationView) {
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_Logout).setVisible(true);
        nav_Menu.findItem(R.id.nav_Profile).setVisible(true);
    }

    private void hideNavItems(NavigationView navigationView) {
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_Logout).setVisible(false);
        nav_Menu.findItem(R.id.nav_Profile).setVisible(false);
        nav_Menu.findItem(R.id.nav_Vehicle).setVisible(false);
    }

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.navigation_Daily:
                            setBottomNavAnimation(item.getItemId(),R.id.navigation_Monthly,R.id.navigation_Airport);
                            if(getSupportFragmentManager().getBackStackEntryCount()>0){
                                getSupportFragmentManager().popBackStackImmediate();
                            }
                            selectedFragment = new DailyFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).addToBackStack(null).commit();
                            textView.setText("Hourly/Daily");
                            break;
                        case R.id.navigation_Monthly:
                            setBottomNavAnimation(item.getItemId(),R.id.navigation_Daily,R.id.navigation_Airport);
                            if(getSupportFragmentManager().getBackStackEntryCount()>0){
                                getSupportFragmentManager().popBackStackImmediate();
                            }
                            selectedFragment = new MonthlyFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).addToBackStack(MonthlyFragment.class.getSimpleName()).commit();
                            textView.setText("Monthly  ");
                            break;
                        case R.id.navigation_Airport:
                            setBottomNavAnimation(item.getItemId(),R.id.navigation_Monthly,R.id.navigation_Daily);
                            if(getSupportFragmentManager().getBackStackEntryCount()>0){
                                getSupportFragmentManager().popBackStackImmediate();
                            }
                            selectedFragment = new AirportFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).addToBackStack(AirportFragment.class.getSimpleName()).commit();
                            textView.setText(" Airport");
                            break;
                    }
                    return true;
                }
            };

    //  Added by Archana--> to set background color of bottom nav
    private void setBottomNavAnimation(int selId, int unSelId1, int unSelId2) {
        View selected= bottomNavigationView.findViewById(selId);
        View unselected1= bottomNavigationView.findViewById(unSelId1);
        View unselected2= bottomNavigationView.findViewById(unSelId2);
        selected.setBackgroundColor(getResources().getColor(R.color.blue));
        unselected1.setBackgroundColor(getResources().getColor(R.color.white));
        unselected2.setBackgroundColor(getResources().getColor(R.color.white));
    }


    private void displaySelectedFragment(Fragment fragment) {
//        currentFrag = backstack_name;
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount()>1){
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        Log.e("Count",":"+manager.getBackStackEntryCount());
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(Fragment.class.getSimpleName());
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        setInitialScreen();
        if (!PreferenceUtil.getAccessTokenFromLogin(this).isEmpty()){
            isUserLoggedIn=true;
//            String UserName  = PreferenceUtil.getUserName(this);
            if (!PreferenceUtil.getAccessTokenFromLogin(this).isEmpty()){
                header_userName.setText("iPark");
                bt_signup.setVisibility(View.GONE);
                bt_login.setVisibility(View.GONE);
                showNavItems(navigationView);
            }else{
                header_userName.setText("iPark");
                bt_signup.setVisibility(View.VISIBLE);
                bt_login.setVisibility(View.VISIBLE);
                hideNavItems(navigationView);
            }

        }else {
            header_userName.setText("iPark");
            bt_signup.setVisibility(View.VISIBLE);
            bt_login.setVisibility(View.VISIBLE);
            hideNavItems(navigationView);
        }

        super.onResume();
    }

    private void setInitialScreen() {
        invalidateOptionsMenu();
        setToolBar(true);
        bottomNavigationView.setVisibility(View.VISIBLE);
        View view = bottomNavigationView.findViewById(R.id.navigation_Daily);
        view.performClick();

        Fragment fragment = new Home_Map_fragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null).commit();
//        setTitle(" Book");
        backStateName = "Book";
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_Book) {
            displaySelectedFragment(new Home_Map_fragment());
            backStateName = "Book";
            textView.setText("Book ");
        } else if (id == R.id.nav_Profile) {
            displaySelectedFragment(new ProfileFragment());
            backStateName = "Profile";
            textView.setText(item.getTitle());
        } else if (id == R.id.nav_Booking) {
            displaySelectedFragment(new BookingFragment());
            backStateName = "Booking";
            textView.setText(item.getTitle());
        } else if (id == R.id.nav_Notification) {
            displaySelectedFragment(new NotificationFragment());
            backStateName = "Notification";
            textView.setText(item.getTitle());
//        }else if (id == R.id.nav_Notifications) {
//            displaySelectedFragment(new NotificationFragment(), backStateName);
//            backStateName = "Notification Fragment";
//            setTitle(item.getTitle());
//        }else if (id == R.id.Contact){
//            displaySelectedFragment(new ContactUsFragment());
//            backStateName = "Contact Us";
//            setTitle(item.getTitle());
//
//        }else if (id == R.id.About){
//            displaySelectedFragment(new AboutUsFragment());
//            backStateName = "About";
//            setTitle(item.getTitle());
//
//        }else if (id == R.id.Privacy){
//            displaySelectedFragment(new PrivacyFragment());
//            backStateName = "Privacy";
//            setTitle(item.getTitle());

        }else if (id == R.id.Report){
            displaySelectedFragment(new ReportFragment());
            backStateName = "Report";
            textView.setText(item.getTitle());
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
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Hey I just found this interesting app about Car Parking"+"\n"+"https://www.iparksimple.com/";
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "BOT");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
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
//                }
                PreferenceUtil.clearPreferenceObject(HomeActivity.this);

                PreferenceUtil.setUserEmail(HomeActivity.this,"");
                PreferenceUtil.setAccessTokenFromLogin(HomeActivity.this,"");
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
        } else if (getSupportFragmentManager().getBackStackEntryCount()>0) {
            getSupportFragmentManager().popBackStackImmediate();
            setInitialScreen();
        } else {
            ExitPopup();
        }
    }


    public static String toCamelCase(final String init) {
        if (init==null)
            return null;

        final StringBuilder ret = new StringBuilder(init.length());

        for (final String word : init.split(" ")) {
            if (!word.isEmpty()) {
                ret.append(word.substring(0, 1).toUpperCase());
                ret.append(word.substring(1).toLowerCase());
            }
            if (!(ret.length()==init.length()))
                ret.append(" ");
        }

        return ret.toString();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (locationAccepted ) {
//                        Snackbar.make(view, "Permission Granted, Now you can access location data and camera.", Snackbar.LENGTH_LONG).show();
                    } else {

                    }
                }
                break;
        }
    }
}






package com.sict.hotelapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sict.hotelapp.Activity.BookingActivity;
import com.sict.hotelapp.Activity.LikeActivity;
import com.sict.hotelapp.Fragment.HomeFragment;
import com.sict.hotelapp.Fragment.PersonFragment;
import com.sict.hotelapp.Fragment.ReviewFragment;
import com.sict.hotelapp.Fragment.SearchFragment;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    private Toolbar toolbar;
    private static int ID_User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        getSupportActionBar().hide();
        toolbar = findViewById( R.id.toolbar );
        toolbar.setOnMenuItemClickListener( this );

        //menu bottom
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        toolbar.setTitle("Hotel Booking");
        loadFragment(new HomeFragment());

        Intent intent = getIntent();
        ID_User = intent.getIntExtra("ID_User",0);

    }
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_Booking:
                Intent intent = new Intent( getApplicationContext(), LikeActivity.class );
                startActivity( intent );
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_Home:
                    toolbar.setTitle("Hotel Booking");
                    fragment = new HomeFragment();
                    loadFragment( (HomeFragment) fragment );
                    return true;
                case R.id.navigation_Search:
                    toolbar.setTitle("Tìm kiếm");
                    fragment = new SearchFragment();
                    loadFragment( (SearchFragment) fragment );
                    return true;
                case R.id.navigation_Review:
                    toolbar.setTitle("Review");
                    fragment = new ReviewFragment();
                    loadFragment( (ReviewFragment) fragment );
                    return true;
                case R.id.navigation_Person:
                    toolbar.setTitle("Cá nhân");
                    fragment = new PersonFragment();
                    loadFragment( (PersonFragment) fragment );
                    return true;
                default:
                    showLockTaskEscapeMessage();
            }
            return false;
        }
    };
    public static int getID_User() {
        return ID_User;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
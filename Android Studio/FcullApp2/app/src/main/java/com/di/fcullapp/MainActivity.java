package com.di.fcullapp;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch(item.getItemId()){
                        case R.id.nav_events:
                            selectedFragment = new FragmentEvents();
                            break;
                        case R.id.nav_communities:
                            selectedFragment = new FragmentCommunities();
                            break;
                        case R.id.nav_user:
                            selectedFragment = new FragmentUser();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(
                            R.id.info_container, selectedFragment).commit();

                    return true;
                }
            };
}

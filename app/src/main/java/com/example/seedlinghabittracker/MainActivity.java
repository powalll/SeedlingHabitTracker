package com.example.seedlinghabittracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bottom nav code
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, new HomeFragment()).commit();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFrag = null;
                    switch(menuItem.getItemId())
                    {
                        case R.id.nav_home:
                            selectedFrag = new HomeFragment();
                            break;
                        case R.id.nav_streak:
                            selectedFrag = new StreakFragment();
                            break;
                        case R.id.nav_more:
                            selectedFrag = new MoreFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, selectedFrag).commit();
                    return true;
                }
            };
}
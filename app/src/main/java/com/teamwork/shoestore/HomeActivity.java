package com.teamwork.shoestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    MaterialToolbar toolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bootomnaviview);
        drawerLayout = findViewById(R.id.drawyerlayout);
        toolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.framelayout);
        navigationView = findViewById(R.id.navbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                HomeActivity.this, drawerLayout,toolbar,R.string.drawerclose,R.string.draweropen
        );
        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
               int Itemid = menuItem.getItemId();
                if(Itemid==R.id.logout)
                {
                    Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                    startActivity(intent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    finish();

                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.home);
        FragmentManager fmanager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fmanager.beginTransaction();
        fragmentTransaction.add(R.id.framelayout,new HomeFragment());
        fragmentTransaction.commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.home)
                {
                    FragmentManager fmanager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fmanager.beginTransaction();
                    fragmentTransaction.add(R.id.framelayout,new HomeFragment());
                    fragmentTransaction.commit();

                } else if (item.getItemId()==R.id.profile)
                {
                    FragmentManager fmanager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fmanager.beginTransaction();
                    fragmentTransaction.add(R.id.framelayout,new ProfileFragment());
                    fragmentTransaction.commit();

                } else if (item.getItemId()==R.id.cart)
                {
                    FragmentManager fmanager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fmanager.beginTransaction();
                    fragmentTransaction.add(R.id.framelayout,new CartFragment());
                    fragmentTransaction.commit();
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }
}
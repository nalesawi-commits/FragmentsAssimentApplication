package com.example.fragmentsassimentapplication;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.activity.OnBackPressedCallback;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNav;
    Toolbar toolbar;

    Fragment eventsFragment = new EventFragment();
    Fragment galleryFragment = new GalleryFragment();
    Fragment placesFragment = new PlacesFragment();
    Fragment newsFragment = new NewsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        bottomNav = findViewById(R.id.bottomNav);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_profile) Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show();
            else if (id == R.id.nav_settings) Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
            else if (id == R.id.nav_logout) Toast.makeText(this, "Logout Clicked", Toast.LENGTH_SHORT).show();
            drawerLayout.closeDrawers();
            return true;
        });

        // عرض EventFragment أول مرة
        setCurrentFragment(eventsFragment, false);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.eventsFragment) setCurrentFragment(eventsFragment, true);
            else if (id == R.id.galleryFragment) setCurrentFragment(galleryFragment, true);
            else if (id == R.id.newsFragment) setCurrentFragment(newsFragment, true);
            else if (id == R.id.placesFragment) setCurrentFragment(placesFragment, true);
            return true;
        });

        // زر الرجوع → يرجع دائمًا لـ EventFragment
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainContainer);
                if (!(currentFragment instanceof EventFragment)) {
                    setCurrentFragment(eventsFragment, false);
                    bottomNav.setSelectedItemId(R.id.eventsFragment);
                } else {
                    finish(); // أصلاً في EventFragment → خرج
                }
            }
        });
    }

    private void setCurrentFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        androidx.fragment.app.FragmentTransaction transaction = fm.beginTransaction()
                .replace(R.id.mainContainer, fragment);

        if (addToBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }
}

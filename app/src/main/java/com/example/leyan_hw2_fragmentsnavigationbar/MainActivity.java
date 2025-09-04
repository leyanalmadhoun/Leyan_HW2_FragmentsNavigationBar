package com.example.leyan_hw2_fragmentsnavigationbar;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private MaterialToolbar toolbar;
    private ImageButton btnBack;

    private final HomeFragment homeFragment = new HomeFragment();
    private final PersonFragment personFragment = new PersonFragment();
    private final SettingFragment settingFragment = new SettingFragment();

    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        toolbar = findViewById(R.id.toolbar);
        btnBack = findViewById(R.id.btnBack);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.flFragment, settingFragment, "settings").hide(settingFragment)
                .add(R.id.flFragment, personFragment, "profile").hide(personFragment)
                .add(R.id.flFragment, homeFragment, "home")
                .commit();

        activeFragment = homeFragment;

        bottomNavigationView.setSelectedItemId(R.id.home);
        navigationView.setCheckedItem(R.id.drawer_home);

        btnBack.setOnClickListener(v -> {
            handleBackPressedLikeYouWant();
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                setCurrentFragment(homeFragment);
                navigationView.setCheckedItem(R.id.drawer_home);
                return true;
            } else if (item.getItemId() == R.id.profile) {
                setCurrentFragment(personFragment);
                navigationView.setCheckedItem(R.id.drawer_profile);
                return true;
            } else if (item.getItemId() == R.id.settings) {
                setCurrentFragment(settingFragment);
                navigationView.setCheckedItem(R.id.drawer_settings);
                return true;
            }
            return false;
        });

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            drawerLayout.closeDrawers();
            int id = menuItem.getItemId();
            if (id == R.id.drawer_home) {
                bottomNavigationView.setSelectedItemId(R.id.home);
            } else if (id == R.id.drawer_profile) {
                bottomNavigationView.setSelectedItemId(R.id.profile);
            } else if (id == R.id.drawer_settings) {
                bottomNavigationView.setSelectedItemId(R.id.settings);
            }
            return true;
        });
    }

    private void setCurrentFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(activeFragment).show(fragment).commit();
        activeFragment = fragment;
    }

    private boolean isOnHome() {
        return bottomNavigationView.getSelectedItemId() == R.id.home;
    }

    private void handleBackPressedLikeYouWant() {
        if (!isOnHome()) {
            bottomNavigationView.setSelectedItemId(R.id.home);
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        handleBackPressedLikeYouWant();
    }
}
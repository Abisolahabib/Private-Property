package uk.ac.tees.b1592041.privateproperty;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainPageActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    SettingsFragment settingsFragment = new SettingsFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    SearchFragment searchFragment = new SearchFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        ImageView logoutBtn = findViewById(R.id.logout);

        getSupportFragmentManager().beginTransaction().replace(com.google.android.material.composethemeadapter.R.id.container, homeFragment).commit();
        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.Map);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            Intent intent = new Intent(MainPageActivity.this, SettingsActivity.class);


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Homepage:
                        getSupportFragmentManager().beginTransaction().replace(com.google.android.material.composethemeadapter.R.id.container, homeFragment).commit();
                        return true;
                    case R.id.Map:
                        getSupportFragmentManager().beginTransaction().replace(com.google.android.material.composethemeadapter.R.id.container, notificationFragment).commit();
                        return true;
                    case R.id.Settings:
                        getSupportFragmentManager().beginTransaction().replace(com.google.android.material.composethemeadapter.R.id.container, settingsFragment).commit();

                        return true;
                    case R.id.Search:
                        getSupportFragmentManager().beginTransaction().replace(com.google.android.material.composethemeadapter.R.id.container, searchFragment).commit();
                        return true;
                    case R.id.Profilefrag:
                        getSupportFragmentManager().beginTransaction().replace(com.google.android.material.composethemeadapter.R.id.container, profileFragment).commit();
                        return true;
                }
                return false;
            }

        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPageActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });
    }


}
package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.Articles.ArticlesActivity;
import com.example.fitnessapp.EditProfile.UploadProfileActivity;
import com.example.fitnessapp.FoodCalories.CalorieActivity;
import com.example.fitnessapp.Fragments.AboutFragment;
import com.example.fitnessapp.Fragments.HomeFragment;
import com.example.fitnessapp.Fragments.SettingsFragment;
import com.example.fitnessapp.Fragments.ShareFragment;
import com.example.fitnessapp.HallOfFame.HallOfFameActivity;
import com.example.fitnessapp.PrivateWorkout.WorkoutActivity;
import com.example.fitnessapp.Users.LoginActivity;
import com.example.fitnessapp.Users.User;
import com.example.fitnessapp.Users.UserOpenHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    FloatingActionButton fab;
    long userId;
    Button successButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        successButton = findViewById(R.id.successButton);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
            toolbar.setTitle("Home");
        }

        View header = navigationView.getHeaderView(0);
        ImageView navImage = (ImageView) header.findViewById(R.id.navImage);
        TextView navName = (TextView) header.findViewById(R.id.navName);
        TextView navEmail = (TextView) header.findViewById(R.id.navEmail);

        userId = getIntent().getLongExtra("userId", -1);


        UserOpenHelper dbHelper = new UserOpenHelper(this);
        dbHelper.open();
        User user = dbHelper.getById(userId);
        dbHelper.close();
        if (user == null){
            Toast.makeText(this, "No Profile Details", Toast.LENGTH_SHORT).show();
        } else {
            String name = user.getName();
            if (name == null)
                navName.setText("No Name");
            else
                navName.setText(name);
            navEmail.setText(user.getEmail());
            byte[] imageByte = user.getImage();
            if (imageByte != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                navImage.setImageBitmap(bitmap);
            }
        }
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, UploadProfileActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                toolbar.setTitle("Home");
                toolbar.setTitleTextAppearance(this, R.style.Theme_FitnessApp);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.nav_settings:
                toolbar.setTitle("Settings");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                break;
            case R.id.nav_share:
                toolbar.setTitle("Share");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShareFragment()).commit();
                break;
            case R.id.nav_about:
                toolbar.setTitle("About");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
                break;
            case R.id.nav_logout:
                showDialog(this);

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    public void move_to_calorie_activity(View v){
        startActivity(new Intent(this, CalorieActivity.class));
    }
    public void move_to_workout_activity(View v){
        startActivity(new Intent(this, WorkoutActivity.class));
    }
    public void move_to_article_activity(View v){
        startActivity(new Intent(this, ArticlesActivity.class));
    }
    public void move_to_acc_activity(View v){
        startActivity(new Intent(this, HallOfFameActivity.class));
    }
    public void move_to_upload(View v) {
        Intent intent = new Intent(this, UploadProfileActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    public void showDialog(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.success_dialog);


        Button dialogButton = (Button) dialog.findViewById(R.id.successDone);
        Button dialogButton2 = (Button) dialog.findViewById(R.id.successCancel);
        dialogButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Logout!", Toast.LENGTH_SHORT).show();
            }
        });



        dialog.show();

    }






}
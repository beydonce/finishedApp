
package com.example.fitnessapp.PrivateWorkout;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.fitnessapp.R;
import com.example.fitnessapp.Users.User;

import java.util.ArrayList;
import java.util.List;


public class WorkoutsScreen extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Workout> workouts;
    WorkoutAdapter adapter;
    WorkoutOpenHelper woh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts_screen);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        woh = new WorkoutOpenHelper(this);
        workouts = new ArrayList<>();
        adapter = new WorkoutAdapter(this, workouts);

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        woh.open();
        workouts.clear();
        workouts.addAll(woh.getAllWorkouts());
        woh.close();
        adapter.notifyDataSetChanged();
    }

    public void move_to_create_workout_activity(View v) {
        startActivity(new Intent(WorkoutsScreen.this, WholeWorkout.class));
    }
}

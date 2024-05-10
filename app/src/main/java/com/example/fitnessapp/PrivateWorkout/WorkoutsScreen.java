package com.example.fitnessapp.PrivateWorkout;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.fitnessapp.R;
import com.example.fitnessapp.Users.User;


public class WorkoutsScreen extends AppCompatActivity {
    private ActivityResultLauncher<Intent> addWorkoutActivityIntent;
    private Workout workoutFromAddWorkoutActivity;


//    @Override
//    protected void onStart() {
//        super.onStart();
//        // Handles result of addWorkoutActivity
//        addWorkoutActivityIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
//            if (result.getData() != null) {
//                workoutFromAddWorkoutActivity = (Workout) result.getData().getSerializableExtra("workout");
//                onWorkoutResult(workoutFromAddWorkoutActivity);
//            }
//        });
//
//        // Handles result of editUserActivity
//        editUserActivityIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
//            if (result.getResultCode() == Activity.RESULT_OK) {
//                if (result.getData() != null) {
//                    User user = (User) result.getData().getSerializableExtra("user");
//                    onEditedUserResult(user);
//                }
//            }
//        });
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts_screen);
    }

    public void move_to_create_workout_activity(View v) {
    startActivity(new Intent(WorkoutsScreen.this, WholeWorkout.class));
    }
}
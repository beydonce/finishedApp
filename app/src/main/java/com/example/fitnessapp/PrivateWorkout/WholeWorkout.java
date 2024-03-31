package com.example.fitnessapp.PrivateWorkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.R;
import com.example.fitnessapp.PrivateWorkout.InputChecker;
import com.example.fitnessapp.PrivateWorkout.Set;
import com.example.fitnessapp.PrivateWorkout.Workout;
import com.example.fitnessapp.PrivateWorkout.SetAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class WholeWorkout extends AppCompatActivity {
    EditText editText_WorkoutName;
    ListView listView;
    ActivityResultLauncher<Intent> startCreateWorkoutIntent;
    FloatingActionButton floatingActionButton_done;

    SetAdapter setsAdapter;
    ActivityResultLauncher<Intent> startAddSetActivityIntent;
    List<Set> sets;

    @Override
    protected void onStart() {
        super.onStart();
        startAddSetActivityIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getData() != null) {
                Set set = (Set) result.getData().getSerializableExtra("set");
                onSetResult(set);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_workout);

        editText_WorkoutName = findViewById(R.id.activityAddWorkout_EditText_Name);
        listView = findViewById(R.id.addWorkout_ListView);
        floatingActionButton_done = findViewById(R.id.activityAddWorkout_FloatingActionButton_add);
        floatingActionButton_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workoutName = editText_WorkoutName.getText().toString().trim();
                // Checks that the workout name is valid
                if (!workoutName.isEmpty()) {
                    // If name is not empty, "send" the workout back Library activity where it will be added
                    if (InputChecker.isStringInputValid(workoutName)) {
                        // Checks if the workout has at least one set
                        if (sets.size() > 0) {
                            addWorkoutToActivityResultAndFinish(new Workout(workoutName, sets));
                        } else {
                            Toast.makeText(WholeWorkout.this, "Must contain at least one set!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Workout name is incorrect!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Workout Name Can't Be Empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // When pressing DONE, all the sets in the list will be added to the Database
        sets = new ArrayList<>();
        setsAdapter = new SetAdapter(this, sets, true);
        listView.setAdapter(setsAdapter);
        addSetAdditionButtonToListView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void addSetAdditionButtonToListView() {
        Button addSetButton = (Button) LayoutInflater.from(this).inflate(R.layout.add_set_button, listView, false);
        addSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAddSetActivity();
            }
        });
        listView.addFooterView(addSetButton);
    }

    private void launchAddSetActivity() {
        Intent addSetActivityIntent = new Intent(this, CreateWorkout.class);
        startAddSetActivityIntent.launch(addSetActivityIntent);
    }

    private void addWorkoutToActivityResultAndFinish(Workout workout) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("workout", workout);
        // Not set from models package
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    private void onSetResult(Set set) {
        setsAdapter.add(set);
        Toast.makeText(WholeWorkout.this, "Added Set " + set, Toast.LENGTH_SHORT).show();
    }


}
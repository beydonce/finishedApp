package com.example.fitnessapp.PrivateWorkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.R;
import com.example.fitnessapp.PrivateWorkout.Exercise;
import com.example.fitnessapp.PrivateWorkout.Set;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.example.main.services.AddSetServices;
//import com.example.main.services.AddSetServicesImplementation;

import java.util.ArrayList;
import java.util.List;


public class CreateWorkout extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner_exercise;
    private EditText editText_reps;
    private EditText editText_iterations;
    private EditText editText_restTime;
//    private Button save_button;
    private FloatingActionButton floatingActionButton_doneButton;


    private Exercise selectedExercise;
    private ArrayAdapter<Exercise> arrayAdapter;
    private List<Exercise> allExercises;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        spinner_exercise = findViewById(R.id.activityCreateWorkout_Spinner);
        editText_reps = findViewById(R.id.activityCreateWorkout_EditText_reps);
        editText_iterations = findViewById(R.id.activityCreateWorkout_EditText_iterations);
        editText_restTime = findViewById(R.id.activityCreateWorkout_EditText_restTime);
//        save_button = findViewById(R.id.saveButton);


        floatingActionButton_doneButton = findViewById(R.id.activityAddSet_FloatingActionButton_add);

        floatingActionButton_doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInputValid()) {
                    addSetToActivityResultAndFinish(new Set(
                            new Exercise(selectedExercise),
                            Integer.parseInt(editText_reps.getText().toString()),
                            Integer.parseInt(editText_iterations.getText().toString()),
                            Double.parseDouble(editText_restTime.getText().toString())
                    ));
                } else {
                    Toast.makeText(CreateWorkout.this, "Values Incorrect! ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ExerciseOpenHelper excop =  new ExerciseOpenHelper(this);
        excop.open();
        allExercises = excop.getAllExercises();
        excop.close();


        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, allExercises);
        spinner_exercise.setAdapter(arrayAdapter);
        spinner_exercise.setOnItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedExercise = allExercises.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    private boolean isInputValid() {
        String reps = editText_reps.getText().toString();
        String iterations = editText_iterations.getText().toString();
        String restTime = editText_restTime.getText().toString();

        if (!reps.isEmpty() && !iterations.isEmpty() && !restTime.isEmpty()) {
            int repsValue = Integer.parseInt(reps);
            int iterationsValue = Integer.parseInt(iterations);
            double restTimeValue = Double.parseDouble(restTime);

            return repsValue > 0 && iterationsValue > 0 && restTimeValue > 0;
        }
        return false;
    }

    private void addSetToActivityResultAndFinish(Set set) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("set", set);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }


}
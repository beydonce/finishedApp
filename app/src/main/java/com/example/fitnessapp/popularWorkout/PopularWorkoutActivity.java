package com.example.fitnessapp.popularWorkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fitnessapp.R;

import java.util.ArrayList;
import java.util.List;

public class PopularWorkoutActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<PopularWorkoutDataClass> dataList;
    PopularWorkoutAdapter adapter;
    PopularWorkoutDataClass androidData;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_workout);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(PopularWorkoutActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();
        androidData = new PopularWorkoutDataClass("AB WORKOUT", R.string.camera, "DETAILS", R.drawable.weightf);
        dataList.add(androidData);
        androidData = new PopularWorkoutDataClass("ABC WORKOUT", R.string.recyclerview, "DETAILS", R.drawable.gyms);
        dataList.add(androidData);
        androidData = new PopularWorkoutDataClass("FBW – Full Body Workout", R.string.date, "DETAILS", R.drawable.gymthird);
        dataList.add(androidData);
        androidData = new PopularWorkoutDataClass(" StrongLifts 5×5", R.string.edit, "DETAILS", R.drawable.gymfifth);
        dataList.add(androidData);
        androidData = new PopularWorkoutDataClass("Bodyweight Workout", R.string.rating, "DETAILS", R.drawable.gymfour);
        dataList.add(androidData);
        adapter = new PopularWorkoutAdapter(PopularWorkoutActivity.this, dataList);
        recyclerView.setAdapter(adapter);
    }
    private void searchList(String text){
        List<PopularWorkoutDataClass> dataSearchList = new ArrayList<>();
        for (PopularWorkoutDataClass data : dataList){
            if (data.getDataTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()){
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }
}
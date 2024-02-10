package com.example.fitnessapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fitnessapp.databinding.ActivityCalorieBinding;
import java.util.ArrayList;
public class CalorieActivity extends AppCompatActivity {
    ActivityCalorieBinding binding;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    ListData listData;

    SearchView searchView;
    ListView mylistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalorieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int[] imageList = {R.drawable.macandcheeze, R.drawable.noodles, R.drawable.cake, R.drawable.pancakes, R.drawable.pizza, R.drawable.hamburger, R.drawable.chips};
        int[] ingredientList = {R.string.pastaIngredients, R.string.maggiIngredients,R.string.cakeIngredients,R.string.pancakeIngredients,R.string.pizzaIngredients, R.string.burgerIngredients, R.string.friesIngredients};
        int[] descList = {R.string.pastaDesc, R.string.maggieDesc, R.string.cakeDesc,R.string.pancakeDesc,R.string.pizzaDesc, R.string.burgerDesc, R.string.friesDesc};
        String[] nameList = {"Cheese Pie" , "Spageti", "Cake", "Pancake", "Pizza","Burgers", "Fries", "lola"};
        String Nutritional = "Nutritional value";
        for (int i = 0; i < imageList.length; i++){
            listData = new ListData(nameList[i], Nutritional, ingredientList[i], descList[i], imageList[i]);
            dataArrayList.add(listData);
        }
        listAdapter = new ListAdapter(CalorieActivity.this, dataArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListData data = listAdapter.getItem(i);
                Intent intent = new Intent(CalorieActivity.this, DetailedActivity.class);
                intent.putExtra("name", data.name);
                intent.putExtra("time", data.time);
                intent.putExtra("ingredients", data.ingredients);
                intent.putExtra("desc", data.desc);
                intent.putExtra("image", data.image);
                startActivity(intent);
            }
        });

        searchView= findViewById(R.id.searchView);
        mylistView = findViewById(R.id.listview);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                listAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
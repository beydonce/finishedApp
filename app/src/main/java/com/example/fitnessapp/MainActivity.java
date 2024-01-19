package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.fir, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.sec, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.third, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.four, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.fifth, ScaleTypes.FIT));

    }
}
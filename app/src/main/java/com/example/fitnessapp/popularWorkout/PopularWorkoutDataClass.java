package com.example.fitnessapp.popularWorkout;

public class PopularWorkoutDataClass {
    private String dataTitle;
    private int dataDesc;
    private String dataLang;
    private int dataImage;
    public String getDataTitle() {
        return dataTitle;
    }
    public int getDataDesc() {
        return dataDesc;
    }
    public String getDataLang() {
        return dataLang;
    }
    public int getDataImage() {
        return dataImage;
    }
    public PopularWorkoutDataClass(String dataTitle, int dataDesc, String dataLang, int dataImage) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataLang = dataLang;
        this.dataImage = dataImage;
    }
}

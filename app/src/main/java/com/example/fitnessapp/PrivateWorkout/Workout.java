package com.example.fitnessapp.PrivateWorkout;

import java.io.Serializable;
import java.util.List;

public class Workout implements Serializable {
    private int id;
    private final String name;
    private final List<Set> sets;
    private final int totalTime;
    private int creatorId;

    public Workout(int id, String workoutName, List<Set> sets, int creatorId) {
        this.id = id;
        this.name = workoutName;
        this.sets = sets;
        this.totalTime = calcTotalTime(sets);
        this.creatorId = creatorId;
    }

    // No workout ID
    public Workout(String workoutName, List<Set> sets, int creatorId) {
        this.id = 0;
        this.name = workoutName;
        this.sets = sets;
        this.totalTime = calcTotalTime(sets);
        this.creatorId = creatorId;
    }

    // No User
    public Workout(String workoutName, List<Set> sets) {
        this.id = 0;
        this.name = workoutName;
        this.sets = sets;
        this.totalTime = calcTotalTime(sets);
        this.creatorId = 0;
    }

    public int getId() {
        return this.id;
    }

    public String getWorkoutName() {
        return this.name;
    }

    public List<Set> getSets() {
        return this.sets;
    }

    public int getTotalTime() {
        return this.totalTime;
    }

    public int getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    private static int calcTotalTime(List<Set> sets) {
        double totalTime = 0;
        for (Set set : sets) {
            // Get the rest time and the approximate time to perform exercises
            // (1 minute for set + and the rest time) * number of sets = Total Time
            totalTime += (1 * set.getSets() + set.getRestTime());
        }
        return (int) Math.round(totalTime);
    }

    @Override
    public String toString() {
        return String.format("[%s: %d]", this.name, this.totalTime);
    }

    public void setId(int id) {
        this.id = id;
    }

}


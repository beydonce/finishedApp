package com.example.fitnessapp.PrivateWorkout;
import java.io.Serializable;

public class Set implements Serializable {
    private int id;
    private final Exercise exercise;
    private final int reps;
    private final int iterations;
    private final double restTime;

    public Set(Set set) {
        this.id = set.getId();
        this.exercise = set.getExercise();
        this.reps = set.getReps();
        this.iterations = set.getSets();
        this.restTime = set.getRestTime();
    }

    public Set(int id, Exercise exercise, int reps, int sets, double restTime) {
        this.id = id;
        this.exercise = exercise;
        this.reps = reps;
        this.iterations = sets;
        this.restTime = restTime;
    }

    public Set(Exercise exercise, int reps, int sets, double restTime) {
        this.id = 0;
        this.exercise = exercise;
        this.reps = reps;
        this.iterations = sets;
        this.restTime = restTime;
    }

    public int getId() {
        return id;
    }

    public Exercise getExercise() {
        return this.exercise;
    }

    public int getReps() {
        return this.reps;
    }

    public int getSets() {
        return this.iterations;
    }

    public double getRestTime() {
        return this.restTime;
    }


    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return String.format("[ %s: %dx%d ]", this.exercise, this.reps, this.iterations);
    }
}

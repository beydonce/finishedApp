package com.example.fitnessapp.PrivateWorkout;
import java.io.Serializable;
public class Exercise implements Serializable {
    private int id;
    private String name;
    private int difficulty;

    private final int MIN_DIFFICULTY = 1;
    private final int MAX_DIFFICULTY = 5;

    public Exercise(int id, String name, int difficulty) {
        try {
            validateDifficulty(difficulty);
            this.id = id;
            this.name = name;
            this.difficulty = difficulty;
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // Copies an existing Exercise
    public Exercise(Exercise exercise) {
        try {
            validateDifficulty(exercise.getDifficulty());
            this.id = exercise.getId();
            this.name = exercise.getName();
            this.difficulty = exercise.getDifficulty();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    @Override
    public String toString() {
        return this.name;
    }

    private void validateDifficulty(int difficulty) throws Exception {
        if (difficulty < MIN_DIFFICULTY || difficulty > MAX_DIFFICULTY) {
            throw new RuntimeException("Difficulty Must Be Between " + MIN_DIFFICULTY + " And " + MAX_DIFFICULTY);
        }
    }

}

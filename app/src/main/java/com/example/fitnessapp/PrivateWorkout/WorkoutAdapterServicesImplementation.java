//package com.example.fitnessapp.PrivateWorkout;
//
//import android.content.Context;
//
//import com.example.fitnessapp.Users.UserOpenHelper;
//
//import java.util.List;
//
//public class WorkoutAdapterServicesImplementation implements WorkoutAdapterServices {
//
//    UserOpenHelper userOpenHelper;
//    SetOpenHelper setOpenHelper;
//    WorkoutOpenHelper workoutOpenHelper;
//    WorkoutSetsOpenHelper workoutSetsOpenHelper;
//
//    public WorkoutAdapterServicesImplementation(Context context) {
//        userOpenHelper = new UserOpenHelper(context);
//        userOpenHelper.open();
//
//        setOpenHelper = new SetOpenHelper(context);
//        setOpenHelper.open();
//
//        workoutOpenHelper = new WorkoutOpenHelper(context);
//        workoutOpenHelper.open();
//
//        workoutSetsOpenHelper = new WorkoutSetsOpenHelper(context);
//        workoutSetsOpenHelper.open();
//    }
//
//    @Override
//    public String getCreatorOfWorkout(Workout workout) {
//        return userOpenHelper.get(workout.getCreatorId()).getUsername();
//    }
//
//    @Override
//    public boolean userExists(int id) {
//        return userOpenHelper.exists(id);
//    }
//
//    @Override
//    public void deleteWorkout(Workout workout) {
//        List<Set> sets = workout.getSets();
//        // Drops all the set columns
//        for (Set set : sets) {
//            setOpenHelper.delete(set);
//        }
//
//        // Drops the workout column
//        workoutOpenHelper.delete(workout);
//
//        // Drops all the columns where the workout id is
//        workoutSetsOpenHelper.delete(workout);
//    }
//
//    @Override
//    public void destroy() {
//        userOpenHelper.close();
//        setOpenHelper.close();
//        workoutOpenHelper.close();
//        workoutSetsOpenHelper.close();
//    }
//}

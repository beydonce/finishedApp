//package com.example.fitnessapp.PrivateWorkout;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//
//
//import com.example.fitnessapp.Users.User;
//
//import java.util.List;
//
//public class WorkoutAdapter extends ArrayAdapter<Workout> {
//    WorkoutAdapterServicesImplementation services;
//    User connectedUser;
//
//    public WorkoutAdapter(@NonNull Context context, int resource, List<Workout> workouts, User connectedUser) {
//        super(context, resource, workouts);
//        services = new WorkoutAdapterServicesImplementation(getContext());
//        this.connectedUser = connectedUser;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // Get the data item for this position
//        Workout workout = getItem(position);
//
//        // Check if an existing view is being reused, otherwise inflate the view
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.workout_list_item, parent, false);
//        }
//
//        // Checks if user exists
//        if (services.userExists(workout.getCreatorId())) {
//            ((TextView) convertView.findViewById(R.id.workout_listItem_TextView_uploadedBy)).setText("Uploaded By: " + services.getCreatorOfWorkout(workout));
//        } else {
//            ((TextView) convertView.findViewById(R.id.workout_listItem_TextView_uploadedBy)).setText("Uploaded By: " + "[deleted]");
//        }
//
//        ((TextView) convertView.findViewById(R.id.workout_listItem_TextView_Name)).setText(workout.getWorkoutName());
//        ((TextView) convertView.findViewById(R.id.workout_listItem_TextView_length)).setText(Integer.toString(workout.getTotalTime()) + " Minutes");
//        ImageView imageView = convertView.findViewById(R.id.workout_listItem_ImageView);
//
//        // If the connected user has workouts, allow the option to delete them
//        if (services.getCreatorOfWorkout(workout).equals(connectedUser.getUsername())) {
//            imageView.setVisibility(View.VISIBLE);
//        }
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Delete the workout
//                services.deleteWorkout(workout);
//
//                // Refresh the list
//                remove(workout);
//                notifyDataSetChanged();
//                Toast.makeText(getContext(), "Deleted workout " + workout, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        return convertView;
//    }
//}

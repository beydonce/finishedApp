package com.example.fitnessapp.PrivateWorkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.example.fitnessapp.PrivateWorkout.Set;

import java.util.List;


public class SetAdapter extends ArrayAdapter<Set> {
    private boolean allowDelete;

    public SetAdapter(Context context, List<Set> sets, boolean allowDelete) {
        super(context, 0, sets);
        this.allowDelete = allowDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Set set = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.set_list_item, parent, false);
        }

        // Set the TextView etc..
        ((TextView) convertView.findViewById(R.id.set_listItem_TextView_Exercise)).setText(set.getExercise().getName());
        ((TextView) convertView.findViewById(R.id.set_listItem_TextView_Reps_Sets)).setText(String.format("%d Reps X %d", set.getReps(), set.getSets()));
        ((TextView) convertView.findViewById(R.id.set_listItem_TextView_Rest_Time)).setText(String.format("Rest %.1f Minutes", set.getRestTime()));

        if (allowDelete) {
            ImageView image = convertView.findViewById(R.id.set_listItem_ImageView);
            image.setVisibility(View.VISIBLE);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    remove(set);
                    notifyDataSetChanged();
                    Toast.makeText(getContext(), "Deleted workout " + set, Toast.LENGTH_SHORT).show();
                }
            });
        }
        return convertView;
    }
}

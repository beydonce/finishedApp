package com.example.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
public class ListAdapter extends ArrayAdapter<ListData> {
    private List<ListData> originalValues;
    public ListAdapter(@NonNull Context context, ArrayList<ListData> dataArrayList) {
        super(context, R.layout.list_item, dataArrayList);
        originalValues = (List<ListData>) dataArrayList.clone();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListData listData = getItem(position);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        ImageView listImage = view.findViewById(R.id.listImage);
        TextView listName = view.findViewById(R.id.listName);
        TextView listTime = view.findViewById(R.id.listTime);
        listImage.setImageResource(listData.image);
        listName.setText(listData.name);
        listTime.setText(listData.time);
        return view;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {
                clear();
                addAll((List<ListData>) results.values);
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<ListData> filtered = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    results.count = originalValues.size();
                    results.values = originalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < originalValues.size(); i++) {
                        ListData data = originalValues.get(i);
                        if (data.name.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            filtered.add(data);
                        }
                    }
                    results.count = filtered.size();
                    results.values = filtered;
                }
                return results;
            }
        };
        return filter;
    }
}
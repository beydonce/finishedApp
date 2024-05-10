package com.example.fitnessapp.Users;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fitnessapp.R;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    Context context;
    List<User> objects;



    public UserAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.user_item, parent, false);

        TextView tvName = (TextView) view.findViewById(R.id.email);
        TextView tvPass = (TextView) view.findViewById(R.id.pass);

        User temp = objects.get(position);
        tvName.setText(temp.getEmail());
        tvPass.setText(temp.getPassword());


        return view;
    }
}

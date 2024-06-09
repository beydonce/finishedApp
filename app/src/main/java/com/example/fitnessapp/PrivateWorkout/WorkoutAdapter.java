package com.example.fitnessapp.PrivateWorkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {

    private Context context;
    private List<Workout> dataList;

    public void setSearchList(List<Workout> dataSearchList){
        this.dataList = dataSearchList;
        notifyDataSetChanged();
    }

    public WorkoutAdapter(Context context, List<Workout> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new WorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        holder.recTitle.setText(dataList.get(position).getWorkoutName());
        holder.recLang.setText(dataList.get(position).getSets().size() + " sets, " + dataList.get(position).getTotalTime() + " minutes");

//        holder.recCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, DetailActivity.class);
//                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getDataImage());
//                intent.putExtra("Title", dataList.get(holder.getAdapterPosition()).getDataTitle());
//                intent.putExtra("Desc", dataList.get(holder.getAdapterPosition()).getDataDesc());
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class WorkoutViewHolder extends RecyclerView.ViewHolder{
        ImageView recImage;
        TextView recTitle, recDesc, recLang;
        CardView recCard;
        public WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            recImage = itemView.findViewById(R.id.recImage);
            recTitle = itemView.findViewById(R.id.recTitle);
            recDesc = itemView.findViewById(R.id.recDesc);
            recLang = itemView.findViewById(R.id.recLang);
            recCard = itemView.findViewById(R.id.recCard);
        }
    }

}
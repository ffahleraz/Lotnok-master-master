package com.adylanroaffa.lotnok;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adylan Roaffa on 7/5/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private Context mContext;
    private List<Task> tasks;

    public static class TaskViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView taskName;
        TextView dateToday;
        TextView taskDeadline;
        TextView timeLeft;

        TaskViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card_view);
            taskName = (TextView) itemView.findViewById(R.id.task_name);
            taskDeadline = (TextView) itemView.findViewById(R.id.task_deadline);
            timeLeft = (TextView) itemView.findViewById(R.id.time_left);
        }

    }

    public TaskAdapter(Context mContext, List<Task> tasks){
        this.mContext = mContext;
        this.tasks = tasks;

    };

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup,false);
        TaskViewHolder tvh = new TaskViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int i) {
        holder.taskName.setText(""+tasks.get(i).mTaskName);
        holder.taskDeadline.setText(""+tasks.get(i).mTaskDeadline);
        holder.timeLeft.setText(""+tasks.get(i).mTimeLeft);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}

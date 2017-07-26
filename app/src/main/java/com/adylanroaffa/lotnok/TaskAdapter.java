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
        TextView taskNotes;
        TextView taskLoc;
        TextView taskStartTime;
        TextView taskEndTime;

        TaskViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card_view);
            taskName = (TextView) itemView.findViewById(R.id.task_name);
            taskNotes = (TextView) itemView.findViewById(R.id .task_notes);
            taskLoc = (TextView) itemView.findViewById(R.id.task_loc);
            taskStartTime = (TextView) itemView.findViewById(R.id.task_start_time);
            taskEndTime = (TextView) itemView.findViewById(R.id.task_end);
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
        holder.taskName.setText("" + tasks.get(i).taskName);
        holder.taskNotes.setText("" + tasks.get(i).taskNotes);
        holder.taskLoc.setText("" + tasks.get(i).taskLoc);
        holder.taskStartTime.setText("" + String.format("%02d", tasks.get(i).taskStartTime.getHour()) + ":" + String.format("%02d", tasks.get(i).taskStartTime.getMinute()));
        holder.taskEndTime.setText("" + tasks.get(i).taskEndTime.print());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}

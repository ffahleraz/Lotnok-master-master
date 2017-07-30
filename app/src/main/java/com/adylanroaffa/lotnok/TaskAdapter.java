package com.adylanroaffa.lotnok;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
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

            taskName = (TextView) itemView.findViewById(R.id.task_name);
            taskName.setTypeface(Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/avenir-light.ttf"));

            taskNotes = (TextView) itemView.findViewById(R.id .task_notes);
            taskNotes.setTypeface(Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/avenir-light.ttf"));

            taskLoc = (TextView) itemView.findViewById(R.id.task_loc);
            taskLoc.setTypeface(Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/avenir-light.ttf"));

            taskStartTime = (TextView) itemView.findViewById(R.id.task_start_time);
            taskStartTime.setTypeface(Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/DIN Regular.ttf"));

            taskEndTime = (TextView) itemView.findViewById(R.id.task_end);
            taskEndTime.setTypeface(Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/avenir-light.ttf"));

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

        holder.taskNotes.setText("" + tasks.get(i).notes);
        holder.taskLoc.setText("" + tasks.get(i).loc);
        holder.taskStartTime.setText("" + String.format("%02d", tasks.get(i).startTime.getHour()) + ":" + String.format("%02d", tasks.get(i).startTime.getMinute()));

        if (tasks.get(i).getIsProject()) {

            holder.taskName.setText("" + tasks.get(i).name + " (" + (tasks.get(i).splitID + 1) + "/" + tasks.get(i).split + ")");

            Date nowD = new Date();
            Date thenD = tasks.get(i).getDueTime().getByDate();
            int remainingDay = (int)( (thenD.getTime() - nowD.getTime()) / (1000 * 60 * 60 * 24));
            //holder.taskEndTime.setText("Due in " + remainingDay + "days");
            holder.taskEndTime.setText("Until " + tasks.get(i).endTime.print());

        } else {
            holder.taskName.setText("" + tasks.get(i).name);
            holder.taskEndTime.setText("Until " + tasks.get(i).endTime.print());
        }

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}

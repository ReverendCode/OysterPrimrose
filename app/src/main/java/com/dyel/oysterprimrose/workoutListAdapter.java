package com.dyel.oysterprimrose;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ReverendCode on 10/15/16.
 */
class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutListAdapter.ViewHolder> {
    private List<ExerciseObject> exerciseObjects;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView title, description, equipType;
        public ViewHolder(View v) {
            super(v);
           title = (TextView) v.findViewById(R.id.text_title);
            description = (TextView) v.findViewById(R.id.descriptionTextView);
            equipType = (TextView) v.findViewById(R.id.text_equipment_type);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public WorkoutListAdapter(List<ExerciseObject> objects) {
        exerciseObjects = objects;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public WorkoutListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_card_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.mTextView.setText(mDataset[position]);
        holder.title.setText(exerciseObjects.get(position).get_exercise());
        holder.description.setText(exerciseObjects.get(position).get_description());
        holder.equipType.setText(exerciseObjects.get(position).get_equipment());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return exerciseObjects.size();
    }
}
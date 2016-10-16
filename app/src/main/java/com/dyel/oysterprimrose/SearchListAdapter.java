package com.dyel.oysterprimrose;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ReverendCode on 10/15/16.
 */
class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder>  {
    private List<ExerciseObject> exerciseObjects;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.text_title_search);
        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public SearchListAdapter(List<ExerciseObject> objects) {
        exerciseObjects = objects;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SearchListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_list_card, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.title.setText(exerciseObjects.get(position).get_exercise());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //this is the location of the item you clicked
                int foo = holder.getAdapterPosition();
                Toast.makeText(v.getContext(), "Position: " + foo, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return exerciseObjects.size();
    }

//    public void setOnItemClickListener(ClickListener clickListener) {
//        SearchListAdapter.clickListener = clickListener;
//    }
//
//    public interface ClickListener {
//        void onItemClick(int position, View v);
//    }
}
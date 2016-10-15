package com.dyel.oysterprimrose;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ExerciseObject> mWorkoutList = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.mWorkoutView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new WorkoutListAdapter(mWorkoutList);
        WorkoutListAdapter mListAdapter = new WorkoutListAdapter(mWorkoutList);
        mRecyclerView.setAdapter(mListAdapter);


        addDemoData();
    }

    private void addDemoData() {
        ExerciseObject object = new ExerciseObject();
        object.set_exercise("Bench Press");
        object.set_description("Lay down on a bench, the bar should be directly above your eyes, " +
                "the knees are somewhat angled and the feet are firmly on the floor. Concentrate, " +
                "breath deeply and grab the bar more than shoulder wide. Bring it slowly down till " +
                "it briefly touches your chest at the height of your nipples. Push the bar up.\n" +
                "\n" +
                "If you train with a high weight it is advisable to have a spotter that can help" +
                " you up if you can't lift the weight on your own.\n");
        mWorkoutList.add(object);

        mAdapter.notifyDataSetChanged();
    }

    public void handleNewExercise(View view) {
        Intent intent = new Intent(this, addExerciseView.class);
        startActivity(intent);
    }
}

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
        WorkoutListAdapter mListAdapter = new WorkoutListAdapter(mWorkoutList);
        mAdapter = new WorkoutListAdapter(mWorkoutList);
        mRecyclerView.setAdapter(mListAdapter);
    }
    protected void onResume() {
        super.onResume();

        // TODO: 10/15/16 This is where to update the List with new DB entries
        //throw away the list, refresh the list from the DB
        DatabaseHandler db = new DatabaseHandler(this);

        List<ExerciseObject> newWorkoutList = db.getAllExercises();
        for (ExerciseObject obj:
                db.getAllExercises()) {
            db.deleteExercise(obj);
        }
        mWorkoutList.addAll(newWorkoutList);


        addDemoData();

        mAdapter.notifyDataSetChanged();
        db.close();
    }
    public void handleAlternateExercise(View view) {
        // TODO: 10/15/16 Make an API call to get alternate exercises here
    }
    
    private void addDemoData() {
        DatabaseHandler db = new DatabaseHandler(this);

        ExerciseObject object = new ExerciseObject("Bench", "image here", "Lay on bench", "DO WEIGHT YOU CAN'T DO AND HAVE PARTNER ROW IT OFF YOU",
                "Bar");
        db.addExerciseObject(object);
        ExerciseObject legobj = new ExerciseObject("Deadlift", "image here", "Arch Back", "DO WEIGHT YOU CAN'T DO AND HAVE PARTNER ROW IT OFF YOU",
                "Bar");
        db.addExerciseObject(legobj);
        ExerciseObject backobj = new ExerciseObject("Squat", "image here", "Dont Brace Core", "DO WEIGHT YOU CAN'T DO AND HAVE PARTNER ROW IT OFF YOU",
                "Bar");
        db.addExerciseObject(backobj);
        db.close();
//        mAdapter.notifyDataSetChanged();
    }

    public void handleNewExercise(View view) {
        Intent intent = new Intent(this, addExerciseView.class);
        startActivity(intent);
    }
}

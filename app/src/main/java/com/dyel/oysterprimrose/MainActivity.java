package com.dyel.oysterprimrose;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

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
//        DatabaseHandler db = new DatabaseHandler(this);

        // TODO: 10/15/16 Connect DB to mWorkoutList
        addDemoData();
    }



    protected void onResume() {
        super.onResume();

        // TODO: 10/15/16 This is where to update the List with new DB entries
        //throw away the list, refresh the list from the DB
        DatabaseHandler db = new DatabaseHandler(this);

        List<ExerciseObject> newWorkoutList = db.getAllExercises();
        //mWorkoutList.clear();
        for (ExerciseObject obj:
                mWorkoutList) {
            db.deleteExercise(obj);
        }
        mWorkoutList.addAll(newWorkoutList);
//        mWorkoutList = newWorkoutList;
//        Toast.makeText(this, mWorkoutList.get(0).get_description(), Toast.LENGTH_SHORT).show();
//        mWorkoutList.clear();
//        for (ExerciseObject obj:
//                mWorkoutList) {
//            db.deleteExercise(obj);
//        }
        mAdapter.notifyDataSetChanged();
        db.close();


    }
    
    
    private void addDemoData() {
        DatabaseHandler db = new DatabaseHandler(this);

        ExerciseObject object = new ExerciseObject("Bench", "image here", "Lay on bench", "DO WEIGHT YOU CAN'T DO AND HAVE PARTNER ROW IT OFF YOU",
                "Bar", "Pecs");
        db.addExerciseObject(object);
        ExerciseObject legobj = new ExerciseObject("Deadlift", "image here", "Arch Back", "DO WEIGHT YOU CAN'T DO AND HAVE PARTNER ROW IT OFF YOU",
                "Bar", "Pecs");
        db.addExerciseObject(legobj);
        ExerciseObject backobj = new ExerciseObject("Squat", "image here", "Dont Brace Core", "DO WEIGHT YOU CAN'T DO AND HAVE PARTNER ROW IT OFF YOU",
                "Bar", "Pecs");
        db.addExerciseObject(backobj);
//        object.get_exercise();
//        object.get_description();
//          mWorkoutList.add(object);
//        ExerciseObject newObject = new ExerciseObject();
//        newObject.set_exercise("Squats");
//        newObject.set_description("Make sure you have put the barbell at a height where you can " +
//                "comfortably take it out and put it back in. Take it out and make yourself ready:\n" +
//                "\n" +
//                "The bar is somewhat lower than your shoulders\n" +
//                "The feet are quite apart and point out\n" +
//                "The head is in your neck and looks up\n" +
//                "The chest is out\n" +
//                "Go now slowly down, till your thighs are parallel with the floor, not lower. " +
//                "The knees point outwards, your butt, out. Make a small pause of 1 second and with" +
//                " as much energy as you can, push the weight up. Make a pause of 2 seconds and repeat.");
        //mWorkoutList.add(object);
        db.close();
        mAdapter.notifyDataSetChanged();
    }

    public void handleNewExercise(View view) {
        Intent intent = new Intent(this, addExerciseView.class);
        startActivity(intent);
    }
}

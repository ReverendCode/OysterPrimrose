package com.dyel.oysterprimrose;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class addExerciseView extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ExerciseObject> mExerciseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_change_exercise);

        SearchView searchView = (SearchView) this.findViewById(R.id.searchView);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        LayoutInflater inflater =  (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mainView = inflater.inflate(R.layout.activity_add_change_exercise,null);
        mRecyclerView = (RecyclerView) mainView.findViewById(R.id.addExerciseView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        SearchListAdapter mListAdapter = new SearchListAdapter(mExerciseList);
        mRecyclerView.setAdapter(mListAdapter);

        Intent intent = getIntent();
        handleIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.v("Query",query);
            getJSONAPI instanceR = new getJSONAPI();
            instanceR.execute(query);
            try {
                JSONObject searchJSON = instanceR.get();

                JSONArray searchJSONArray = searchJSON.getJSONArray("suggestions");
                Log.v("DataGrabCheck",searchJSONArray.getJSONObject(0).toString());
                JSONArrayAdapter searchJSONAdapter = new JSONArrayAdapter(this,searchJSONArray);
                mExerciseList = processSearchJson(searchJSONArray);
                // TODO: 10/15/16 process searchJSONArray into a List<ExerciseObject>, and hand it to mExerciseList
                mRecyclerView.setAdapter(searchJSONAdapter);
            }
            catch(Exception e) {
                Log.v("Ok",e.toString());
                //TODO:print that no result was given
            }
        }
    }
    private List<ExerciseObject> processSearchJson(JSONArray json) throws JSONException {
        List<ExerciseObject> results = new ArrayList<>();
        for (int i = 0; i < json.length(); i++) {
            JSONObject obj = json.getJSONObject(i);
            // TODO: 10/15/16 convert obj push and return
        }

        return results;
    }

    //TODO: make an api call to find workouts with same name as the exercise entered into search bar
}

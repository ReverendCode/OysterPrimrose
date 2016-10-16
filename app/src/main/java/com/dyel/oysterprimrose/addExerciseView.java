package com.dyel.oysterprimrose;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class addExerciseView extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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

        Intent intent = getIntent();
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
                mRecyclerView.setAdapter((RecyclerView.Adapter)searchJSONAdapter);
            }
            catch(Exception e) {
                Log.v("Ok",e.toString());
                //TODO:print that no result was given
            }
        }
    }


    //TODO: make an api call to find workouts with same name as the exercise entered into search bar
}

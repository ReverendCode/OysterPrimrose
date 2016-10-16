package com.dyel.oysterprimrose;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

        mRecyclerView = (RecyclerView) this.findViewById(R.id.addExerciseView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SearchListAdapter(mExerciseList);
        mRecyclerView.setAdapter(mAdapter);


//        mAdapter.setOnItemClickListener();


        Intent intent = getIntent();
        handleIntent(intent);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
//            Log.v("Query",query);
            getJSONAPI instanceR = new getJSONAPI();
            instanceR.execute(query);
            try {
                JSONObject searchJSON = instanceR.get();

                JSONArray searchJSONArray = searchJSON.getJSONArray("suggestions");
//                Log.v("DataGrabCheck",searchJSONArray.getJSONObject(0).toString());
                JSONArrayAdapter searchJSONAdapter = new JSONArrayAdapter(this,searchJSONArray);
                try {
                    List<ExerciseObject> tList = processSearchJson(searchJSONArray);
                    for (ExerciseObject obj :
                            tList) {
                        mExerciseList.add(obj);
                    }
                } catch (JSONException e){
                    Log.e("Catch",e.toString());
                }
                mAdapter.notifyDataSetChanged();
            }
            catch(Exception e) {
//                Log.v("Ok",e.toString());
                //TODO:print that no result was given
            }
        }
    }
    private List<ExerciseObject> processSearchJson(JSONArray json) throws JSONException {
//        Toast.makeText(this, "Starting process", Toast.LENGTH_SHORT).show();

        List<ExerciseObject> results = new ArrayList<>();
        for (int i = 0; i < json.length(); i++) {

            JSONObject obj = json.getJSONObject(i).getJSONObject("data");
            String name = obj.getString("name");

//            String description = obj.getString("description");
//            String comments = obj.getString("comments");
//            String comments = "[COMMENTS]";
//            String equipment = obj.getString();
//            int equip = obj.getInt("equipment");
//            String equipment = getNewJsonRequest("equipment",equip);
//            String equipment = "equipment";
            // TODO: 10/15/16 convert obj push and return
            ExerciseObject temp = new ExerciseObject(name,"","","","");
            results.add(temp);
        }
//        ExerciseObject foo = new ExerciseObject("Title","image","description","comments","equipment");
//        results.add(foo);
//        Toast.makeText(this, "ending process", Toast.LENGTH_SHORT).show();

        return results;
    }

    //TODO: make an api call to find workouts with same name as the exercise entered into search bar
}

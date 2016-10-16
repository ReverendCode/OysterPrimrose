package com.dyel.oysterprimrose;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by secomd on 10/15/2016.
 */

public class getJSONAPI extends AsyncTask<String,Void,JSONObject> {

    @Override
    protected void onPreExecute() {

    }
    @Override
    protected JSONObject doInBackground(String... params) {
        try {
            String query = params[0];
            String root = "https://wger.de/api/v2/exercise/search/?term=";
            root = root.concat(query);
            Log.v("Query",root);
            URL url = new URL(root);
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            //connection.addRequestProperty("x-api-key",
            //context.getString(R.string.open_weather_maps_app_id));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            Log.v("Ok","What");
            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            // This value will be 404 if the request was not
            // successful
            /*if(data.getInt("cod") != 200){
                Log.v("Ok","Success");
                return null;

            }else {
                Log.v("Ok","404ed");
            }*/
            Log.v("DataCheck",data.toString());
            return data;
        }catch(Exception e){
            Log.v("InBackground",e.toString());
            return null;
        }

    }
    @Override
    protected void onPostExecute(JSONObject Jobj){

    }
}

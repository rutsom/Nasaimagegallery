package com.obvioustest.nasaimagegallery;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

class DataHandler {
    private Context context;
    private ArrayList<HashMap<String, String>> DataList = new ArrayList<HashMap<String, String>>();
    private HashMap<String, String> List;

    public DataHandler(Context context) {
        this.context = context;

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray arry = obj.getJSONArray("title");


            for (int i = 0; i < arry.length(); i++) {
                JSONObject object = arry.getJSONObject(i);
                Log.d("Details-->", object.getString("title"));

                List = new HashMap<String, String>();
                List.put("title", object.getString("title"));
                List.put("copyright", object.getString("copyright"));
                List.put("hdurl", object.getString("hdurl"));
                List.put("date", object.getString("date"));
                List.put("emplanation", object.getString("emplanation"));
                List.put("media_type", object.getString("media_type"));
                List.put("service_version", object.getString("service_version"));
                List.put("url", object.getString("url"));


                DataList.add(List);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

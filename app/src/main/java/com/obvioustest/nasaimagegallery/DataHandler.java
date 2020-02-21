package com.obvioustest.nasaimagegallery;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;

class DataHandler {
    private Context context;
    private ArrayList<HashMap<String, String>> DataList = new ArrayList<HashMap<String, String>>();
    private HashMap<String, String> List;

    public DataHandler(Context context) {
        this.context = context;

        JSONArray arry = (JSONArray) loadJSONFromAsset();


        for (int i = 0; i < arry.size(); i++) {
            JSONObject object = (JSONObject) arry.get(i);
            Log.d("Details-->", (String) object.get("title"));

            List = new HashMap<String, String>();
            List.put("title", (String) object.get("title"));
            List.put("copyright", (String) object.get("copyright"));
            List.put("hdurl", (String) object.get("hdurl"));
            List.put("date", (String) object.get("date"));
            List.put("emplanation", (String) object.get("emplanation"));
            List.put("media_type", (String) object.get("media_type"));
            List.put("service_version", (String) object.get("service_version"));
            List.put("url", (String) object.get("url"));


            DataList.add(List);
        }
    }

    public String getThumbnailURL(int pos) {
        return DataList.get(pos).get("url");
    }

    public ArrayList getRawData() {
        return DataList;
    }

    private Object loadJSONFromAsset() {
        Object json = null;
        try {

            Reader reader = new InputStreamReader(context.getAssets().open("data.json"));

            json = new JSONParser().parse(reader);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return json;
    }
}

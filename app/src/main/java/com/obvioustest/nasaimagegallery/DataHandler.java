package com.obvioustest.nasaimagegallery;

import android.content.Context;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

class DataHandler {
    private Context context;
    private ArrayList<HashMap<String, String>> DataList = new ArrayList<HashMap<String, String>>();

    public DataHandler(Context context) {
        this.context = context;

        JSONArray arry = (JSONArray) loadJSONFromAsset();


        assert arry != null;
        for (int i = 0; i < arry.size(); i++) {
            JSONObject object = (JSONObject) arry.get(i);
            // Log.d("Details-->", (String) object.get("title"));

            HashMap<String, String> list = new HashMap<String, String>();
            list.put("title", (String) object.get("title"));
            list.put("copyright", (String) object.get("copyright"));
            list.put("hdurl", (String) object.get("hdurl"));
            list.put("date", (String) object.get("date"));
            list.put("explanation", (String) object.get("explanation"));
            list.put("media_type", (String) object.get("media_type"));
            list.put("service_version", (String) object.get("service_version"));
            list.put("url", (String) object.get("url"));


            DataList.add(list);
        }
        Collections.sort(DataList, new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date d1, d2;
                    d1 = sdf.parse(o1.get("date"));
                    d2 = sdf.parse(o2.get("date"));
                    return d2.compareTo(d1);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                return 0;
            }
        });
    }


    ArrayList getRawData() {
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

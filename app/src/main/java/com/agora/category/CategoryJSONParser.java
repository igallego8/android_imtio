package com.agora.category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ivan on 5/10/15.
 */
public class CategoryJSONParser {

    public List<HashMap<String,String>> parse(JSONObject jObject){
        JSONArray jsonCategories = null;
        try {
            jsonCategories = jObject.getJSONArray("predictions");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getCategories(jsonCategories);
    }

    private List<HashMap<String, String>> getCategories(JSONArray jsonCategories){
        int placesCount = jsonCategories.length();
        List<HashMap<String, String>> categoryList = new ArrayList<HashMap<String,String>>();
        HashMap<String, String> category = null;
        for(int i=0; i<placesCount;i++){
            try {
                category = getCategory((JSONObject) jsonCategories.get(i));
                categoryList.add(category);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return categoryList;
    }


    private HashMap<String, String> getCategory(JSONObject jsonCategory){
        HashMap<String, String> place = new HashMap<String, String>();
        String id="";
        String description="";
        try {
            description = jsonCategory.getString("prediction");
            id = jsonCategory.getString("id");
            place.put("description", description);
            place.put("_id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return place;
    }
}

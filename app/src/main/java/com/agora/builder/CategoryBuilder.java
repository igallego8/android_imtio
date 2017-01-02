package com.agora.builder;

import com.agora.entity.Category;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ivan on 6/10/15.
 */
public class CategoryBuilder implements Builder {
    @Override
    public Object build(JSONObject o) throws JSONException {
        Long key=Long.valueOf(o.getString("categoryKey"));
        String description= o.getString("description");
        String imageName= o.getString("imageName");
        Category category= new Category(key,description,imageName);
        return category;
    }
}

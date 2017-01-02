package com.agora.builder;

import com.agora.entity.Category;
import com.agora.entity.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ivan on 6/10/15.
 */
public class UserBuilder implements Builder {
    @Override
    public Object build(JSONObject o) throws JSONException {
        Long userKey= Long.valueOf(o.getString("userKey"));
        String name=o.getString("name");
        String lastName=o.getString("lastName");
        String image=o.optString("image");
        String email=o.optString("email");
        User user = new User();
        user.setUserKey(userKey);
        user.setName(name);
        user.setImage(image);
        user.setEmail(email);
        user.setLastName(lastName);
        return user;
    }
}

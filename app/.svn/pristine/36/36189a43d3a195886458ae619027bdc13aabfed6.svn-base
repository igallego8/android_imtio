package com.agora.builder;

import com.agora.entity.Need;
import com.agora.entity.Topic;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Ivan on 18/09/15.
 */
public class TopicBuilder implements Builder {

    @Override
    public Object build(JSONObject o) throws JSONException {
        Long key=Long.valueOf(o.getString("topicKey"));
        String description= o.getString("description");
        Topic topic = new Topic(key, description);
        return topic;
    }
}

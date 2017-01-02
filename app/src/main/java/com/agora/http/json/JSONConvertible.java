package com.agora.http.json;

import org.json.JSONException;
import org.json.JSONObject;

public interface JSONConvertible {
	
	
	JSONObject convert()throws JSONException;

}

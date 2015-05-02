package com.vraenchike.Services.JSON;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Artyom on 23.04.2015.
 */
public interface JSONable {

   JSONObject toJSONObject() throws JSONException;
}

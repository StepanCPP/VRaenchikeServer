package com.vraenchike.Services.ApiUtil;

import com.vraenchike.Services.JSON.JSONable;
import org.json.JSONException;
import org.json.JSONObject;

public class Status implements JSONable {

 private int code;
 private String message;

 public Status() {
 }

 public Status(int code, String message) {
  this.code = code;
  this.message = message;
 }

 public int getCode() {
  return code;
 }

 public void setCode(int code) {
  this.code = code;
 }

 public String getMessage() {
  return message;
 }

 public void setMessage(String message) {
  this.message = message;
 }

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",this.code);
        jsonObject.put("message",this.message);
        return  jsonObject;
    }
}


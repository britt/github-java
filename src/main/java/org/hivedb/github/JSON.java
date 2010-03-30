package org.hivedb.github;

import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;

import java.util.Collection;
import java.util.ArrayList;

public class JSON {
   public static Object getIfExists(String key, Object def, JSONObject o) throws JSONException {
    return o.has(key) ? o.get(key) : def;
  }

  public static Collection<String> toStringCollection(JSONArray a) throws JSONException {
    Collection<String> s = new ArrayList<String>();
    for(int i=0; i<a.length(); i++) {
      s.add(a.get(i).toString());  
    }
    return s;
  }
}

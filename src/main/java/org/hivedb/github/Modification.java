package org.hivedb.github;

import org.json.JSONObject;
import org.json.JSONException;

public class Modification {
  String fileName, diff;

  public static Modification loadJSON(JSONObject o) throws JSONException {
    return new Modification(JSON.getIfExists("filename","",o).toString(), JSON.getIfExists("diff","",o).toString());
  }

  public Modification() {}

  public Modification(String fileName, String diff) {
    this.fileName = fileName;
    this.diff = diff;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getDiff() {
    return diff;
  }

  public void setDiff(String diff) {
    this.diff = diff;
  }
}

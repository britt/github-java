package org.hivedb.github;

import org.json.JSONObject;
import org.json.JSONException;

public class Committer {
  private String name;
  private String email;
  
  public Committer(){}

  public Committer(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public static Committer loadJSON(JSONObject o) throws JSONException {
    return new Committer(JSON.getIfExists("name","",o).toString(), JSON.getIfExists("email","",o).toString());
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isSamePerson(Committer committer) {
    return this.email.equals(committer.getEmail());
  }
}

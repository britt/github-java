package org.hivedb.github;

import org.json.JSONObject;
import org.json.JSONException;

public class Repository {
  private String name;
  private String url;
  private String description;
  private String homePage;

  public static Repository loadJSON(JSONObject o) throws JSONException {
    Repository repo = new Repository();
    repo.setName(JSON.getIfExists("name","",o).toString());
    repo.setUrl(JSON.getIfExists("url","",o).toString());
    repo.setDescription(JSON.getIfExists("description","",o).toString());    
    repo.setHomePage(JSON.getIfExists("homepage","",o).toString());
    return repo;
  }

  public Repository(){}

  public Repository(String name, String url, String description, String homePage) {
    this.name = name;
    this.url = url;
    this.description = description;
    this.homePage = homePage;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getHomePage() {
    return homePage;
  }

  public void setHomePage(String homePage) {
    this.homePage = homePage;
  }
}

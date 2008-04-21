package org.hivedb.github;

public class Repository {
  private String name;
  private String url;
  private String description;
  private String homePage;

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

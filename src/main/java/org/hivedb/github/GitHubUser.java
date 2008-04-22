package org.hivedb.github;

import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;

import java.util.Collection;
import java.util.ArrayList;

public class GitHubUser extends Committer {
  private String company, blog, userName, location;
  private Collection<Repository> repositories;

  public static GitHubUser loadJSON(JSONObject o) throws JSONException {
    GitHubUser user = new GitHubUser();
    user.setName(JSON.getIfExists("name","",o).toString());
    user.setCompany(JSON.getIfExists("company","",o).toString());
    user.setBlog(JSON.getIfExists("blog","",o).toString());
    user.setUserName(JSON.getIfExists("login","",o).toString());
    user.setEmail(JSON.getIfExists("email","",o).toString());
    user.setLocation(JSON.getIfExists("location","",o).toString());
    JSONArray repos = (JSONArray) JSON.getIfExists("repositories",new JSONArray(),o);
    Collection<Repository> repositories = new ArrayList<Repository>();
    for(int i=0; i<repos.length(); i++)
      repositories.add(Repository.loadJSON((JSONObject) repos.get(i)));
    user.setRepositories(repositories);
    return user;
  }
  
  public GitHubUser(){}

  public GitHubUser(String company, String name, String blog, String userName, String email, String location, Collection<Repository> repositories) {
    super(name, email);
    this.company = company;
    this.blog = blog;
    this.userName = userName;
    this.location = location;
    this.repositories = repositories;
  }

  public String getCompany() {
  return company;
}

  public void setCompany(String company) {
    this.company = company;
  }

  public String getBlog() {
    return blog;
  }

  public void setBlog(String blog) {
    this.blog = blog;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Collection<Repository> getRepositories() {
    return repositories;
  }

  public void setRepositories(Collection<Repository> repositories) {
    this.repositories = repositories;
  }
}

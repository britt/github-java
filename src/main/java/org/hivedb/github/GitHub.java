package org.hivedb.github;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.Collection;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;

//  http://github.com/api/version/format/username/repository/type/object
public class GitHub {
  private Logger log = Logger.getLogger(GitHub.class);

  private String userName;
  private String repository;
  private String version = "1";
  private String branch = "master";
  private HttpClient http;
  
  public GitHub(String userName, String repository) {
    this.userName = userName;
    this.repository = repository;
    this.http = new HttpClient();
  }

  public GitHub(String userName, String repository, String branch, String version) {
    this(userName, repository);
    this.version = version;
    this.branch = branch;
  }

  public Collection<Commit> getCommits() {
    String url = GitHub.formatUrl(getUserName(), getRepository(), "commits", getBranch(), getVersion());
    JSONObject response = doGet(url);
    Collection<Commit> commits = new ArrayList<Commit>();

    Iterator i = response.keys();
    while(i.hasNext()) {
      String key = i.next().toString();
      try {
        log.debug(key + " => " + response.get(key));
        if(response.get(key) != null)
          commits.add(new Commit());
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return commits;
  }

  public Commit getCommit(String id) {
    throw new UnsupportedOperationException();
  }

  public GitHubUser getUserInfo() {
    throw new UnsupportedOperationException();
  }

  public static String formatUrl(String userName, String repository, String type, String objectId, String version){
    return String.format("http://github.com/api/v%s/json/%s/%s/%s/%s", version, userName, repository, type, objectId);
  }

  private JSONObject doGet(String url) {
    GetMethod get = new GetMethod(url);
    String body;
    int code;
    try {
      log.info("Executing HTTP/GET " + url);
      code = http.executeMethod(get);
      if(code >= 200 && code < 300) {
        body = new String(get.getResponseBody());
      } else {
        log.warn("HTTP/GET Failed with code " + code);        
        throw new RuntimeException(String.format("HTTP Error %s while connecting to GitHub: %s", code, url));
      }
    } catch (IOException e) {
      throw new RuntimeException("Error connecting to GitHub: " + url, e);
    }
    log.debug("Reponse returned:\n" + body.trim());
    JSONObject json = null;
    try {
      json = new JSONObject(body.trim());
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return json;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getRepository() {
    return repository;
  }

  public void setRepository(String repository) {
    this.repository = repository;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getBranch() {
    return branch;
  }

  public void setBranch(String branch) {
    this.branch = branch;
  }
}

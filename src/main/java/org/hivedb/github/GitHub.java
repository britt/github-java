package org.hivedb.github;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;

import java.util.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;

//  http://github.com/api/version/format/username/repository/type/object
public class GitHub {
  private Logger log = Logger.getLogger(GitHub.class);

  private static final String GITHUB_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
  private static SimpleDateFormat dateFormat = new SimpleDateFormat(GitHub.GITHUB_DATE_FORMAT);
  
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

    try {
      JSONArray a = (JSONArray) response.get("commits");
      for(int i=0; i<a.length(); i++) {
        Object commit = a.get(i);
        if(commit.getClass().equals(JSONObject.class))
          commits.add(Commit.loadJSON((JSONObject) commit));
      }
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }

    return commits;
  }

  public Commit getCommit(String id) {
    String url = GitHub.formatUrl(getUserName(), getRepository(), "commit", id, getVersion());
    JSONObject response = doGet(url);
    try {
      return Commit.loadJSON((JSONObject) JSON.getIfExists("commit","",response));
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
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
      log.debug("Executing HTTP/GET " + url);
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

    JSONObject json = null;
    try {
      json = new JSONObject(body.trim());
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
    return json;
  }

  public static Date parseDate(String dateString) throws ParseException {
    int tzError = dateString.lastIndexOf(':');
    String rfc822 = dateString.substring(0,tzError) + dateString.substring(tzError+1);
    return dateFormat.parse(rfc822);
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

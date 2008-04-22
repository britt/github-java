package org.hivedb.github;

import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;

import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;
import java.text.ParseException;

public class Commit {
  String id, message, url, tree;
  Committer author, committer;
  Collection<String> added, removed, parents;
  Date commitDate, authorDate;
  Collection<Modification> modified;

  public static Commit loadJSON(JSONObject o) throws JSONException {
    Commit commit = new Commit();
    commit.setMessage(JSON.getIfExists("message", "", o).toString());
    commit.setUrl(JSON.getIfExists("url", "", o).toString());
    commit.setId(JSON.getIfExists("id","", o).toString());
    commit.setTree(JSON.getIfExists("tree","", o).toString());
    commit.setAuthor(Committer.loadJSON((JSONObject) JSON.getIfExists("author", new JSONObject(), o)));
    commit.setCommitter(Committer.loadJSON((JSONObject) JSON.getIfExists("committer", new JSONObject(), o)));
    commit.setParents(mapToCollection("id", (JSONArray) JSON.getIfExists("parents", new JSONArray(), o)));
    try {
      commit.setCommitDate(GitHub.parseDate(JSON.getIfExists("committed_date","",o).toString()));
      commit.setAuthorDate(GitHub.parseDate(JSON.getIfExists("authored_date","",o).toString()));
    } catch (ParseException e) {
      throw new JSONException(e);
    }
    commit.setAdded(mapToCollection("filename", (JSONArray) JSON.getIfExists("added", new JSONArray(), o)));
    commit.setRemoved(mapToCollection("filename", (JSONArray) JSON.getIfExists("removed", new JSONArray(), o)));

    JSONArray a = (JSONArray) JSON.getIfExists("modified", new JSONArray(), o);
    Collection<Modification> mods = new ArrayList<Modification>();
    
    for(int i=0; i<a.length(); i++)
      mods.add(Modification.loadJSON((JSONObject) a.get(i)));

    commit.setModified(mods);

    return commit;
  }

  private static Collection<String> mapToCollection(String key, JSONArray a) throws JSONException {
    Collection<String> s = new ArrayList<String>();
    for(int i=0; i<a.length(); i++) {
      Object parent = ((JSONObject) a.get(i)).get(key);
      s.add(parent.toString());
    }
    return s;
  }

  public Commit() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTree() {
    return tree;
  }

  public void setTree(String tree) {
    this.tree = tree;
  }

  public Committer getAuthor() {
    return author;
  }

  public void setAuthor(Committer author) {
    this.author = author;
  }

  public Committer getCommitter() {
    return committer;
  }

  public void setCommitter(Committer committer) {
    this.committer = committer;
  }

  public Collection<String> getAdded() {
    return added;
  }

  public void setAdded(Collection<String> added) {
    this.added = added;
  }

  public Collection<String> getRemoved() {
    return removed;
  }

  public void setRemoved(Collection<String> removed) {
    this.removed = removed;
  }

  public Date getCommitDate() {
    return commitDate;
  }

  public void setCommitDate(Date commitDate) {
    this.commitDate = commitDate;
  }

  public Date getAuthorDate() {
    return authorDate;
  }

  public void setAuthorDate(Date authorDate) {
    this.authorDate = authorDate;
  }

  public Collection<Modification> getModified() {
    return modified;
  }

  public void setModified(Collection<Modification> modified) {
    this.modified = modified;
  }

  public Collection<String> getParents() {
    return parents;
  }

  public void setParents(Collection<String> parents) {
    this.parents = parents;
  }

  public int hashCode() {
    return id.hashCode();
  }

  public boolean equals(Object obj) {
    return this.hashCode() == obj.hashCode();
  }
}

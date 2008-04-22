package org.hivedb.github;

import java.util.Collection;
import java.util.Date;

public class Commit {
  String id, message, url, tree;
  Committer author, committer;
  Collection<String> added, removed, parents;
  Date commitDate, authorDate;
  Collection<Modification> modified;

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

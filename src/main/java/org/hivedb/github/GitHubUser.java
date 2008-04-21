package org.hivedb.github;

import java.util.Collection;

public class GitHubUser extends Committer {
  private String company, blog, userName, location;
  private Collection<Repository> repositories;

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

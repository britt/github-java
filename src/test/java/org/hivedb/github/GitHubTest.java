package org.hivedb.github;

import junit.framework.TestCase;

import java.util.Collection;

public class GitHubTest extends TestCase {
  String userName = "britt";
  String repository = "github-java";
  String modifyCommit = "6391999633e68bfebdde284832771fada5df7468";
  String addCommit = "94d54d57ee7d0decc7f37e7b3b0253057fc9b8b9";
  GitHub gitHub;

  protected void setUp() throws Exception {
    gitHub = new GitHub(userName, repository);
  }

  public void testGetCommits() throws Exception {
    Collection<Commit> commits = gitHub.getCommits();
    assertTrue(commits.size() > 0);
    for(Commit commit : commits)
      validateBasicCommit(commit);
  }

  public void testGetCommit() throws Exception {
    Commit commit = gitHub.getCommit(addCommit);
    assertEquals(addCommit, commit.getId());
    validateBasicCommit(commit);
    assertTrue(commit.getAdded().size() > 0);
  }

  public void testDiffs() throws Exception {
    Commit commit = gitHub.getCommit(modifyCommit);
    assertEquals(modifyCommit, commit.getId());
    validateBasicCommit(commit);
    assertTrue(commit.getModified().size() > 0);
    for(Modification mod : commit.getModified())
      validateModification(mod);
  }

  private void validateModification(Modification mod) {
    assertNotNull(mod.getDiff());
    assertNotNull(mod.getFileName());
  }

  public void testGetUserInfo() throws Exception {
    GitHubUser britt = gitHub.getUserInfo();
    validateCommitter(britt);
    assertEquals("Cafepress.com", britt.getCompany());
    assertEquals("http://britt.illtemper.org", britt.getBlog());
    assertEquals("britt", britt.getUserName());
    assertEquals("San Francisco, CA", britt.getLocation());
    assertTrue(britt.getRepositories().size() > 0);
    for(Repository repo : britt.getRepositories())
      validateRepository(repo);
  }

  private void validateRepository(Repository repo) {
    assertNotNull(repo.getName());
    assertNotNull(repo.getUrl());
    assertNotNull(repo.getDescription());
  }

  private void validateBasicCommit(Commit commit) {
    assertNotNull(commit.getMessage());
    assertNotNull(commit.getParents());
    validateCommitter(commit.getAuthor());
    assertNotNull(commit.getId());
    assertNotNull(commit.getCommitDate());
    assertNotNull(commit.getAuthorDate());
    assertNotNull(commit.getTree());
    validateCommitter(commit.getCommitter());
  }

  private void validateCommitter(Committer committer) {
    assertNotNull(committer);
    assertNotNull(committer.getName());
    assertNotNull(committer.getEmail());
  }
}

## A Java Client for the GitHub API.

### Installing
* Clone this repository and build the project with maven.

        git clone git://github.com/britt/github-java.git
        mvn install
  
* Or simply include the library as a dependency in your maven project.
  * Add the HiveDB maven repository

              <repository>
                  <id>HiveDB</id>
                  <url>http://www.hivedb.org/maven/</url>
              </repository>

  * Add the dependency
  
               <dependency>
                   <groupId>org.hivedb</groupId>
                   <artifactId>github-api</artifactId>
                   <version>1.0</version>
               </dependency>
  
### Using GitHub-API
* Create a GitHub client.

        GitHub hub = new GitHub("user", "repository");
  
* Get the recent commits for a project.

        Collection<Commit> commits = hub.getCommits();

* Get a specific commit.

        Commit anImportantCommit = hub.getCommit("f7e8c6cf6273967d8f7237c828e4c86e036949bc");
  
* Get the information for a GitHub user including their repositories.

        GitHubUser britt = hub.getUserInfo();

### Note on Patches/Pull Requests

* Fork the project.
* Make your feature addition or bug fix.
* Add tests for it. This is important so I don’t break it in a future version unintentionally.
* Commit, do not mess with the version, or history. (if you want to have your own version, that is fine but bump version in a commit by itself I can ignore when I pull)

Send me a pull request. Bonus points for topic branches.



package org.hivedb.github;

public class Modification {
  String fileName, diff;

  public Modification() {}

  public Modification(String fileName, String diff) {
    this.fileName = fileName;
    this.diff = diff;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getDiff() {
    return diff;
  }

  public void setDiff(String diff) {
    this.diff = diff;
  }
}

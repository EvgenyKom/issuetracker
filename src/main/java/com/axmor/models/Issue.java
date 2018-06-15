package com.axmor.models;


public class Issue {

    private String issueName;
    private String issueAuthor;
    private String startDate;
    private String issueStatus;
    private String issueDescription;
    private int id;

    public Issue(String issueName, String issueAuthor, String startDate, String issueStatus, String issueDescription) {
        this.issueName = issueName;
        this.issueAuthor = issueAuthor;
        this.startDate = startDate;
        this.issueStatus = issueStatus;
        this.issueDescription = issueDescription;

    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getIssueAuthor() {
        return issueAuthor;
    }

    public void setIssueAuthor(String issueAuthor) {
        this.issueAuthor = issueAuthor;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Issue issue = (Issue) o;

        if (id != issue.id) return false;
        if (!issueName.equals(issue.issueName)) return false;
        if (!issueAuthor.equals(issue.issueAuthor)) return false;
        if (!issueStatus.equals(issue.issueStatus)) return false;
        return issueDescription.equals(issue.issueDescription);
    }

    @Override
    public int hashCode() {
        int result = issueName.hashCode();
        result = 31 * result + issueAuthor.hashCode();
        result = 31 * result + issueStatus.hashCode();
        result = 31 * result + issueDescription.hashCode();
        result = 31 * result + id;
        return result;
    }
}

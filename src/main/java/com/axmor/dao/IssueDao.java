package com.axmor.dao;

import com.axmor.models.Issue;

import java.util.List;

public interface IssueDao {

    List<Issue> getAll();

    void add(Issue issue);

    Issue findById(int id);

    void update(int id, String issueName, String issueAuthor, String startDate, String issueStatus, String issueDescription );

    void deleteById(int id);

    void clearAllIssues();

}

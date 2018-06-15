package com.axmor.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class IssueTest {

    @Test
    public void NewIssueObjectGetsCorrectlyCreated_true() throws Exception {
        Issue issue = setupNewIssue();
        assertEquals(true, issue instanceof Issue);
    }

    @Test
    public void IssueInstantiatesWithDescription_true() throws Exception {
        Issue issue = setupNewIssue();
        assertEquals("Issue 1", issue.getIssueName());
        assertEquals("Author 1", issue.getIssueAuthor());
        assertEquals("10.06.2018", issue.getStartDate());
        assertEquals("created", issue.getIssueStatus());
        assertEquals("problems", issue.getIssueDescription());

    }

    public Issue setupNewIssue(){
        return new Issue("Issue 1", "Author 1", "10.06.2018", "created", "problems");
    }

}
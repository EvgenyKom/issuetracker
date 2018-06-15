package com.axmor.dao;

import com.axmor.models.Issue;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class IssueDaoImpl implements IssueDao {

    private final Sql2o sql2o;

    public IssueDaoImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Issue> getAll() {
        try (Connection connection = sql2o.open()){
            String sql = "SELECT * FROM issues";
            return  connection.createQuery(sql)
                    .executeAndFetch(Issue.class);
        }
    }

    @Override
    public void add(Issue issue) {
        String sql = "INSERT INTO issues (issueName, issueAuthor, startDate, issueStatus, issueDescription) VALUES (:issueName, :issueAuthor, :startDate, :issueStatus, :issueDescription)";
        try (Connection connection =sql2o.open()){
            int id = (int) connection.createQuery(sql,true)
                    .bind(issue)
                    .executeUpdate()
                    .getKey();
            issue.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Issue findById(int id) {
        try (Connection connection = sql2o.open()) {
            String sql = "SELECT * FROM issues WHERE id = :id";
            return connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Issue.class);
        }
    }

    @Override
    public void update(int id, String issueName, String issueAuthor, String startDate, String issueStatus, String issueDescription ){
        String sql = "UPDATE issues SET issueName = :issueName, issueAuthor = :issueAuthor, startDate = :startDate, issueStatus = :issueStatus, issueDescription = :issueDescription WHERE id=:id";
        try(Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .addParameter("issueName", issueName)
                    .addParameter("issueAuthor", issueAuthor)
                    .addParameter("startDate", startDate)
                    .addParameter("issueStatus", issueStatus)
                    .addParameter("issueDescription", issueDescription)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from issues WHERE id = :id";
        try(Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllIssues(){
        String sql = "DELETE from issues";
        try(Connection connection = sql2o.open()) {
            connection.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}

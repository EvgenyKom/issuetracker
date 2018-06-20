package com.axmor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.axmor.dao.IssueDaoImpl;
import com.axmor.models.Issue;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;

/**
 * Application entry point
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("org.h2.Driver");
        staticFileLocation("/public");
        port(80);
        String connectionString = "jdbc:h2:~/issuetrackerdb.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        IssueDaoImpl issueDao = new IssueDaoImpl(sql2o);

        get("/issues/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "issue-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/issues/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String issueName = request.queryParams("issueName");
            String issueAuthor = request.queryParams("issueAuthor");
            LocalDateTime localDateTime = LocalDateTime.now();
            String startDate = localDateTime.toLocalDate().toString();
            String issueStatus = "created";
            String issueDescription = request.queryParams("issueDescription");
            Issue newIssue = new Issue(issueName, issueAuthor, startDate, issueStatus, issueDescription);
            issueDao.add(newIssue);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Issue> issues = issueDao.getAll();
            model.put("issues", issues);
            return new ModelAndView(model, "index.hbs");
        },new HandlebarsTemplateEngine());

        get("/issues/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            issueDao.clearAllIssues();
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/issues/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfIssue = Integer.parseInt(request.params("id"));
            Issue foundIssue = issueDao.findById(idOfIssue);
            model.put("issue", foundIssue);
            return new ModelAndView(model, "issue-detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/issues/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfIssueToEdit = Integer.parseInt(req.params("id"));
            Issue editIssue = issueDao.findById(idOfIssueToEdit);
            model.put("editIssue", editIssue);
            return new ModelAndView(model, "issue-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/issues/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newIssueName = req.queryParams("issueName");
            String newIssueAuthor = req.queryParams("issueAuthor");
            String startDate = req.queryParams("startDate");
            String IssueStatus = req.queryParams("issueStatus");
            String IssueDescription = req.queryParams("issueDescription");
            int idOfIssueToEdit = Integer.parseInt(req.params("id"));
            issueDao.update(idOfIssueToEdit,newIssueName,newIssueAuthor, startDate, IssueStatus, IssueDescription);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/issues/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfIssueToDelete = Integer.parseInt(req.params("id"));
            issueDao.deleteById(idOfIssueToDelete);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());



}
}

SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS issues (
  id int PRIMARY KEY auto_increment,
  issueName VARCHAR(255),
  issueAuthor VARCHAR(255),
  startDate VARCHAR(255),
  issueStatus VARCHAR(255),
  issueDescription VARCHAR(255)
);
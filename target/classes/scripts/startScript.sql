DROP DATABASE IF EXISTS servlet_forum;

CREATE DATABASE IF NOT EXISTS servlet_forum;

USE servlet_forum;


-- Table: users

CREATE TABLE servlet_forum.users(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  nick VARCHAR(100) NOT NULL ,
  email VARCHAR(100) NOT NULL UNIQUE
  )
  ENGINE = InnoDB;


CREATE TABLE servlet_forum.users_messages(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  message VARCHAR(300) NOT NULL ,
  user_id INT NOT NULL ,
  message_date DATETIME NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE
)
  ENGINE = InnoDB;




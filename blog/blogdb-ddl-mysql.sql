DROP TABLE    IF EXISTS blog;
DROP TABLE    IF EXISTS author; 

CREATE TABLE author (
  id               integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username         varchar(255) NOT NULL CHECK (username <> ''),
  hashed_password  varchar(255) NOT NULL CHECK (hashed_password <> ''),
  email            varchar(100) NOT NULL CHECK (email <> ''),
  bio              text
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE blog (
  id          integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title       varchar(255) NOT NULL CHECK (title <> ''),
  author_id   integer NOT NULL,
  FOREIGN KEY (author_id) REFERENCES author(id)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


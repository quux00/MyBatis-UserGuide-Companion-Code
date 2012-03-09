DROP TABLE    IF EXISTS blog;
DROP TABLE    IF EXISTS author; 
DROP SEQUENCE IF EXISTS blogdb_blog_seq;
DROP SEQUENCE IF EXISTS blogdb_author_seq;
CREATE SEQUENCE blogdb_blog_seq;
CREATE SEQUENCE blogdb_author_seq;

CREATE TABLE author (
  id               integer PRIMARY KEY DEFAULT nextval('blogdb_author_seq') NOT NULL,
  username         varchar(255) NOT NULL CHECK (username <> ''),
  hashed_password  varchar(255) NOT NULL CHECK (hashed_password <> ''),
  email            varchar(100) NOT NULL CHECK (email <> ''),
  bio              text
);

CREATE TABLE blog (
  id          integer PRIMARY KEY DEFAULT nextval('blogdb_blog_seq') NOT NULL,
  title       varchar(255) NOT NULL CHECK (title <> ''),
  author_id   integer NOT NULL references author(id)
);

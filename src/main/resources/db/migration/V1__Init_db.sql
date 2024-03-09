CREATE TABLE IF NOT EXISTS Writer (
 
    id serial NOT NULL PRIMARY KEY,
    firstName varchar NOT NULL,
    lastName varchar NOT NULL,
    created timestamp NOT NULL,
    updated timestamp,
    status int NOT NULL

);

CREATE TABLE IF NOT EXISTS Post (

    id serial NOT NULL PRIMARY KEY,
    title varchar NOT NULL,
    content varchar NOT NULL,
    created timestamp NOT NULL,
    updated timestamp,
    status int NOT NULL

);

CREATE TABLE IF NOT EXISTS Label (

    id serial NOT NULL PRIMARY KEY,
    name varchar NOT NULL,
    created timestamp NOT NULL,
    updated timestamp,
    status int NOT NULL

);

CREATE TABLE Writer_Post (
  Writer_id int NOT NULL,
  Post_id int NOT NULL,
  PRIMARY KEY (Writer_id,Post_id),
  CONSTRAINT Writer_Post_fk_1
   FOREIGN KEY (Writer_id) REFERENCES Writer (id),
  CONSTRAINT Writer_Post_fk_2
   FOREIGN KEY (Post_id) REFERENCES Post (id)
);

CREATE TABLE Post_Label (
  Label_id int NOT NULL,
  Post_id int NOT NULL,
  PRIMARY KEY (Label_id,Post_id),
  CONSTRAINT Post_Label_fk_1 FOREIGN KEY (Post_id) REFERENCES Post (id),
  CONSTRAINT Post_Label_fk_2 FOREIGN KEY (Label_id) REFERENCES Label (id)
)

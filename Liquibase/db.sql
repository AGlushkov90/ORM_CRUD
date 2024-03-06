TRUNCATE TABLE  "Writer" CASCADE;
TRUNCATE TABLE  "Post" CASCADE;
TRUNCATE TABLE  "Label" CASCADE;
ALTER SEQUENCE "Label_id_seq" RESTART WITH 1;
--UPDATE operations SET id=nextval("Label_id_seq");
TRUNCATE TABLE  "Writer_Post" CASCADE;
TRUNCATE TABLE  "Post_Label" CASCADE;

INSERT INTO "Label" (name) VALUES ('sport');
INSERT INTO "Label" (name) VALUES ('music');


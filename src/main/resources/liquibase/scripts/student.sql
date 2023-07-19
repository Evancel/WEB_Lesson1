--liquibase formatted sql

--changeset amargolina:1
CREATE INDEX student_name_index ON student (name);

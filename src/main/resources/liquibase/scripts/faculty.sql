--liquibase formatted sql

--changeset amargolina:1
CREATE INDEX faculty_name_and_color_index ON faculty(name,color);
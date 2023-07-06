--liquibase formatted sql

--changeset asharipov:1
CREATE INDEX students_name_index ON student (name);

--changeset asharipov:2
CREATE INDEX faculty_name_and_color_index ON faculty (name, color);
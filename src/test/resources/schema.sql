CREATE SEQUENCE IF NOT EXISTS employee_seq;

CREATE TABLE IF NOT EXISTS employee (

employee_id BIGINT NOT NULL DEFAULT nextval('employee_seq') PRIMARY KEY,
email VARCHAR(100) NOT NULL,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL

);

CREATE SEQUENCE IF NOT EXISTS project_seq;

CREATE TABLE IF NOT EXISTS project (

project_id BIGINT NOT NULL DEFAULT nextval('project_seq') PRIMARY KEY,
name VARCHAR(100) NOT NULL,
description VARCHAR(300),
start_date DATE,
end_date DATE 

);
CREATE SEQUENCE IF NOT EXISTS task_seq;

CREATE TABLE IF NOT EXISTS task (

task_id BIGINT NOT NULL DEFAULT nextval('task_seq') PRIMARY KEY,
name VARCHAR(100) NOT NULL,
description VARCHAR(300),
start_date DATE,
end_date DATE 
);

CREATE TABLE IF NOT EXISTS task_employee (

task_id BIGINT REFERENCES task, 
employee_id BIGINT REFERENCES employee

);



CREATE TABLE IF NOT EXISTS project_task (

project_id BIGINT REFERENCES project, 
task_id BIGINT REFERENCES task

);

CREATE SEQUENCE IF NOT EXISTS user_accounts_seq;

CREATE TABLE IF NOT EXISTS user_accounts (
	user_id BIGINT NOT NULL DEFAULT nextval('user_accounts_seq') PRIMARY KEY,
	username varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	role varchar(255),
	enabled boolean NOT NULL
);
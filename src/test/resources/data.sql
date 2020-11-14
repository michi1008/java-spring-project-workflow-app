-- INSERT EMPLOYEES			
insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'John', 'Warton', 'warton@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Mike', 'Lanister', 'lanister@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Steve', 'Reeves', 'Reeves@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Ronald', 'Connor', 'connor@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Jim', 'Salvator', 'Sal@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Peter', 'Henley', 'henley@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Richard', 'Carson', 'carson@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Honor', 'Miles', 'miles@gmail.com');
insert into employee (employee_id, first_name, last_name, email) values (nextval('employee_seq'), 'Tony', 'Roggers', 'roggers@gmail.com');

-- INSERT PROJECTS			
insert into project (project_id, name, description) values (nextval('project_seq'), 'Large Production Deploy', 'This requires all hands on deck for the final deployment of the software into production');
insert into project (project_id, name, description) values (nextval('project_seq'), 'New Employee Budget', 'Decide on a new employee bonus budget for the year and figureout who will be promoted');
insert into project (project_id, name, description) values (nextval('project_seq'), 'Office Reconstruction', 'The office building in Monroe has been damaged due to hurricane in the region. This needs to be reconstructed');
insert into project (project_id, name, description) values (nextval('project_seq'), 'Improve Intranet Security', 'With the recent data hack, the office security needs to be improved and proper security team needs to be hired for implementation');


-- INSERT TASKS			
insert into task (task_id, name, description) values (nextval('task_seq'), 'Task 1', 'This requires all hands on deck for the final deployment of the software into production');
insert into task (task_id, name, description) values (nextval('task_seq'), 'Task 2', 'Decide on a new employee bonus budget for the year and figureout who will be promoted');
insert into task (task_id, name, description) values (nextval('task_seq'), 'Task 3', 'The office building in Monroe has been damaged due to hurricane in the region. This needs to be reconstructed');
insert into task (task_id, name, description) values (nextval('task_seq'), 'Task 4', 'With the recent data hack, the office security needs to be improved and proper security team needs to be hired for implementation');

-- INSERT TASK_EMPLOYEE_RELATION
insert into task_employee (task_id, employee_id) (select e.employee_id, t.task_id from employee e, task t where e.last_name ='Warton' AND t.name = 'Task 1');
insert into task_employee (task_id, employee_id) (select e.employee_id, t.task_id from employee e, task t where e.last_name ='Warton' AND t.name = 'Task 2');
insert into task_employee (task_id, employee_id) (select e.employee_id, t.task_id from employee e, task t where e.last_name ='Warton' AND t.name = 'Task 3');
insert into task_employee (task_id, employee_id) (select e.employee_id, t.task_id from employee e, task t where e.last_name ='Reeves' AND t.name = 'Task 4');


-- INSERT TASK_EMPLOYEE_RELATION
insert into project_task (project_id, task_id) (select p.project_id, t.task_id from project p, task t where p.name ='Large Production Deploy' AND t.name = 'Task 1');
insert into project_task (project_id, task_id) (select p.project_id, t.task_id from project p, task t where p.name ='New Employee Budget' AND t.name = 'Task 2');
insert into project_task (project_id, task_id) (select p.project_id, t.task_id from project p, task t where p.name ='Office Reconstruction' AND t.name = 'Task 3');
insert into project_task (project_id, task_id) (select p.project_id, t.task_id from project p, task t where p.name ='Improve Intranet Security' AND t.name = 'Task 4');
																	
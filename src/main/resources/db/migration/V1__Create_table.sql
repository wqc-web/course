create table t_user(
    id serial primary key,
    username varchar (50),
    password varchar (50),
    create_time timestamp default now(),
    status varchar (10)
);

create table t_role(
    id serial primary key,
    name varchar (50)
);

create table t_user_role(
    id serial primary key,
    user_id integer,
    role_id integer
);

create table t_permission(
    id serial primary key,
    name varchar (50),
    role_id integer
);

insert into t_user(id, username , password) values (1,'xiaohong' , '123456');
insert into t_user(id, username , password) values (2,'zhangsan' , '123456');
insert into t_user(id, username , password) values (3,'lisi' , '123456');

insert into t_role(id,name) values (1,'学生');
insert into t_role(id,name) values (2,'老师');
insert into t_role(id,name) values (3,'管理员');

insert into t_permission(id,name , role_id) values (1,'登录用户', 1);
insert into t_permission(id,name , role_id) values (2,'登录用户', 2);
insert into t_permission(id,name , role_id) values (3,'上传课程', 2);
insert into t_permission(id,name , role_id) values (4,'登录用户', 3);
insert into t_permission(id,name , role_id) values (5,'上传课程', 3);
insert into t_permission(id,name , role_id) values (6,'管理用户', 3);


alter table liked_user rename column name to who;

alter table liked_user alter column who type int using who::int;

alter table liked_user drop column photo;

alter table liked_user
    add whom int not null;

alter table liked_user
    add action int;

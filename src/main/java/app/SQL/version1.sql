create table liked_user
(
	id serial not null,
	name varchar not null,
	photo varchar not null
);

create unique index liked_user_id_uindex
	on liked_user (id);

alter table liked_user
	add constraint liked_user_pk
		primary key (id);
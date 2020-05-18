create table users
(
	id serial not null,
	login varchar not null,
	password varchar not null,
	name varchar not null,
	photo varchar not null
);

create unique index users_id_uindex
	on users (id);

alter table users
	add constraint users_pk
		primary key (id);


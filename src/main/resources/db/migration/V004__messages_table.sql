create table messages
(
	id serial not null,
	"from" int not null,
	"to" int not null,
	text varchar,
	date varchar not null
);

create unique index messages_id_uindex
	on messages (id);

alter table messages
	add constraint messages_pk
		primary key (id);


alter table liked_user
    add constraint liked_user_users_id_fk
        foreign key (who) references users;
alter table liked_user
    add constraint liked_user_users_id_fk_2
        foreign key (whom) references users;
alter table messages
    add constraint messages_users_id_fk
        foreign key (who) references users;
alter table messages
    add constraint messages_users_id_fk_2
        foreign key (whom) references users;
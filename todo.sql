create table todo
(
    id          int auto_increment primary key,
    title       varchar(50)                        not null,
    contents    varchar(200)                        not null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    updated_at timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    userid int,
    foreign key (userid) references user(id) on update cascade
);

create table comment
(
    id          int auto_increment primary key,
    contents    varchar(200)                        not null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    updated_at timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    userid int,
    foreign key (userid) references user(id) on update cascade
);


create table user
(
    id int auto_increment primary key,
    username varchar(50) not null,
    email varchar(50) not null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    updated_at timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);
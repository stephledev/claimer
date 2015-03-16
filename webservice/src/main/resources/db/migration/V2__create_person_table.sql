create table person (
    id int not null auto_increment,
    type ENUM("Assistant", "Contact", "Supervisor") NOT NULL,
    lastname varchar(255) not null,
    firstname varchar(255) not null,
    username varchar(255) not null,
    passwort varchar(255) not null,
    phone varchar(255),
    email varchar(255),
    primary key(id)
);
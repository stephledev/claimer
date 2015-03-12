create table company (
    id int not null auto_increment,
    type ENUM("GeneralContractor", "Subcontractor") NOT NULL,
    name varchar(255) not null,
    street varchar(255) not null,
    zip varchar(10) not null,
    place varchar(255) not null,
    phone varchar(255),
    email varchar(255),
    primary key(id)
);
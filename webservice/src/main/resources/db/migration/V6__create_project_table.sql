create table project (
    id int not null auto_increment,
    start timestamp not null,
    end timestamp not null,
    street varchar(255) not null,
    zip varchar(10) not null,
    place varchar(255) not null,
    supervisor_id int not null,
    category_id int not null,
    type_id int not null,
    state_id int not null,
    primary key(id),
    foreign key(supervisor_id) references supervisor(id),
    foreign key(category_id) references category(id),
    foreign key(type_id) references type(id),
    foreign key(state_id) references state(id)
);
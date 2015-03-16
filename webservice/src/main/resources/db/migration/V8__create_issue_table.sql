create table issue (
    id int not null auto_increment,
    description varchar(255) not null,
    created timestamp not null,
    solved timestamp not null,
    state_id int not null,
    project_id int not null,
    supervisor_id int not null,
    person_id int not null,
    primary key(id),
    foreign key(state_id) references state(id),
    foreign key(project_id) references project(id),
    foreign key(supervisor_id) references supervisor(id),
    foreign key(person_id) references person(id)
);
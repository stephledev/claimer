create table comment (
    id int not null auto_increment,
    created timestamp not null,
    content text not null,
    person_id int not null,
    issue_id int not null,
    primary key(id),
    foreign key(person_id) references person(id),
    foreign key(issue_id) references issue(id)
);
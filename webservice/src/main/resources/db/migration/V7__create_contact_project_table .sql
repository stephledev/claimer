create table contact_project (
    contact_id int not null,
    project_id int not null,
    primary key(contact_id, project_id),
    foreign key(contact_id) references contact(id),
    foreign key(project_id) references project(id)
);
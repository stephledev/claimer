create table assistant (
    id int not null,
    subcontractor_id int not null,
    primary key (id),
	foreign key (id) references person(id),
	foreign key(subcontractor_id) references company(id)

);
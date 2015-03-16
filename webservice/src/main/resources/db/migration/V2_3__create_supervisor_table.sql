create table supervisor (
    id int not null,
    generalcontractor_id int not null,
    primary key (id),
	foreign key (id) references person(id),
	foreign key(generalcontractor_id) references company(id)
);
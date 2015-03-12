create table general_contractor (
    id int not null,
    primary key (id),
	foreign key (id) references company(id)
);
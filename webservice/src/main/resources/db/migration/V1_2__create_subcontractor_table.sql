create table subcontractor (
    id int not null,
    primary key (id),
	foreign key (id) references company(id)
);
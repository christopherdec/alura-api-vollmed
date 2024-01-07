create table appointment
(
    id              bigint   not null auto_increment,
    professional_id bigint   not null,
    client_id       bigint   not null,
    date            datetime not null,
    primary key (id),
    constraint fk_appointment_professional_id foreign key (professional_id) references professional (id),
    constraint fk_appointment_client foreign key (client_id) references client (id)
);

alter table professional add active tinyint(1);

update professional set active = 1;

alter table professional modify column active tinyint(1) not null;
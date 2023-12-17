alter table client add active tinyint(1);

update client set active = 1;

alter table client modify column active tinyint(1) not null;
create table properties (
  application text not null,
  profile     text not null,
  label       text not null,
	key         text not null,
	value       text
);

create index properties_application_profile_label_index on properties (application, profile, label);
create unique index properties_application_profile_label_key_uindex	on properties (application, profile, label, key);

insert into properties (application, profile, label, key, value) values ('demo-client', 'default', 'master', 'demo.text', 'db');
insert into properties (application, profile, label, key, value) values ('demo-client', 'default', 'master', 'demo.num', '1');
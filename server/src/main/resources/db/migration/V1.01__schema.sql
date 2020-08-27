create table properties (
  application text not null,
  profile     text not null,
  label       text not null,
	key         text not null,
	value       text
);

-- insert into properties (key, value) values ('property1', 'value 1');
-- insert into properties (key, value) values ('property2', 'value 2');
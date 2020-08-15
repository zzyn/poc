create schema if not exists master_data collate utf8mb4_general_ci;

use master_data;

create table if not exists center
(
	id bigint auto_increment,
	city_key varchar(50) null,
	center_code varchar(40) null,
	center_name varchar(200) null,
	center_status int(3) null,
	created_by varchar(200) null,
	created_timestamp timestamp null,
	updated_by varchar(200) null,
	updated_timestamp timestamp null,
	constraint center_id_uindex
		unique (id)
);

alter table center
	add primary key (id);

create table if not exists city
(
	id bigint auto_increment,
	city_key varchar(50) null,
	city_name varchar(200) null,
	city_cname varchar(200) charset utf8 null,
	country varchar(200) null,
	created_by varchar(200) null,
	created_timestamp timestamp null,
	updated_by varchar(200) null,
	updated_timestamp timestamp null,
	constraint city_id_uindex
		unique (id)
);

alter table city
	add primary key (id);

create table grade
(
	input_key int not null,
	input_value nvarchar(50) not null,
	system_key nvarchar(50) null,
	system_value nvarchar(50) not null
);

alter table grade
	add primary key (input_key);
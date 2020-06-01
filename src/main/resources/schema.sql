drop table if exists record;
create table if not exists record (
    id int NOT NULL AUTO_INCREMENT,
    data_date date not null unique,
    distance float,
    memo varchar(255),
   	created_at timestamp not null default current_timestamp,
  	updated_at timestamp not null default current_timestamp on update current_timestamp,

    PRIMARY KEY (id)
);

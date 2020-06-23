drop table if exists record;
create table if not exists record (
    data_date date,
    distance float,
    memo varchar(255),
   	created_at timestamp not null default current_timestamp,
  	updated_at timestamp not null default current_timestamp on update current_timestamp,
    PRIMARY KEY (data_date)
);

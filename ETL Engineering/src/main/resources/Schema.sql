DROP TABLE IF EXISTS customers;

create table customers (
    customer_id BIGINT auto_increment NOT null primary key,
    sign_up_date varchar(30),
    first varchar (30),
    last varchar (30),
    email varchar(50),
    latitude double,
    longitude double,
    ip varchar(20)
);
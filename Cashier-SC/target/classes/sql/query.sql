drop database if exists cashier;

create database cashier;

use cashier;

create table total_per_item(
                               id int primary key auto_increment,
                               item_name varchar(100),
                               total decimal(10,2)
);

create table total_sales(
                            id int primary key auto_increment,
                            total_sales_count int,
                            total_sales decimal(10,2)
);
create database airline;
use airline;

create table login(username varchar(20) not null,id int not null auto_increment primary key ,password varchar(20) not null);
insert into login values("admin","admin@123,admin012@gmail.com");

create table passengers(passenger_id not null unique, username varchar(20) not null, nationality varchar(20) not null, aadhar varchar(20) unique, address varchar(50) , phone varchar(10) unique not null, gender enum('Male','Female','other') not null);
select * from passengers;

create table flights(f_code varchar(20) primary key, f_name varchar(20) not null, f_source varchar(20) not null, f_destination varchar(20) not null);
insert into flights values
     ('1002','A-2','Delhi','Mumbai'),
     ('1003','A-3','Mumbai','Chennai'),
     ('1004','A-5','Chennai','Goa'),
     ('1005','A-6','Mumbai','Ahmedabad'),
     ('1006','A-7','Noida','Bhopal');
select * from flights;

select username from passengers where aadhar = '12345678';

create table reservations(ticket varchar(20) primary key, aadhar varchar(20) not null, name varchar(20) not null, address varchar(50), nationality varchar(10), gender varchar(10), source varchar(20), destination varchar(20), date varchar(20), f_name varchar(10), f_code varchar(15));
DROP TABLE reservations;
select * from reservations;                      

drop database if exists nttdata;
create database nttdata;
use nttdata;

create table person (
    id bigint auto_increment primary key,
    name varchar(255),
    address varchar(255),
    phone varchar(255)
);

create table client (
    cliente_id bigint auto_increment primary key,
    password varchar(255),
    status char(1) default 'y',
    person_id bigint,
    constraint fk_person foreign key (person_id) references person(id)
);

create table account (
    id bigint auto_increment primary key,
    accountnumber varchar(255),
    type varchar(255),
    initialbalance double,
    status char(1) default 'y'
);

create table transaction (
    id bigint auto_increment primary key,
    date datetime,
    transactiontype varchar(255),
    amount double,
    balance double,
    account_id bigint,
    constraint fk_account foreign key (account_id) references account(id)
);


-- insert sample persons
insert into person (name, gender, age, identification, address, phone) values
('alice johnson', 'female', 30, 'id1001', '123 maple street', '555-1001'),
('bob smith', 'male', 45, 'id1002', '456 oak avenue', '555-1002');

-- insert sample clients
insert into client (password, status, person_id) values
('pass123', 'y', 1),
('pass456', 'y', 2);

-- insert sample accounts
insert into account (accountnumber, type, initialbalance, status) values
('acc1001', 'savings', 1000.00, 'y'),
('acc1002', 'checking', 500.00, 'y');

-- insert sample transactions
insert into transaction (date, transactiontype, amount, balance, account_id) values
(now(), 'deposito', 500.00, 1500.00, 1),
(now(), 'retiro', 100.00, 400.00, 2);


-- insert more persons
insert into person (name, gender, age, identification, address, phone) values
('carol lee', 'female', 28, 'id1003', '789 birch blvd', '555-1003'),
('david kim', 'male', 38, 'id1004', '321 pine road', '555-1004'),
('eva martinez', 'female', 50, 'id1005', '654 cedar lane', '555-1005');

-- insert more clients
insert into client (password, status, person_id) values
('pass789', 'y', 3),
('pass321', 'y', 4),
('pass654', 'y', 5);

-- insert more accounts
insert into account (accountnumber, type, initialbalance, status) values
('acc1003', 'ahorros', 3000.00, 'y'),
('acc1004', 'corriente', 200.00, 'y'),
('acc1005', 'ahorros', 5000.00, 'y');

-- insert more transactions
insert into transaction (date, transactiontype, amount, balance, account_id) values
(now(), 'deposito', 1500.00, 4500.00, 3),
(now(), 'retiro', 50.00, 150.00, 4),
(now(), 'deposito', 1000.00, 6000.00, 5);

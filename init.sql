
drop database if exists nttdata;
create database nttdata;
use nttdata;

create table person (
    id bigint auto_increment primary key,
    name varchar(255),
    identification varchar(255),
    address varchar(255),
    phone varchar(255)
);

create table client (
    cliente_id bigint auto_increment primary key,
    password varchar(255),
    status char(1) default 'Y',
    person_id bigint,
    constraint fk_person foreign key (person_id) references person(id)
);

create table account (
    id bigint auto_increment primary key,
    account_number varchar(255),
    type varchar(255),
    initial_balance double,
    status char(1) default 'Y',
    client_id bigint,
    constraint fk_cc_client foreign key (client_id) references client(cliente_id)
);

create table transaction (
    id bigint auto_increment primary key,
    date datetime,
    transaction_type varchar(255),
    amount double,
    balance double,
    account_id bigint,
    constraint fk_account foreign key (account_id) references account(id)
);


-- insert sample persons
insert into person (name, identification, address, phone) values
('alice johnson', 'id1001', '123 maple street', '555-1001'),
('bob smith', 'id1002', '456 oak avenue', '555-1002');

-- insert sample clients
insert into client (password, status, person_id) values
('pass123', 'Y', 1),
('pass456', 'Y', 2);

-- insert sample accounts
insert into account (account_number, type, initial_balance, status,client_id) values
('acc1001', 'ahorro', 1000.00, 'Y',1),
('acc1002', 'corriente', 500.00, 'Y',2);

-- insert sample transactions
insert into transaction (date, transaction_type, amount, balance, account_id) values
(now(), 'deposito', 500.00, 1500.00, 1),
(now(), 'retiro', 100.00, 400.00, 2);


-- insert more persons
insert into person (name, identification, address, phone) values
('carol lee', 'id1003', '789 birch blvd', '555-1003'),
('david kim', 'id1004', '321 pine road', '555-1004'),
('eva martinez', 'id1005', '654 cedar lane', '555-1005');

-- insert more clients
insert into client (password, status, person_id) values
('pass789', 'Y', 3),
('pass321', 'Y', 4),
('pass654', 'Y', 5);

-- insert more accounts
insert into account (account_number, type, initial_balance, status, client_id) values
('acc1003', 'ahorros', 3000.00, 'Y',3),
('acc1004', 'corriente', 200.00, 'Y',4),
('acc1005', 'ahorros', 5000.00, 'Y',5);

-- insert more transactions
insert into transaction (date, transaction_type, amount, balance, account_id) values
(now(), 'deposito', 1500.00, 4500.00, 3),
(now(), 'retiro', 50.00, 150.00, 4),
(now(), 'deposito', 1000.00, 6000.00, 5);

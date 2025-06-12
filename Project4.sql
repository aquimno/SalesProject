create schema finalGUIProject;
use finalGUIProject;

create table users(
	id integer not null unique auto_increment,
    username varchar(255) not null unique,
    pass varchar(255) not null,
    userRole integer not null,
    primary key (id)
);

create table product(
	id integer not null unique auto_increment,
    prodName varchar(255) not null,
    price decimal(10,2) not null,
    primary key (id)
);

create table sale(
	saleID integer not null unique auto_increment,
    prodID integer not null,
    userID integer not null,
    qty integer not null,
    total decimal(10,2) not null,
    primary key (saleID),
    foreign key (prodID) references product(id),
    foreign key (userID) references users(id)
);

insert into users (username, pass, userRole) VALUES ("Admin", "Admin", 1);

DELIMITER $$
create procedure RegisterNewUser(
	IN inName varchar(255),
    IN inPass varchar(255),
    IN inRole int)
BEGIN
    insert into users (username, pass, userRole) values (inName, inPass, inRole);
END$$

DELIMITER $$
create procedure LoginWithCreds(
	IN inUsername varchar(255),
    IN inPass varchar(255))
BEGIN
	Select id, username, userRole from users where username = inUsername AND pass = inPass;
END$$

DELIMITER $$
create procedure GetAllProducts()
BEGIN
	select prodName, id, price from product;
END$$

DELIMITER $$
create procedure GetSalesTotal() 
BEGIN
	select SUM(total) as totalSales from sale;
END$$

DELIMITER $$
create procedure SubmitOrder(
	IN in_userID int,
    IN in_prodID int,
    IN in_qty int)
BEGIN
	insert into sale (userId, prodID, qty, total) 
    values (in_userID, in_prodID, in_qty, (select price from product where id = in_prodID) * in_qty);
END$$

DELIMITER $$
create procedure SubmitNewProduct(
    IN in_prodName varchar(255),
    IN in_price decimal(10,2))
BEGIN
	insert into product (prodName, price) values (in_prodName, in_price);
END$$

DELIMITER ;
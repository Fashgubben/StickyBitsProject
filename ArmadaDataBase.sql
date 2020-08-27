
create database fleet;

use fleet;

create table ship (
ShipID int not null auto_increment,
ShipName varchar(50) not null,
Propulsion varchar(50) not null,
Carrying varChar(50) not null,
WeightInTons double not null,
PRIMARY KEY (ShipID)
);

create table ShipLog (
ShipLogID int not null auto_increment,
ShipID int not null,
Position varchar(7) not null,
DestinationCoordinates varchar(7),
OriginCoordinates varchar(7),
SpeedKnots int,
Bearing varchar(2),
PRIMARY KEY (ShipLogID),
FOREIGN KEY (ShipID) REFERENCES ship(ShipID)
);

insert into ship(ShipName, Propulsion, Carrying, WeightInTons)
VALUES("HMS Oboy", "Motor", "Cargo", 60);

select * from ship;

-- drop database fleet
create database fleet;

use fleet;

create table ship (
ShipID int not null auto_increment,
ShipName varchar(50) not null,
MaxCargoWeight int,
MaxSpeed int not null,
CruisingSpeed int not null,
Cargo varChar(50) not null,
PRIMARY KEY (ShipID)
);

create table ShipLog (
ShipLogID int not null auto_increment,
ShipID int not null,
CurrentCoordinates varchar(7) not null,
DestinationCoordinates varchar(7),
StartCoordinates varchar(7) not null,
CurrentSpeed int,
NauticalMilage int,
Bearing varchar(20),
Route varchar(50),
CurrentRoute varchar(50),
PRIMARY KEY (ShipLogID)
);

insert into ship(ShipName, MaxCargoWeight, MaxSpeed, CruisingSpeed, Cargo)
VALUES
("HMS Esteban", 100, 40, 30, "Oil"),
("HMS G.Vasa", 59, 40, 30, "Container"),
("M/S Sinaloa", 43, 40, 30, "Oil"),
("HSM Britt-Mari", 56, 40, 30, "Container"),
("HMS Boaty McBoatface", 62, 40, 20, "Oil"),
("HMS Retail", 76, 30, 20, "Container"),
("Black Pearl", 234, 40, 30, "Oil"),
("S/S Flying Dutchman", 52, 40, 20, "Container"),
("S/S Chris P Bacon", 51, 40, 30, "Oil"),
("HMS Vasa", 512, 40, 30, "Container");

insert into shiplog(ShipID, CurrentCoordinates, StartCoordinates, Route, DestinationCoordinates, CurrentSpeed, CurrentRoute)
Values
(1,"0,0", "0,0", "99,99-49,49-0,0","99,99", "30", "99,99-49,49-0,0"),
(2,"0,0", "0,0", "49,49-0,99-0,0", "49,49", "30", "49,49-0,99-0,0"),
(3,"0,99", "0,99", "0,0-99,0-0,99", "0,0", "30", "0,0-99,0-0,99"),
(4,"0,99","0,99", "49,49-0,99-0,99", "49,49", "30", "49,49-0,99-0,99"),
(5,"99,0", "99,0", "0,99-0,0-99,0", "0,99", "20", "0,99-0,0-99,0"),
(6,"99,0", "99,0", "99,99-0,0-99,0", "99,99", "20", "99,99-0,0-99,0"),
(7,"99,99", "99,99", "99,0-0,0-99,99", "99,0", "30", "99,0-0,0-99,99"),
(8,"99,99", "99,99", "0,99-49,49-99,99", "0,99", "20", "0,99-49,49-99,99"),
(9,"49,49", "49,49", "0,0-0,99-49,49", "0,0","30", "0,0-0,99-49,49"),
(10,"49,49","49,49", "99,99-99,0-49,49", "99,99","30", "99,99-99,0-49,49");


select s.ShipName,s.Cargo, sl.CurrentCoordinates, sl.DestinationCoordinates, s.CruisingSpeed
from ship s inner join shiplog sl
on s.ShipID = sl.ShipID;

-- Get all ship postition
CREATE VIEW uvshipposition AS
select s.ShipName, sl.Bearing, sl.CurrentCoordinates
from ship s inner join shiplog sl
on s.ShipId = sl.ShipId;

-- View to  populate java code
Create view uvships as
select s.ShipID, s.ShipName, s.MaxCargoWeight, s.MaxSpeed, s.CruisingSpeed, s.Cargo, sl.ShipLogID, sl.CurrentCoordinates, sl.DestinationCoordinates, sl.StartCoordinates, sl.CurrentSpeed, sl.NauticalMilage, sl.Bearing, sl.Route, sl.CurrentRoute
from ship s inner join shiplog sl
on s.ShipId = sl.ShipId;

-- Stored Procedure to update current coordinates
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE uspUpdateCurrentCoordinatesAndBearingAndNauticalMilage(sID int, slID int, coordinates varchar(20), bearing varchar(20), nauticalMilage int)
BEGIN
    update shiplog
    set CurrentCoordinates = coordinates, Bearing = bearing, NauticalMilage = nauticalMilage
    where ShipLogID = slID and ShipId = sID;
END$$
DELIMITER ;

-- Stored Procedure to update Route coordinates
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE uspUpdateDestinationStartCoordinates(sID int, slID int, dest varchar(20), start varchar(20))
BEGIN
    update shiplog
    set Route = DestionationCoordinates = destCoord , StartCoordinates = startCoord
    where ShipLogID = slID and ShipId = sID;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE uspUpdateCurrentRoute(sID int, slID int, currentroute varchar(20), destinationcoord varchar(20))
BEGIN
    update shiplog
    set CurrentRoute = currentroute, DestinationCoordinates = destinationcoord
    where ShipLogID = slID and ShipId = sID;
END$$
DELIMITER ;

select * from shiplog;
select * from uvships;
select * from uvshipposition



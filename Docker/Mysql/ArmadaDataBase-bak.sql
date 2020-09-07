-- MySQL dump 10.13  Distrib 8.0.21, for Linux (x86_64)
--
-- Host: localhost    Database: fleet
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ship`
--

DROP TABLE IF EXISTS `ship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ship` (
  `ShipID` int NOT NULL AUTO_INCREMENT,
  `ShipName` varchar(50) NOT NULL,
  `MaxCargoWeight` int DEFAULT NULL,
  `MaxSpeed` int NOT NULL,
  `CruisingSpeed` int NOT NULL,
  `Cargo` varchar(50) NOT NULL,
  PRIMARY KEY (`ShipID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ship`
--

LOCK TABLES `ship` WRITE;
/*!40000 ALTER TABLE `ship` DISABLE KEYS */;
INSERT INTO `ship` VALUES (1,'HMS Esteban',100,40,30,'Oil'),(2,'HMS G.Vasa',59,40,30,'Container'),(3,'M/S Sinaloa',43,40,30,'Oil'),(4,'HSM Britt-Mari',56,40,30,'Container'),(5,'HMS Boaty McBoatface',62,40,20,'Oil'),(6,'HMS Retail',76,30,20,'Container'),(7,'Black Pearl',234,40,30,'Oil'),(8,'S/S Flying Dutchman',52,40,20,'Container'),(9,'S/S Chris P Bacon',51,40,30,'Oil'),(10,'HMS Vasa',512,40,30,'Container');
/*!40000 ALTER TABLE `ship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipLog`
--

DROP TABLE IF EXISTS `shipLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipLog` (
  `ShipLogID` int NOT NULL AUTO_INCREMENT,
  `ShipID` int NOT NULL,
  `CurrentCoordinates` varchar(7) NOT NULL,
  `DestinationCoordinates` varchar(7) DEFAULT NULL,
  `StartCoordinates` varchar(7) NOT NULL,
  `CurrentSpeed` int DEFAULT NULL,
  `NauticalMilage` int DEFAULT NULL,
  `Bearing` varchar(20) DEFAULT NULL,
  `Route` varchar(50) DEFAULT NULL,
  `CurrentRoute` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ShipLogID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipLog`
--

LOCK TABLES `shipLog` WRITE;
/*!40000 ALTER TABLE `shipLog` DISABLE KEYS */;
INSERT INTO `shipLog` VALUES (1,1,'81,81','99,99','0,0',30,1467,'SE','99,99-49,49-0,0','99,99-49,49-0,0'),(2,2,'40,58','0,99','0,0',30,1444,'NE','49,49-0,99-0,0','0,99-0,0'),(3,3,'0,96','0,0','0,99',30,1488,'W','0,0-99,0-0,99','0,0-99,0-0,99'),(4,4,'48,51','49,49','0,99',30,1488,'NE','49,49-0,99-0,99','49,49-0,99-0,99'),(5,5,'7,92','0,99','99,0',20,983,'NE','0,99-0,0-99,0','0,99-0,0-99,0'),(6,6,'99,92','99,99','99,0',20,983,'E','99,99-0,0-99,0','99,99-0,0-99,0'),(7,7,'99,96','99,0','99,99',30,1488,'W','99,0-0,0-99,99','99,0-0,0-99,99'),(8,8,'81,81','99,99','99,99',20,982,'SE','0,99-49,49-99,99','99,99'),(9,9,'48,51','49,49','49,49',30,1486,'NE','0,0-0,99-49,49','49,49'),(10,10,'51,48','49,49','49,49',30,1487,'SW','99,99-99,0-49,49','49,49');
/*!40000 ALTER TABLE `shipLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `uvshipposition`
--

DROP TABLE IF EXISTS `uvshipposition`;
/*!50001 DROP VIEW IF EXISTS `uvshipposition`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `uvshipposition` AS SELECT 
 1 AS `ShipName`,
 1 AS `Bearing`,
 1 AS `CurrentCoordinates`,
 1 AS `NauticalMilage`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `uvships`
--

DROP TABLE IF EXISTS `uvships`;
/*!50001 DROP VIEW IF EXISTS `uvships`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `uvships` AS SELECT 
 1 AS `ShipID`,
 1 AS `ShipName`,
 1 AS `MaxCargoWeight`,
 1 AS `MaxSpeed`,
 1 AS `CruisingSpeed`,
 1 AS `Cargo`,
 1 AS `ShipLogID`,
 1 AS `CurrentCoordinates`,
 1 AS `DestinationCoordinates`,
 1 AS `StartCoordinates`,
 1 AS `CurrentSpeed`,
 1 AS `NauticalMilage`,
 1 AS `Bearing`,
 1 AS `Route`,
 1 AS `CurrentRoute`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `uvshipposition`
--

/*!50001 DROP VIEW IF EXISTS `uvshipposition`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = latin1 */;
/*!50001 SET character_set_results     = latin1 */;
/*!50001 SET collation_connection      = latin1_swedish_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `uvshipposition` AS select `s`.`ShipName` AS `ShipName`,`sl`.`Bearing` AS `Bearing`,`sl`.`CurrentCoordinates` AS `CurrentCoordinates`,`sl`.`NauticalMilage` AS `NauticalMilage` from (`ship` `s` join `shipLog` `sl` on((`s`.`ShipID` = `sl`.`ShipID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `uvships`
--

/*!50001 DROP VIEW IF EXISTS `uvships`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = latin1 */;
/*!50001 SET character_set_results     = latin1 */;
/*!50001 SET collation_connection      = latin1_swedish_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `uvships` AS select `s`.`ShipID` AS `ShipID`,`s`.`ShipName` AS `ShipName`,`s`.`MaxCargoWeight` AS `MaxCargoWeight`,`s`.`MaxSpeed` AS `MaxSpeed`,`s`.`CruisingSpeed` AS `CruisingSpeed`,`s`.`Cargo` AS `Cargo`,`sl`.`ShipLogID` AS `ShipLogID`,`sl`.`CurrentCoordinates` AS `CurrentCoordinates`,`sl`.`DestinationCoordinates` AS `DestinationCoordinates`,`sl`.`StartCoordinates` AS `StartCoordinates`,`sl`.`CurrentSpeed` AS `CurrentSpeed`,`sl`.`NauticalMilage` AS `NauticalMilage`,`sl`.`Bearing` AS `Bearing`,`sl`.`Route` AS `Route`,`sl`.`CurrentRoute` AS `CurrentRoute` from (`ship` `s` join `shipLog` `sl` on((`s`.`ShipID` = `sl`.`ShipID`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-07 13:24:24

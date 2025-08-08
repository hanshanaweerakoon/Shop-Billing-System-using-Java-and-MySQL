-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 08, 2025 at 08:01 PM
-- Server version: 8.0.31
-- PHP Version: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shopbillingsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `billhistory`
--

DROP TABLE IF EXISTS `billhistory`;
CREATE TABLE IF NOT EXISTS `billhistory` (
  `Code` int NOT NULL AUTO_INCREMENT,
  `DateTime` date NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Total` double NOT NULL,
  `Cash` tinyint(1) NOT NULL,
  `UpdatedINO` varchar(50) NOT NULL,
  `Remark` varchar(50) NOT NULL,
  PRIMARY KEY (`Code`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `billhistory`
--

INSERT INTO `billhistory` (`Code`, `DateTime`, `Name`, `Total`, `Cash`, `UpdatedINO`, `Remark`) VALUES
(21, '2023-11-19', 'WMBH Weerakoon', 500000, 1, '', 'capital'),
(22, '2023-11-19', 'WA Wijerathna', 2950, 1, '', ''),
(23, '2023-11-19', 'AD Weerakoon', 1950, 1, '', ''),
(24, '2023-11-21', 'AD Weerakoon', 1950, 1, '23', 'debtor'),
(25, '2023-11-21', 'RA Rathnapala', 4570, 1, '', ''),
(26, '2024-02-06', 'bhawantha hanshana', 2880, 1, '', '');

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

DROP TABLE IF EXISTS `bills`;
CREATE TABLE IF NOT EXISTS `bills` (
  `Code` int NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Qty.` double NOT NULL,
  `Unit price` double NOT NULL,
  `Price` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `capitalaccount`
--

DROP TABLE IF EXISTS `capitalaccount`;
CREATE TABLE IF NOT EXISTS `capitalaccount` (
  `DebitDate` date DEFAULT NULL,
  `DebitRef` int DEFAULT NULL,
  `DebitRemark` varchar(50) DEFAULT NULL,
  `DebitRs` double DEFAULT NULL,
  `CreditDate` date DEFAULT NULL,
  `CreditRef` int DEFAULT NULL,
  `CreditRemark` varchar(50) DEFAULT NULL,
  `CreditRs` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `capitalaccount`
--

INSERT INTO `capitalaccount` (`DebitDate`, `DebitRef`, `DebitRemark`, `DebitRs`, `CreditDate`, `CreditRef`, `CreditRemark`, `CreditRs`) VALUES
(NULL, NULL, NULL, NULL, '2023-11-19', 21, 'capital', 500000);

-- --------------------------------------------------------

--
-- Table structure for table `cashaccount`
--

DROP TABLE IF EXISTS `cashaccount`;
CREATE TABLE IF NOT EXISTS `cashaccount` (
  `DebitDate` date DEFAULT NULL,
  `DebitRef` int DEFAULT NULL,
  `DebitRemark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `DebitRs` double DEFAULT NULL,
  `CreditDate` date DEFAULT NULL,
  `CreditRef` int DEFAULT NULL,
  `CreditRemark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `CreditRs` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `cashaccount`
--

INSERT INTO `cashaccount` (`DebitDate`, `DebitRef`, `DebitRemark`, `DebitRs`, `CreditDate`, `CreditRef`, `CreditRemark`, `CreditRs`) VALUES
(NULL, NULL, NULL, NULL, '2023-11-19', 28, 'Elephant House', 17000),
(NULL, NULL, NULL, NULL, '2023-11-19', 31, 'Roy Rajesh', 81300),
(NULL, NULL, NULL, NULL, '2023-11-19', 32, 'Munchi172', 15000),
('2023-11-19', 21, 'capital', 500000, NULL, NULL, NULL, NULL),
(NULL, NULL, NULL, NULL, '2023-11-19', 33, 'Sunil Dambulla', 36000),
('2023-11-19', 22, 'WA Wijerathna', 2950, NULL, NULL, NULL, NULL),
('2023-11-21', 24, 'AD Weerakoon', 1950, NULL, NULL, NULL, NULL),
(NULL, NULL, NULL, NULL, '2023-11-21', 34, 'Nimal M.', 126000),
(NULL, NULL, NULL, NULL, '2023-11-21', 35, 'drawings', 5000),
(NULL, NULL, NULL, NULL, '2023-11-21', 36, 'Electricity Bill', 2500),
('2023-11-21', 25, 'RA Rathnapala', 4570, NULL, NULL, NULL, NULL),
('2024-02-06', 26, 'bhawantha hanshana', 2880, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `creditorsaccount`
--

DROP TABLE IF EXISTS `creditorsaccount`;
CREATE TABLE IF NOT EXISTS `creditorsaccount` (
  `DebitDate` date DEFAULT NULL,
  `DebitRef` int DEFAULT NULL,
  `DebitRemark` varchar(50) DEFAULT NULL,
  `DebitRs` double DEFAULT NULL,
  `CreditDate` date DEFAULT NULL,
  `CreditRef` int DEFAULT NULL,
  `CreditRemark` varchar(50) DEFAULT NULL,
  `CreditRs` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `creditorsaccount`
--

INSERT INTO `creditorsaccount` (`DebitDate`, `DebitRef`, `DebitRemark`, `DebitRs`, `CreditDate`, `CreditRef`, `CreditRemark`, `CreditRs`) VALUES
(NULL, NULL, NULL, NULL, '2023-11-19', 29, 'Nimal M.', 126000),
(NULL, NULL, NULL, NULL, '2023-11-19', 30, 'Sunil Dambulla', 36000),
('2023-11-19', 33, 'Sunil Dambulla', 36000, NULL, NULL, NULL, NULL),
('2023-11-21', 34, 'Nimal M.', 126000, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `debtorsaccount`
--

DROP TABLE IF EXISTS `debtorsaccount`;
CREATE TABLE IF NOT EXISTS `debtorsaccount` (
  `DebitDate` date DEFAULT NULL,
  `DebitRef` int DEFAULT NULL,
  `DebitRemark` varchar(50) DEFAULT NULL,
  `DebitRs` double DEFAULT NULL,
  `CreditDate` date DEFAULT NULL,
  `CreditRef` int DEFAULT NULL,
  `CreditRemark` varchar(50) DEFAULT NULL,
  `CreditRs` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `debtorsaccount`
--

INSERT INTO `debtorsaccount` (`DebitDate`, `DebitRef`, `DebitRemark`, `DebitRs`, `CreditDate`, `CreditRef`, `CreditRemark`, `CreditRs`) VALUES
('2023-11-19', 23, 'AD Weerakoon', 1950, NULL, NULL, NULL, NULL),
(NULL, NULL, NULL, NULL, '2023-11-21', 24, 'AD Weerakoon', 1950);

-- --------------------------------------------------------

--
-- Table structure for table `details`
--

DROP TABLE IF EXISTS `details`;
CREATE TABLE IF NOT EXISTS `details` (
  `BR` varchar(50) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `User` varchar(15) NOT NULL,
  `Others` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `details`
--

INSERT INTO `details` (`BR`, `Name`, `User`, `Others`) VALUES
('000123', 'BHW', 'admin', '');

-- --------------------------------------------------------

--
-- Table structure for table `drawingsaccount`
--

DROP TABLE IF EXISTS `drawingsaccount`;
CREATE TABLE IF NOT EXISTS `drawingsaccount` (
  `DebitDate` date DEFAULT NULL,
  `DebitRef` int DEFAULT NULL,
  `DebitRemark` varchar(50) DEFAULT NULL,
  `DebitRs` double DEFAULT NULL,
  `CreditDate` date DEFAULT NULL,
  `CreditRef` int DEFAULT NULL,
  `CreditRemark` varchar(50) DEFAULT NULL,
  `CreditRs` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `drawingsaccount`
--

INSERT INTO `drawingsaccount` (`DebitDate`, `DebitRef`, `DebitRemark`, `DebitRs`, `CreditDate`, `CreditRef`, `CreditRemark`, `CreditRs`) VALUES
('2023-11-21', 35, 'drawings', 5000, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `expensesaccount`
--

DROP TABLE IF EXISTS `expensesaccount`;
CREATE TABLE IF NOT EXISTS `expensesaccount` (
  `DebitDate` date DEFAULT NULL,
  `DebitRef` int DEFAULT NULL,
  `DebitRemark` varchar(50) DEFAULT NULL,
  `DebitRs` double DEFAULT NULL,
  `CreditDate` date DEFAULT NULL,
  `CreditRef` int DEFAULT NULL,
  `CreditRemark` varchar(50) DEFAULT NULL,
  `CreditRs` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `expensesaccount`
--

INSERT INTO `expensesaccount` (`DebitDate`, `DebitRef`, `DebitRemark`, `DebitRs`, `CreditDate`, `CreditRef`, `CreditRemark`, `CreditRs`) VALUES
('2023-11-21', 36, 'Electricity Bill', 2500, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `incomeaccount`
--

DROP TABLE IF EXISTS `incomeaccount`;
CREATE TABLE IF NOT EXISTS `incomeaccount` (
  `DebitDate` date DEFAULT NULL,
  `DebitRef` int DEFAULT NULL,
  `DebitRemark` varchar(50) DEFAULT NULL,
  `DebitRs` double DEFAULT NULL,
  `CreditDate` date DEFAULT NULL,
  `CreditRef` int DEFAULT NULL,
  `CreditRemark` varchar(50) DEFAULT NULL,
  `CreditRs` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `incomeaccount`
--

INSERT INTO `incomeaccount` (`DebitDate`, `DebitRef`, `DebitRemark`, `DebitRs`, `CreditDate`, `CreditRef`, `CreditRemark`, `CreditRs`) VALUES
(NULL, NULL, NULL, NULL, '2023-11-19', 22, 'WA Wijerathna', 475),
(NULL, NULL, NULL, NULL, '2023-11-19', 23, 'AD Weerakoon', 250),
(NULL, NULL, NULL, NULL, '2023-11-21', 25, 'RA Rathnapala', 775),
(NULL, NULL, NULL, NULL, '2024-02-06', 26, 'bhawantha hanshana', 460);

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE IF NOT EXISTS `item` (
  `Code` int NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Unit` varchar(50) NOT NULL,
  `Price` double NOT NULL,
  `Details` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`Code`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`Code`, `Name`, `Unit`, `Price`, `Details`) VALUES
(1253, 'Sugar', 'kg', 350, ''),
(1566, 'Cream cracker', 'pcs', 100, ''),
(2353, 'Onion', 'kg', 205, ''),
(2586, 'Milk', 'L', 250, ''),
(123, 'Samba rice', 'kg', 420, ''),
(124, 'Nadu rice', 'kg', 320, ''),
(2568, 'Coco oil', 'L', 220, ''),
(2263, 'Potato', 'kg', 300, ''),
(1254, 'Carrot', 'kg', 360, ''),
(2365, 'Dhal', 'kg', 350, ''),
(2589, 'Flour', 'kg', 400, ''),
(2222, 'Yogout', 'pcs', 80, ''),
(1233, 'Ice Cream', 'pcs', 120, '');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (
  `username` varchar(15) NOT NULL,
  `password` varchar(6) NOT NULL,
  `Administration` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`, `Administration`) VALUES
('admin', '000000', 1),
('user1', '000000', 0),
('user2', '000000', 1);

-- --------------------------------------------------------

--
-- Table structure for table `purchaseaccount`
--

DROP TABLE IF EXISTS `purchaseaccount`;
CREATE TABLE IF NOT EXISTS `purchaseaccount` (
  `DebitDate` date DEFAULT NULL,
  `DebitRef` int DEFAULT NULL,
  `DebitRemark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `DebitRs` double DEFAULT NULL,
  `CreditDate` date DEFAULT NULL,
  `CreditRef` int DEFAULT NULL,
  `CreditRemark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `CreditRs` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `purchaseaccount`
--

INSERT INTO `purchaseaccount` (`DebitDate`, `DebitRef`, `DebitRemark`, `DebitRs`, `CreditDate`, `CreditRef`, `CreditRemark`, `CreditRs`) VALUES
('2023-11-19', 28, 'Elephant House', 17000, NULL, NULL, NULL, NULL),
('2023-11-19', 29, 'Nimal M.', 126000, NULL, NULL, NULL, NULL),
('2023-11-19', 30, 'Sunil Dambulla', 36000, NULL, NULL, NULL, NULL),
('2023-11-19', 31, 'Roy Rajesh', 81300, NULL, NULL, NULL, NULL),
('2023-11-19', 32, 'Munchi172', 15000, NULL, NULL, NULL, NULL),
(NULL, NULL, NULL, NULL, '2023-11-19', 22, 'WA Wijerathna', 2475),
(NULL, NULL, NULL, NULL, '2023-11-19', 23, 'AD Weerakoon', 1700),
(NULL, NULL, NULL, NULL, '2023-11-21', 25, 'RA Rathnapala', 3795),
(NULL, NULL, NULL, NULL, '2024-02-06', 26, 'bhawantha hanshana', 2420);

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
CREATE TABLE IF NOT EXISTS `stock` (
  `Code` int NOT NULL,
  `Stock` double NOT NULL,
  `UnitPrice` double NOT NULL,
  PRIMARY KEY (`Code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`Code`, `Stock`, `UnitPrice`) VALUES
(1253, 93, 290),
(1566, 198, 75),
(2353, 150, 150),
(2586, 10, 180),
(123, 188, 350),
(124, 193, 280),
(2568, 19, 150),
(2263, 30, 250),
(1254, 20, 300),
(2365, 96, 300),
(2589, 50, 350),
(2222, 90, 70),
(1233, 100, 100);

-- --------------------------------------------------------

--
-- Table structure for table `stockhistory`
--

DROP TABLE IF EXISTS `stockhistory`;
CREATE TABLE IF NOT EXISTS `stockhistory` (
  `Code` int NOT NULL AUTO_INCREMENT,
  `DateTime` date NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Total` double NOT NULL,
  `Cash` tinyint(1) NOT NULL,
  `UpdatedINO` varchar(50) NOT NULL,
  `Remark` varchar(50) NOT NULL,
  PRIMARY KEY (`Code`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `stockhistory`
--

INSERT INTO `stockhistory` (`Code`, `DateTime`, `Name`, `Total`, `Cash`, `UpdatedINO`, `Remark`) VALUES
(28, '2023-11-19', 'Elephant House', 17000, 1, '', ''),
(29, '2023-11-19', 'Nimal M.', 126000, 1, '', ''),
(30, '2023-11-19', 'Sunil Dambulla', 36000, 1, '', ''),
(31, '2023-11-19', 'Roy Rajesh', 81300, 1, '', ''),
(32, '2023-11-19', 'Munchi172', 15000, 1, '', ''),
(33, '2023-11-19', 'Sunil Dambulla', 36000, 1, '30', 'creditors'),
(34, '2023-11-21', 'Nimal M.', 126000, 1, '29', 'creditor'),
(35, '2023-11-21', 'WMBH Weerakoon', 5000, 1, '', 'drawings'),
(36, '2023-11-21', 'WMSA Weerakoon', 2500, 1, '', 'Electricity Bill');

-- --------------------------------------------------------

--
-- Table structure for table `stockinvoice`
--

DROP TABLE IF EXISTS `stockinvoice`;
CREATE TABLE IF NOT EXISTS `stockinvoice` (
  `Code` int NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Qty.` double NOT NULL,
  `Unit price` double NOT NULL,
  `Price` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

--
-- Generated by mysql-backup4j
-- https://github.com/SeunMatt/mysql-backup4j
-- Date: 7-12-2020 21:11:23
--

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

--
-- start  table dump : archived_invoices
--

CREATE TABLE IF NOT EXISTS `archived_invoices` (
  `INVOICE_ID` int DEFAULT NULL,
  `CUSTOMER_ID` int DEFAULT NULL,
  `BILL_DATE` date DEFAULT NULL,
  `PAYMENT_DATE` date DEFAULT NULL,
  `INVOICE_TYPE` varchar(50) DEFAULT NULL,
  `RECCURENCE` varchar(20) DEFAULT NULL,
  `REPEAT_EVERY` int DEFAULT NULL,
  `CYCLES` int DEFAULT NULL,
  `CHANGE_FROM_PAYMENT` float DEFAULT NULL,
  `FULLYPAYED` tinyint(1) DEFAULT NULL,
  `NOTES` varchar(250) DEFAULT NULL,
  KEY `CUSTOMER_ID` (`CUSTOMER_ID`),
  CONSTRAINT `archived_invoices_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customers` (`CUSTOMER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- end  table dump : archived_invoices
--


--
-- Inserts of archived_invoices
--


/*!40000 ALTER TABLE `archived_invoices` DISABLE KEYS */;

--
-- start table insert : archived_invoices
--
INSERT INTO `archived_invoices`(`INVOICE_ID`, `CUSTOMER_ID`, `BILL_DATE`, `PAYMENT_DATE`, `INVOICE_TYPE`, `RECCURENCE`, `REPEAT_EVERY`, `CYCLES`, `CHANGE_FROM_PAYMENT`, `FULLYPAYED`, `NOTES`) VALUES 
(12, 1, '2020-12-07', '2020-12-07', 'ONCE', 'ONCE', 0, 0, '0.0', 1, '1re');
--
-- end table insert : archived_invoices
--

/*!40000 ALTER TABLE `archived_invoices` ENABLE KEYS */;


--
-- start  table dump : archived_items
--

CREATE TABLE IF NOT EXISTS `archived_items` (
  `INVOICE_ID` int DEFAULT NULL,
  `ITEM_ID` int DEFAULT NULL,
  `ITEM_TYPE` varchar(60) DEFAULT NULL,
  `PRICE` float DEFAULT NULL,
  `DISCOUNT` float DEFAULT NULL,
  `QUANTITY` int DEFAULT NULL,
  `ISPAYED` tinyint(1) DEFAULT NULL,
  `PAYMENT_DATE` date DEFAULT NULL,
  `ISRECURRING` tinyint(1) DEFAULT NULL,
  `RECURRENCE` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- end  table dump : archived_items
--


--
-- Inserts of archived_items
--


/*!40000 ALTER TABLE `archived_items` DISABLE KEYS */;

--
-- start table insert : archived_items
--
INSERT INTO `archived_items`(`INVOICE_ID`, `ITEM_ID`, `ITEM_TYPE`, `PRICE`, `DISCOUNT`, `QUANTITY`, `ISPAYED`, `PAYMENT_DATE`, `ISRECURRING`, `RECURRENCE`) VALUES 
(12, 6, 'SUPPORT', '30.0', '0.0', 1, 0, null, 0, 'YEARLY');
--
-- end table insert : archived_items
--

/*!40000 ALTER TABLE `archived_items` ENABLE KEYS */;


--
-- start  table dump : archived_payments
--

CREATE TABLE IF NOT EXISTS `archived_payments` (
  `PAYMENT_ID` int DEFAULT NULL,
  `INVOICE_ID` int DEFAULT NULL,
  `PAYMENT_DATE` date DEFAULT NULL,
  `PRICE` float DEFAULT NULL,
  `NOTES` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- end  table dump : archived_payments
--


--
-- Inserts of archived_payments
--


/*!40000 ALTER TABLE `archived_payments` DISABLE KEYS */;

--
-- start table insert : archived_payments
--
INSERT INTO `archived_payments`(`PAYMENT_ID`, `INVOICE_ID`, `PAYMENT_DATE`, `PRICE`, `NOTES`) VALUES 
(1, 12, '2020-12-07', '30.0', ' ');
--
-- end table insert : archived_payments
--

/*!40000 ALTER TABLE `archived_payments` ENABLE KEYS */;


--
-- start  table dump : category
--

CREATE TABLE IF NOT EXISTS `category` (
  `CATEGORY_ID` int NOT NULL AUTO_INCREMENT,
  `NAMECATEGORY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- end  table dump : category
--


--
-- Inserts of category
--


/*!40000 ALTER TABLE `category` DISABLE KEYS */;

--
-- start table insert : category
--
INSERT INTO `category`(`CATEGORY_ID`, `NAMECATEGORY`) VALUES 
(1, 'DEVELOPER'),
(2, 'BACKEND TRASH'),
(3, 'GUI'),
(4, 'UX');
--
-- end table insert : category
--

/*!40000 ALTER TABLE `category` ENABLE KEYS */;


--
-- start  table dump : customer_items
--

CREATE TABLE IF NOT EXISTS `customer_items` (
  `INVOICE_ID` int DEFAULT NULL,
  `ITEM_ID` int DEFAULT NULL,
  `ITEM_TYPE` varchar(60) DEFAULT NULL,
  `PRICE` float DEFAULT NULL,
  `DISCOUNT` float DEFAULT NULL,
  `QUANTITY` int DEFAULT NULL,
  `ISPAYED` tinyint(1) DEFAULT NULL,
  `PAYMENT_DATE` date DEFAULT NULL,
  `ISRECURRING` tinyint(1) DEFAULT NULL,
  `RECURRENCE` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- end  table dump : customer_items
--


--
-- Inserts of customer_items
--


/*!40000 ALTER TABLE `customer_items` DISABLE KEYS */;

--
-- start table insert : customer_items
--
INSERT INTO `customer_items`(`INVOICE_ID`, `ITEM_ID`, `ITEM_TYPE`, `PRICE`, `DISCOUNT`, `QUANTITY`, `ISPAYED`, `PAYMENT_DATE`, `ISRECURRING`, `RECURRENCE`) VALUES 
(1, 1, 'HOSTING SMALL', '50.0', '10.0', 1, 0, null, 0, 'YEARLY'),
(1, 2, 'DOMAIN.GR', '20.0', '0.0', 1, 0, null, 0, 'YEARLY'),
(10, 4, 'HOSTING BIG', '100.0', '0.0', 1, 0, null, 0, 'YEARLY');
--
-- end table insert : customer_items
--

/*!40000 ALTER TABLE `customer_items` ENABLE KEYS */;


--
-- start  table dump : customers
--

CREATE TABLE IF NOT EXISTS `customers` (
  `CUSTOMER_ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) DEFAULT NULL,
  `COUNTRY` varchar(20) DEFAULT NULL,
  `CITY` varchar(20) DEFAULT NULL,
  `ADDRESS` varchar(20) DEFAULT NULL,
  `ZIP` varchar(10) DEFAULT NULL,
  `PHONE` varchar(15) DEFAULT NULL,
  `EMAIL` varchar(40) DEFAULT NULL,
  `AFM` varchar(20) DEFAULT NULL,
  `CUSTOMER_GROUP` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`CUSTOMER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- end  table dump : customers
--


--
-- Inserts of customers
--


/*!40000 ALTER TABLE `customers` DISABLE KEYS */;

--
-- start table insert : customers
--
INSERT INTO `customers`(`CUSTOMER_ID`, `NAME`, `COUNTRY`, `CITY`, `ADDRESS`, `ZIP`, `PHONE`, `EMAIL`, `AFM`, `CUSTOMER_GROUP`) VALUES 
(1, 'Apostolos', 'GREECE', 'ATHENS', 'KAREA 100 A', '16343', '6987142576', 'apos.pao98@gmail.com', '19823923', ''),
(2, 'Kwstas', 'GREECE', 'ATHENS', 'KAREA 100 B', '16343', '6986823504', 'paparas84@hotmail.com', '19823927', ''),
(3, 'Achilleas', 'GREECE', 'ATHENS', 'DIMOS8ENOYS 24', '16343', '6987142576', 'achilchald@gmail.com', '12878993', '');
--
-- end table insert : customers
--

/*!40000 ALTER TABLE `customers` ENABLE KEYS */;


--
-- start  table dump : domains
--

CREATE TABLE IF NOT EXISTS `domains` (
  `DOMAIN_ID` int NOT NULL AUTO_INCREMENT,
  `DOMAIN_NAME` varchar(30) DEFAULT NULL,
  `CUSTOMER_ID` int DEFAULT NULL,
  `INVOICE_ID` int DEFAULT NULL,
  `START_DATE` date DEFAULT NULL,
  `EXPIRY_DATE` date DEFAULT NULL,
  PRIMARY KEY (`DOMAIN_ID`),
  KEY `CUSTOMER_ID` (`CUSTOMER_ID`),
  CONSTRAINT `domains_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customers` (`CUSTOMER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- end  table dump : domains
--


--
-- Inserts of domains
--


/*!40000 ALTER TABLE `domains` DISABLE KEYS */;

--
-- start table insert : domains
--
INSERT INTO `domains`(`DOMAIN_ID`, `DOMAIN_NAME`, `CUSTOMER_ID`, `INVOICE_ID`, `START_DATE`, `EXPIRY_DATE`) VALUES 
(1, 'www.uniwa.gr', 1, 1, '2020-09-09', '2022-09-10'),
(2, 'www.asoe.com', 2, 2, '2020-09-09', '2022-09-10'),
(3, 'www.opa.gr', 3, 3, '2018-11-12', '2022-09-10');
--
-- end table insert : domains
--

/*!40000 ALTER TABLE `domains` ENABLE KEYS */;


--
-- start  table dump : invoice
--

CREATE TABLE IF NOT EXISTS `invoice` (
  `INVOICE_ID` int DEFAULT NULL,
  `CUSTOMER_ID` int DEFAULT NULL,
  `BILL_DATE` date DEFAULT NULL,
  `PAYMENT_DATE` date DEFAULT NULL,
  `INVOICE_TYPE` varchar(50) DEFAULT NULL,
  `RECCURENCE` varchar(20) DEFAULT NULL,
  `REPEAT_EVERY` int DEFAULT NULL,
  `CYCLES` int DEFAULT NULL,
  `CHANGE_FROM_PAYMENT` float DEFAULT NULL,
  `FULLYPAYED` tinyint(1) DEFAULT NULL,
  `NOTES` varchar(250) DEFAULT NULL,
  KEY `CUSTOMER_ID` (`CUSTOMER_ID`),
  CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customers` (`CUSTOMER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- end  table dump : invoice
--


--
-- Inserts of invoice
--


/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;

--
-- start table insert : invoice
--
INSERT INTO `invoice`(`INVOICE_ID`, `CUSTOMER_ID`, `BILL_DATE`, `PAYMENT_DATE`, `INVOICE_TYPE`, `RECCURENCE`, `REPEAT_EVERY`, `CYCLES`, `CHANGE_FROM_PAYMENT`, `FULLYPAYED`, `NOTES`) VALUES 
(1, 1, '2021-09-10', '2022-09-10', 'YEARLY', 'YEARLY', 1, 1, '0.0', 0, ''),
(2, 2, '2022-10-10', '2022-09-10', 'YEARLY', 'YEARLY', 2, 1, '0.0', 0, ''),
(3, 3, '2022-12-27', '2022-09-10', 'YEARLY', 'YEARLY', 2, 2, '0.0', 0, ''),
(4, 1, '2021-09-10', '2022-09-10', 'MONTHLY', 'ONCE', 0, 0, '0.0', 0, ''),
(5, 2, '2022-10-10', '2022-09-10', 'MONTHLY', 'ONCE', 0, 0, '0.0', 0, ''),
(6, 3, '2022-12-27', '2022-09-10', 'MONTHLY', 'ONCE', 0, 0, '0.0', 0, ''),
(7, 1, '2021-09-10', '2022-09-10', 'ONCE', 'ONCE', 0, 0, '0.0', 0, ''),
(8, 2, '2022-10-10', '2022-09-10', 'ONCE', 'ONCE', 0, 0, '0.0', 0, ''),
(9, 2, '2022-10-10', '2022-09-10', 'ONCE', 'ONCE', 0, 0, '0.0', 0, ''),
(10, 1, '2020-12-07', '2021-12-07', 'ONCE', 'YEARLY', 1, 0, '0.0', 0, 'laala'),
(11, 1, '2020-12-07', '2020-12-07', 'ONCE', 'YEARLY', 1, -1, '0.0', 0, 'ere');
--
-- end table insert : invoice
--

/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;


--
-- start  table dump : items
--

CREATE TABLE IF NOT EXISTS `items` (
  `ITEM_ID` int NOT NULL AUTO_INCREMENT,
  `ITEM_NAME` varchar(20) DEFAULT NULL,
  `RECCURING` varchar(20) DEFAULT NULL,
  `PRICE` float DEFAULT NULL,
  PRIMARY KEY (`ITEM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- end  table dump : items
--


--
-- Inserts of items
--


/*!40000 ALTER TABLE `items` DISABLE KEYS */;

--
-- start table insert : items
--
INSERT INTO `items`(`ITEM_ID`, `ITEM_NAME`, `RECCURING`, `PRICE`) VALUES 
(1, 'HOSTING SMALL', 'YEARLY', '60.0'),
(2, 'HOSTING MEDIUM', 'YEARLY', '80.0'),
(3, 'HOSTING BIG', 'YEARLY', '100.0'),
(4, 'SUPPORT', 'YEARLY', '30.0'),
(5, 'SOCIAL MEDIA', 'MONTHLY', '180.0'),
(6, 'DOMAIN.GR', 'YEARLY', '20.0'),
(7, 'DOMAIN.COM', 'YEARLY', '10.0'),
(8, 'FLYER', 'ONCE', '80.0');
--
-- end table insert : items
--

/*!40000 ALTER TABLE `items` ENABLE KEYS */;


--
-- start  table dump : log
--

CREATE TABLE IF NOT EXISTS `log` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `DATA` varchar(255) DEFAULT NULL,
  `WORKERID` int DEFAULT NULL,
  `TASKID` int DEFAULT NULL,
  `PROJECTID` int DEFAULT NULL,
  `reg_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- end  table dump : log
--



--
-- start  table dump : payments
--

CREATE TABLE IF NOT EXISTS `payments` (
  `PAYMENT_ID` int DEFAULT NULL,
  `INVOICE_ID` int DEFAULT NULL,
  `PAYMENT_DATE` date DEFAULT NULL,
  `PRICE` float DEFAULT NULL,
  `NOTES` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- end  table dump : payments
--



--
-- start  table dump : proj_worker_link
--

CREATE TABLE IF NOT EXISTS `proj_worker_link` (
  `PROJECT_ID` int DEFAULT NULL,
  `WORKER_ID` int DEFAULT NULL,
  KEY `PROJECT_ID` (`PROJECT_ID`),
  KEY `WORKER_ID` (`WORKER_ID`),
  CONSTRAINT `proj_worker_link_ibfk_1` FOREIGN KEY (`PROJECT_ID`) REFERENCES `projects` (`PROJECT_ID`),
  CONSTRAINT `proj_worker_link_ibfk_2` FOREIGN KEY (`WORKER_ID`) REFERENCES `workers` (`WORKER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- end  table dump : proj_worker_link
--


--
-- Inserts of proj_worker_link
--


/*!40000 ALTER TABLE `proj_worker_link` DISABLE KEYS */;

--
-- start table insert : proj_worker_link
--
INSERT INTO `proj_worker_link`(`PROJECT_ID`, `WORKER_ID`) VALUES 
(1, 1),
(1, 2),
(1, 3),
(2, 1);
--
-- end table insert : proj_worker_link
--

/*!40000 ALTER TABLE `proj_worker_link` ENABLE KEYS */;


--
-- start  table dump : projects
--

CREATE TABLE IF NOT EXISTS `projects` (
  `PROJECT_ID` int NOT NULL AUTO_INCREMENT,
  `PROJECT_NAME` varchar(30) DEFAULT NULL,
  `DUE_DATE` date DEFAULT NULL,
  `PRICE` float DEFAULT NULL,
  `WORKFORCE` int DEFAULT NULL,
  PRIMARY KEY (`PROJECT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- end  table dump : projects
--


--
-- Inserts of projects
--


/*!40000 ALTER TABLE `projects` DISABLE KEYS */;

--
-- start table insert : projects
--
INSERT INTO `projects`(`PROJECT_ID`, `PROJECT_NAME`, `DUE_DATE`, `PRICE`, `WORKFORCE`) VALUES 
(1, 'CASPER_ERP', '2022-09-18', '600.0', 3),
(2, 'CASPER_DB', '2021-09-18', '200.0', 1);
--
-- end table insert : projects
--

/*!40000 ALTER TABLE `projects` ENABLE KEYS */;


--
-- start  table dump : stickynote
--

CREATE TABLE IF NOT EXISTS `stickynote` (
  `NOTES` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- end  table dump : stickynote
--


--
-- Inserts of stickynote
--


/*!40000 ALTER TABLE `stickynote` DISABLE KEYS */;

--
-- start table insert : stickynote
--
INSERT INTO `stickynote`(`NOTES`) VALUES 
('kati');
--
-- end table insert : stickynote
--

/*!40000 ALTER TABLE `stickynote` ENABLE KEYS */;


--
-- start  table dump : tasks
--

CREATE TABLE IF NOT EXISTS `tasks` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `TASK_NAME` varchar(30) DEFAULT NULL,
  `TASK_DESCRIPTION` varchar(50) DEFAULT NULL,
  `DUE_DATE` date DEFAULT NULL,
  `STATUS` tinyint(1) DEFAULT NULL,
  `PROJECT_ID` int DEFAULT NULL,
  `WORKER_ID` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `PROJECT_ID` (`PROJECT_ID`),
  KEY `WORKER_ID` (`WORKER_ID`),
  CONSTRAINT `tasks_ibfk_1` FOREIGN KEY (`PROJECT_ID`) REFERENCES `projects` (`PROJECT_ID`),
  CONSTRAINT `tasks_ibfk_2` FOREIGN KEY (`WORKER_ID`) REFERENCES `workers` (`WORKER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- end  table dump : tasks
--


--
-- Inserts of tasks
--


/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;

--
-- start table insert : tasks
--
INSERT INTO `tasks`(`ID`, `TASK_NAME`, `TASK_DESCRIPTION`, `DUE_DATE`, `STATUS`, `PROJECT_ID`, `WORKER_ID`) VALUES 
(1, 'BACKEND', 'BACKEND DESIGN', '2021-03-27', 0, 1, 1),
(2, 'BACKEND', 'BACKEND DESIGN', '2021-03-27', 0, 1, 2),
(3, 'GUI', 'GUI DESIGN', '2021-03-27', 0, 1, 3),
(4, 'BACKEND', 'BACKEND DESIGN', '2021-03-27', 0, 2, 1);
--
-- end table insert : tasks
--

/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;


--
-- start  table dump : workers
--

CREATE TABLE IF NOT EXISTS `workers` (
  `WORKER_ID` int NOT NULL AUTO_INCREMENT,
  `WORKER_NAME` varchar(30) DEFAULT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`WORKER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--
-- end  table dump : workers
--


--
-- Inserts of workers
--


/*!40000 ALTER TABLE `workers` DISABLE KEYS */;

--
-- start table insert : workers
--
INSERT INTO `workers`(`WORKER_ID`, `WORKER_NAME`, `EMAIL`) VALUES 
(1, 'Apostolos', 'apos.pao98@gmail.com'),
(2, 'Kwstas', 'kwstas.pao98@gmail.com'),
(3, 'Achilleas', 'achilchald@gmail.com');
--
-- end table insert : workers
--

/*!40000 ALTER TABLE `workers` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
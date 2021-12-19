-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 01, 2020 at 11:20 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cukrarna`
--

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `ORDER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CITY` varchar(100) NOT NULL,
  `EMAIL` varchar(70) NOT NULL,
  `FIRSTANAME` varchar(80) NOT NULL,
  `HOUSE_NUMBER` int(11) NOT NULL,
  `LASTNAME` varchar(100) NOT NULL,
  `STREET` varchar(100) NOT NULL,
  `TEL_NUMBER` int(11) NOT NULL,
  `ZIP_CODE` int(11) NOT NULL,
  `STATE` int(11) NOT NULL,
  `PHONE_PREFIX` int(11) NOT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `ORDERED` timestamp NOT NULL DEFAULT current_timestamp(),
  `COMPLETED` TinyInt(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`ORDER_ID`),
  KEY `USER_ID` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `order_rows`
--

CREATE TABLE IF NOT EXISTS `order_rows` (
  `ORDER_ROW_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COUNT` int(11) NOT NULL,
  `PRODUCT_ID` int(11) NOT NULL,
  `ORDER_ID` int(11) NOT NULL,
  `PRICE_PER_UNIT` int(11) NOT NULL,
  PRIMARY KEY (`ORDER_ROW_ID`),
  KEY `PRODUCT_ID` (`PRODUCT_ID`),
  KEY `ORDER_ID` (`ORDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `product_categories`
--

CREATE TABLE IF NOT EXISTS `product_categories` (
  `CATEGORY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE IF NOT EXISTS `products` (
  `PRODUCT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRICE` int(11) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `DESCRIPTION` text DEFAULT NULL,
  `ADDED` timestamp NOT NULL DEFAULT current_timestamp(),
  `CATEGORY_ID` int(11) DEFAULT NULL,
  `TAG_ID` int(11) DEFAULT NULL,
  `PICTURE_URL` varchar(150) DEFAULT NULL,
  `ARCHIVED` TinyInt(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`PRODUCT_ID`),
  KEY `TAG_ID` (`TAG_ID`),
  KEY `CATEGORY_ID` (`CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE IF NOT EXISTS `reviews` (
  `REVIEW_ID` int(11) NOT NULL AUTO_INCREMENT,
  `REVIEW` text NOT NULL,
  `STARS` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `PRODUCT_ID` int(11) NOT NULL,
  PRIMARY KEY (`REVIEW_ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `STARS` (`STARS`),
  KEY `PRODUCT_ID` (`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

CREATE TABLE IF NOT EXISTS `tags` (
  `TAG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TAGNAME` varchar(50) NOT NULL,
  `COLOR` varchar(50) NOT NULL,
  PRIMARY KEY (`TAG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `ADMINISTRATOR` tinyint(1) NOT NULL DEFAULT 0,
  `REGISTRATION_TIMESTAMP` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `user_uk` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK-orders-users` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `order_rows`
--
ALTER TABLE `order_rows`
  ADD CONSTRAINT `FK-order_rows-orders` FOREIGN KEY (`ORDER_ID`) REFERENCES `orders` (`ORDER_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK-order_rows-products` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `products` (`PRODUCT_ID`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FK-products-product_categories` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `product_categories` (`CATEGORY_ID`)ON DELETE RESTRICT ON UPDATE CASCADE,
  ADD CONSTRAINT `FK-products-tags` FOREIGN KEY (`TAG_ID`) REFERENCES `tags` (`TAG_ID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `FK-reviews-products` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `products` (`PRODUCT_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK-reviews-users` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

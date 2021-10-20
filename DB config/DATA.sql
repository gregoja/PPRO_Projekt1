-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 24, 2020 at 07:28 PM
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

--
-- Dumping data for table `productcategories`
--

INSERT INTO `product_categories` (`CATEGORY_ID`, `NAME`) VALUES(1, 'Donut');
INSERT INTO `product_categories` (`CATEGORY_ID`, `NAME`) VALUES(2, 'Muffin');
INSERT INTO `product_categories` (`CATEGORY_ID`, `NAME`) VALUES(3, 'Dort');

--
-- Dumping data for table `tags`
--

INSERT INTO `tags` (`TAG_ID`, `TAGNAME`, `COLOR`) VALUES(1, 'Sleva', '#b83636');
INSERT INTO `tags` (`TAG_ID`, `TAGNAME`, `COLOR`) VALUES(2, 'Novinka', '#70bd4a');

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(1, 25, 'Homernut', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-07 08:43:00', 1, 1, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(2, 35, 'Borůvkonut', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-31 08:33:00', 1, NULL, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(3, 12, 'Lentilkonut', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-02-21 10:25:00', 1, 2, 3);
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(4, 16, 'Christmassnut', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-13 14:50:00', 1, NULL, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(5, 21, 'Vanilkonut', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-02-21 19:36:00', 1, 1, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(6, 17, 'Posyponut', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-02-02 11:16:00', 1, NULL, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(7, 60, 'Cokoladonut', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-20 20:29:00', 1, NULL, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(8, 8, 'Prostě donut', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-17 16:31:00', 1, 1, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(9, 19, 'TřešňoMuffin', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-02-08 19:00:00', 2, NULL, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(10, 24, 'PolevoMuffin', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-03-13 18:06:00', 2, NULL, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(11, 17, 'TalířoMuffin', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-20 21:31:00', 2, NULL, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(12, 36, 'NormálníMuffin', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-02-01 03:44:00', 2, 2, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(13, 90, 'ŽlutoMuffin', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-02-02 17:14:00', 2, NULL, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(14, 12, 'Prostě muffin', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-13 00:32:00', 2, 1, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(15, 253, 'Super dort', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-02-23 18:24:54', 3, NULL, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(16, 270, 'Iron dort', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-29 21:52:00', 3, 2, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(17, 325, 'Čtverec dort', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-03-17 18:24:36', 3, NULL, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(18, 125, 'Prase dort', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-02-16 10:01:00', 3, 2, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(19, 231, 'Medo dort', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-31 00:43:00', 3, NULL, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(20, 199, 'Toto dort', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-12 00:38:00', 3, 2, 'Homernut');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(21, 100, 'Prostě dort', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-02-13 17:06:00', 3, 1, 'Homernut');

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`USER_ID`, `USERNAME`, `PASSWORD`, `ADMINISTRATOR`, `REGISTRATION_TIMESTAMP`) VALUES(1, 'admin', '$2y$10$MFtH3LRKkRL9lWeF.io94eWuQmmNQwDlvcuyCcVHCbCFFjeTs.pK6', 1, '2020-02-24 17:47:45');


--
-- Dumping data for table `stars_to_text`
--
INSERT INTO `stars_to_text`(`STARS`, `TEXT`) VALUES (5,"Bez Chyby");
INSERT INTO `stars_to_text`(`STARS`, `TEXT`) VALUES (4,"Výborné");
INSERT INTO `stars_to_text`(`STARS`, `TEXT`) VALUES (3,"Průměrné");
INSERT INTO `stars_to_text`(`STARS`, `TEXT`) VALUES (2,"Nic moc");
INSERT INTO `stars_to_text`(`STARS`, `TEXT`) VALUES (1,"Nechutné");


--
-- Dumping data for table `states`
--
INSERT INTO `states`(`STATE_ID`, `NAME`) VALUES (1,"Česká Republika");
INSERT INTO `states`(`STATE_ID`, `NAME`) VALUES (2,"Slovenská Republika");

--
-- Dumping data for table `phone_prefixes`
--
INSERT INTO `phone_prefixes`(`PHONE_PREFIX`, `REGION`) VALUES (420,"ČR");
INSERT INTO `phone_prefixes`(`PHONE_PREFIX`, `REGION`) VALUES (421,"SR");

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

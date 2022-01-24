SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

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


CREATE TABLE IF NOT EXISTS `product_categories` (
    `CATEGORY_ID` int(11) NOT NULL AUTO_INCREMENT,
    `NAME` varchar(50) NOT NULL,
    PRIMARY KEY (`CATEGORY_ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

CREATE TABLE IF NOT EXISTS `tags` (
    `TAG_ID` int(11) NOT NULL AUTO_INCREMENT,
    `TAGNAME` varchar(50) NOT NULL,
    `COLOR` varchar(50) NOT NULL,
    PRIMARY KEY (`TAG_ID`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `users` (
    `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
    `USERNAME` varchar(50) NOT NULL,
    `PASSWORD` varchar(255) NOT NULL,
    `ADMINISTRATOR` tinyint(1) NOT NULL DEFAULT 0,
    `REGISTRATION_TIMESTAMP` timestamp NOT NULL DEFAULT current_timestamp(),
    PRIMARY KEY (`USER_ID`),
    UNIQUE KEY `user_uk` (`USERNAME`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `orders`
    ADD CONSTRAINT `FK-orders-users` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE `order_rows`
    ADD CONSTRAINT `FK-order_rows-orders` FOREIGN KEY (`ORDER_ID`) REFERENCES `orders` (`ORDER_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK-order_rows-products` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `products` (`PRODUCT_ID`) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE `products`
    ADD CONSTRAINT `FK-products-product_categories` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `product_categories` (`CATEGORY_ID`)ON DELETE RESTRICT ON UPDATE CASCADE,
  ADD CONSTRAINT `FK-products-tags` FOREIGN KEY (`TAG_ID`) REFERENCES `tags` (`TAG_ID`) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE `reviews`
    ADD CONSTRAINT `FK-reviews-products` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `products` (`PRODUCT_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK-reviews-users` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

CREATE VIEW taged_products_6
AS
SELECT products.PRODUCT_ID,products.NAME,products.PRICE,tags.TAGNAME, tags.COLOR,products.PICTURE_URL FROM products NATURAL JOIN tags
WHERE (tags.TAG_ID = 1 or tags.TAG_ID = 2) AND products.ARCHIVED = 0
ORDER BY ADDED DESC
    LIMIT 6;


CREATE VIEW product_page
AS
SELECT `PRODUCT_ID`,products.`NAME`,product_categories.`NAME` AS "Category",`PRICE`,`TAGNAME`,`COLOR`,`PICTURE_URL`,`ADDED` FROM `products` LEFT JOIN tags ON products.TAG_ID = tags.TAG_ID JOIN product_categories ON product_categories.CATEGORY_ID = products.CATEGORY_ID WHERE products.ARCHIVED = 0;

CREATE VIEW administration_producsts
AS
Select `PRODUCT_ID`,products.`NAME`,`PICTURE_URL`,`PRICE`,product_categories.NAME AS "CATEGORY_NAME",tags.TAGNAME,`ADDED` FROM products JOIN product_categories ON products.CATEGORY_ID = product_categories.CATEGORY_ID LEFT JOIN tags ON tags.TAG_ID = products.TAG_ID WHERE products.ARCHIVED = 0 ORDER BY ADDED DESC;

CREATE VIEW administration_orders
AS
SELECT `ORDER_ID`, `CITY`, `EMAIL`, `FIRSTANAME`, `HOUSE_NUMBER`, `LASTNAME`, `STREET`, `TEL_NUMBER`, `ZIP_CODE`, `STATE`, `PHONE_PREFIX`, `USER_ID`, `ORDERED` FROM `orders` WHERE orders.COMPLETED LIKE 0 ORDER BY 1;

DELIMITER $$
CREATE PROCEDURE `postReview`(IN `P_PRODUCT_ID` INT(11),IN `P_USER_ID` INT(11),IN `P_REVIEW` TEXT,IN `P_STARS` int(11))
BEGIN

DECLARE EXIT HANDLER FOR SQLEXCEPTION
BEGIN
ROLLBACK;
RESIGNAL;
END;
START TRANSACTION;
SET @rows = (SELECT hasReview(`P_PRODUCT_ID`,P_USER_ID));
    IF  @rows = 0 THEN
		INSERT INTO `reviews`(`REVIEW`, `STARS`, `USER_ID`, `PRODUCT_ID`) VALUES (P_REVIEW,P_STARS,P_USER_ID,P_PRODUCT_ID);
ELSE
UPDATE `reviews` SET `REVIEW`=P_REVIEW,`STARS`=P_STARS WHERE`USER_ID`=P_USER_ID AND`PRODUCT_ID`=P_PRODUCT_ID;
END IF;
COMMIT;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER TR_order_rows_BD
    BEFORE DELETE ON order_rows
    FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'DELETE cancelled';
END $$
DELIMITER ;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

INSERT INTO `product_categories` (`CATEGORY_ID`, `NAME`) VALUES(1, 'Donut');
INSERT INTO `product_categories` (`CATEGORY_ID`, `NAME`) VALUES(2, 'Muffin');
INSERT INTO `product_categories` (`CATEGORY_ID`, `NAME`) VALUES(3, 'Dort');

INSERT INTO `tags` (`TAG_ID`, `TAGNAME`, `COLOR`) VALUES(1, 'Sleva', '#b83636');
INSERT INTO `tags` (`TAG_ID`, `TAGNAME`, `COLOR`) VALUES(2, 'Novinka', '#70bd4a');

INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(1, 25, 'homernut', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-07 08:43:00', 1, 1, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(2, 35, 'Borůvkonut', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-31 08:33:00', 1, NULL, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(3, 12, 'Lentilkonut', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-02-21 10:25:00', 1, 2, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(4, 16, 'Christmassnut', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-13 14:50:00', 1, NULL, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(5, 21, 'Vanilkonut', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-02-21 19:36:00', 1, 1, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(6, 17, 'Posyponut', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-02-02 11:16:00', 1, NULL, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(7, 60, 'Cokoladonut', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-20 20:29:00', 1, NULL, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(8, 8, 'Prostě donut', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-17 16:31:00', 1, 1, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(9, 19, 'TřešňoMuffin', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-02-08 19:00:00', 2, NULL, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(10, 24, 'PolevoMuffin', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-03-13 18:06:00', 2, NULL, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(11, 17, 'TalířoMuffin', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-20 21:31:00', 2, NULL, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(12, 36, 'NormálníMuffin', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-02-01 03:44:00', 2, 2, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(13, 90, 'ŽlutoMuffin', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-02-02 17:14:00', 2, NULL, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(14, 12, 'Prostě muffin', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-13 00:32:00', 2, 1, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(15, 253, 'Super dort', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-02-23 18:24:54', 3, NULL, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(16, 270, 'Iron dort', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-29 21:52:00', 3, 2, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(17, 325, 'Čtverec dort', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-03-17 18:24:36', 3, NULL, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(18, 125, 'Prase dort', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-02-16 10:01:00', 3, 2, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(19, 231, 'Medo dort', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-31 00:43:00', 3, NULL, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(20, 199, 'Toto dort', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-01-12 00:38:00', 3, 2, 'homernut.png');
INSERT INTO `products` (`PRODUCT_ID`, `PRICE`, `NAME`, `DESCRIPTION`, `ADDED`, `CATEGORY_ID`, `TAG_ID`, `PICTURE_URL`) VALUES(21, 100, 'Prostě dort', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Praesent in mauris eu tortor porttitor accumsan. Suspendisse nisl. Morbi scelerisque luctus velit. Praesent id justo in neque elementum ultrices. Praesent dapibus. Praesent in mauris eu tortor porttitor accumsan. Praesent vitae arcu tempor neque lacinia pretium. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla pulvinar eleifend sem. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo.', '2020-02-13 17:06:00', 3, 1, 'homernut.png');

INSERT INTO `users` (`USER_ID`, `USERNAME`, `PASSWORD`, `ADMINISTRATOR`, `REGISTRATION_TIMESTAMP`) VALUES(1, 'admin', '$2y$10$MFtH3LRKkRL9lWeF.io94eWuQmmNQwDlvcuyCcVHCbCFFjeTs.pK6', 1, '2020-02-24 17:47:45');


COMMIT;
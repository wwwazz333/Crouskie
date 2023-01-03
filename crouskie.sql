-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: crouskie
-- Generation Time: Dec 07, 2022 at 07:00 PM
-- Server version: 10.6.11-MariaDB-1:10.6.11+maria~ubu2004
-- PHP Version: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `crouskie`
--

DELIMITER $$
--
-- Functions
--
CREATE DEFINER=`p2100284`@`%` FUNCTION `customer_connection` (`mail` VARCHAR(255) CHARSET utf8, `password_hash` VARCHAR(255) CHARSET utf8) RETURNS INT(1) COMMENT '1: connected, 0: wrong pwd or mail, -1:to many try' BEGIN
	DECLARE pwd varchar(255) DEFAULT null;
    DECLARE counter int(11) DEFAULT null;
    DECLARE last_date TIMESTAMP;
    select `password`, counter_connection, last_connection_try into pwd, counter, last_date from CUSTOMER WHERE mail_address = mail;

    if pwd is null or counter is null THEN -- pas de correspondance avec le mail trouvé
    	return 0;
    end if;

    -- correspondance avec le mail trouvé
    if counter < 3 or (SELECT customer_delay_passed(mail)) = 1 THEN
        IF pwd = password_hash THEN
            UPDATE CUSTOMER SET counter_connection = 0, last_connection_try = CURRENT_TIMESTAMP() WHERE mail_address = mail;
            return 1;
        ELSE
            UPDATE CUSTOMER SET counter_connection = counter_connection + 1, last_connection_try = CURRENT_TIMESTAMP() WHERE mail_address = mail;
            return 0;
    	END IF;
    end if;
    return -1;
END$$

CREATE DEFINER=`p2100284`@`%` FUNCTION `customer_delay_passed` (`mail` VARCHAR(255) CHARSET utf8) RETURNS INT(1) UNSIGNED  BEGIN
    DECLARE last_date TIMESTAMP;
    select last_connection_try into last_date from CUSTOMER WHERE mail_address = mail;

    if CURRENT_TIMESTAMP() >= DATE_ADD(last_date,INTERVAL 1 HOUR) THEN -- reinitialise counter_connection si tps attente passé
        UPDATE CUSTOMER SET counter_connection = 0, last_connection_try = CURRENT_DATE() WHERE mail_address = mail;
        return 1;
    end if;
    return 0;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `idcustomer` int(11) NOT NULL,
  `idprod` int(11) NOT NULL,
  `quantitycart` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`idcustomer`, `idprod`, `quantitycart`) VALUES
(1, 1, 2),
(1, 2, 5),
(2, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `cloth_size`
--

CREATE TABLE `cloth_size` (
  `idsize` int(11) NOT NULL,
  `namesize` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `cloth_size`
--

INSERT INTO `cloth_size` (`idsize`, `namesize`) VALUES
(1, 'XS'),
(2, 'S'),
(3, 'M'),
(4, 'L'),
(5, 'XL'),
(6, 'Grand'),
(7, 'superGrand'),
(8, 'taille');

-- --------------------------------------------------------

--
-- Table structure for table `cmd`
--

CREATE TABLE `cmd` (
  `dateorder` timestamp NULL DEFAULT NULL,
  `numorder` int(11) NOT NULL,
  `idcustomer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `cmd`
--

INSERT INTO `cmd` (`dateorder`, `numorder`, `idcustomer`) VALUES
('2022-10-23 22:00:00', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `collection`
--

CREATE TABLE `collection` (
  `namecollection` varchar(255) NOT NULL,
  `idcollection` int(11) NOT NULL,
  `pathpicture` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `collection`
--

INSERT INTO `collection` (`namecollection`, `idcollection`, `pathpicture`) VALUES
('crouskievert', 1, './assets/images/uploads/qX68fyZxVPOCxn7P.png');

-- --------------------------------------------------------

--
-- Table structure for table `color`
--

CREATE TABLE `color` (
  `namecolor` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;;

--
-- Dumping data for table `color`
--

INSERT INTO `color` (`namecolor`) VALUES
('Bleu'),
('jaune'),
('orange'),
('rouge'),
('vert');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `idcustomer` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mail_address` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `counter_connection` int(11) NOT NULL DEFAULT 0,
  `last_connection_try` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`idcustomer`, `first_name`, `last_name`, `mail_address`, `password`, `counter_connection`, `last_connection_try`) VALUES
(1, 'Thomas', 'Warrier', 'totot@gamil.com', 'default_password', 2, '2022-11-11 17:07:37'),
(2, 'loic', 'pupier', 'lolo@gmail.com', 'default_password', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `existingcolor`
--

CREATE TABLE `existingcolor` (
  `idprod` int(11) NOT NULL,
  `namecolor` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `existingcolor`
--

INSERT INTO `existingcolor` (`idprod`, `namecolor`) VALUES
(1, 'Bleu'),
(1, 'rouge'),
(2, 'orange'),
(2, 'vert'),
(4, 'jaune'),
(11, 'Bleu'),
(13, 'orange');

-- --------------------------------------------------------

--
-- Table structure for table `existingsize`
--

CREATE TABLE `existingsize` (
  `idprod` int(11) NOT NULL,
  `idsize` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `existingsize`
--

INSERT INTO `existingsize` (`idprod`, `idsize`) VALUES
(1, 1),
(1, 6),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(4, 1),
(13, 6);

-- --------------------------------------------------------

--
-- Table structure for table `favorite`
--

CREATE TABLE `favorite` (
  `idcustomer` int(11) NOT NULL,
  `idprod` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `favorite`
--

INSERT INTO `favorite` (`idcustomer`, `idprod`) VALUES
(1, 1),
(2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `picture`
--

CREATE TABLE `picture` (
  `pathpicture` varchar(255) NOT NULL,
  `idprod` int(11) DEFAULT NULL,
  `altpicture` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci; 

--
-- Dumping data for table `picture`
--

INSERT INTO `picture` (`pathpicture`, `idprod`, `altpicture`) VALUES
('./assets/images/uploads/qX68fyZxVPOCxn7P.png', 14, 'xwcv');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `idprod` int(11) NOT NULL,
  `idcollection` int(11) DEFAULT NULL,
  `nameprod` varchar(255) NOT NULL,
  `descriptionprod` varchar(255) DEFAULT NULL,
  `priceprod` float(10,2) NOT NULL,
  `envente` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`idprod`, `idcollection`, `nameprod`, `descriptionprod`, `priceprod`, `envente`) VALUES
(1, 1, 'ertyu', 'dfg', 10.00, 1),
(2, NULL, '[value-TEst]', '[value-Des]', 21.00, 1),
(3, NULL, 'nameprodTestId', 'super descripti', 100.00, 1),
(4, NULL, 'name product isnerted', 'sldfkj', 424.00, 1),
(7, 1, 'nom', 'dessss', 11.00, 1),
(8, 1, 'nom', 'dessss', 11.00, 1),
(11, 1, '???', '????smdk\n', 10.01, 1),
(12, 1, 'ertyu', 'dfg', 10.00, 1),
(13, NULL, 'Observer', 'il t\'observe', 99.00, 1),
(14, NULL, 'Add', 'sf', 10.00, 1),
(15, NULL, 'test??', 'c\'est meiux ?', 10.00, 1);

-- --------------------------------------------------------

--
-- Table structure for table `productbought`
--

CREATE TABLE `productbought` (
  `idpp` int(11) NOT NULL,
  `namecolor` varchar(255) NOT NULL,
  `idprod` int(11) NOT NULL,
  `idsize` int(11) NOT NULL,
  `numorder` int(11) NOT NULL,
  `quantitybought` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `productbought`
--

INSERT INTO `productbought` (`idpp`, `namecolor`, `idprod`, `idsize`, `numorder`, `quantitybought`) VALUES
(2, 'rouge', 1, 2, 1, 2),
(3, 'vert', 2, 3, 1, 1),
(4, 'rouge', 1, 2, 1, 55),
(5, 'rouge', 1, 2, 1, 55),
(6, 'rouge', 1, 2, 1, 55),
(7, 'rouge', 1, 2, 1, 55),
(8, 'rouge', 1, 2, 1, 55),
(9, 'rouge', 1, 2, 1, 55),
(10, 'rouge', 1, 2, 1, 55),
(11, 'rouge', 1, 2, 1, 55);

-- --------------------------------------------------------

--
-- Table structure for table `stocked`
--

CREATE TABLE `stocked` (
  `idprod` int(11) NOT NULL,
  `namecolor` varchar(255) NOT NULL,
  `idsize` int(11) NOT NULL,
  `quantitystocked` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `stocked`
--

INSERT INTO `stocked` (`idprod`, `namecolor`, `idsize`, `quantitystocked`) VALUES
(1, 'rouge', 2, 14),
(1, 'rouge', 3, 100),
(1, 'rouge', 4, 100),
(2, 'jaune', 2, 50),
(2, 'orange', 2, 10),
(2, 'vert', 3, 10);

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE `tag` (
  `idtag` int(11) NOT NULL,
  `nametag` varchar(255) NOT NULL
) ;

--
-- Dumping data for table `tag`
--

INSERT INTO `tag` (`idtag`, `nametag`) VALUES
(1, 'pull'),
(2, 't-shirt'),
(3, 'autreXd'),
(4, 'observer');

-- --------------------------------------------------------

--
-- Table structure for table `tags_product`
--

CREATE TABLE `tags_product` (
  `idprod` int(11) NOT NULL,
  `idtag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

--
-- Dumping data for table `tags_product`
--

INSERT INTO `tags_product` (`idprod`, `idtag`) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(3, 1),
(8, 1),
(11, 1),
(13, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`idcustomer`,`idprod`),
  ADD KEY `fk_CART2` (`idprod`);

--
-- Indexes for table `cloth_size`
--
ALTER TABLE `cloth_size`
  ADD PRIMARY KEY (`idsize`);

--
-- Indexes for table `cmd`
--
ALTER TABLE `cmd`
  ADD PRIMARY KEY (`numorder`),
  ADD KEY `fk_ORDERED` (`idcustomer`);

--
-- Indexes for table `collection`
--
ALTER TABLE `collection`
  ADD PRIMARY KEY (`idcollection`),
  ADD KEY `fk_REPRESENTATIONOF` (`pathpicture`);

--
-- Indexes for table `color`
--
ALTER TABLE `color`
  ADD PRIMARY KEY (`namecolor`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`idcustomer`),
  ADD UNIQUE KEY `mail_address` (`mail_address`);

--
-- Indexes for table `existingcolor`
--
ALTER TABLE `existingcolor`
  ADD PRIMARY KEY (`idprod`,`namecolor`),
  ADD KEY `fk_EXISTINGCOLOR2` (`namecolor`);

--
-- Indexes for table `existingsize`
--
ALTER TABLE `existingsize`
  ADD PRIMARY KEY (`idprod`,`idsize`),
  ADD KEY `fk_EXISTINGSIZE2` (`idsize`);

--
-- Indexes for table `favorite`
--
ALTER TABLE `favorite`
  ADD PRIMARY KEY (`idcustomer`,`idprod`),
  ADD KEY `fk_FAVORITE2` (`idprod`);

--
-- Indexes for table `picture`
--
ALTER TABLE `picture`
  ADD PRIMARY KEY (`pathpicture`),
  ADD KEY `fk_LOOKS_LIKE` (`idprod`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`idprod`),
  ADD KEY `fk_PARTOF` (`idcollection`);

--
-- Indexes for table `productbought`
--
ALTER TABLE `productbought`
  ADD PRIMARY KEY (`idpp`),
  ADD KEY `fk_BOUGHT` (`numorder`),
  ADD KEY `fk_COLORCHOOSEN` (`namecolor`),
  ADD KEY `fk_SIZECHOOSEN` (`idsize`),
  ADD KEY `fk_SOLDPROD` (`idprod`);

--
-- Indexes for table `stocked`
--
ALTER TABLE `stocked`
  ADD PRIMARY KEY (`idprod`,`namecolor`,`idsize`),
  ADD KEY `fk_STocked` (`idsize`),
  ADD KEY `fk2_STocked` (`namecolor`);

--
-- Indexes for table `tag`
--
ALTER TABLE `tag`
  ADD PRIMARY KEY (`idtag`);

--
-- Indexes for table `tags_product`
--
ALTER TABLE `tags_product`
  ADD PRIMARY KEY (`idprod`,`idtag`),
  ADD KEY `fk2_TAGS_PRODUCT` (`idtag`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cloth_size`
--
ALTER TABLE `cloth_size`
  MODIFY `idsize` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cmd`
--
ALTER TABLE `cmd`
  MODIFY `numorder` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `collection`
--
ALTER TABLE `collection`
  MODIFY `idcollection` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `idcustomer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `idprod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `productbought`
--
ALTER TABLE `productbought`
  MODIFY `idpp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `tag`
--
ALTER TABLE `tag`
  MODIFY `idtag` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `fk_cart` FOREIGN KEY (`idcustomer`) REFERENCES `customer` (`idcustomer`),
  ADD CONSTRAINT `fk_cart2` FOREIGN KEY (`idprod`) REFERENCES `product` (`idprod`),
  ADD CONSTRAINT `fk_cart3` FOREIGN KEY (`idsize`) REFERENCES `cloth_size` (`idsize`),
  ADD CONSTRAINT `fk_cart4` FOREIGN KEY (`namecolor`) REFERENCES `color` (`namecolor`);

--
-- Constraints for table `collection`
--
ALTER TABLE `collection`
  ADD CONSTRAINT `fk_collection` FOREIGN KEY (`pathpicture`) REFERENCES `picture` (`pathpicture`);

--
-- Constraints for table `existingcolor`
--
ALTER TABLE `existingcolor`
  ADD CONSTRAINT `fk_existingcolor` FOREIGN KEY (`namecolor`) REFERENCES `color` (`namecolor`),
  ADD CONSTRAINT `fk_existingcolor2` FOREIGN KEY (`idprod`) REFERENCES `product` (`idprod`);

--
-- Constraints for table `existingsize`
--
ALTER TABLE `existingsize`
  ADD CONSTRAINT `fk_existing_size` FOREIGN KEY (`idsize`) REFERENCES `cloth_size` (`idsize`),
  ADD CONSTRAINT `fk_existing_size2` FOREIGN KEY (`idprod`) REFERENCES `product` (`idprod`);

--
-- Constraints for table `favorite`
--
ALTER TABLE `favorite`
  ADD CONSTRAINT `fk_favorite` FOREIGN KEY (`idprod`) REFERENCES `product` (`idprod`),
  ADD CONSTRAINT `fk_favorite2` FOREIGN KEY (`idcustomer`) REFERENCES `customer` (`idcustomer`);

--
-- Constraints for table `picture`
--
ALTER TABLE `picture`
  ADD CONSTRAINT `fk_picture` FOREIGN KEY (`idprod`) REFERENCES `product` (`idprod`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_product` FOREIGN KEY (`idcollection`) REFERENCES `collection` (`idcollection`);

--
-- Constraints for table `productbought`
--
ALTER TABLE `productbought`
  ADD CONSTRAINT `fk_productbought` FOREIGN KEY (`namecolor`) REFERENCES `color` (`namecolor`),
  ADD CONSTRAINT `fk_productbought2` FOREIGN KEY (`idprod`) REFERENCES `product` (`idprod`),
  ADD CONSTRAINT `fk_productbought3` FOREIGN KEY (`idsize`) REFERENCES `cloth_size` (`idsize`),
  ADD CONSTRAINT `fk_productbought4` FOREIGN KEY (`numorder`) REFERENCES `cmd` (`numorder`);

--
-- Constraints for table `stocked`
--
ALTER TABLE `stocked`
  ADD CONSTRAINT `fk_stocked` FOREIGN KEY (`namecolor`) REFERENCES `color` (`namecolor`),
  ADD CONSTRAINT `fk_stocked2` FOREIGN KEY (`idprod`) REFERENCES `product` (`idprod`),
  ADD CONSTRAINT `fk_stocked3` FOREIGN KEY (`idsize`) REFERENCES `cloth_size` (`idsize`);

--
-- Constraints for table `tags_product`
--
ALTER TABLE `tags_product`
  ADD CONSTRAINT `fk_tags_product` FOREIGN KEY (`idtag`) REFERENCES `tag` (`idtag`),
  ADD CONSTRAINT `fk_tags_product2` FOREIGN KEY (`idprod`) REFERENCES `product` (`idprod`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

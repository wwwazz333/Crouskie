-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 01, 2022 at 09:11 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `IDcustomer` int(11) NOT NULL,
  `IDPROD` int(11) NOT NULL,
  `QUANTITYcart` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`IDcustomer`, `IDPROD`, `QUANTITYcart`) VALUES
(1, 1, 2),
(1, 2, 5),
(2, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `cloth_size`
--

CREATE TABLE `cloth_size` (
  `IDSIZE` int(11) NOT NULL,
  `NAMESIZE` varchar(255) NOT NULL
) ;

--
-- Dumping data for table `cloth_size`
--

INSERT INTO `cloth_size` (`IDSIZE`, `NAMESIZE`) VALUES
(1, 'XS'),
(2, 'S'),
(3, 'M'),
(4, 'L'),
(5, 'XL'),
(6, 'Grand'),
(7, 'superGrand');

-- --------------------------------------------------------

--
-- Table structure for table `cmd`
--

CREATE TABLE `cmd` (
  `DATEORDER` timestamp NULL DEFAULT NULL,
  `NUMORDER` int(11) NOT NULL,
  `IDcustomer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `cmd`
--

INSERT INTO `cmd` (`DATEORDER`, `NUMORDER`, `IDcustomer`) VALUES
('2022-10-23 22:00:00', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `collection`
--

CREATE TABLE `collection` (
  `NAMEcollection` varchar(255) NOT NULL,
  `IDcollection` int(11) NOT NULL,
  `PATHpicture` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `collection`
--

INSERT INTO `collection` (`NAMEcollection`, `IDcollection`, `PATHpicture`) VALUES
('crooskieverre', 1, 'cheminVersLimageDeTest');

-- --------------------------------------------------------

--
-- Table structure for table `color`
--

CREATE TABLE `color` (
  `NAMEcolor` varchar(255) NOT NULL
) ;

--
-- Dumping data for table `color`
--

INSERT INTO `color` (`NAMEcolor`) VALUES
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
  `IDcustomer` int(11) NOT NULL,
  `FIRST_NAME` varchar(255) NOT NULL,
  `LAST_NAME` varchar(255) DEFAULT NULL,
  `MAIL_ADDRESS` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `counter_connection` int(11) NOT NULL DEFAULT 0,
  `last_connection_try` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`IDcustomer`, `FIRST_NAME`, `LAST_NAME`, `MAIL_ADDRESS`, `password`, `counter_connection`, `last_connection_try`) VALUES
(1, 'Thomas', 'Warrier', 'totot@gamil.com', 'default_password', 2, '2022-11-11 17:07:37'),
(2, 'loic', 'pupier', 'lolo@gmail.com', 'default_password', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `existingcolor`
--

CREATE TABLE `existingcolor` (
  `IDPROD` int(11) NOT NULL,
  `NAMEcolor` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `existingcolor`
--

INSERT INTO `existingcolor` (`IDPROD`, `NAMEcolor`) VALUES
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
  `IDPROD` int(11) NOT NULL,
  `IDSIZE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `existingsize`
--

INSERT INTO `existingsize` (`IDPROD`, `IDSIZE`) VALUES
(1, 1),
(1, 6),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(4, 1),
(12, 1),
(12, 4),
(13, 6);

-- --------------------------------------------------------

--
-- Table structure for table `favorite`
--

CREATE TABLE `favorite` (
  `IDcustomer` int(11) NOT NULL,
  `IDPROD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `favorite`
--

INSERT INTO `favorite` (`IDcustomer`, `IDPROD`) VALUES
(1, 1),
(2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `picture`
--

CREATE TABLE `picture` (
  `PATHpicture` varchar(255) NOT NULL,
  `IDPROD` int(11) DEFAULT NULL,
  `ALTpicture` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `picture`
--

INSERT INTO `picture` (`PATHpicture`, `IDPROD`, `ALTpicture`) VALUES
('cheminVersLimageDeTest', 1, 'pull très stylé'),
('cheminVersLimageDeTest2ndVersion', 2, 'pull encore plus stylé');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `IDPROD` int(11) NOT NULL,
  `IDcollection` int(11) DEFAULT NULL,
  `NAMEPROD` varchar(255) NOT NULL,
  `DESCRIPTIONPROD` varchar(255) DEFAULT NULL,
  `PRICEPROD` float(10,2) NOT NULL,
  `enVente` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`IDPROD`, `IDcollection`, `NAMEPROD`, `DESCRIPTIONPROD`, `PRICEPROD`, `enVente`) VALUES
(1, 1, 'ertyu', 'dfg', 10.00, 12),
(2, NULL, '[value-TEst]', '[value-Des]', 21.00, 1),
(3, NULL, 'nameprodTestId', 'super descripti', 100.00, 1),
(4, 1, 'name Product isnerted', 'sldfkj', 424.00, 1),
(7, 1, 'nom', 'dessss', 11.00, 1),
(8, 1, 'nom', 'dessss', 11.00, 1),
(11, 1, '???', '????smdk\n', 10.01, 1),
(12, 1, 'ertyu', 'dfg', 10.00, 1),
(13, NULL, 'Observer', 'il t\'observe', 99.00, 1),
(14, NULL, 'Add', 'sf', 10.00, 0),
(15, NULL, 'test??', 'c\'est meiux ?', 10.00, 1);

-- --------------------------------------------------------

--
-- Table structure for table `productbought`
--

CREATE TABLE `productbought` (
  `IDPP` int(11) NOT NULL,
  `NAMEcolor` varchar(255) NOT NULL,
  `IDPROD` int(11) NOT NULL,
  `IDSIZE` int(11) NOT NULL,
  `NUMORDER` int(11) NOT NULL,
  `QUANTITYBOUGHT` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `productbought`
--

INSERT INTO `productbought` (`IDPP`, `NAMEcolor`, `IDPROD`, `IDSIZE`, `NUMORDER`, `QUANTITYBOUGHT`) VALUES
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
  `IDPROD` int(11) NOT NULL,
  `NAMEcolor` varchar(255) NOT NULL,
  `IDSIZE` int(11) NOT NULL,
  `QUANTITYstocked` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `stocked`
--

INSERT INTO `stocked` (`IDPROD`, `NAMEcolor`, `IDSIZE`, `QUANTITYstocked`) VALUES
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
  `IDPROD` int(11) NOT NULL,
  `IDtag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `tags_product`
--

INSERT INTO `tags_product` (`IDPROD`, `IDtag`) VALUES
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
  ADD PRIMARY KEY (`IDcustomer`,`IDPROD`),
  ADD KEY `FK_cart2` (`IDPROD`);

--
-- Indexes for table `cloth_size`
--
ALTER TABLE `cloth_size`
  ADD PRIMARY KEY (`IDSIZE`);

--
-- Indexes for table `cmd`
--
ALTER TABLE `cmd`
  ADD PRIMARY KEY (`NUMORDER`),
  ADD KEY `FK_ORDERED` (`IDcustomer`);

--
-- Indexes for table `collection`
--
ALTER TABLE `collection`
  ADD PRIMARY KEY (`IDcollection`),
  ADD KEY `FK_REPRESENTATIONOF` (`PATHpicture`);

--
-- Indexes for table `color`
--
ALTER TABLE `color`
  ADD PRIMARY KEY (`NAMEcolor`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`IDcustomer`),
  ADD UNIQUE KEY `MAIL_ADDRESS` (`MAIL_ADDRESS`);

--
-- Indexes for table `existingcolor`
--
ALTER TABLE `existingcolor`
  ADD PRIMARY KEY (`IDPROD`,`NAMEcolor`),
  ADD KEY `FK_existingcolor2` (`NAMEcolor`);

--
-- Indexes for table `existingsize`
--
ALTER TABLE `existingsize`
  ADD PRIMARY KEY (`IDPROD`,`IDSIZE`),
  ADD KEY `FK_existingsize2` (`IDSIZE`);

--
-- Indexes for table `favorite`
--
ALTER TABLE `favorite`
  ADD PRIMARY KEY (`IDcustomer`,`IDPROD`),
  ADD KEY `FK_favorite2` (`IDPROD`);

--
-- Indexes for table `picture`
--
ALTER TABLE `picture`
  ADD PRIMARY KEY (`PATHpicture`),
  ADD KEY `FK_LOOKS_LIKE` (`IDPROD`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`IDPROD`),
  ADD KEY `FK_PARTOF` (`IDcollection`);

--
-- Indexes for table `productbought`
--
ALTER TABLE `productbought`
  ADD PRIMARY KEY (`IDPP`),
  ADD KEY `FK_BOUGHT` (`NUMORDER`),
  ADD KEY `FK_colorCHOOSEN` (`NAMEcolor`),
  ADD KEY `FK_SIZECHOOSEN` (`IDSIZE`),
  ADD KEY `FK_SOLDPROD` (`IDPROD`);

--
-- Indexes for table `stocked`
--
ALTER TABLE `stocked`
  ADD PRIMARY KEY (`IDPROD`,`NAMEcolor`,`IDSIZE`),
  ADD KEY `Fk_STocked` (`IDSIZE`),
  ADD KEY `Fk2_STocked` (`NAMEcolor`);

--
-- Indexes for table `tag`
--
ALTER TABLE `tag`
  ADD PRIMARY KEY (`idtag`);

--
-- Indexes for table `tags_product`
--
ALTER TABLE `tags_product`
  ADD PRIMARY KEY (`IDPROD`,`IDtag`),
  ADD KEY `Fk2_tags_product` (`IDtag`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cloth_size`
--
ALTER TABLE `cloth_size`
  MODIFY `IDSIZE` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cmd`
--
ALTER TABLE `cmd`
  MODIFY `NUMORDER` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `collection`
--
ALTER TABLE `collection`
  MODIFY `IDcollection` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `IDcustomer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `IDPROD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `productbought`
--
ALTER TABLE `productbought`
  MODIFY `IDPP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `tag`
--
ALTER TABLE `tag`
  MODIFY `idtag` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `existingsize`
--
ALTER TABLE `existingsize`
  ADD CONSTRAINT `Fk2_existing_size` FOREIGN KEY (`IDSIZE`) REFERENCES `cloth_size` (`IDSIZE`),
  ADD CONSTRAINT `Fk_existing_size` FOREIGN KEY (`IDPROD`) REFERENCES `product` (`IDPROD`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 30, 2022 at 08:12 AM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

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
-- Table structure for table `PICTURE`
--

CREATE TABLE `PICTURE` (
  `PATHPICTURE` varchar(255) NOT NULL,
  `IDPROD` int(11) DEFAULT NULL,
  `ALTPICTURE` varchar(255) DEFAULT NULL
) ;

--
-- Dumping data for table `PICTURE`
--

INSERT INTO `PICTURE` (`PATHPICTURE`, `IDPROD`, `ALTPICTURE`) VALUES
('cheminVersLimageDeTest', 1, 'pull très stylé'),
('cheminVersLimageDeTest2ndVersion', 2, 'pull encore plus stylé');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `PICTURE`
--
ALTER TABLE `PICTURE`
  ADD PRIMARY KEY (`PATHPICTURE`),
  ADD KEY `FK_LOOKS_LIKE` (`IDPROD`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `PICTURE`
--
ALTER TABLE `PICTURE`
  ADD CONSTRAINT `Fk_picture` FOREIGN KEY (`IDPROD`) REFERENCES `PRODUCT` (`IDPROD`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

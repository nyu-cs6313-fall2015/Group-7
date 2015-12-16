-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 14, 2015 at 12:48 AM
-- Server version: 5.7.9-log
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `keyword`
--

CREATE TABLE IF NOT EXISTS `keyword` (
  `Areaid` int(100) NOT NULL,
  `Relevance` varchar(100) NOT NULL,
  `Time` timestamp NULL DEFAULT NULL,
  `Entity` varchar(100) NOT NULL,
  `new` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `keyword`
--

INSERT INTO `keyword` (`Areaid`, `Relevance`, `Time`, `Entity`, `new`) VALUES
(2, '0.842823', '2015-12-13 23:37:44', 'Pelham Bay Park.', 'y'),
(2, '0.831392', '2015-12-13 23:38:40', 'AAMCAR Car Rental', 'y'),
(16, '0.586129', '2015-12-13 23:39:10', 'NY', 'y'),
(16, '0.615479', '2015-12-13 23:39:10', 'Queens', 'y'),
(17, '0.33', '2015-12-13 23:39:10', 'Brooklyn', 'y'),
(17, '0.33', '2015-12-13 23:39:10', 'NY', 'y'),
(17, '0.33', '2015-12-13 23:39:10', 'Porto Bello Pizza', 'y'),
(17, '0.33', '2015-12-13 23:39:10', 'Astoria', 'y'),
(18, '0.927699', '2015-12-13 23:39:11', 'Spring Creek Towers https', 'y'),
(18, '0.442967', '2015-12-13 23:39:11', 'Brooklyn', 'y'),
(25, '0.33', '2015-12-13 23:39:11', 'Williamsburg', 'y'),
(25, '0.33', '2015-12-13 23:39:11', 'Brooklyn', 'y'),
(26, '0.946266', '2015-12-13 23:39:11', 'Fort Lee', 'n'),
(26, '0.673992', '2015-12-13 23:39:11', 'NJ', 'n'),
(26, '0.530033', '2015-12-13 23:39:11', 'Grand Central Parkway', 'n'),
(26, '0.389252', '2015-12-13 23:39:11', 'Lola', 'n'),
(31, '0.772981', '2015-12-13 23:39:11', 'Skaneateles', 'y'),
(31, '0.676968', '2015-12-13 23:39:11', 'Street Station', 'y'),
(33, '0.789974', '2015-12-13 23:39:12', 'Mario s Restaurant', 'y'),
(33, '0.6203', '2015-12-13 23:39:12', 'NY', 'y'),
(33, '0.895239', '2015-12-13 23:39:12', 'George Washington Bridge', 'y'),
(39, '0.846718', '2015-12-13 23:39:12', 'Anthony Gallo Nursery', 'y'),
(40, '0.821455', '2015-12-13 23:39:12', 'Specialist Millwork', 'y'),
(2, '0.77417', '2015-12-13 23:47:54', 'Rodolfo', 'y'),
(2, '0.637639', '2015-12-13 23:47:54', 'Mimi', 'y'),
(16, '0.802391', '2015-12-13 23:47:55', 'John F. Kennedy International Airport', 'y'),
(16, '0.369649', '2015-12-13 23:47:55', 'Official', 'y'),
(23, '0.943773', '2015-12-13 23:47:55', 'Brooklyn', 'y'),
(23, '0.24761', '2015-12-13 23:47:55', 'mcclarrenpark', 'y'),
(25, '0.33', '2015-12-13 23:47:55', 'Stone Park Caf&#xE9;', 'y'),
(25, '0.33', '2015-12-13 23:47:55', 'NY', 'y'),
(31, '0.803996', '2015-12-13 23:47:55', 'New York', 'y'),
(31, '0.66113', '2015-12-13 23:47:56', 'NY', 'y'),
(33, '0.826034', '2015-12-13 23:47:56', 'Lower Manhattan', 'y'),
(39, '0.993899', '2015-12-13 23:47:56', 'Republic Airways Holdings', 'y'),
(39, '0.830758', '2015-12-13 23:47:56', 'NY', 'y'),
(39, '0.567493', '2015-12-13 23:47:56', 'Jamaica', 'y'),
(40, '0.690608', '2015-12-13 23:47:56', 'dell', 'y'),
(40, '0.64193', '2015-12-13 23:47:56', 'Albany', 'y'),
(47, '0.910448', '2015-12-13 23:47:56', 'NewYork', 'y'),
(47, '0.774943', '2015-12-13 23:47:56', 'Sales makeup Job Jobs Hiring', 'y'),
(47, '0.64613', '2015-12-13 23:47:56', 'New York City', 'y'),
(47, '0.437642', '2015-12-13 23:47:56', 'William', 'y');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

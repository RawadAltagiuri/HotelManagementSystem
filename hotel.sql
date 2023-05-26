-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 21, 2023 at 10:13 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 5.6.39

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel`
--

-- --------------------------------------------------------

--
-- Table structure for table `area`
--

CREATE TABLE `area` (
  `AreaNumber` int(11) NOT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Capacity` int(11) DEFAULT NULL,
  `Availability` varchar(150) DEFAULT NULL,
  `Reservation` varchar(20) DEFAULT NULL,
  `CheckInDate` date DEFAULT NULL,
  `CheckOutDate` date DEFAULT NULL,
  `Price` double NOT NULL,
  `extra` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `area`
--

INSERT INTO `area` (`AreaNumber`, `Type`, `Capacity`, `Availability`, `Reservation`, `CheckInDate`, `CheckOutDate`, `Price`, `extra`) VALUES
(201, 'Conference Room', 300, 'Available', NULL, NULL, NULL, 350, 'This is a spacious conference room'),
(202, 'Fitness Center', 30, 'Available', NULL, NULL, NULL, 40, 'Fully equipped gym with modern amenities'),
(203, 'Spa', 10, 'Available', NULL, NULL, NULL, 30, 'A relaxing spa experience'),
(204, 'Swimming Pool', 20, 'Occupied', NULL, NULL, NULL, 30, 'A large swimming pool with a beautiful view'),
(205, 'Bar', 20, 'Occupied', NULL, NULL, NULL, 60, 'A cozy bar for socializing and drinks'),
(206, 'Big Hall', 400, 'Available', NULL, NULL, NULL, 3500, 'A big hall for events'),
(207, 'Big Hall', 600, 'Available', NULL, NULL, NULL, 5000, NULL),
(208, 'Small Hall', 200, 'Available', NULL, NULL, NULL, 2500, NULL),
(209, 'Conference Room', 40, 'Available', NULL, NULL, NULL, 500, NULL),
(210, 'Fitness Center', 100, 'Occupied', NULL, NULL, NULL, 80, NULL),
(211, 'Swimming Pool', 90, 'Available', NULL, NULL, NULL, 80, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `CustomerID` int(11) NOT NULL,
  `total` double NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Age` int(11) DEFAULT NULL,
  `IdentificationNumber` varchar(20) DEFAULT NULL,
  `PhoneNumber` varchar(20) NOT NULL,
  `Email` varchar(150) NOT NULL,
  `Address` varchar(100) DEFAULT NULL,
  `RoomNumber` int(11) DEFAULT NULL,
  `RoomType` varchar(150) DEFAULT NULL,
  `AreaType` varchar(100) DEFAULT NULL,
  `AreaNumber` int(10) DEFAULT NULL,
  `CheckInDate` date NOT NULL,
  `CheckOutDate` date NOT NULL,
  `extra` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`CustomerID`, `total`, `FirstName`, `LastName`, `Age`, `IdentificationNumber`, `PhoneNumber`, `Email`, `Address`, `RoomNumber`, `RoomType`, `AreaType`, `AreaNumber`, `CheckInDate`, `CheckOutDate`, `extra`) VALUES
(1, 900, 'Joel', 'Miller', NULL, NULL, '552 5552 ', 'test@test', NULL, 100, 'Deluxe Room', NULL, NULL, '2023-05-21', '2023-05-24', NULL),
(2, 640, 'Ashley', 'Johnson', NULL, NULL, '2223 223', 'test@gmail.com', NULL, 102, 'Deluxe Room', NULL, NULL, '2023-05-29', '2023-05-31', NULL),
(3, 9000, 'Tommy', 'Miller', NULL, NULL, '223 4422', 'tommy@gmail.com', NULL, 108, 'Suite', NULL, NULL, '2023-05-23', '2023-05-29', NULL),
(4, 2400, 'Marlene', 'M', NULL, NULL, '213123 44213', 'marlene@test.com', NULL, 109, 'Executive Room', NULL, NULL, '2023-05-22', '2023-05-30', NULL),
(5, 30, 'Tess', 'T', NULL, NULL, '2342 5324', 'tess@test.com', NULL, NULL, NULL, 'Swimming Pool', 204, '2023-05-23', '2023-05-24', NULL),
(6, 120, 'Bill', 'B', NULL, NULL, '4234 523424', 'billTown@test.com', NULL, NULL, NULL, 'Bar', 205, '2023-05-23', '2023-05-25', NULL),
(7, 560, 'Henry ', 'H', NULL, NULL, '7383 8373', 'henry&sam@test.com', NULL, NULL, NULL, 'Fitness Center', 210, '2023-05-23', '2023-05-30', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `customer_receipt`
--

CREATE TABLE `customer_receipt` (
  `id` int(100) NOT NULL,
  `customer_num` int(100) NOT NULL,
  `total` double NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer_receipt`
--

INSERT INTO `customer_receipt` (`id`, `customer_num`, `total`, `date`) VALUES
(1, 21, 3566574, '2023-05-19'),
(2, 22, 149191, '2023-05-23'),
(3, 23, 50808, '2023-05-17'),
(4, 24, 4539276, '2023-05-16'),
(5, 25, 170504, '2023-05-23'),
(6, 26, 3890808, '2023-05-17'),
(7, 27, 213130, '2023-05-19'),
(8, 28, 2769, '2023-05-11'),
(9, 29, 46574, '2023-05-19'),
(10, 30, 50808, '2023-05-17'),
(11, 31, 2769, '2023-05-16'),
(12, 32, 15899, '2023-05-11'),
(13, 33, 50808, '2023-05-15'),
(14, 34, 1945404, '2023-05-24'),
(15, 21, 1065, '2023-05-17'),
(16, 22, 0, '2023-05-18'),
(17, 23, 0, '2023-05-18'),
(18, 20, 50000, '2023-05-19'),
(19, 21, 852, '2023-05-18'),
(20, 22, 120, '2023-05-19'),
(21, 20, 87, '2023-05-18'),
(22, 19, 14676, '2023-05-18'),
(23, 20, 9600, '2023-05-18'),
(24, 20, 2769, '2023-05-18'),
(25, 21, 10800, '2023-05-18'),
(26, 22, 500, '2023-05-19'),
(27, 21, 1065, '2023-05-18'),
(28, 21, 780, '2023-05-18'),
(29, 1, 480, '2023-05-17'),
(30, 2, 65000, '2023-05-19'),
(31, 3, 360, '2023-05-19'),
(32, 4, 10000, '2023-05-26'),
(33, 5, 60, '2023-05-20'),
(34, 6, 522, '2023-05-25'),
(35, 1, 213, '2023-05-20'),
(36, 2, 21313, '2023-05-20'),
(37, 3, 2343, '2023-05-20'),
(38, 4, 60, '2023-05-20'),
(39, 5, 1704, '2023-05-20'),
(40, 6, 852, '2023-05-20'),
(41, 7, 149191, '2023-05-20'),
(42, 8, 2700, '2023-05-20'),
(43, 9, 87, '2023-05-22'),
(44, 8, 120, '2023-05-23'),
(45, 9, 348, '2023-05-25'),
(46, 1, 900, '2023-05-21'),
(47, 2, 640, '2023-05-29'),
(48, 3, 9000, '2023-05-23'),
(49, 4, 2400, '2023-05-22'),
(50, 5, 30, '2023-05-23'),
(51, 6, 120, '2023-05-23'),
(52, 7, 560, '2023-05-23');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `EmployeeID` int(11) NOT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Title` varchar(20) DEFAULT NULL,
  `Supervisor` varchar(30) DEFAULT NULL,
  `BirthDate` date DEFAULT NULL,
  `HireDate` date DEFAULT NULL,
  `Language` varchar(150) NOT NULL,
  `PhoneNumber` varchar(20) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL,
  `Extra` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`EmployeeID`, `FirstName`, `LastName`, `Title`, `Supervisor`, `BirthDate`, `HireDate`, `Language`, `PhoneNumber`, `Address`, `Extra`) VALUES
(2, 'Jane', 'Smith', 'Receptionist', 'Manager', '1990-05-15', '2015-03-01', 'English', '555-2345', '456 Oak Ave', NULL),
(3, 'Robert', 'Johnson', 'Maintenance', 'Manager', '1985-09-20', '2012-06-15', '', '555-3456', '789 Maple Dr', NULL),
(4, 'Susan', 'Lee', 'Housekeeping', 'Receptionist', '1992-11-30', '2017-02-01', '', '555-4567', '321 Elm St', 'Some extra information'),
(5, 'David', 'Kim', 'Chef', 'Manager', '1988-03-10', '2013-09-01', '', '555-5678', '987 Pine Ln', NULL),
(6, 'Sarah', 'Jones', 'Waiter', 'Chef', '1995-07-05', '2019-01-01', '', '555-6789', '654 Cedar Rd', NULL),
(7, 'Michael', 'Nguyen', 'Housekeeping', 'Receptionist', '1993-12-25', '2016-08-15', '', '555-7890', '321 Birch Blvd', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `hotelsettings`
--

CREATE TABLE `hotelsettings` (
  `hotel_id` int(11) NOT NULL,
  `hotel_name` varchar(100) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL,
  `currency` varchar(10) DEFAULT NULL,
  `time_zone` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hotelsettings`
--

INSERT INTO `hotelsettings` (`hotel_id`, `hotel_name`, `address`, `city`, `country`, `phone_number`, `email`, `website`, `currency`, `time_zone`) VALUES
(1, 'Ground Zeroes', '123 Main Street', 'Istanbul', 'Turkey', '+90 123456789', 'info@groundzero.com', 'www.groundzero.com', 'USD', 'Europe/Istanbul');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `RoomNumber` int(10) NOT NULL,
  `Type` varchar(50) NOT NULL,
  `Availability` varchar(50) NOT NULL,
  `Price` double(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`RoomNumber`, `Type`, `Availability`, `Price`) VALUES
(100, 'Deluxe Room', 'Occupied', 300.00),
(101, 'Suite', 'Available', 500.00),
(102, 'Deluxe Room', 'Occupied', 320.00),
(103, 'Standard Room', 'Available', 150.00),
(104, 'Standard Room', 'Available', 65.00),
(105, 'Deluxe Room', 'Available', 650.00),
(106, 'Standard Room', 'Available', 200.00),
(107, 'Suite', 'Available', 1000.00),
(108, 'Suite', 'Occupied', 1500.00),
(109, 'Executive Room', 'Occupied', 300.00),
(110, 'Deluxe Room', 'Not Available', 1200.00),
(111, 'Standard Room', 'Not Available', 40.00),
(112, 'Standard Room', 'Not Available', 35.00),
(113, 'Suite', 'Available', 600.00),
(114, 'Deluxe Room', 'Available', 380.00);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `occupation` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `name`, `lastname`, `occupation`) VALUES
(1, 'manager', '1234', 'Joel', 'Miller', 'Manager'),
(2, 'receptionist', '1234', 'Arthur', 'Morgan', 'Receptionist');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `area`
--
ALTER TABLE `area`
  ADD PRIMARY KEY (`AreaNumber`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`CustomerID`),
  ADD UNIQUE KEY `IdentificationNumber` (`IdentificationNumber`);

--
-- Indexes for table `customer_receipt`
--
ALTER TABLE `customer_receipt`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`EmployeeID`);

--
-- Indexes for table `hotelsettings`
--
ALTER TABLE `hotelsettings`
  ADD PRIMARY KEY (`hotel_id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`RoomNumber`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer_receipt`
--
ALTER TABLE `customer_receipt`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `EmployeeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `RoomNumber` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=115;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

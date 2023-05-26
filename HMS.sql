-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 17, 2023 at 12:20 PM
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
(100, 'Big Hall', 450, 'Occupied', NULL, NULL, NULL, 5000, NULL),
(201, 'Conference Room', 300, 'Not Available', NULL, NULL, NULL, 30, 'This is a spacious conference room'),
(202, 'Fitness Center', 30, 'Occupied', NULL, NULL, NULL, 40, 'Fully equipped gym with modern amenities'),
(203, 'Spa', 10, 'Available', NULL, NULL, NULL, 30, 'A relaxing spa experience'),
(204, 'Swimming Pool', 60, 'Not Available', NULL, NULL, NULL, 30, 'A large swimming pool with a beautiful view'),
(205, 'Bar', 20, 'Available', NULL, NULL, NULL, 60, 'A cozy bar for socializing and drinks'),
(206, 'Big Hall', 400, 'Available', NULL, NULL, NULL, 6500, 'A big hall for events'),
(207, 'Conference Room', 40, 'Available', NULL, NULL, NULL, 60, NULL);

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
(1, 0, 'test', 'Doe', 32, 'ID1234', '555-1234', 'John@email', '123 Main St.', 104, 'Standard Room', NULL, NULL, '2023-04-14', '2023-04-17', NULL),
(2, 0, 'Jane', 'Doe', 28, 'ID2345', '555-2345', 'test@email', '456 Oak St.', NULL, NULL, 'Bar', 205, '2023-04-14', '2023-04-18', NULL),
(3, 0, 'Bob', 'Smith', 45, 'ID3456', '555-3456', 'test@email', '789 Maple St.', NULL, NULL, NULL, NULL, '2023-04-15', '2023-04-17', NULL),
(4, 0, 'Alice', 'Johnson', 27, 'ID4567', '555-4567', 'test@email', '321 Elm St.', NULL, NULL, NULL, NULL, '2023-04-15', '2023-04-19', NULL),
(5, 0, 'Mike', 'Jones', 52, 'ID5678', '555-5678', 'test@email', '555 Pine St.', NULL, NULL, NULL, NULL, '2023-04-16', '2023-04-18', NULL),
(6, 0, 'Sarah', 'Lee', 39, 'ID6789', '555-6789', 'test@email', '777 Cedar St.', NULL, NULL, NULL, NULL, '2023-04-21', '2023-04-24', NULL),
(7, 0, 'David', 'Brown', 31, 'ID7890', '555-7890', 'test@email', '999 Oak St.', NULL, NULL, NULL, NULL, '2023-04-22', '2023-04-25', NULL),
(8, 0, 'Amy', 'Taylor', 42, 'ID8901', '555-8901', 'test@email', '111 Maple St.', NULL, NULL, NULL, NULL, '2023-04-17', '2023-04-26', NULL),
(9, 0, 'Mark', 'Davis', 29, 'ID9012', '555-9012', 'test@email', '222 Elm St.', NULL, NULL, NULL, NULL, '2023-04-23', '2023-04-27', NULL),
(10, 0, 'Emily', 'White', 36, 'ID0123', '555-0123', 'test@email', '333 Pine St.', NULL, NULL, NULL, NULL, '2023-04-23', '2023-04-27', NULL),
(11, 0, 'Samantha', 'Garcia', 28, 'ID3452', '555-3452', 'test@email', '123 Main St.', NULL, NULL, NULL, NULL, '2023-04-14', '2023-04-17', NULL),
(12, 0, 'Tyler', 'Lee', 33, 'ID4563', '555-4563', 'test@email', '456 Oak St.', NULL, NULL, NULL, NULL, '2023-04-15', '2023-04-17', NULL),
(13, 0, 'Jessica', 'Clark', 41, 'ID5674', '555-5674', 'test@email', '789 Maple St.', NULL, NULL, NULL, NULL, '2023-04-15', '2023-04-17', NULL),
(15, 0, 'Olivia', 'Hall', 37, 'ID7896', '555-7896', 'test@email', '555 Pine St.', NULL, NULL, NULL, NULL, '2023-04-15', '2023-04-19', NULL),
(16, 0, 'Daniel', 'Ramirez', 26, 'ID8907', '555-8907', 'test@email', '777 Cedar St.', NULL, NULL, NULL, NULL, '2023-04-21', '2023-04-24', NULL),
(17, 0, 'Isabella', 'Wright', 29, 'ID9018', '555-9018', 'test@email', '999 Oak St.', NULL, NULL, NULL, NULL, '2023-04-22', '2023-04-25', NULL),
(18, 0, 'Jacob', 'Adams', 35, 'ID0129', '555-0129', 'test@email', '111 Maple St.', NULL, NULL, 'Bar', 205, '2023-04-17', '2023-04-26', NULL);

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
(21, 20, 87, '2023-05-18');

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
(7, 'Michael', 'Nguyen', 'Housekeeping', 'Receptionist', '1993-12-25', '2016-08-15', '', '555-7890', '321 Birch Blvd', NULL),
(8, 'Karen', 'Garcia', 'Concierge', 'Receptionist', '1982-06-12', '2011-03-01', '', '555-8901', '876 Oakwood Ave', NULL),
(9, 'Andrew', 'Chen', 'Housekeeping', 'Receptionist', '1991-04-05', '2014-06-15', '', '555-9012', '543 Maple St', NULL);

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
(100, 'Deluxe Room', 'Occupied', 213.00),
(101, 'Suite', 'Occupied', 213.00),
(102, 'Deluxe Room', 'Occupied', 213.00),
(103, 'Standard Room', 'Occupied', 21313.00),
(104, 'Standard Room', 'Occupied', 87.00),
(105, 'Deluxe Room', 'Available', 1223.00),
(106, 'Standard Room', 'Available', 800.00);

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
(2, 'receptionist', '12345', 'Arthur', 'Morgan', 'Receptionist');

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
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `EmployeeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `RoomNumber` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=108;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=125;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

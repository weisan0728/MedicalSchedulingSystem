-- SQL file to create the 'user' table and insert sample data

-- Switch to the desired database
USE hospital;

-- Create the 'user' table
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(200) NOT NULL,
  `lastName` varchar(200) NOT NULL,
  `ic` varchar(12) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `phoneNum` varchar(11) NOT NULL,
  `email` varchar(200) NOT NULL,
  `address` varchar(300) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
);

-- Create the 'nurse' table
CREATE TABLE `nurse` (
    `nurseId` int NOT NULL AUTO_INCREMENT,
    `nurseName` varchar(200) NOT NULL,
    `nurseIc` varchar(12) NOT NULL,
    `nurseEmail` varchar(200) NOT NULL,
    `nursePhoneNum` varchar(11) NOT NULL,
    `nurseAddress` varchar(300) NOT NULL,
    `nursePsw` varchar(50) NOT NULL,
    `position` varchar(50) NOT NULL,
    PRIMARY KEY (`nurseId`)
);

-- Create the 'doc' table
CREATE TABLE `doc` (
    `docId` int NOT NULL AUTO_INCREMENT,
    `docName` varchar(200) NOT NULL,
    `docIc` varchar(12) NOT NULL,
    `docEmail` varchar(200) NOT NULL,
    `docPhoneNum` varchar(11) NOT NULL,
    `docAddress` varchar(300) NOT NULL,
    `docPsw` varchar(50) NOT NULL,
    `position` varchar(50) NOT NULL,
    PRIMARY KEY (`docId`)
);

-- Create the 'clock_in' table
CREATE TABLE `clock_in` (
    `id` int NOT NULL AUTO_INCREMENT,
    `staffId` int NOT NULL,
    `clockInDate` date NOT NULL,
    `clockInTime` varchar(8) NOT NULL,
    PRIMARY KEY (`id`)
);

-- Create the 'clock_out' table
CREATE TABLE `clock_out` (
    `id` int NOT NULL AUTO_INCREMENT,
    `staffId` int NOT NULL,
    `clockOutDate` date NOT NULL,
    `clockOutTime` varchar(8) NOT NULL,
    PRIMARY KEY (`id`)
);

-- Create the 'room' table
CREATE TABLE `room` (
    `roomNo` varchar(10) NOT NULL,
    `location` varchar(200) NOT NULL,
    `status` varchar(20) NOT NULL,
    PRIMARY KEY (`roomNo`)
);

-- Create the 'facilities' table
CREATE TABLE `facilities` (
    `type` varchar(200) NOT NULL,
    `name` varchar(200) NOT NULL,
    `status` varchar(20) NOT NULL,
    `location` varchar(200) NOT NULL,
    PRIMARY KEY (`name`)
);

-- Create the 'customer' table
CREATE TABLE `customer` (
    `sur_id` int NOT NULL AUTO_INCREMENT,
    `id` varchar(10) NOT NULL,
    `name` varchar(200) NOT NULL,
    `phone` varchar(11) NOT NULL,
    `nurseId` varchar(10) NOT NULL,
    `nurseName` varchar(200) NOT NULL,
    `docId` varchar(10) NOT NULL,
    `docName` varchar(200) NOT NULL,
    `anestheId` varchar(10) NOT NULL,
    `anestheName` varchar(200) NOT NULL,
    `facilitiesType` varchar(200) NOT NULL,
    `facilitiesName` varchar(200) NOT NULL,
    `location` varchar(200) NOT NULL,
    `roomId` varchar(10) NOT NULL,
    `date` date NOT NULL,
    `time` varchar(8) NOT NULL,
    PRIMARY KEY (`sur_id`)
);

-- Create the 'bill' table
CREATE TABLE `bill` (
    `receiptId` varchar(100) NOT NULL,
    `customerId` varchar(100) NOT NULL,
    `payDate` date NOT NULL,
    `payTime` varchar(8) NOT NULL,
    `amount` double NOT NULL,
    `paymentMethod` varchar(200) NOT NULL,
    `payLocation` varchar(200) NOT NULL,
    `phoneNum` varchar(11) NOT NULL,
    PRIMARY KEY (`receiptId`)
);

-- Table: make_appointment
CREATE TABLE IF NOT EXISTS make_appointment (
    request_id INT NOT NULL AUTO_INCREMENT,
    Patient_ID INT NOT NULL,
    Name VARCHAR(200) NOT NULL,
    PhoneNum VARCHAR(11) NOT NULL,
    AppointmentDate DATE NOT NULL,
    AppointmentTime VARCHAR(8) NOT NULL,
    Clinic VARCHAR(45) NOT NULL,
    Reason LONGTEXT NOT NULL,
    SpecialNeeds LONGTEXT NULL,
    PRIMARY KEY (request_id),
    FOREIGN KEY (Patient_ID) REFERENCES user(id) -- Assuming 'user' table has a primary key 'id'
);

-- Table: appointment_approved
CREATE TABLE IF NOT EXISTS appointment_approved (
    Appointment_ID INT NOT NULL,
    Patient_ID VARCHAR(45) NOT NULL,
    Name VARCHAR(200) NOT NULL,
    PhoneNum VARCHAR(11) NOT NULL,
    ApproveDate DATE NOT NULL,
    AppointmentDate DATE NOT NULL,
    AppointmentTime VARCHAR(8) NOT NULL,
    Clinic VARCHAR(200) NOT NULL,
    reason LONGTEXT NOT NULL,
    PRIMARY KEY (Appointment_ID)
);

-- Table: appointment_cancelled
CREATE TABLE IF NOT EXISTS appointment_cancelled (
    Appointment_ID INT NOT NULL,
    Patient_ID INT NOT NULL,
    CancelDate DATE NOT NULL,
    AppointmentDate DATE NOT NULL,
    AppointmentTime VARCHAR(8) NOT NULL,
    Clinic VARCHAR(200) NOT NULL,
    CancellationReason MEDIUMTEXT NOT NULL
);

-- Table: appointment_records
CREATE TABLE IF NOT EXISTS appointment_records (
    Appointment_ID INT NOT NULL,
    Patient_ID INT NOT NULL,
    Date DATE NOT NULL,
    Time VARCHAR(8) NOT NULL,
    DoctorName VARCHAR(200) NOT NULL,
    Clinic VARCHAR(200) NOT NULL,
    Reason LONGTEXT NOT NULL,
    BillingStatus VARCHAR(45) NOT NULL,
    PRIMARY KEY (Appointment_ID)
);

-- Table: appointment_rejected
CREATE TABLE IF NOT EXISTS appointment_rejected (
    Request_ID INT NOT NULL,
    Patient_ID INT NOT NULL,
    Name VARCHAR(200) NOT NULL,
    PhoneNum VARCHAR(11) NOT NULL,
    RejectionDate DATE NOT NULL,
    AppointmentRequestedDate DATE NOT NULL,
    AppointmentRequestedTime VARCHAR(8) NOT NULL,
    Clinic VARCHAR(200) NOT NULL,
    RejectionReason MEDIUMTEXT NOT NULL,
    PRIMARY KEY (Request_ID)
);

CREATE DATABASE IF NOT EXISTS fleetmanagementsystem;

CREATE TABLE fleetmanagementsystem.vehicles (
    `vehicle_id` INT AUTO_INCREMENT,
    `registration_number` VARCHAR(255),
    `model_type` VARCHAR(255),
    `capacity` INT,
    `driver_id` INT,
PRIMARY KEY (vehicle_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE fleetmanagementsystem.drivers (
    `driver_id` INT AUTO_INCREMENT,
    `full_name` VARCHAR(255),
    `license_code` VARCHAR(255),
    `contact_info` VARCHAR(255),
PRIMARY KEY (driver_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE fleetmanagementsystem.journeys (
    `journey_id` INT AUTO_INCREMENT,
    `start_point` VARCHAR(255),
    `end_point` VARCHAR(255),
    `distance_traveled` DOUBLE,
    `fare` DOUBLE,
PRIMARY KEY (journey_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE fleetmanagementsystem.estimates (
    `estimate_id` INT AUTO_INCREMENT,
    `vehicle_id` INT,
    `journey_id` INT,
    `date_of_departure` DATETIME,
    `estimated_cost` DOUBLE,
PRIMARY KEY (estimate_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE fleetmanagementsystem.bookings (
    `booking_id` INT AUTO_INCREMENT,
    `estimate_id` INT,
    `number_of_passengers` INT,
    `contact_person_name` VARCHAR(255),
    `contact_person_email` VARCHAR(255),
PRIMARY KEY (booking_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE fleetmanagementsystem.dutys (
    `duty_id` INT AUTO_INCREMENT,
    `assigned_driver_id` INT,
PRIMARY KEY (duty_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE fleetmanagementsystem.driver_dutys (
    `driver_duty_id` INT AUTO_INCREMENT,
    `duty_id` INT,
PRIMARY KEY (driver_duty_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE fleetmanagementsystem.vehicle_dutys (
    `vehicle_duty_id` INT AUTO_INCREMENT,
    `vehicle_id` INT,
PRIMARY KEY (vehicle_duty_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE fleetmanagementsystem.transactions (
    `transaction_id` INT AUTO_INCREMENT,
    `booking_id` INT,
    `total_amount` DOUBLE,
    `transaction_date` DATETIME,
PRIMARY KEY (transaction_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE fleetmanagementsystem.facilitys (
    `facility_id` INT AUTO_INCREMENT,
    `facility_name` VARCHAR(255),
    `facility_description` VARCHAR(255),
PRIMARY KEY (facility_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE fleetmanagementsystem.feedbacks (
    `feedback_id` INT AUTO_INCREMENT,
    `task_id` TEXT,
    `reviewer_id` TEXT,
    `review_content` VARCHAR(255),
    `feedback_date` DATETIME,
PRIMARY KEY (feedback_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE fleetmanagementsystem.submitters (
    `submitter_id` INT AUTO_INCREMENT,
    `task_id` TEXT,
    `user_id` TEXT,
    `submission_date` DATETIME,
PRIMARY KEY (submitter_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE fleetmanagementsystem.executors (
    `executor_id` INT AUTO_INCREMENT,
    `task_id` TEXT,
    `executor_user_id` TEXT,
    `execution_date` DATETIME,
PRIMARY KEY (executor_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE fleetmanagementsystem.verifiers (
    `verifier_id` INT AUTO_INCREMENT,
    `task_id` TEXT,
    `verifier_user_id` TEXT,
    `verification_date` DATETIME,
PRIMARY KEY (verifier_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

USE drcvelocity;

--------------------------------------------------------------------------------
-- Exercise 1 Creation of tables.
-- The SQL DDL Statements in this file creates the tables. 
--
-- Note that the create table statements are not complete and will be
-- extended later in the course
--------------------------------------------------------------------------------

-- Employees table
CREATE TABLE Employees (
	FNAME		VARCHAR (30),
 	MINIT   	VARCHAR (1),
	LNAME   	VARCHAR (30),
	CPR     	VARCHAR (11),
	BDATE   	DATE,	
	ADDRESS    	VARCHAR (50),
	SEX 		VARCHAR (1),
	SALARY 		NUMERIC (8,0),
	DNO		NUMERIC (2,0));

-- Departments table
CREATE TABLE Departments (
	DNAME		VARCHAR (20),
	DNUMBER  	NUMERIC (3,0),
	MGRCPR 		VARCHAR (11),
	MGRSTARTDATE 	DATE);

--Projects table
CREATE TABLE Projects(
	PNAME		VARCHAR (30),
	PNUMBER		NUMERIC (2,0),
	PLOCATION	VARCHAR (20),
	DNUM		NUMERIC (3,0));

-- Locations table
CREATE TABLE Locations(
	DNBR		NUMERIC (3,0),
	DLOCATION	VARCHAR (20));


-- Allocations table
CREATE TABLE Allocations(
	ECPR		VARCHAR (11),
	PNO 		NUMERIC (2,0),
	HOURS		NUMERIC (3,0));


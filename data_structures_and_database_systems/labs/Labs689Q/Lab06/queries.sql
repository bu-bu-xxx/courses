--------------------------------------------------------------------------------
-- Various SQL queries for testing the company database
--------------------------------------------------------------------------------

-- Get all the contents from the Employees table
SELECT *
FROM   Employees;

-- Find the first names of female employees
SELECT FName
FROM   Employees
WHERE  SEX = 'F';

-- Get the average salary of the Employees
SELECT AVG(SALARY) "Average Salary"
FROM   Employees;

-- Insert a new tuple
INSERT INTO Employees VALUES 
('Viggo', 'M', 'Madsen', 999, '1945/12/10', 'København', 'M', 45000, 13);

-- Update the salary and department of the newly inserted typle 
UPDATE Employees
SET SALARY = 35000,
    DNO    = 12
WHERE FName = 'Viggo';

-- Delete the inserted tuple again
DELETE FROM Employees 
WHERE FName =  'Viggo';

-- Save your work
COMMIT;

-- Exit from the Oracle instance
EXIT;



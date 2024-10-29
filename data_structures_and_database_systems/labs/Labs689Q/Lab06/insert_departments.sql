--------------------------------------------------------------------------------
-- Populate the Departments table with tuples
--------------------------------------------------------------------------------

INSERT INTO Departments VALUES 
('ConsProd', 10, 333, '1994-10-01');

INSERT INTO Departments VALUES 
('InduProd', 11, 654, '1995-05-01');

INSERT INTO Departments VALUES 
('Research', 12, 111, '1990-06-15');

-- Now check the number of tuples in the Departments table 
SELECT COUNT (*) FROM Departments;

-- Save your work
COMMIT;

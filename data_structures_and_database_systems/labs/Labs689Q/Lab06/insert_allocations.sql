--------------------------------------------------------------------------------
-- Populate the Allocations table with tuples
--------------------------------------------------------------------------------

INSERT INTO Allocations VALUES 
(123, 3, 27);

INSERT INTO Allocations VALUES 
(456, 3, 12);

INSERT INTO Allocations VALUES 
(789, 4, 35);

INSERT INTO Allocations VALUES 
(111, 1, 15);

INSERT INTO Allocations VALUES 
(111, 2, 5);

INSERT INTO Allocations VALUES 
(222, 5, 30);

INSERT INTO Allocations VALUES 
(333, 3, 20);

INSERT INTO Allocations VALUES 
(333, 5, 20);

INSERT INTO Allocations VALUES 
(987, 4, 25);

INSERT INTO Allocations VALUES 
(654, 2, 30);

INSERT INTO Allocations VALUES 
(321, 1, 15);

INSERT INTO Allocations VALUES 
(321, 2, 10);

-- Now check the number of tuples in the Allocations table 
SELECT COUNT (*) FROM Allocations;

-- Save your work
COMMIT;

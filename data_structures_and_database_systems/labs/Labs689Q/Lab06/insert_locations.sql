--------------------------------------------------------------------------------
-- Populate the Locations table with tuples
--------------------------------------------------------------------------------

INSERT INTO Locations VALUES 
(10, 'Aalborg');

INSERT INTO Locations VALUES 
(10, 'Nørresundby');

INSERT INTO Locations VALUES 
(11, 'Aarhus');

INSERT INTO Locations VALUES 
(12, 'Nørresundby');

INSERT INTO Locations VALUES 
(12, 'Frederikshavn');

-- Now check the number of tuples in the Locations table 
SELECT COUNT (*) FROM Locations;

-- Save your work
COMMIT;

--------------------------------------------------------------------------------
-- Populate the Projects table with tuples
--------------------------------------------------------------------------------

INSERT INTO Projects VALUES 
('MobilePhone', 1, 'Nørresundby', 10);

INSERT INTO Projects VALUES 
('InteractiveTV', 2, 'Nørresundby', 12);

INSERT INTO Projects VALUES 
('MmedMonitor', 3, 'Aarhus', 11);

INSERT INTO Projects VALUES 
('PalmTop', 4, 'Aalborg', 10);

INSERT INTO Projects VALUES 
('MobileOffice', 5, 'Aarhus', 11);

-- Now check the number of tuples in the Projects table 
SELECT COUNT (*) FROM Projects;

-- Save your work
COMMIT;

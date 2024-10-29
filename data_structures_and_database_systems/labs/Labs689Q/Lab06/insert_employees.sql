--------------------------------------------------------------------------------
-- Populate the Employees table with tuples
--------------------------------------------------------------------------------

INSERT INTO Employees VALUES 
('Lars', 'T', 'Andersen', '123', '1955-12-10', 'Klarup', 'M', '15000',  '12');

INSERT INTO Employees VALUES 
('Kristian', 'C', 'Bohr', '456', '1965-10-05', 'Tylstrup', 'M', '18000', '11');

INSERT INTO Employees VALUES 
('Charlotte', 'F', 'Kierkegaard', '789', '1975-08-06', 'Vejgaard', 'F', '14000', '11');

INSERT INTO Employees VALUES 
('Uffe', 'J', 'Bajers', 111, '1960-09-07', 'Gistrup', 'M', 30000, 12);

INSERT INTO Employees VALUES 
('Hans', 'U',  'Brahe', 222, '1970-04-02', 'Svenstrup', 'M', 20000, 10);

INSERT INTO Employees VALUES 
('Helle', 'O', 'Dreyer', 333, '1950-01-08', 'Uttrup', 'F', 35000,  10);

INSERT INTO Employees VALUES 
('Peter', 'P', 'Nielsen', 987, '1973-05-30', 'Lundby', 'M', 23000,  12);

INSERT INTO Employees VALUES 
('Niels', 'A', 'Thorvaldsen', 654, '1953-02-27', 'Vodskov', 'M', 32000, 11);

INSERT INTO Employees VALUES 
('Tina',  'C', 'Jacobsen',  321, '1963-11-16', 'Nytorv', 'F', 26000,  12);

-- Now check the number of tuples in the Employees table 
SELECT COUNT (*) FROM Employees;

-- Save your work
COMMIT;

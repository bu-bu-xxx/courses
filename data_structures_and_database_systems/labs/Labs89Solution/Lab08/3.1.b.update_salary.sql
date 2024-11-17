UPDATE Employees SET Salary =
CASE WHEN Salary >= 30000
     THEN Salary * 1.15
     ELSE Salary * 1.2
END;

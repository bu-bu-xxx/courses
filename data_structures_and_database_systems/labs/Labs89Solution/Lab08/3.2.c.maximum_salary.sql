SELECT FNAME, CPR, Employees.DNO AS DepartmentNumber, T.MSLY AS MaxSalary
FROM Employees
LEFT JOIN (
    SELECT DNO, MAX(SALARY) AS MSLY
    FROM Employees
    GROUP BY DNO) T
    ON T.DNO = Employees.DNO;

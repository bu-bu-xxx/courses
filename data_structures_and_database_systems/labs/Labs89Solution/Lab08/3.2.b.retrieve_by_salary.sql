SELECT DNAME AS DepartmentName, T.CNT AS MaleCount
FROM Departments
INNER JOIN (
    SELECT DNO FROM Employees
    GROUP BY DNO
    HAVING AVG(SALARY) > 27000 ) S
    ON S.DNO = Departments.DNUMBER
INNER JOIN (
    SELECT DNO, COUNT(*) AS CNT FROM Employees
    WHERE SEX = 'M'
    GROUP BY DNO ) T
    ON T.DNO = Departments.DNUMBER
;

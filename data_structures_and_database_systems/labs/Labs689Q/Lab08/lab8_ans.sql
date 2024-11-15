-- rollback the changes
ROLLBACK;

-- ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD';


--3.1(a)
-- (a) Write a SQL query statement to retrieves Tina’s name, cpr, and current salary. 
-- Execute the SQL query statement. 
-- What is her current salary? 

SELECT fname, lname, cpr, salary 
from EMPLOYEES 
WHERE FNAME = 'Tina';

--3.1(b)
-- (b) Write SQL update statement(s) to raise the salaries of employees as follows: 
-- • Employees who earn less than 30000 will get 20% raise 
-- • Employees who earn 30000 or more will get 15% raise 
-- Execute your SQL update statement(s). 

UPDATE EMPLOYEES 
SET SALARY = SALARY * 1.2
where SALARY < 30000;

UPDATE EMPLOYEES 
SET SALARY = SALARY * 1.15
where SALARY >= 30000;

COMMIT;

--3.1(c)
-- (c) Execute the SQL query statement in part (a) to check Tina’s salary again. 
-- Is this result correct or not?

SELECT fname, lname, cpr, salary
from EMPLOYEES
WHERE FNAME = 'Tina';

--3.1(d)
-- (d) Write a SQL query statement that retrieves the first name and birthday 
-- of all employees born in the 1950-1959. 
-- Execute your SQL query statement. 
SELECT FNAME, BDATE
FROM EMPLOYEES
WHERE BDATE BETWEEN '1950-01-01' AND '1959-12-31';

-- 3.2(a)
-- (a) Retrieve the number of persons working in the department that 
-- is responsible for the most projects.

-- JOIN: return rows when there is at least one match in both tables
-- JOIN == INNER JOIN
-- LEFT JOIN: return all rows from the left table, 
-- if there are no matches in the right table, the result is NULL

-- ?????????????

-- 3.2(b)
-- (b) For each department whose average salary is more than 27K, 
-- retrieve the department name and the number of male employees 
-- working for that department.

SELECT DNO, SUM(SEX = 'M') MALECNT
FROM EMPLOYEES
GROUP BY DNO
HAVING AVG(SALARY) > 27000;

-- -- 3.2(c)
-- (c) For each employee, retrieve the employee CPR number, the department 
-- number, and the maximal salary in this department.
SELECT e1.CPR, e1.DNO 
FROM EMPLOYEES e1
JOIN 
{SELECT MAX(SALARY) MAXSALARY, DNO
FROM EMPLOYEES 
GROUP BY DNO} e2
ON e1.DNO = e2.DNO;

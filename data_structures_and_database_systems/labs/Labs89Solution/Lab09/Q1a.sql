SELECT fname, lname
FROM employees
WHERE salary > SOME (
	SELECT e.SALARY FROM employees e, departments d
	WHERE d.dname = 'ConsProd' AND d.DNUMBER = e.dno
);

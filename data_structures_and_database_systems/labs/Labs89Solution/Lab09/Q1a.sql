SELECT fname, lname
FROM employees
WHERE salary > SOME (
	SELECT salary FROM employees em, departments 
	WHERE departments.dname = 'ConsProd' AND departments.dnumber = em.dno
);

SELECT employees.dno DepartmentNumber, COUNT(*)
FROM employees, 
(SELECT dno, AVG(salary) AS avg_sal FROM employees GROUP BY dno) avg_sal_tab
WHERE employees.dno = avg_sal_tab.dno AND employees.salary >= avg_sal_tab.avg_sal
GROUP BY employees.dno;


SELECT fname, cpr, employees.dno DepartmentNumber, max_salary MaxSalary 
FROM employees, 
(SELECT dno, MAX(salary) max_salary FROM employees GROUP BY employees.dno) max_sal_tab
WHERE employees.dno = max_sal_tab.dno;


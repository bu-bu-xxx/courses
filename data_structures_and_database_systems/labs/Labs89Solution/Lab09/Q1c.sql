SELECT d.dname, COUNT(*) AS male_cnt
FROM employees e, departments d
WHERE d.dnumber = e.dno AND e.sex = 'M' AND e.dno IN
               (SELECT e2.dno FROM employees e2
                 GROUP BY e2.dno
                 HAVING AVG (e2.salary) > 27000)
GROUP BY d.dname;

------alternative solution
SELECT dtab.dname, male_cnt_tab.male_cnt FROM 
(SELECT dno, COUNT(*) AS male_cnt FROM Employees WHERE SEX = 'M' GROUP BY DNO) male_cnt_tab,
Departments dtab
WHERE male_cnt_tab.dno = dtab.dnumber AND male_cnt_tab.dno IN (
	SELECT dno FROM employees GROUP BY dno HAVING AVG(salary) > 27000
);

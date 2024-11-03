--3(a)
select name, cpr, SALARY
from employees;

--3(b)
update employees
set salary = salary * 0.8
where salary < 30000;
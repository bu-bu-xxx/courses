1. 

```matlab
cvx_solver sdpt3
% 1
cvx_begin
	variables x(4) y z(5) p(2)
	minimize y + abs(x(3)-x(4)+7)
	subject to
		[z(2), x(2)-5; x(2)-5, 1] == semidefinite(2);
		p(1) >= abs(x(3) - 1);
		p(1) >= 0;
		[z(3), p(2); p(2), p(1)] == semidefinite(2);
		[p(2), p(1); p(1), 1] == semidefinite(2);
		[z(4), x(4)+1; x(4)+1, 1] == semidefinite(2);
		[z(5), z(4); z(4), 1] == semidefinite(2);
		y >= 0;
		[[y, x(1), z(2), z(3), z(5), sqrt(2)];[[x(1); z(2); z(3); z(5); sqrt(2)],
		eye(5)*y]] == semidefinite(6);
		x(1)^2 + x(2)^2 + x(4)^2 <= 2;
cvx_end
```

> Optimal value (cvx_optval): +19.8227  

2. 

```matlab
% 2
A = [1, 5/2, -1/2; 5/2, 8, 0; -1/2, 0, 9];
cvx_begin
	variables x(3) y
	minimize quad_form(x, A) + ...
		8*(abs(x(1)-1) + abs(x(2)+3) + abs(x(3)-5))
	subject to
		[5, y; y, x(2)+1] == semidefinite(2);
		[y, x(3); x(3), 1] == semidefinite(2);
cvx_end
```

> Optimal value (cvx_optval): +57.4688  

3. 

```matlab
% 3
cvx_begin
	variables x(3)
	minimize 2*x(1) + 3*x(2) - x(3) + ...
		norm([1/sqrt(3)*x(1), x(2)-5, sqrt(6)*(x(3)-1/3*x(1)), 1], 2)
	subject to
		x(1)+x(2)<=2;
		x(3)+x(2)<=2;
		x(1)+x(3)<=2;
		x(1)>=0;
		x(2)>=0;
cvx_end
```

> Optimal value (cvx_optval): +4.65475  
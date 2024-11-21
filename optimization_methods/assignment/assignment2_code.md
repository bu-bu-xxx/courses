2. 

```matlab
cvx_solver sdpt3

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

**Results:**

>Incorrect number or types of inputs or outputs for function vec.
>
>
>Error in [cvx/quad_form](matlab:matlab.lang.internal.introspective.errorDocCallback('cvx/quad_form', '/MATLAB Drive/cvx/cvx/functions/@cvx/quad_form.m', 43)) ([line 43](matlab: opentoline('/MATLAB Drive/cvx/cvx/functions/@cvx/quad_form.m',43,0)))
>v = vec( v );
>
>Error in [minimize](matlab:matlab.lang.internal.introspective.errorDocCallback('minimize', '/MATLAB Drive/cvx/cvx/keywords/minimize.m', 14)) ([line 14](matlab: opentoline('/MATLAB Drive/cvx/cvx/keywords/minimize.m',14,0)))
>    x = evalin( 'caller', sprintf( '%s ', varargin{:

3. 

```matlab
cvx_solver sdpt3

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

**Results:**

> Optimal value (cvx_optval): +4.6547
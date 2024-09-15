function [x,fval,iter] = Lecture2_demo(A,b,mu,tol,maxiter)

% This is demo code for solving the example on slide 34 of Lecture 2
% It uses steepest descent direction, Armijo line-search (backtracking), with BB stepsize
% (see Lecture 3) as bar_alpha_k

% initialization
[~,n] = size(A);
x = zeros(n,1);
sigma = 1e-4;
iter = 0;

% initial fval and grad
Axb = A*x - b;
fval = sum(log(1 + Axb.^2)) + mu/2*norm(x)^2;
grad = A'*(2*Axb./(1 + Axb.^2)) + mu*x;
step_init = 1; % bar_alpha_0

while iter < maxiter % enter main loop
    
    dderiv = -norm(grad)^2; % direction derivative
    stepsize = step_init;     
    Ad = A*grad;
    
    ls = 0; % linesearch counter
    while 1 == 1 % linesearch loop
        xnew = x - stepsize*grad;
        Axbnew = Axb - stepsize*Ad;
        fnew = sum(log(1 + Axbnew.^2)) + mu/2*norm(xnew)^2;
        if fnew <= fval + sigma*stepsize*dderiv % Armijo rule
            break
        else
            stepsize = stepsize/2;
            ls = ls + 1;
        end
    end
    gnew = A'*(2*Axbnew./(1 + Axbnew.^2)) + mu*xnew; 
    
    % Printout
    if mod(iter,20) == 0
        fprintf(' iter = %5.0f, fval = %4.3e, ls = %3.0f, stepsize = %3.2e\n', iter, fnew, ls, stepsize)
    end
    
    % check for termination
    if norm(xnew - x) < tol*max(1,norm(xnew))
        fprintf(' Termination: iter = %5.0f, fval = %4.3e\n', iter, fnew)
        x = xnew;
        fval = fnew;
        break
    end
    
    % BB-type stepsize for bar_alpha_k
    dy = gnew - grad;
    ds = -stepsize*grad; % xnew - x
    dsdy = ds'*dy;
    if dsdy < 1e-12 % close to being negative
        step_init = max(1e-8,min(1e8,stepsize)); % inherit the old stepsize
    else
        step_init = max(1e-8,min(1e8,ds'*ds/dsdy)); % truncated BB stepsize
    end
    
    % Update iterates
    x = xnew;
    grad = gnew;
    fval = fnew;
    Axb = Axbnew;
    
    iter = iter + 1;
end
    
% Lecture 2: Run code

index = 10;
m = 200*index;
n = 1000*index;
k = 50*index;

mu = 0.01;

% random point
x0 = randn(n,1);

% generate A and b
A = randn(m,n);
for i = 1:n
    A(:,i) = A(:,i)/norm(A(:,i)); % normalize the columns, making the problem easier
end
b = A*x0 + randn(m,1)./randn(m,1); % adding Cauchy noise

[x,fval,iter] = Lecture2_demo(A,b,mu,1e-4,inf);
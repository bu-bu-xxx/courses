**ZHONG Qiaoyang**

**24112456g**

## Q1

### Task 1

**Step 1: mapper**

| Input & Split               | Mapping (output)                                             |
| --------------------------- | ------------------------------------------------------------ |
| dog cat bird rabbit dog     | dog: 1<br />dog: 1<br />dogcat: 1<br />bird: 1<br />rabbit: 1 |
| cat bird rabbit rabbit bird | cat: 1<br />bird: 1<br />bird: 1<br />rabbit: 1<br />rabbit: 1 |
| bird bird cat cat           | bird: 1<br />bird: 1<br />cat: 1<br />cat: 1                 |
| dog bird cat bird bird      | dog: 1<br />bird: 1<br />bird: 1<br />bird: 1<br />cat: 1    |

**Step 2: reducer**

| Shuffle                                                | Reducing (output) |
| ------------------------------------------------------ | ----------------- |
| dog: 1<br />dog: 1<br />dog: 1                         | dog: 3            |
| cat: 1<br />cat: 1<br />cat: 1<br />cat: 1<br />cat: 1 | cat: 5            |
| bird: 1<br />...<br />bird: 1<br />bird: 1             | bird: 8           |
| rabbit: 1<br />rabbit: 1<br />rabbit: 1                | rabbit: 3         |

### Task 2

* (a) **I**
    1. Client break the file into blocks
    2. Client retrieve pipeline from node 1 (pipeline: node2 -> node3)
    3. block forward to node2 (through Switch1 -> Switch4 -> Switch2) and node2 writes the block to DN
    4. block forwards to node3 (through Switch 2) and node3 writes the block to DN
    5. after successful write, node3 send signalto node2, node2 send signal to node1, node1 completes.

* (a) **II**

    1. **Slow workers**: 

        Issue: slow worker may lengthen the whole completion time

        Solution: redundant execution, complete when the fastest worker completes 

    2. **Locality Optimization**

        Issue: blocks of files forwarding is constrained by network

        Solution: Map block replica on same machine or same rack

* (b)

    Suppose network failures happen

    1. C+P: Cancel the operation fails in node1 or node2 or node4.
    2. A+P: If network fails between Switch4 and Switch5, proceed with operation on node1 and node2, cancel operation on node4.

## Q2

### Task 1

$$N_{out}(Node 1) = 3$$

$$N_{out}(Node 2) = 2$$

$$N_{out}(Node 3) = 2$$

$$N_{out}(Node 4) = 1$$

### Task 2

**Init:**

$PR_1^{(0)} = PR_2^{(0)} = PR_3^{(0)} = PR_4^{(0)} = PR_5^{(0)} = PR_6^{(0)} = 0.167$

**Iter 1:**

$PR^{(1)}_1 = \frac{1-0.8}{6} + 0.8 \left(\frac{PR^{(0)}_4}{1} \right )=0.167$

$PR^{(1)}_2 = \frac{1-0.8}{6} + 0.8 \left(0 \right )=0.033$

$PR^{(1)}_3 = \frac{1-0.8}{6} + 0.8 \left(\frac{PR^{(0)}_1}{3} \right )=0.078$

$PR^{(1)}_4 = \frac{1-0.8}{6} + 0.8 \left( \frac{PR^{(0)}_3}{2} + \frac{PR^{(0)}_6}{1} \right )=0.234$

$PR^{(1)}_5 = \frac{1-0.8}{6} + 0.8 \left( \frac{PR^{(0)}_1}{3} + \frac{PR^{(0)}_2}{2}  + \frac{PR^{(0)}_3}{2} \right )=0.212$

$PR^{(1)}_6 = \frac{1-0.8}{6} + 0.8 \left( \frac{PR^{(0)}_1}{3} + \frac{PR^{(0)}_2}{2} + \frac{PR^{(0)}_5}{1}\right )=0.279$

**Iter 2:**

$PR^{(2)}_1 = \frac{1-0.8}{6} + 0.8 \left(\frac{PR^{(1)}_4}{1} \right )=0.220$

$PR^{(2)}_2 = \frac{1-0.8}{6} + 0.8 \left(0 \right )=0.033$

$PR^{(2)}_3 = \frac{1-0.8}{6} + 0.8 \left(\frac{PR^{(1)}_1}{3} \right )=0.078$

$PR^{(2)}_4 = \frac{1-0.8}{6} + 0.8 \left( \frac{PR^{(1)}_3}{2} + \frac{PR^{(1)}_6}{1} \right )=0.287$

$PR^{(2)}_5 = \frac{1-0.8}{6} + 0.8 \left( \frac{PR^{(1)}_1}{3} + \frac{PR^{(1)}_2}{2}  + \frac{PR^{(1)}_3}{2} \right )=0.123$

$PR^{(2)}_6 = \frac{1-0.8}{6} + 0.8 \left( \frac{PR^{(1)}_1}{3} + \frac{PR^{(1)}_2}{2} + \frac{PR^{(1)}_5}{1}\right )=0.261$

**Iter 3:**

$PR^{(3)}_1 = \frac{1-0.8}{6} + 0.8 \left(\frac{PR^{(2)}_4}{1} \right )=0.263$

$PR^{(3)}_2 = \frac{1-0.8}{6} + 0.8 \left(0 \right )=0.033$

$PR^{(3)}_3 = \frac{1-0.8}{6} + 0.8 \left(\frac{PR^{(2)}_1}{3} \right )=0.091$

$PR^{(3)}_4 = \frac{1-0.8}{6} + 0.8 \left( \frac{PR^{(2)}_3}{2} + \frac{PR^{(2)}_6}{1} \right )=0.273$

$PR^{(3)}_5 = \frac{1-0.8}{6} + 0.8 \left( \frac{PR^{(2)}_1}{3} + \frac{PR^{(2)}_2}{2}  + \frac{PR^{(2)}_3}{2} \right )=0.136$

$PR^{(3)}_6 = \frac{1-0.8}{6} + 0.8 \left( \frac{PR^{(2)}_1}{3} + \frac{PR^{(2)}_2}{2} + \frac{PR^{(2)}_5}{1}\right )=0.213$

## Q3

## Task1

1. 

$W^{(1)} =  \begin{bmatrix} w_{11}^{(1)} & w_{12}^{(1)} \\ w_{21}^{(1)} & w_{22}^{(1)} \\ w_{31}^{(1)} & w_{32}^{(1)} \end{bmatrix} = \begin{bmatrix} -0.4 & -0.3 \\ 0.4 & -0.5 \\ -0.1 & -0.3 \end{bmatrix}$

$\mathbf{b}^{(1)} =  \begin{bmatrix} w_{10}^{(1)} \\ w_{20}^{(1)} \\ w_{30}^{(1)} \end{bmatrix} = \begin{bmatrix} 1 \\ 1 \\ 1 \end{bmatrix}$

$\mathbf{z}^{(1)} = W^{(1)} \mathbf{x} + \mathbf{b}^{(1)}$

$W^{(2)} =  \begin{bmatrix} w_{11}^{(2)} & w_{12}^{(2)} & w_{13}^{(2)} \\ w_{21}^{(2)} & w_{22}^{(2)} & w_{23}^{(2)} \end{bmatrix} = \begin{bmatrix} -0.2 & 0.8 & 0.3 \\ 0.2 & 0.7 & -0.2 \end{bmatrix}$

$\mathbf{b}^{(2)} =  \begin{bmatrix} w_{10}^{(2)} \\ w_{20}^{(2)} \end{bmatrix} = \begin{bmatrix} 1 \\ 1 \end{bmatrix}$

$\mathbf{z}^{(2)} = W^{(2)} \mathbf{a}^{(1)} + \mathbf{b}^{(2)}$

$\mathbf{p} = \begin{bmatrix} 1.710 \\ 1.521 \end{bmatrix}$

2. 

$L(\mathbf{y}, \mathbf{p}) = \frac{1}{2} \left[ (p_1 - y_1)^2 + (p_2 - y_2)^2 \right]=1.598$

3. 

$$
\frac{\partial L}{\partial W^{(2)}} = \frac{\partial L}{\partial z^{(2)}} \cdot a^{(1)T} = \begin{bmatrix} 1.146 & 1.317 & 1.300 \\ 0.349 & 0.401 & 0.396 \end{bmatrix}
$$

$$
\frac{\partial L}{\partial b^{(2)}} = \frac{\partial L}{\partial z^{(2)}} \cdot 1 = \begin{bmatrix} 1.710 \\ 0.521 \end{bmatrix}
$$

4. 

$w \leftarrow w - \alpha \cdot \frac{\partial L}{\partial w}$

$W^{(2)} = \begin{bmatrix} -0.315 & 0.668 & 0.170 \\ 0.165 & 0.660 & -0.240 \end{bmatrix}$

$\mathbf{b}^{(2)}= \begin{bmatrix} 0.829 \\ 0.948 \end{bmatrix}$

## Task 2

**Maximize**

First term is the probability of discriminating the real image as real. Second term is the probability of discriminating the generalized image as fake. So that we need to maximize these 2 terms, and it leads to real discrimination. 
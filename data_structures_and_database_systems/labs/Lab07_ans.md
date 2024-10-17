## 1

$$
TEMP <-  EMPLOYEE \  >< \  DEPARTMENT \\

\prod_{EName} \sigma_{\{DName == "COMP"\}} \{TEMP\}
$$


$$
A \ <- \ \sigma_{\{PName ==  "ProductX"\}} \{PROJECT\} \\

B \ <- \ \sigma_{\{DName=="COMP"\}} \{DEPARTMENT\} \\ 

\prod_{EName}\{A \bowtie WORK\_ON \bowtie EMPLOYEE \bowtie B\}
$$



## 2

color is red and cost<100  --> sname

retrieve sname under condition color is red and cost<100

## 3


$$
A \ <- \ \sigma_{\{Library\_name=="Sharpstown"\}}\{LIBRARY\} \\ 

B \ <-  \ \sigma_{\{title=="The Lost Tribe"\}}\{BOOK\} \\

G_{sum \{ No\_of\_copies\}}  \{A \bowtie BOOK\_COPIES \bowtie B\}
$$

------------------------

$$
\prod_{Name} \{ \\
\prod_{Card\_no, Name}\{BORROWER\}\  - \
\prod_{Card\_no,Name}\{BORROWER \bowtie BOOK\_LOANS \} \\
\}
$$


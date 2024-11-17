--Solution for Q2(b1)
CREATE OR REPLACE VIEW available_row_table AS 
SELECT No, CAST(FLOOR(No/20) AS integer) row_name 
FROM reservation WHERE available='Y';

--Solution for Q2(b2)
CREATE OR REPLACE VIEW na_row_table AS 
SELECT No, CAST(FLOOR(No/20) AS integer) row_name 
FROM reservation WHERE available='N';

--Solution for Q2(c)
CREATE OR REPLACE VIEW min_available_table AS 
SELECT available_num, min_available_num, row_name
FROM (
(
--find the nearest not available position in a row, if there exists the minimum available position 
    SELECT a.No AS available_num, na.No + 1 AS min_available_num, a.row_name
    FROM available_row_table a, na_row_table na
	WHERE a.row_name = na.row_name AND a.No > na.No AND na.No >= ALL (
        SELECT No FROM na_row_table WHERE row_name = na.row_name AND a.No > No
	) 
) union (
--find the nearest not availble position in a row, if not exists the minimum available position
    SELECT a.No AS available_num, a.row_name * 20 AS min_available_num, a.row_name
    FROM available_row_table a
    WHERE a.No < ALL (
        SELECT No FROM na_row_table WHERE row_name = a.row_name
	) 
)
) ORDER BY row_name, available_num;

--Solution for Q2(d)
CREATE OR REPLACE VIEW block_table AS 
SELECT min_available_num first_available, MAX(available_num) last_available, row_name 
FROM min_available_table GROUP BY min_available_num, row_name ORDER BY row_name;

--Solution for Q2(e)
SELECT b.first_available "First", b.last_available "Last", b.row_name "Row" 
FROM block_table b, (
	SELECT row_name, MAX(last_available - first_available) max_block 
	FROM block_table GROUP BY row_name
) a 
WHERE b.row_name = a.row_name AND last_available - first_available = max_block
ORDER BY "Row", "First";


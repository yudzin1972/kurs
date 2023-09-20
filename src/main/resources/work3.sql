# 4.	Показать всех авторов и количество экземпляров книг по каждому автору.
SELECT 
`a_name`,
SUM(`b_quantity`) as `books_sum`
FROM `authors`
JOIN `m2m_books_authors` USING (`a_id`)
JOIN `books` USING (`b_id`)
GROUP BY `a_id`;

# 5.	Показать всех читателей, не вернувших книги, и количество невозвращённых книг по каждому такому читателю.
SELECT `s_id`, `s_name`, COUNT(`sb_is_active`) as `book(s) count`
FROM `subscribers`
JOIN subscriptions  on `sb_subscriber`=`s_id`
WHERE `sb_is_active`="Y"
GROUP BY `s_id`;

# 6.	Показать медиану читаемости жанров, т.е. медианное значение от того, сколько раз читатели брали книги каждого жанра.

WITH `authors_popularity` AS
(
SELECT COUNT(`sb_book`) AS `books`,
ROW_NUMBER() OVER (ORDER BY COUNT(`sb_book`) ASC) AS `row_number`
FROM `genres`
JOIN `m2m_books_genres` USING (`g_id`)
LEFT OUTER JOIN `subscriptions`
ON `m2m_books_genres`.`b_id` = `sb_book`
GROUP BY `g_id`
)
SELECT AVG(`books`) AS `med_reading`
FROM `authors_popularity`,
(SELECT COUNT(*) AS `row_count`
FROM `authors_popularity`) AS `total_rows`
WHERE `row_number` IN ( FLOOR(( `row_count` + 1 ) / 2),
FLOOR(( `row_count` + 2 ) / 2) );

# 7.	Показать последнюю книгу, которую каждый из читателей взял в библиотеке.

SELECT `s_id`,`s_name`, `b_name`, max(`sb_start`)
FROM `subscriptions`
JOIN `subscribers` ON `s_id`=`sb_subscriber`
JOIN `books` ON `b_id`=`sb_book`
GROUP BY `sb_subscriber`
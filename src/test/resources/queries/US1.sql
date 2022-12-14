select count(id) as 'number of users' from users;

select * from users;

select count(*) from book_borrow
where is_returned = 0;

select name from book_categories;
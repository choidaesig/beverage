show databases;

CREATE Database beverage;

use beverage;

show tables;

SELECT * FROM beverage;

CREATE table beverage(
id int(10) not null auto_increment primary key,
name varchar(255),
price int(10),
amount int(10),
img varchar(255)
) engine = innoDB default charset = utf8;

INSERT into beverage(name,price, amount)
values('코카콜라',1000,5)

INSERT into beverage(name,price, amount, img)
values('사이다',1200,4,'https://shop3.daumcdn.net/thumb/R500x500/?fname=http%3A%2F%2Fshop3.daumcdn.net%2Fshophow%2Fp%2FZ234999812.jpg%3Fut%3D20210817230431')

INSERT into beverage(name,price, amount,img)
values('우유',2000,10,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3pJrncV1E_J7bXptM-es3I8p8DyeeWMWbyQ&usqp=CAU');

select * from beverage where 1 AND id like 4

UPDATE beverage Set img = 'http://img.danawa.com/prod_img/500000/492/722/img/1722492_1.jpg?shrink=330:330&_v=20200819161846' WHERE id = 4;

Delete from beverage WHERE id = 7; 

INSERT into beverage(name,price, amount, img)
values('밀키스',900,4,'https://company.lottechilsung.co.kr/common/images/product_view0104_bh3.jpg')



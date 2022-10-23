-- 데이터베이스 생성
create database yanolja;

use yanolja; 

-- 회원관리
create table userInfo(
	userNo int primary key auto_increment, 
    id varchar (20) unique,
    password varchar(20) not null, 
    userName varchar(20), 
    userPhoneNumber varchar(30) not null, 
    userYear varchar(20) not null 
);

select * from userInfo;





select u.userName, u.userPhoneNumber, h.hotelNo, h.hotelName,h.address,h.telphone
from userInfo as u
join hotel as h
on u.userNo = h.hotelNo
where u.userName = '조지훈';

create table hotel( -- 숙소
	hotelNo int primary key auto_increment, -- 숙소번호
    hotelName varchar(20) not null, -- 숙소이름
    address varchar(30) not null, -- 주소
	telPhone varchar(30), -- 전화번호
    image varchar(30) 
);

select * from hotel;
select * from room;

select * from userInfo;
delete from userInfo;






select * from hotel; -- 숙소 전체 조회


-- 리뷰 
create table review(
	reviewNo int primary key auto_increment, -- 리뷰번호
	userNo int not null, -- 회원번호
    hotelNo int not null, -- 숙소번호
    roomNo int not null, -- 방번호
    content varchar(300), -- 리뷰내용
    rating int, -- 평점
    foreign key (userNO) references userInfo(userNo),
    foreign key (hotelNo) references hotel(hotelNo)
);

 insert into review values(0,1,1,1,'좋아요',5);





create table room(
	roomId int primary key auto_increment, -- 방ID
    roomNo int not null,  -- 호실 
	hotelNo int not null, -- 숙소번호
    price int not null, -- 가격
    foreign key (hotelNo) references hotel(hotelNo) cascade 
);



select * from room;

create table reservation( -- 예약
	reservationNumber int unique key auto_increment,
	roomNo int not null,  -- 호실 
	hotelNo int not null, -- 숙소번호
    userNo int not null, -- 회원정보
    checkinDate date,
    foreign key (userNo) references userInfo(userNo),
	primary key (roomNo, hotelNo)
);

select * from reservation;

create table reservationList( -- 예약목록
	roomId int not null, -- 방ID
    userNo int not null, -- 회원번호
    checkInDate timestamp not null, -- 입실일
    reservationNumber int not null auto_increment, -- 예약번호
    foreign key (reservationNumber) references reservation(reservationNumber),
    foreign key (roomId) references room(roomId)
);

select * from reservationList;






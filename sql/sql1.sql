z-- 회원가입하기
insert into userInfo values(0,'whwlgns','12345','조지훈','010-1111-2222','1994-06-18');

-- 모든 회원 조회하기
select *
from userInfo; 

-- 아이디로 예약현황 조회하기
select u.userNo, u.id, u.userName,h.hotelNo,h.hotelName,h.telPhone
from userInfo as u
join reservation as r
on u.userNo = r.userNo
join hotel as h
on r.hotelNo = h.hotelNo
where u.id = 'whwlgns';

-- 숙소 정보 저장하기
insert into hotel values -- 숙소 정보
(0,'오렌지(Orange)','해운대구', '010-1111-3333');

select * from hotel;
select * from room;

-- 방 정보 저장하기
insert into reservation values(0, 201, 1, 2);



select * from hotel
right outer join room 
on hotel.hotelNo = room.hotelno;


-- 리뷰 쓰기(작성중)


-- 호텔 번호 호텔이름  보유방의 수 총 예약수


-- " select r.roomId, roomNo, h.hotelName, r.price " + "from hotel as h " + "join room as r "
			+ "on h.hotelNo = r.hotelNo " + "where h.hotelNo = ? limit 1 ";
                
                
                
                


use yanolja;
select * from hotel;
select * from userInfo;
select * from reservation;
select * from room;
;

select * from hotel as h
join room as r
on h.hotelNo = r.hotelNO
join reservation as rs
on h.hotelNo = rs.hotelNo
where h.hotelName like "%오렌지%";
(select count(roomNo) from room as r join hotel as h on h.hotelNo = r.hotelNO where h.hotelNo = 3)as roomcount


select h.hotelNo, h.hotelName, count(reservationNumber)as rscount 
from reservation rs
join hotel h
on h.hotelNo = hotelNO
where h.hotelNo = 3;

select * from hotel h
join reservation rs 
on h.hotelNo = rs.hotelNo;


select count(*) as "방의 수" from room
group by hotelNo;

select h.hotelNo, h.hotelName, (select count(*)from room
group by hotelNo) as "방의 수
from hotel";


select hotelNo, hotelName 
from hotel
where

select 




on h.hotelNo = rs.hotelNo;





select * from hotel;
select * from room;
select h.hotelNo, h.hotelName, count(r.roomNo)as room, count(rs.roomNo)as "예약수" from hotel as h
join room as r
on h.hotelNo = r.hotelNO
join reservation as rs
on h.hotelNo = rs.hotelNo
where h.hotelName like "%오렌지%"
group by h.hotelNo;

-- 아이디로 리뷰 조회하기
select  u.id, u.userName, rv.content
from userInfo as u
join review as rv
on u.userNo = rv.userNo
where u.id = 'whwlgns';
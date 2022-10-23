insert into hotel values -- 숙소 데이터 입력
(0,'서면 짝','진구', '010-1111-3333', 'images/hotel1.png'),(0,'수영 스미스호텔','수영구', '010-1111-4444', 'images/hotel2.png'),
(0,'초량 브라운도트','동구', '010-1111-5555', 'images/hotel3.png'),(0,'광안 Stay hotel','수영구', '010-1111-6666', 'images/hotel4.png'),
(0,'광안 오션뷰 호텔','수영구', '010-1111-7777', 'images/hotel5.png'),(0,'신라스테이 서부산','강서구', '010-1111-8888', 'images/hotel6.png'),
(0,'해운드 하운드호텔','해운대구', '010-1111-9999', 'images/hotel7.png'),(0,'서면 라이온호텔','진구', '010-2222-2222', 'images/hotel8.png')
;
select * from hotel;

-- 유저 데이터 입력
insert into userInfo values(0,'whwlgns','12345','조지훈','010-1111-2222','1994-06-18'),
(0,'cjsqudwo','123451','천병재','010-2222-2222','1992-08-07'),
(0,'rlarudals','123452','김경민','010-3333-2222','1989-06-12'),
(0,'rkdtjdqls','123453','강성빈','010-4444-2222','1997-03-18'),
(0,'dlwldms','1234533','이지은','010-9999-2222','1996-07-05');

delete from room
where roomid = 1;
select * from room;
select * from userInfo;

update room set dayPrice = ? where roomId = ? 




desc userInfo;
-- 숙소 방 데이터 입력
insert into room values(0, 101, 1, 85000),
(0, 102, 1, 100000),
(0, 101, 2, 90000),
(0, 201, 2, 110000),
(0, 101, 3, 120000),
(0, 102, 3, 125000),
(0, 101, 4, 7000),
(0, 101, 5,  80000),
(0, 101, 6, 90000),
(0, 101, 7, 90000), (0, 101, 8, 90000);

select * from room;
update room set roomNo = 202 where roomId = 1;
select * from userInfo where userNo = 1; 
select *from hotel;
-- 예약list 데이터 입력

-- 예약 데이터 입력
insert into reservation(roomNo, hotelNo, userNo, checkInDate) values(102, 2, 1, now() ),(101, 2,2,now()),(201,3,3,now())
,(101,4,5,now()),(102,5,2,now()),(101,6,1,now()),(101,7,3,now()),(101,8,2,now());

select * from reservation;

 insert into review values(0,1,1,101, "자주 이용하는 숙소입니다.
 친절하시고 편안합니다.", 5),(0,1,3,102,"화장실이 넓고, TV로 유튜브 시청도 가능하여 잘 이용하였습니다.", 3),(0,1,5,102,"3층 묶었던 침대가 한쪽이 다 꺼져서 허리가 진짜 아팠어요 
 매트리스 점검이 필요해보여요 나머진 괜찮았어요", 2);
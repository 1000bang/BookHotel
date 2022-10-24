-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: yanolja
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel` (
  `hotelNo` int NOT NULL AUTO_INCREMENT,
  `hotelName` varchar(20) NOT NULL,
  `address` varchar(30) NOT NULL,
  `telPhone` varchar(30) DEFAULT NULL,
  `image` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`hotelNo`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'서면 짝','진구','010-1111-3333','images/hotel1.png'),(2,'수영 스미스호텔','수영구','010-1111-4444','images/hotel2.png'),(3,'초량 브라운도트','동구','010-1111-5555','images/hotel3.png'),(4,'광안 Stay hotel','수영구','010-1111-6666','images/hotel4.png'),(5,'광안 오션뷰 호텔','수영구','010-1111-7777','images/hotel5.png'),(6,'신라스테이 서부산','강서구','010-1111-8888','images/hotel6.png'),(7,'해운드 하운드호텔','해운대구','010-1111-9999','images/hotel7.png'),(8,'서면 라이온호텔','진구','010-2222-2222','images/hotel8.png');
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `reservationNumber` int NOT NULL AUTO_INCREMENT,
  `roomNo` int NOT NULL,
  `hotelNo` int NOT NULL,
  `userNo` int NOT NULL,
  `checkinDate` date NOT NULL,
  PRIMARY KEY (`roomNo`,`hotelNo`),
  UNIQUE KEY `reservationNumber` (`reservationNumber`),
  KEY `userNo` (`userNo`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`userNo`) REFERENCES `userinfo` (`userNo`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (2,101,2,2,'2022-10-24'),(3,101,3,3,'2022-10-24'),(7,101,4,5,'2022-10-24'),(11,101,5,5,'2022-10-24'),(16,101,6,1,'2022-10-24'),(21,101,7,3,'2022-10-24'),(25,101,8,2,'2022-10-24'),(1,102,2,1,'2022-10-24'),(4,201,3,3,'2022-10-24'),(8,201,4,5,'2022-10-24'),(12,201,5,5,'2022-10-24'),(17,201,6,1,'2022-10-24'),(22,201,7,3,'2022-10-24'),(26,201,8,2,'2022-10-24'),(5,301,3,3,'2022-10-24'),(9,301,4,5,'2022-10-24'),(13,301,5,5,'2022-10-24'),(18,301,6,1,'2022-10-24'),(23,301,7,3,'2022-10-24'),(27,301,8,2,'2022-10-24'),(6,401,3,3,'2022-10-24'),(10,401,4,5,'2022-10-24'),(14,401,5,5,'2022-10-24'),(19,401,6,1,'2022-10-24'),(24,401,7,3,'2022-10-24'),(28,401,8,2,'2022-10-24'),(15,501,5,2,'2022-10-24'),(20,501,6,1,'2022-10-24'),(29,501,8,2,'2022-10-24');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `reviewNo` int NOT NULL AUTO_INCREMENT,
  `userNo` int NOT NULL,
  `hotelNo` int NOT NULL,
  `roomNo` int NOT NULL,
  `content` varchar(700) DEFAULT NULL,
  `rating` int DEFAULT NULL,
  PRIMARY KEY (`reviewNo`),
  KEY `userNo` (`userNo`),
  KEY `hotelNo` (`hotelNo`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`userNo`) REFERENCES `userinfo` (`userNo`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`hotelNo`) REFERENCES `hotel` (`hotelNo`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,1,1,101,'전동 휠체어를 사용하는 장애인이라 \n장애인객실을 이용했습니다. \n방 자체는 쾌적하고 넓은 편이였지만 불이 좀\n어둡습니다. \n문제는 화장실이였는데 장애인 편의성을 고려하지\n 않은 디자인이였습니다. \n세면대가 크고 두껍고 넓어서 수동이든 진동이든\n 휠제어 사용자는 물을 틀기도 힘들고 높습니다. \n그리고 샤워실로 가는데 세면대와 변기 사이가\n 좁아서 휠체어로 들어갈 수가 없습니다.\n 그래서 혼자 사용할 수가 없어 도움 받아야합니다.\n 그리고 화장실 자체도 좁아 회전하고 \n 움직임도 자유롭지 않으며 샤워 도구도 높이 있어\n 꺼내기가 힘듭니다. 고려하지 못한 부분이 \n 많아 아쉬웠고 다음에 이용하진 않을\n 것 같습니다.',5),(2,2,2,201,'도착하자마자 ..응???방온도\n 25도에서 30분뒤 28도 28.5도..에어컨이\n 왜그러지?\n 딱히 뷰라고 할께없어서 저층줬어도괜찮았음\n 다른층없냐고했더니 프론트 키작은남자분이 \n 다른층없다함\n 3시되기 4분전에 체크인..그러려니했는데 \n 방이후덥.. 방초이스잘못한건가싶었고\n 비가와서그런지 꿉꿉해서 더예민해졌고..\n 우와...선풍기가져다 주신분은 진짜친절하시고\n 그래도안될까봐\n 에어컨점검도 보내주셨음 위에천장까더니 필터빼고\n 새걸로교체..새필터가 검정이던데 위에서\n 뺀 필터는\n 먼지진짜 꽉찬회색임 ..먼지가 공중에 \n 떠다니고 난리 난리..필터바꾸시고 내려오시더니\n 온도측정하는레이저라시고 몇번쏘더니\n 나한테 바뀐온도보여줄려고 하셨는데\n 결국 잘안됨ㅋㅋ온도변화없다니깐\n 에어컨조작하는벽에붙은거 가르키면서 벽온도라서\n ..벽온도가 좀높아서 그럴수있다고하심ㅋ 엥?\n ?실제온도는 다르다하심..네네 그러나 진짜\n 진짜 친절 점검하신 두분에게감사.\n 감사한마음으로 \n 문밖배웅에 나가실동안 문 잡고있었음ㅋㅋ\n 결론은 선풍기가 최고다!!!\n 직접적인 맛이있어서 ..\n 방온도에 필터먼지에 많은일이있었지만\n 구로디지털단지역\n 1층에 튀김집 괜찮더라..',5),(3,3,3,301,'생각보다 사진보다\n 더 좋아서 놀랬어요~~\n쾌적하고 깨끗한게 좋았고\n수영장 시설도 깔끔해서\n 좋았어요ㅎㅎ',5),(4,4,4,401,'오래된 호텔인듯하지만\n 4성급다운 좋은호텔이었어요 깨끗하고\n 욕조도 넓고 침대도 편안해서\n 잘쉬고 왔습니다.',5),(5,5,1,101,'화장실이 넓고, TV로 유튜브\n 시청도 가능하여 잘 이용하였습니다.',3),(6,6,2,101,'깔끔하고 좋았어요.\n급 예약해서 저렴한 느낌은 아니었어요.\n조식도 따로 추가하고.\n침구류 깨끗하고 방은 아늑하고\n 포근했어요.\n33개월 아이랑 더블 썼을 때\n 불편함은 없었어요.\n성인한명 따로 자고 한명은\n 아이랑 잤어요.\n집 아닌데도 구스 베개 포근해서 아이가\n 잘 잔것 같아요. 집에도 하나 장만하고싶네요.\n만족할 만한 별 4개짜리 컨디션 입니다.\n근처 볼일 있으신 분들 깔끔한방 원하시면\n 괜찮을듯 합니다.\n위치가 조금 아쉬웠는데 그래도 다시가고싶은\n 좋은 추억이었어요..',5),(7,7,5,101,'베개에서 냄새가 난거랑,\n 기본 카페트에 좀 오래된 냄새가 밴거 빼고는\n 괜찮았어요. \n침구는 편안했으나... 베개 새탁은 꼭 한것을\n 넣어주셨으면 좋겠어요ㅠ\n(트윈룸에 두개다 머리 냄새 났어요 ㅜㅠ)\n 그외에는 청담 접근성 좋아서 좋았습니다',2),(8,8,8,101,'노후 된 호텔이지만 나름\n 4성급이라 서비는 좋았고 위치가 너무 괜찮았어요\n직원분들 대부분 친절했는데 검정단발머리\n여자분은 말투나 표정,서비스가 안좋았고 나이 좀\n 있으신 남자 직원분은 프론트에서 손님들도\n 다 보고 있는데 \n직원들을 큰소리로 혼내는 모습이 불쾌하고\n 보기 안좋았어요',1),(9,1,2,301,'3층 묶었던 침대가 한쪽이 다\n 꺼져서 허리가 진짜 아팠어요\n 매트리스 점검이 필요해보여요 나머진 괜찮았어요',2),(10,2,1,301,'외부 공조기 소리인듯 한데 너무너무\n 시끄러웠습니다. 잠을 잘 수가 없었네요. \n어플로 할인예약 했다고 그런 방 준듯한데 다시는 안갑니다. \n건물 겉만 새건물 처럼 보이지, \n내부는 낡은 옛 건물 그대로임.',2),(11,3,4,301,'정말 최악이였습니다. Day Use 이용했고,\n 넷플릭스 사용 가능한 방으로 예약했습니다.\n 하지만 넷플릭스를 포함한 스트리밍 서비스가\n 연결되지 않았으며, 이를 고치기 위해서 \n 4명의 관계자 분들께서 한시간 반을 방에\n 왔다갔다하시기를 반복하며 같이 있었습니다. \n 결국은 룸 체인지를 해주실 수 밖에 없으셨고,\n 이 과정에서 저희가 피해 본 시간과 \n 피로도에 대한 사과의 말씀은\n 한마디도 없으셨습니다. \n 오히려 본인들 잘못이 아니라는 둥 핑계만\n 대시더군요. 당연히 사사로운 문제들이 일어날\n 수는 있다고 생각합니다. 하지만 비용을 지불하고 간건데, \n 저희가 받은 피해에 대해서 미안한 기색조차 없으셨던게 \n 그 상황을 더욱 불쾌해지게 만들었습니다.\n추후에 사과 요청을 드렸더니, 저희를 응대해주셨던\n 분이 높으신 분이신지는 모르겠으나 \n그분이 아닌, 전혀 관련 없는 프론트 담당자 분께서\n 전화 오셔서 사과하시더라구요..\n 더욱 마음이 안좋았습니다. 신라스테이라는 네임밸류\n 때문에 힐링하러 방문한거였는데 \n 정말 실망스러웠습니다.',2),(12,4,5,301,'위치가 딱 좋았어요! 번화가랑 도보 5분?\n 정도 거리라 편하게 갈 수 있어서 좋았고\n호텔 자체는 조용한 거리에 있어서 편히 쉴 수 있었어요\n 내부 청결 같은 것도 말할 것 없이 좋았습니당',2),(13,5,3,101,'10.5-6 24시간 이용: 체크아웃\n 하는날 시간대에 온수 공사를\n 한다는 공지를 공식홈페이지에서만 공지를 해놓고\n 야놀자 등 타 플랫폼으로\n 예약한 고객들에게 개별공지는 없었음. 체크인시\n 현장에서만 안내를 함.\n 이미 그때가선 환불이니 수수료니 고객 몫임.\n 당연 체크아웃때 세안, 샤워 제대로 하지 못함.\n체크인 당시 배정받은 객실 문이 제대로 열리지 않고\n 하자점이 있어 데스크에 요청 했더니\n 그냥 쎄게 밀어서 열으라는 퉁명스러운 해결방안만 제시함.\n또한 24시간 이용이라 여유롭게 즐기고 가려했는데\n 아침 댓바람 부터 청소 하시는 분들의 요란한 청소\n 소리에 깨고 늦잠을 제대로 잘수 없었음.\n 타 고객들 체크 아웃 시간대에 한참 소음 발생함.\n24시간 이용하려해 5일 15:00-부터\n 익일 15:00 이용이였으나 온수 공급 중단,\n 방음 불가, 소음 발생 등 여러 문제점들로\n 편안한 여행을 느끼지 못하게 된것 같은 생각이 듦.\n 금액은 금액대로 받고 3성급 호텔이라는\n 명성에 신라스테이가 고객\n 응대 상황 발생과 현재 이 설명으로 명성을 저하됨.\n체크아웃시 불만사항 제기 후 직원들의 사과요청과 추후 이용시 추가 혜택을 약속 받음.\n허나 추후 이용할 마음이 생기지 않음.\n물론 건물의 하자점들이나 상황은 직원들의 잘못이 아니나 \n대처방안과 고객의 응대하는 태도에 하자점이 있어보임.',1),(14,6,4,201,'예스러운 호텔 무드를 느끼기 좋음\n방이 넓고 인테리어가 좋음\n모텔을 많이 이용하는 젊은 사람들에게는 좀 불편할 수도 있음 그러나 부모님 모시기에는 좋은 것 같음\n오전에 체크아웃하는데 한 직원이 언성을 높이며 얘기해서(부하직원이 상사한테 혼나는 분위기였음\n 보기가 좀 불편했음',3),(15,7,3,401,'너무 만족합니다~ 신라 스테이는 라이트 퇴실이 있어서 정말 너무 만족해요 ㅠㅠ\n여유있게 있을수 있었고 서비스도 정말 만족합니다~\n미니바 간식도 다양했어요! 이번에 스트리밍도 가능해서 더 좋았네요\n 다음에도 또 이용하고 싶습니다~ 가격대비 가성비 정말 최고 입니다',5),(16,8,2,201,'직원분들 모두 친절하셔서 기분이 좋았는데 객실 상태가 좋은 편은 아니라서 실망했어요.\n 바로 옆 객실에서 문을 여는 소리가 너무 커서 깜짝 깜짝 놀랐고 테이블과 다리미는 닦지 않으셨는지\n 먼지가 쌓여 있었고 샤워 부스 바닥도 머리카락이 있어 찝찝했어요. 화장실 변기도 잘 안 내려가서 불편했어요 \n (엘리베이터 이용 시 카드 키를 찍어야 한다는 점 프런트에서 직원분들이 설명해주시면 당황하지 \n 않고 편히 이용할 수 있을 것 같아요 직원분들은 정말 친절하세요!)',5),(17,9,5,301,'구디역에서 가까워서 접근성은좋긴한데 방에 에어컨도안나오고 \n온도조절하는게있기는한데 온도가 고정되어있는지 18도로내려도 \n28도로 바로바뀌고 방이너무더웠어요 ㅠ\n마지막타임에 가서 그런가 조식도 별로먹을게 많지않고 직원분들이 없어서 그런지\n그릇도 빨리 안채워지고..\n조식이랑 에어컨은 좀개선하셔야할거같아요',3),(18,1,6,101,'이날 단체 손님이 있었나봐요. \n복도에서 너무 시끄럽게 돌아다니고\n 이문 저문 여닫고 다니는 팀 때문에 너무 힘들었습니다.\n 호텔에서 CCTV로다 보일텐데 아무런 제제 없었습니다.\n 일행중에 제가 있는방 벨을 여러차례 누르기도 했습니다.\n 시간은 11시. 호텔 체크인부터 친절함은 없습니다.\n 신라스테이 왜 이렇게 되었는지...\n 가성비 좋은 곳이었는데... 다시는 안갈듯 합니다.... \n 친구랑 둘이 거의 잠을 설쳐서\n 다음날 일하는데 너무 힘들었네요...',5),(19,2,8,401,'이번호 쓰기전부터 지금까지 \n20번이상 이용했을정도로 편의성때문에\n 자주이용했는데요\n이번엔 정말 모든면에서 최악이었습니다\n야놀자 어플상에서 예약이된거면 이미\n 문제가없다는 얘기아닌가요?\n예약후 30분정도후에 호텔측에서 전화가\n 오더니 시스템오류,예약직전 \n블락이걸려 예약하신방은 사용불가고\n 이용하시려면 만원을더내고 그 윗단계\n 객실로 이용을 하라는겁니다\n기분이나빠 취소를하겠다하니 야놀자측에\n 전화를하라길래 귀찮아서 그럼\n 기존에했던방말고레이트체크아웃방으로\n 바꾸고 추가요금 드린다했더니\n 3만원을 더 달라는겁니다\n 그래서 제가 평일기준\n 제가예약한방이134,000원인가 그랬고\n레이트체크아웃방이149,000원인가 그래서\n만오천원만 더드리면되는거아닌가요?\n라고했더니 아그러면 한단계높은방으로\n 만원만 더받고 해주시겠다는겁니다\n그것도 레이트도 아닌 한시간 더연장하는걸로요\n대체 시종일관 무슨말씀하시는지 모르겠고\n제가 잘못한게있나요?\n참고하세요',1),(20,3,5,201,'3성급호텔이라며 홍보하더니\n 정말 최악입니다. 모텔도 이렇게는 안하겠어요.\n체크인하고 키받아서 들어갔는데 청소가 하나도\n 안되어있고 사진처럼 쓰레기가 잔뜩이었습니다;\n; 프론트에 전화하니 관리자가 오셔서\n 사과하는것도 아니고 청소하시는 분께서 오시고, \n방바꿔달라니까 스위트룸은 우리방뿐이라\n 교환할 방이 없다해서 방에 청소 다 끝날때까지\n서서 기다리고 이후에는 이미 체크인\n 완료했는데 몇시에 오시냐고 전화가 오질않나,\n 이게 뭔가요??\n저희가 뭐 이벤트 이용해서 간게 아니라\n 다 돈내고 스탠다드도 아니라\n 스위트룸 결제한거였습니다.\n부모님 처음 모시고 호캉스 와봤는데 월풀스파\n 써져있는데 쓰려고 보니 안돼 물어보니까 고장났다하고,\n 가운입으니 구멍이 나있고 어이가 없더군요.\n정말 별로인 부분이 너무많은데\n 하나하나 쓰고있기도 화가 납니다.\n티비는 오류라고 나오지도 않고, 망가진 블라인드,\n 콘센트에 외관이보이는 뷰라더니 바로\n 앞이 공사장(이건 호텔측 잘못은 아니지만요)\n다신 가고싶지않았습니다.',2);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `roomId` int NOT NULL AUTO_INCREMENT,
  `roomNo` int NOT NULL,
  `hotelNo` int NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`roomId`),
  KEY `hotelNo` (`hotelNo`),
  CONSTRAINT `room_ibfk_1` FOREIGN KEY (`hotelNo`) REFERENCES `hotel` (`hotelNo`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,101,1,85000),(2,102,1,100000),(3,103,1,100000),(4,104,1,110000),(5,105,1,130000),(6,106,1,80000),(7,107,1,100000),(8,108,1,120000),(9,109,1,100000),(10,101,2,80000),(11,201,2,110000),(12,301,2,110000),(13,401,2,112000),(14,501,2,113000),(15,601,2,110000),(16,101,3,70000),(17,201,3,80000),(18,301,3,110000),(19,401,3,110000),(20,501,3,1300000),(21,602,3,120000),(22,101,4,127000),(23,201,4,127000),(24,301,4,70000),(25,401,4,60000),(26,501,5,100000),(27,101,6,80000),(28,201,6,100000),(29,301,6,100000),(30,401,6,100000),(31,101,7,100000),(32,201,7,120000),(33,301,7,120000),(34,401,7,110000),(35,501,7,110000),(36,601,7,110000),(37,101,8,130000),(38,201,8,130000),(39,301,8,130000),(40,401,8,60000),(41,501,8,60000);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userinfo` (
  `userNo` int NOT NULL AUTO_INCREMENT,
  `id` varchar(20) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `userPhoneNumber` varchar(30) NOT NULL,
  `userYear` varchar(20) NOT NULL,
  PRIMARY KEY (`userNo`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES (1,'whwlgns','1234','조지훈','010-1111-2222','1994-06-18'),(2,'cjsqudwo','1234','천병재','010-2222-2222','1992-08-07'),(3,'rlarudals','1234','김경민','010-3333-2222','1989-06-12'),(4,'rkdtjdqls','1234','강성빈','010-4444-2222','1997-03-18'),(5,'dltmddnjs','1234','이승원','010-9999-2222','1996-07-05'),(6,'이지은','1234','이지은','010-9999-6767','1992-07-05'),(7,'tjqhrud','1234','서보경','010-9999-5668','1936-07-05'),(8,'rlarmsgh','1234','김근호','010-9999-5656','1966-07-05'),(9,'rlawnsdlf','1234','김준일','010-9999-1212','1976-07-05'),(10,'rudalsclsrn','1234','경민친구','010-9999-4242','1926-07-05'),(11,'wlgnsclsrn','1234','지훈친구','010-9999-3232','1999-07-05'),(12,'master','1234','마스터','010-9999-6767','1992-07-05');
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-24 12:07:56

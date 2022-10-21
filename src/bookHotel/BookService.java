package bookHotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import bookHotel.Frame.LoginFrame;
import bookHotel.Frame.MainPageFrame;
import bookHotel.Frame.MasterFrame;
import bookHotel.Frame.RoomUpdateFrame;
import bookHotel.dto.ResponseInfo;
import bookHotel.dto.ResquestInfo;
import bookHotel.interfaces.IBookService;
import bookHotel.utils.DBHelper;
import lombok.Data;

@Data
public class BookService implements IBookService {

	private DBHelper dbHelper;
	private PreparedStatement psmt;
	private ResultSet rs;

	public BookService() {
		this.dbHelper = DBHelper.getInstance();

	}

	@Override
	public void selectLoginInfo(LoginFrame loginFrame) {

		String sql = "SELECT Id, password FROM userinfo where Id =   ?  and password = ? ";

		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, loginFrame.getId().getText());
			psmt.setString(2, loginFrame.getPw().getText());
			rs = psmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equals("master")) {
					new MasterFrame();
					loginFrame.dispose();
				} else {
					loginFrame.dispose();
					new MainPageFrame();
				}

			} else {
				JOptionPane.showMessageDialog(loginFrame, "일치하는 정보가 없습니다.");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				rs.close();
				psmt.close();
				dbHelper.connectionClose();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	public void closeAll() {
		try {
			rs.close();
			psmt.close();
			dbHelper.connectionClose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 회원가입
	@Override
	public void signIn(ResquestInfo req) {
		String signInSql = " insert into userInfo(id, password, userName, userPhoneNumber, userYear) "
				+ " values(?,?,?,?,?) ";

		try {
			psmt = dbHelper.getConnection().prepareStatement(signInSql);
			psmt.setString(1, req.getId());
			psmt.setString(2, req.getPassword());
			psmt.setString(3, req.getUserName());
			psmt.setString(4, req.getUserPhoneNumber());
			psmt.setString(5, req.getUserYear());
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Request : insert , update,
	// Response : select
	@Override // 유저 정보 전체 조회
	public List<ResponseInfo> selectAllUserInfo() {
		String sql = " 	select * from userinfo ";

		List<ResponseInfo> userList = new ArrayList<>();
		
		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			rs = psmt.executeQuery();
			
			int count = 0;
			while(rs.next()) {
				count++;
				ResponseInfo info = new ResponseInfo();
				info.setUserNo(rs.getString("userNo"));
				info.setId(rs.getString("id"));
				info.setPassword(rs.getString("password"));
				info.setUserName(rs.getString("userName"));
				info.setUserPhoneNumber(rs.getString("userPhoneNumber"));
				info.setUserYear(rs.getString("userYear"));
				userList.add(info);
			}
			System.out.println("count 확인 " + count);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		
		return userList;
		}

	@Override // 예약하기
	public void book(ResquestInfo req) {
		boolean flag = true;

		try {
			dbHelper.getConnection().setAutoCommit(false);

			// 마지막 예약번호 가져오기
			String query1 = " SELECT reservationNumber FROM reservation ORDER BY reservationNumber DESC LIMIT 1 ";
			rs = dbHelper.getConnection().prepareStatement(query1).executeQuery();
			while (rs.next()) {
				int lastNo = (rs.getInt("reservationNumber"));
				req.setReservationNumber(lastNo);
				System.out.println("예약 마지막 No" + lastNo);
			}
			// 마지막 유저번호 가져오기
//			String query2 = " SELECT userNo FROM userInfo ORDER BY userNo DESC LIMIT 1 ";
//			rs = dbHelper.getConnection().prepareStatement(query2).executeQuery();
//			while(rs.next()) {
//				int lastNo = (rs.getInt("userNo"));
//				req.setReservationNumber(lastNo);
//				System.out.println("회원 마지막 No" + lastNo);
//			}

			// 마지막 방Id 가져오기
//			String query3 = " SELECT roomId FROM room ORDER BY roomId DESC LIMIT 1 ";
//			rs = dbHelper.getConnection().prepareStatement(query3).executeQuery();
//			while(rs.next()) {
//				int lastNo = (rs.getInt("roomId"));
//				req.setReservationNumber(lastNo);
//				System.out.println("방 마지막 Id" + lastNo);
//			}

			// 예약자 등록하기
//			String query4 = "insert into reservation( hotelNo,roomNo, userNo, checkInDate ) values " + " ( ?, ?, ?, ? ) ";
//			psmt = dbHelper.getConnection().prepareStatement(query4);
//			psmt.setInt(1, req.getHotelNo());
//			psmt.setInt(2, req.getRoomNo());
//			psmt.setInt(3, req.getUserNo());
//			psmt.setformat(4, req.getCheckInDate());
//			psmt.executeUpdate();

			// 방 선택
//			String query5 = " insert into room( roomNo ) values( ? ) ";
//			psmt = dbHelper.getConnection().prepareStatement(query5);
//			psmt.setInt(1, req.getRoomNo());
//			psmt.executeUpdate();

			dbHelper.getConnection().commit(); // db에 반영 (테스트시 주석처리!!!)
			dbHelper.getConnection().setAutoCommit(true); // 원상복구
		} catch (SQLException e) {
			try {
				dbHelper.getConnection().rollback();
				System.out.println("롤백했습니다.");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	// 호텔 정보 저장하기 (트랜잭션)
	@Override
	public boolean insertHotelInfo(ResquestInfo req) {
		boolean flag = true;

		try {
			dbHelper.getConnection().setAutoCommit(false);

			// 숙소 마지막 번호 들고오기
			String hotelLastNoSql = " SELECT hotelNo FROM hotel ORDER BY hotelNo DESC LIMIT 1 ";
			rs = dbHelper.getConnection().prepareStatement(hotelLastNoSql).executeQuery();
			while (rs.next()) {
				int lastNo = (rs.getInt("hotelNo"));
				req.setHotelNo(lastNo);
				System.out.println("호텔 마지막 No" + lastNo);
			}

			// 숙소 정보 저장하기
			String hotelSql = "insert into hotel( hotelName, address, telPhone ) values " + " (?, ?, ?) ";
			psmt = dbHelper.getConnection().prepareStatement(hotelSql);
			psmt.setString(1, req.getHotelName());
			psmt.setString(2, req.getAddress());
			psmt.setString(3, req.getTelPhone());
			psmt.executeUpdate();

			String roomSql = " insert into room( roomNo, hotelNo, price) values( ?, ?, ?) ";

			// 방 정보 저장하기
			psmt = dbHelper.getConnection().prepareStatement(roomSql);
			psmt.setInt(1, req.getRoomNo());
			psmt.setInt(2, req.getHotelNo());
			psmt.setInt(3, req.getDayPrice());
			psmt.executeUpdate();

			dbHelper.getConnection().commit(); // db에 반영 (테스트시 주석처리!!!)
			dbHelper.getConnection().setAutoCommit(true); // 원상복구
		} catch (SQLException e) {
			try {
				dbHelper.getConnection().rollback();
				System.out.println("롤백했습니다.");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

		} finally {
			closeAll();
		}

		return flag;
	}

	// 호텔 이름 수정(한방 수정 만듬으로 인해 죽는 코드 확인후 삭제하기)
//	@Override
//	public boolean hotelNameUpdate(String oldHotelName, String changeName) {
//
//		boolean flag = false;
//
//		// 방어적 코드 실제 있는지 없는지
//		String query1 = " select count(hotelNo) from hotel where hotelName  = ? limit 1 "; // 있으면 1 없으면 0 반환 쿼리
//		int isNotEmpty = 0;
//		try {
//			dbHelper.getConnection().setAutoCommit(false);
//			psmt = dbHelper.getConnection().prepareStatement(query1);
//			psmt.setString(1, oldHotelName);
//
//			rs = psmt.executeQuery();
//			while (rs.next()) {
//				isNotEmpty = (rs.getInt("count(hotelNo)"));
//				// TODO remove
//				// System.out.println(" 1 있다 , 0 없다 :: " + isNotEmpty);
//			}
//
//			// 만약 0 이면 잘못딘 값을 요청했습니다 ..
//			if (isNotEmpty == 0) {
//				flag = false;
//			} else {
//				//
//				String query2 = " UPDATE hotel SET hotelName = ? WHERE hotelName = ? ";
//				psmt = dbHelper.getConnection().prepareStatement(query2);
//				psmt.setString(1, changeName);
//				psmt.setString(2, oldHotelName);
//				int rowCount = psmt.executeUpdate();
//				if (rowCount >= 1) {
//					flag = true;
//				} else {
//					flag = false;
//				}
//
//			}
//
//			dbHelper.getConnection().commit();
//			dbHelper.getConnection().setAutoCommit(true);
//
//		} catch (SQLException e) {
//			try {
//				dbHelper.getConnection().rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		} finally {
//			try {
//				rs.close();
//				psmt.close();
//				dbHelper.connectionClose();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//
//		}
//
//		return flag;
//	}

//	@Override(삭제)
//	public boolean hotelAddressUpdate(String hotelName, String changeAddress) {
//
//		return false;
//	}

	///

	@Override
	public void searchRoom(RoomUpdateFrame roomUpdateFrame) {

		String sql = "SELECT * FROM room where roomId = ? ";

		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, roomUpdateFrame.getRoomIdText().getText());
			rs = psmt.executeQuery();

			if (rs.next()) {
				roomUpdateFrame.getRoomNoText().setText(rs.getString("roomNo"));
				roomUpdateFrame.getHotelNoText().setText(rs.getString("hotelNo"));
				roomUpdateFrame.getDayPriceText().setText(rs.getString("dayPrice"));
				roomUpdateFrame.getNightPriceText().setText(rs.getString("nightPrice"));
			} else {
				JOptionPane.showMessageDialog(roomUpdateFrame, "일치하는 정보가 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}

	}

	// 방정보 한방 수정하기
	@Override
	public void updateRoom(String roomId, String newDayPrice, String newNightPrice, String newRoomNo) {

		try {
			dbHelper.getConnection().setAutoCommit(false);
			// 대실 가격 수정
			String query1 = "update room set dayPrice = ? where roomId = ?";
			psmt = dbHelper.getConnection().prepareStatement(query1);
			psmt.setString(1, newDayPrice);
			psmt.setString(2, roomId);
			psmt.executeUpdate();

			// 숙박가격 수정
			String query2 = "update room set nightPrice = ? where roomId = ?";
			psmt = dbHelper.getConnection().prepareStatement(query2);
			psmt.setString(1, newNightPrice);
			psmt.setString(2, roomId);
			psmt.executeUpdate();

			// 룸번호 수정
			String query3 = "update room set roomNo = ? where roomId = ?";
			psmt = dbHelper.getConnection().prepareStatement(query3);
			psmt.setString(1, newRoomNo);
			psmt.setString(2, roomId);
			psmt.executeUpdate();

			dbHelper.getConnection().commit(); // 실제 데이터 베이스에 반영
			dbHelper.getConnection().setAutoCommit(true); // 오토커밋 원상태로 돌려놓는 것을 권장

		} catch (SQLException e) {
			try {

				dbHelper.getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			closeAll();
		}

	}

	// 호텔 정보 한방 수정
	@Override
	public void updateHotel(String hotelNo, String newHotelName, String newAddress, String newTelPhone) {

		try {
			dbHelper.getConnection().setAutoCommit(false);

			// 호텔 이름 수정
			String query1 = " update hotel set hotelName = ?, Address = ?, telPhone = ? where hotelNo = ? ";
			psmt = dbHelper.getConnection().prepareStatement(query1);
			psmt.setString(1, newHotelName);
			psmt.setString(2, newAddress);
			psmt.setString(3, newTelPhone);
			psmt.setString(4, hotelNo);
			psmt.executeUpdate();

			// 호텔 주소 수정(코드를 줄일수 있음!!!!)
//			String query2 = " update hotel set Address = ? where hotelNo = ? ";
//			psmt = dbHelper.getConnection().prepareStatement(query2);
//			psmt.setString(1, newAddress);
//			psmt.setString(2, hotelNo);
//			psmt.executeUpdate();
//			
//			
//			// 호텔 전화번호 수정
//			String query3 = " update hotel set telPhone = ? where hotelNo = ? ";
//			psmt = dbHelper.getConnection().prepareStatement(query3);
//			psmt.setString(1, newTelPhone);
//			psmt.setString(2, hotelNo);
//			psmt.executeUpdate();

			dbHelper.getConnection().commit(); // 실제 데이터 베이스에 반영
			dbHelper.getConnection().setAutoCommit(true); // 오토커밋 원상태로 돌려놓는 것을 권장

		} catch (SQLException e) {
			try {
				dbHelper.getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	@Override // 호텔번호(Pk)로 모든 테이블 호텔 정보 삭제하기
	public void deleteHotel(int hotelNo) {

		boolean flag = true;
		try {
			dbHelper.getConnection().setAutoCommit(false);
			String query1 = " delete from reservation where hotelNo = ? "; // 예약
			psmt = dbHelper.getConnection().prepareStatement(query1);
			psmt.setInt(1, hotelNo);
			psmt.executeUpdate();

			String query2 = " delete from room where hotelNo = ? "; // 방
			psmt = dbHelper.getConnection().prepareStatement(query2);
			psmt.setInt(1, hotelNo);
			psmt.executeUpdate();

			String query3 = " delete from review where hotelNo = ? "; // 리뷰
			psmt = dbHelper.getConnection().prepareStatement(query3);
			psmt.setInt(1, hotelNo);
			psmt.executeUpdate();

			String query4 = " delete from hotel where hotelNo = ? "; // 호텔
			psmt = dbHelper.getConnection().prepareStatement(query4);
			psmt.setInt(1, hotelNo);
			psmt.executeUpdate();

			dbHelper.getConnection().commit();
			dbHelper.getConnection().setAutoCommit(true);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BookService bookService = new BookService();
		ResquestInfo info = new ResquestInfo();
//		List<ResponseInfo> list = bookService.bookSearchByHotelName("브라운도트");
//		List<ResponseInfo> list = bookService.bookSearchByHotelName("2");
//		List<ResponseInfo> list = bookService.reservationSearchByUserName("천병재");
		List<ResponseInfo> list = bookService.roomInfoSearchByHotelNo("1");
		System.out.println(list);
		
		// 유저 전체 조회
//		List<ResponseInfo> list = bookService.selectAllUserInfo();
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
		
		
		


		// 숙소 정보 입력
//		info.setHotelName("테스트모텔102");
//		info.setAddress("테스트구102");
//		info.setTelPhone("1221-12212-9999");
//		
//		// 방정보 입력
//		info.setRoomNo(12202);
//		info.setDayPrice(5220000);
//		info.setNightPrice(1520000);
//		
//		bookService.insertHotelInfo(info);

//		info.setHotelNo(2);
//		info.setRoomNo(2);
//		info.setUserNo(1);
//		info.getCheckInDate();
//		bookService.book(info);
////		

//		System.out.println(bookService.selectAllUserInfo()); // 유저 정보 전체조회 확인하기

//		 회원가입
//		info.setId("테스트유저2");
//		info.setPassword("9999");
//		info.setUserName("테스트입니다.");
//		info.setUserPhoneNumber("999-9999-9999");
//		info.setUserYear("7777-77-77");
//		bookService.signIn(info);

		// 호텔 이름 수정하기
//		info.hotelNameUpdate("갈색도트","브라운도트");

		// 홍텔 정보 한반 수정 테스트 확인
//		 bookService.updateHotel("4", "그냥", "1왜되지", "77-7777-7777");

		// 삭제기능 테스트 확인
//		bookService.deleteHotel(1);

		// 호텔 이름 수정하기(확인 후 삭제)
//		boolean result =  bookService.hotelNameUpdate("테스트모텔1" ,"안녕모텔");
//		System.out.println("result : " + result);
//		bookService.hotelNameUpdate("테스트모텔1" ,"안녕모텔");

	}

	@Override // 호텔이름으로 (호텔 번호, 호텔 이름, 보유방의 수, 총예약 수) 조회하기 (1행)
	public List<ResponseInfo> bookSearchByHotelName(String hotelName) {

		String sql = " select h.hotelNo, h.hotelName, count(rs.reservationNumber) as \"총 예약수\", "
				+ " (select count(roomNo) from room as r join hotel as h on h.hotelNo = r.hotelNO ) as \"보유 방의 수\" "
				+ " from reservation rs " + " join hotel as h " + " on h.hotelNo = rs.hotelNo "
				+ " where h.hotelName = ? limit 1 ";

		List<ResponseInfo> list = new ArrayList<>();

		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, hotelName);
			rs = psmt.executeQuery();
			while (rs.next()) {
				
				ResponseInfo userInfo = new ResponseInfo();
				userInfo.setHotelNo(rs.getString("hotelNo"));
				userInfo.setHotelName(rs.getString("hotelName"));
				userInfo.setReservationNumber(rs.getString("보유 방의 수"));
				userInfo.setRoomNo(rs.getString("총 예약수"));

				list.add(userInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}

		return list;
	}

	// 호텔번호로 (호텔 번호, 호텔 이름, 보유방의 수, 총예약 수) 조회하기 (1행)
	@Override
	public List<ResponseInfo> bookSearchByHotelNo(String hotelNo) {
		String sql = " select h.hotelNo, h.hotelName, count(rs.reservationNumber) as \"총 예약수\","
				+ " (select count(roomNo) from room as r join hotel as h on h.hotelNo = r.hotelNo ) as \"보유 방의 수\" "
				+ " from reservation rs "
				+ " join hotel as h "
				+ " on h.hotelNo = rs.hotelNO "
				+ " where h.hotelNo = ? ";
		List<ResponseInfo> list = new ArrayList<>();
		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, hotelNo);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ResponseInfo userInfo = new ResponseInfo();
				userInfo.setHotelNo(rs.getString("hotelNo"));
				userInfo.setHotelName(rs.getString("hotelName"));
				userInfo.setReservationNumber(rs.getString("보유 방의 수"));
				userInfo.setRoomNo(rs.getString("총 예약수"));
				list.add(userInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return list;

	}

	// 회원이름으로 (회원번호, 호텔이름, 방 호수, 가격) 조회하기 (예약 현황) (다중행)
	@Override
	public List<ResponseInfo> reservationSearchByUserName(String userName) {
		
		String sql = " select u.userNo, h.hotelName, r.roomNo, r.price "
				+ " from reservation as rs "
				+ " join userInfo as u "
				+ " on rs.userNo = u.userNo "
				+ " join hotel as h "
				+ " on rs.hotelNo = h.hotelNo "
				+ " join room as r "
				+ " on h.hotelNo = r.hotelNo "
				+ " where u.userName = ?  ";
		
		List<ResponseInfo> list = new ArrayList<>();
		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, userName);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ResponseInfo userInfo = new ResponseInfo();
				userInfo.setUserNo(rs.getString("userNo"));
				userInfo.setHotelName(rs.getString("hotelName"));
				userInfo.setRoomNo(rs.getString("roomNo"));
				userInfo.setPrice(rs.getString("price"));
				list.add(userInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return list;

	}

	// 호텔이름으로 (회원번호, 호텔이름, 방 호수, 가격) 조회하기 (예약 현황) (다중행)
	@Override
	public List<ResponseInfo> reservationSearchByHotelName(String hotelName) {
		String sql = " select u.userNo, h.hotelName, r.roomNo, r.price "
				+ " from reservation as rs "
				+ " join userInfo as u "
				+ " on rs.userNo = u.userNo "
				+ " join hotel as h "
				+ " on rs.hotelNo = h.hotelNo "
				+ " join room as r "
				+ " on h.hotelNo = r.hotelNo "
				+ " where u.userName = ?  ";
		
		List<ResponseInfo> list = new ArrayList<>();
		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, hotelName);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ResponseInfo userInfo = new ResponseInfo();
				userInfo.setUserNo(rs.getString("userNo"));
				userInfo.setHotelName(rs.getString("hotelName"));
				userInfo.setRoomNo(rs.getString("roomNo"));
				userInfo.setPrice(rs.getString("price"));
				list.add(userInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return list;
	}

	// 호텔번호로 (방Id, 호수, 호텔이름, 가격) (다중행)
	@Override
	public List<ResponseInfo>  roomInfoSearchByHotelNo(String hotelNo) {
		String sql = " select r.roomId, roomNo, h.hotelName, r.price "
				+ "from hotel as h "
				+ "join room as r "
				+ "on h.hotelNo = r.hotelNo "
				+ "where h.hotelNo = ?  ";
		
		List<ResponseInfo> list = new ArrayList<>();
		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, hotelNo);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ResponseInfo userInfo = new ResponseInfo();
				userInfo.setRoomId(rs.getString("roomId"));
				userInfo.setRoomNo(rs.getString("roomNo"));
				userInfo.setHotelName(rs.getString("hotelName"));
				userInfo.setPrice(rs.getString("price"));
				list.add(userInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return list;
	}

	@Override
	public void roomInfoSearchByHotelName() {
		// TODO Auto-generated method stub

	}

	@Override
	public void roomInfoSearchByRoomId() {
		// TODO Auto-generated method stub

	}

	@Override
	public void userInfoSearchByUserName() {
		// TODO Auto-generated method stub

	}

	@Override
	public void userInfoSearchByUserNo() {
		// TODO Auto-generated method stub

	}

}

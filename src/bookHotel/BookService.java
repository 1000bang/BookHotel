package bookHotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import bookHotel.Frame.BookFrame;
import bookHotel.Frame.HotelUpdateFrame;
import bookHotel.Frame.JoinFrame;
import bookHotel.Frame.LoginFrame;
import bookHotel.Frame.MainPageFrame;
import bookHotel.Frame.MasterFrame;
import bookHotel.Frame.RoomUpdateFrame;
import bookHotel.Frame.SearchBookFrame;
import bookHotel.dto.LoginUserInfo;
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
	private LoginUserInfo loginuserino;

	public BookService() {
		this.dbHelper = DBHelper.getInstance();
		this.loginuserino = LoginUserInfo.getInstance();
	}

	// 사용됨 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void setHotelName(MainPageFrame main) {
		String sql = "select hotelName \n" + "from hotel\n" + "where image = ?";
		try {

			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, main.getHotelPanelMain().getIcon().toString());
			rs = psmt.executeQuery();

			if (rs.next()) {
				BookFrame book = new BookFrame();
				book.getHotelNameText().setText(rs.getString("hotelName"));

			} else {
				System.out.println("123");
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

	@Override // 사용됨 !!!!!!!!!!!!!!!!!!!!!!!!!
	public void selectLoginInfo(LoginFrame loginFrame, LoginUserInfo userInfo) {

		String sql = "SELECT * FROM userinfo where Id = ?  and password = ? ";

		try {

			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, loginFrame.getId().getText());
			psmt.setString(2, loginFrame.getPw().getText());
			System.out.println(psmt.toString());
			rs = psmt.executeQuery();

			if (rs.next()) {
				userInfo.userNo = rs.getString("userNo");
				userInfo.id = rs.getString("id");
				userInfo.passWord = rs.getString("password");
				userInfo.userName = rs.getString("userName");
				userInfo.userPhoneNumber = rs.getString("userPhoneNumber");
				userInfo.useryear = rs.getString("userYear");

				if (rs.getString(2).equals("master")) {
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

	// 회원가입 사용됨 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@Override
	public void signIn(JoinFrame join) {
		String signInSql = " insert into userInfo(id, password, userName, userPhoneNumber, userYear) "
				+ " values(?,?,?,?,?) ";
		/*
		 * private RoundedTextField idText; private RoundedPass pwText; private
		 * RoundedPass pwcheck; private RoundedTextField nameText; private
		 * RoundedTextField phoneNumberText; private RoundedTextField birthText;
		 */
		try {
			psmt = dbHelper.getConnection().prepareStatement(signInSql);
			psmt.setString(1, join.getIdText().getText());
			psmt.setString(2, join.getPwText().getText());
			psmt.setString(3, join.getNameText().getText());
			psmt.setString(4, join.getPhoneNumberText().getText());
			psmt.setString(5, join.getBirthText().getText());
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				psmt.close();
				dbHelper.connectionClose();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// ???????????
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
			while (rs.next()) {
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
		} finally {
			closeAll();
		}

		return userList;
	}

	// 사용됨

	public List<String> reservationList() {
		String query1 = " select hotel.hotelName, reservation.roomNo, reservation.checkinDate from reservation \n"
				+ "join hotel\n" + "on hotel.hotelNo = reservation.hotelNo\n" + "where userNo = ? ";
		List<String> bookList = new ArrayList<>();
		try {
			psmt = dbHelper.getConnection().prepareStatement(query1);
			psmt.setString(1, loginuserino.userNo);
			rs = psmt.executeQuery();

			while (rs.next()) {
				bookList.add(rs.getString("hotelName"));
				bookList.add(rs.getString("roomNo"));
				bookList.add(rs.getString("checkinDate"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			closeAll();
		}
		return bookList;
	}

	@Override // 예약하기 //사용됨 주석 지워도됨!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void book(BookFrame book) {
		boolean flag = true;

		try {
			dbHelper.getConnection().setAutoCommit(false);

			// 마지막 예약번호 가져오기
			String query1 = " SELECT reservationNumber FROM reservation ORDER BY reservationNumber DESC LIMIT 1 ";
			rs = dbHelper.getConnection().prepareStatement(query1).executeQuery();
			int lastNo = 0;
			if (rs.next()) {
				lastNo = (rs.getInt("reservationNumber"));

			}
			System.out.println(lastNo);

			// 호텔 넘버 가져오기

			String query2 = " SELECT hotelNo FROM hotel where hotelName = ? ";
			psmt = dbHelper.getConnection().prepareStatement(query2);
			psmt.setString(1, book.getHotelNameText().getText());
			rs = psmt.executeQuery();
			int hotelNo = 0;
			if (rs.next()) {
				hotelNo = (rs.getInt("hotelNo"));
			}
			System.out.println(hotelNo);

			String query3 = "insert into reservation values (?, ?, ?, ?, ?)";

			psmt = dbHelper.getConnection().prepareStatement(query3);
			psmt.setInt(1, lastNo + 1);
			psmt.setInt(2, Integer.parseInt(book.getRoomNameText().getText()));
			psmt.setInt(3, hotelNo);
			psmt.setInt(4, Integer.parseInt(loginuserino.userNo));
			psmt.setString(5, book.getReservationDate().getText());
			System.out.println(psmt.toString());
			psmt.executeUpdate();
			JOptionPane.showMessageDialog(book, " 예약 성공 ");

			dbHelper.getConnection().commit(); // db에 반영 (테스트시 주석처리!!!)
			dbHelper.getConnection().setAutoCommit(true); // 원상복구
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(book, "예약 실패.");
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
	public boolean insertHotelInfo(HotelUpdateFrame hotel) {
		boolean flag = true;

		try {
			dbHelper.getConnection().setAutoCommit(false);

			// 숙소 마지막 번호 들고오기
			String hotelLastNoSql = " SELECT hotelNo FROM hotel ORDER BY hotelNo DESC LIMIT 1 ";
			int lastNo = 0;
			rs = dbHelper.getConnection().prepareStatement(hotelLastNoSql).executeQuery();
			if (rs.next()) {
				lastNo = (rs.getInt("hotelNo"));
			}
//			(hotelNo, hotelName, address, telPhone )
			// hotel 정보 저장하기
			String hotelSql = "insert into hotel (hotelNo, hotelName, address, telPhone) values " + " (?,?, ?, ?) ";
			psmt = dbHelper.getConnection().prepareStatement(hotelSql);
			psmt.setInt(1, lastNo + 1);
			psmt.setString(2, hotel.getHotelNameText().getText());
			psmt.setString(3, hotel.getHotelAddressText().getText());
			psmt.setString(4, hotel.getTelPhoneText().getText());
			psmt.executeUpdate();

			JOptionPane.showMessageDialog(hotel, "추가 성공.");
			dbHelper.getConnection().commit(); // db에 반영 (테스트시 주석처리!!!)
			dbHelper.getConnection().setAutoCommit(true); // 원상복구
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(hotel, "추가 실패.");
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

	// 사용됨 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	@Override
	public void searchRoom(RoomUpdateFrame roomUpdateFrame) {

		String sql = "SELECT * FROM room where roomId = ? ";

		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, roomUpdateFrame.getTextField().getText());
			rs = psmt.executeQuery();

			if (rs.next()) {
				roomUpdateFrame.getRoomIdText().setText(rs.getString("roomId"));
				roomUpdateFrame.getRoomNoText().setText(rs.getString("roomNo"));
				roomUpdateFrame.getHotelNoText().setText(rs.getString("hotelNo"));
				roomUpdateFrame.getPriceText().setText(rs.getString("Price"));
			} else {
				JOptionPane.showMessageDialog(roomUpdateFrame, "일치하는 정보가 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}

	}

	// 방정보 한방 수정하기 사용됨 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@Override
	public void updateRoom(String roomId, String newDayPrice, String newRoomNo) {

		try {
			dbHelper.getConnection().setAutoCommit(false);
			// 대실 가격 수정
			String query1 = "update room set Price = ? where roomId = ?";
			psmt = dbHelper.getConnection().prepareStatement(query1);
			psmt.setString(1, newDayPrice);
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
			try {
				psmt.close();
				dbHelper.connectionClose();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			try {
				psmt.close();
				dbHelper.connectionClose();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override // 호텔번호(Pk)로 모든 테이블 호텔 정보 삭제하기
	// 사용됨hotelupdateframe - delete
	public void deleteHotel(String hotelNo) {

		boolean flag = true;
		try {
			dbHelper.getConnection().setAutoCommit(false);
			String query1 = " delete from reservation where hotelNo = ? "; // 예약
			psmt = dbHelper.getConnection().prepareStatement(query1);
			psmt.setString(1, hotelNo);
			psmt.executeUpdate();

			String query2 = " delete from room where hotelNo = ? "; // 방
			psmt = dbHelper.getConnection().prepareStatement(query2);
			psmt.setString(1, hotelNo);
			psmt.executeUpdate();

			String query3 = " delete from review where hotelNo = ? "; // 리뷰
			psmt = dbHelper.getConnection().prepareStatement(query3);
			psmt.setString(1, hotelNo);
			psmt.executeUpdate();

			String query4 = " delete from hotel where hotelNo = ? "; // 호텔
			psmt = dbHelper.getConnection().prepareStatement(query4);
			psmt.setString(1, hotelNo);
			psmt.executeUpdate();

			dbHelper.getConnection().commit();
			dbHelper.getConnection().setAutoCommit(true);

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				psmt.close();
				dbHelper.connectionClose();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		BookService bookService = new BookService();
		ResquestInfo info = new ResquestInfo();
//		List<ResponseInfo> list = bookService.bookSearchByHotelName("브라운도트");
//		List<ResponseInfo> list = bookService.bookSearchByHotelName("2");
//		List<ResponseInfo> list = bookService.reservationSearchByUserName("천병재");

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
	}

// 사용

	public void bookSearchByHotelName(SearchBookFrame searchBookFrame) {

		String sql = " select hotelNo, hotelName, "
				+ " (select count(roomNo) from reservation where hotelName like ?) as \"총 예약수\", "
				+ " (select count(roomNo) from room as r join hotel as h on h.hotelNo = r.hotelNO where h.hotelName like ? ) as \"보유 방의 수\" "
				+ " from hotel " + " where hotelName like ?  ";

//        List<ResponseInfo> list = new ArrayList<>();

		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, "%" + searchBookFrame.getTextField().getText() + "%");
			psmt.setString(2, "%" + searchBookFrame.getTextField().getText() + "%");
			psmt.setString(3, "%" + searchBookFrame.getTextField().getText() + "%");
			rs = psmt.executeQuery();

			if (rs.next()) {
				searchBookFrame.getInfonext().setText(rs.getString("hotelNo"));
				searchBookFrame.getInfo_2next().setText(rs.getString("hotelName"));
				searchBookFrame.getInfo_3next().setText(rs.getString("총 예약수"));
				searchBookFrame.getInfo_4next().setText(rs.getString("보유 방의 수"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}

	}

	// 홍텔 정보 한반 수정 테스트 확인
//		 bookService.updateHotel("4", "그냥", "1왜되지", "77-7777-7777");

	// 삭제기능 테스트 확인
//		bookService.deleteHotel(1);

	// 호텔 이름 수정하기(확인 후 삭제)
//		boolean result =  bookService.hotelNameUpdate("테스트모텔1" ,"안녕모텔");
//		System.out.println("result : " + result);
//		bookService.hotelNameUpdate("테스트모텔1" ,"안녕모텔");

	// 호텔번호로 (호텔 번호, 호텔 이름, 보유방의 수, 총예약 수) 조회하기 (1행)
	@Override
	public void bookSearchByHotelNo(SearchBookFrame searchBookFrame) {
//        String sql = " select h.hotelNo, h.hotelName, count(rs.reservationNumber) as "총 예약수","
//                + " (select count(roomNo) from room as r join hotel as h on h.hotelNo = r.hotelNo ) as "보유 방의 수" "
//                + " from reservation rs " + " join hotel as h " + " on h.hotelNo = rs.hotelNO "
//                + " where h.hotelNo = ? limit 1";

		String sql = " select hotelNo, hotelName, "
				+ " (select count(roomNo) from reservation where hotelNo = ? ) as \"총 예약수\", "
				+ " (select count(roomNo) from room as r join hotel as h on h.hotelNo = r.hotelNO where h.hotelNo = ? ) as \"보유 방의 수\" "
				+ " from hotel " + " where hotelNo = ? ";

		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, searchBookFrame.getTextField().getText());
			psmt.setString(2, searchBookFrame.getTextField().getText());
			psmt.setString(3, searchBookFrame.getTextField().getText());
			rs = psmt.executeQuery();
			if (rs.next()) {

				searchBookFrame.getInfonext().setText(rs.getString("hotelNo"));
				searchBookFrame.getInfo_2next().setText(rs.getString("hotelName"));
				searchBookFrame.getInfo_3next().setText(rs.getString("총 예약수"));
				searchBookFrame.getInfo_4next().setText(rs.getString("보유 방의 수"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}

	}

	// 회원이름으로 (회원번호, 호텔이름, 방 호수, 가격) 조회하기 (예약 현황) (다중행)
	@Override
	public void reservationSearchByUserName(SearchBookFrame searchBookFrame) {

		String sql = " select u.userNo, h.hotelName, r.roomNo, r.price " + " from reservation as rs "
				+ " join userInfo as u " + " on rs.userNo = u.userNo " + " join hotel as h "
				+ " on rs.hotelNo = h.hotelNo " + " join room as r " + " on h.hotelNo = r.hotelNo "
				+ " where u.userName = ? group by u.userNo ";

		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, searchBookFrame.getTextField().getText());
			rs = psmt.executeQuery();
			if (rs.next()) {

				searchBookFrame.getInfonext().setText(rs.getString("u.userNo"));
				searchBookFrame.getInfo_2next().setText(rs.getString("h.hotelName"));
				searchBookFrame.getInfo_3next().setText(rs.getString("r.roomNo"));
				searchBookFrame.getInfo_4next().setText(rs.getString("r.price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	// 회원번호로 (회원번호, 호텔이름, 방 호수, 가격) 조회하기 (예약 현황) (다중행)
	@Override
	public void reservationSearchByUserNo(SearchBookFrame searchBookFrame) {
		String sql = " select u.userNo, h.hotelName, r.roomNo, r.price from reservation as rs "
				+ "join userInfo as u on rs.userNo = u.userNo  join hotel as h "
				+ "on rs.hotelNo = h.hotelNo join room as r  on h.hotelNo = r.hotelNo "
				+ "where u.userNo = ? group by u.userNo ";

		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, searchBookFrame.getTextField().getText());
			rs = psmt.executeQuery();
			if (rs.next()) {
				searchBookFrame.getInfonext().setText(rs.getString("u.userNo"));
				searchBookFrame.getInfo_2next().setText(rs.getString("h.hotelName"));
				searchBookFrame.getInfo_3next().setText(rs.getString("r.roomNo"));
				searchBookFrame.getInfo_4next().setText(rs.getString("r.price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	// 방Id(방Id, 호수, 호텔이름, 가격) (다중행)
	@Override
	public void roomInfoSearchByHotelNo(SearchBookFrame searchBookFrame) {
		String sql = " select r.roomId, r.roomNo, h.hotelName, r.price from reservation as rs "
				+ " join userInfo as u on rs.userNo = u.userNo  join hotel as h "
				+ " on rs.hotelNo = h.hotelNo join room as r  on h.hotelNo = r.hotelNo "
				+ " where r.roomId = ? group by r.roomId ";

		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, searchBookFrame.getTextField().getText());
			rs = psmt.executeQuery();
			if (rs.next()) {
				searchBookFrame.getInfonext().setText(rs.getString("r.roomId"));
				searchBookFrame.getInfo_2next().setText(rs.getString("r.roomNo"));
				searchBookFrame.getInfo_3next().setText(rs.getString("h.hotelName"));
				searchBookFrame.getInfo_4next().setText(rs.getString("r.price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	@Override
	public void insertRoom(RoomUpdateFrame roomUpdateFrame) {

		try {
			dbHelper.getConnection().setAutoCommit(false);
			int lastNo = 0;
			// 숙소 마지막 번호 들고오기
			String roomLastNoSql = " SELECT roomId FROM room ORDER BY hotelNo DESC LIMIT 1 ";
			rs = dbHelper.getConnection().prepareStatement(roomLastNoSql).executeQuery();
			if (rs.next()) {
				lastNo = (rs.getInt("roomId"));
			}

			// 숙소 정보 저장하기
			String hotelSql = "insert into room values " + " (?,?, ?, ?) ";
			psmt = dbHelper.getConnection().prepareStatement(hotelSql);
			psmt.setInt(1, lastNo + 1);
			psmt.setString(2, roomUpdateFrame.getRoomNoText().getText());
			psmt.setString(3, roomUpdateFrame.getHotelNoText().getText());
			psmt.setString(4, roomUpdateFrame.getPriceText().getText());
			psmt.executeUpdate();

			dbHelper.getConnection().commit(); // db에 반영 (테스트시 주석처리!!!)
			dbHelper.getConnection().setAutoCommit(true); // 원상복구
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(roomUpdateFrame, "일치하는 호텔이 없습니다.");
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

	public void hotelInfoSearch(HotelUpdateFrame update) {
		String sql = " select * from hotel where hotelName like ? ";

		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, "%" + update.getTextField().getText() + "%");
			rs = psmt.executeQuery();
			if (rs.next()) {
				update.getHotelNoText().setText(rs.getString("hotelNo"));
				update.getHotelNameText().setText(rs.getString("hotelName"));
				update.getHotelAddressText().setText(rs.getString("address"));
				update.getTelPhoneText().setText(rs.getString("telPhone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	public List<String> review() {
		String sql = "select hotel.hotelName, rating, content from review\n" + "join hotel\n"
				+ "on review.hotelNo = hotel.hotelNo\n" + "where userNo = ?";
		List<String> listR = new ArrayList<>();

		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, loginuserino.userNo);
			rs = psmt.executeQuery();
			while (rs.next()) {

				listR.add(rs.getString("content"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return listR;

	}

	public List<String> reviewH() {
		String sql = "select hotel.hotelName, rating, content from review\n" + "join hotel\n"
				+ "on review.hotelNo = hotel.hotelNo\n" + "where userNo = ?";
		List<String> listH = new ArrayList<>();

		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, loginuserino.userNo);
			rs = psmt.executeQuery();
			while (rs.next()) {
				listH.add(rs.getString("hotelName"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return listH;

	}

	@Override
	public void userInfoSearchByUserName(SearchBookFrame searchBookFrame) {

		String sql = " select * " + "from userInfo " + "where userName like ? ";

//        List<ResponseInfo> list = new ArrayList<>();

		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, "%" + searchBookFrame.getTextField().getText() + "%");
			rs = psmt.executeQuery();

			if (rs.next()) {
				searchBookFrame.getInfonext().setText(rs.getString("userNo"));
				searchBookFrame.getInfo_2next().setText(rs.getString("userName"));
				searchBookFrame.getInfo_3next().setText(rs.getString("userPhoneNumber"));
				searchBookFrame.getInfo_4next().setText(rs.getString("userYear"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	// d유저번호 유저이름 전화번호 생년월일
	@Override
	public void userInfoSearchByUserNo(SearchBookFrame searchBookFrame) {
		String sql = " select * from userInfo where userNo = ? ";

		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, searchBookFrame.getTextField().getText());
			rs = psmt.executeQuery();
			if (rs.next()) {
				searchBookFrame.getInfonext().setText(rs.getString("userNo"));
				searchBookFrame.getInfo_2next().setText(rs.getString("userName"));
				searchBookFrame.getInfo_3next().setText(rs.getString("userPhoneNumber"));
				searchBookFrame.getInfo_4next().setText(rs.getString("userYear"));
			}
		} catch (SQLException e) {
			System.out.println("123");
			e.printStackTrace();
		} finally {

			closeAll();

		}
	}

	@Override
	public void deleteRoom(RoomUpdateFrame room) {

		try {
			String query1 = " delete from room\r\n" + "where roomid = ? "; // 예약
			psmt = dbHelper.getConnection().prepareStatement(query1);
			psmt.setString(1, room.getRoomIdText().getText());
			psmt.executeUpdate();
			JOptionPane.showMessageDialog(room, "삭제 성공");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(room, "삭제 실패");
			e.printStackTrace();
		} finally {
			try {
				psmt.close();
				dbHelper.connectionClose();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	};

	public void closeAll() {
		try {
			rs.close();
			psmt.close();
			dbHelper.connectionClose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
package bookHotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import bookHotel.Frame.BookFrame;
import bookHotel.Frame.HotelUpdateFrame;
import bookHotel.Frame.JoinFrame;
import bookHotel.Frame.MainFrame;
import bookHotel.Frame.UserHomeFrame;
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
	private LoginUserInfo loginUserInfo;

	public static BookService  instance = new BookService();
	
	public static BookService getInstance() {
		return instance;
	}
	
	
	public BookService() {
		this.dbHelper = DBHelper.getInstance();
		this.loginUserInfo = LoginUserInfo.getInstance();
	}

	// 메인프레임 호텔이미지와 북프레임 연결
	public void setHotelName(UserHomeFrame main) {
		String sql = "select hotelName \n" + "from hotel\n" + "where image = ?";
		try {

			psmt = dbHelper.getConnection().prepareStatement(sql);
			if(main.isFlag() == true) {
				psmt.setString(1, main.getHotelPanelMain().getIcon().toString());
			}else {
			psmt.setString(1, main.getHotelPanelMainB().getIcon().toString());
			}
			rs = psmt.executeQuery();

			if (rs.next()) {
				BookFrame book = new BookFrame();
				book.getHotelNameText().setText(rs.getString("hotelName"));

			} else {
				JOptionPane.showMessageDialog(main, "해당 호텔 정보가 없습니다.");
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

	@Override
	// 로그인프레임 아이디 비밀번호
	public void selectLoginInfo(MainFrame loginFrame, LoginUserInfo userInfo) {

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
					JOptionPane.showMessageDialog(loginFrame, "관리자 계정으로 로그인합니다.");
				} else {
					loginFrame.dispose();
					new UserHomeFrame();
					JOptionPane.showMessageDialog(loginFrame, "로그인 성공.");
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

	// 회원가입 insert
	@Override
	public void signIn(JoinFrame join) {
		String signInSql = " insert into userInfo(id, password, userName, userPhoneNumber, userYear) "
				+ " values(?,?,?,?,?) ";
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

	// 메인페이지 내 정보 예약목록
	public List<String> reservationList() {
		String query1 = " select hotel.hotelName, reservation.roomNo, reservation.checkinDate from reservation \n"
				+ "join hotel\n" + "on hotel.hotelNo = reservation.hotelNo\n" + "where userNo = ? ";
		List<String> bookList = new ArrayList<>();
		try {
			psmt = dbHelper.getConnection().prepareStatement(query1);
			psmt.setString(1, loginUserInfo.userNo);
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

	@Override
	// 예약하기 북프레임
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
			psmt.setInt(4, Integer.parseInt(loginUserInfo.userNo));
			psmt.setString(5, book.getReservationDate().getText());
			System.out.println(psmt.toString());
			psmt.executeUpdate();
			JOptionPane.showMessageDialog(book, " 예약 성공 ");
			book.dispose();
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

	// 호텔 정보 insert (트랜잭션)
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

			String hotelSql = "insert into hotel (hotelNo, hotelName, address, telPhone) values " + " (?,?, ?, ?) ";
			psmt = dbHelper.getConnection().prepareStatement(hotelSql);
			psmt.setInt(1, lastNo + 1);
			psmt.setString(2, hotel.getHotelNameText().getText());
			psmt.setString(3, hotel.getHotelAddressText().getText());
			psmt.setString(4, hotel.getTelPhoneText().getText());
			psmt.executeUpdate();

			JOptionPane.showMessageDialog(hotel, "호텔 정보 추가 성공.");
			dbHelper.getConnection().commit(); // db에 반영 (테스트시 주석처리!!!)
			dbHelper.getConnection().setAutoCommit(true); // 원상복구
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(hotel, "호텔 정보 추가 실패.");
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

	// 방정보 조회 ID를 통한 book frame

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

	// 방정보 수정하기 book frame
	@Override
	public void updateRoom(String roomId, String newDayPrice, String newRoomNo, RoomUpdateFrame room) {

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
			JOptionPane.showMessageDialog(room, "방 정보 수정 완료.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(room, "수정 실패 오류를 확인하세요.");
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

	// 호텔 정보 수정 hotel Frame
	@Override
	public void updateHotel(String hotelNo, String newHotelName, String newAddress, String newTelPhone,
			HotelUpdateFrame hotel) {

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
			JOptionPane.showMessageDialog(hotel, "수정 성공");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(hotel, "수정 실패 오류를 확인하세요 ");
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

	@Override
	// 호텔번호(Pk)로 모든 테이블 호텔 정보 삭제하기
	// hotel update frame - delete
	public void deleteHotel(String hotelNo, HotelUpdateFrame hotel) {

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
			JOptionPane.showMessageDialog(hotel, "삭제 완료.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(hotel, "삭제 실패 오류를 확인하세요 ");
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

	// Search Frame 호텔이름으로 예약조회
	public void bookSearchByHotelName(SearchBookFrame searchBookFrame) {

		String sql = " select hotelNo, hotelName, "
				+ " (select count(roomNo) from reservation where hotelName like ?) as \"총 예약수\", "
				+ " (select count(roomNo) from room as r join hotel as h on h.hotelNo = r.hotelNO where h.hotelName like ? ) as \"보유 방의 수\" "
				+ " from hotel " + " where hotelName like ?  ";

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

	// Search Frame 호텔 번호로 예약조회
	@Override
	public void bookSearchByHotelNo(SearchBookFrame searchBookFrame) {

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

	// Search Frame
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

	// Search Frame
	// 회원번호로 (회원번호, 호텔이름, 방 호수, 가격) 조회하기 (예약 현황)
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

	// Search Frame
	// 방Id(방Id, 호수, 호텔이름, 가격) (다중행)
	@Override
	public void roomInfoSearchByHotelNo(SearchBookFrame searchBookFrame) {
		String sql = "select r.roomId, roomNo, h.hotelName, r.price \r\n" + "from hotel as h join room as r\r\n"
				+ "on h.hotelNo = r.hotelNo\r\n" + "where roomId = ?";

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

	// room Frame
	// 방정보 insert
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
			JOptionPane.showMessageDialog(roomUpdateFrame, "호텔 정보 추가 성공");
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

	// HotelFrame
	// 호텔이름으로 호텔정보 검색
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
			}else {
				JOptionPane.showMessageDialog(update, "<html><body>" +"해당 호텔 정보가 없습니다."+ "<br>"
						+ "호텔이름으로 검색하세요 !!");
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(update, "일치하는 정보가 없습니다.");
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}

	// 나의 리뷰 리뷰 리스트
	public List<String> review() {
		String sql = "select hotel.hotelName, rating, content from review\n" + "join hotel\n"
				+ "on review.hotelNo = hotel.hotelNo\n" + "where userNo = ?";
		List<String> listR = new ArrayList<>();

		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, loginUserInfo.userNo);
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

	// 나의 리뷰 호텔 이름
	public List<String> reviewH() {
		String sql = "select hotel.hotelName, rating, content from review\n" + "join hotel\n"
				+ "on review.hotelNo = hotel.hotelNo\n" + "where userNo = ?";
		List<String> listH = new ArrayList<>();

		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, loginUserInfo.userNo);
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

	// search Frame
	// 유저이름으로 유저 정보검색
	@Override
	public void userInfoSearchByUserName(SearchBookFrame searchBookFrame) {

		String sql = " select * " + "from userInfo " + "where userName like ? ";

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

	// Search Book Frame
	// d유저번호 유저이름 전화번호 생년월일
	// 유저 번호로 유저정보 검색
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

	// Room Update Frame
	// 방정보 삭제
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
	}

	// 전체 닫기 메서드
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
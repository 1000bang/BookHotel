package bookHotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import bookHotel.Frame.LoginFrame;
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

		String sql = "SELECT * FROM userinfo where Id =   ?  and password = ? ";

		try {
			psmt = dbHelper.getConnection().prepareStatement(sql);
			psmt.setString(1, loginFrame.getId().getText());
			psmt.setString(2, loginFrame.getPw().getText());
			rs = psmt.executeQuery();

			if (rs.next()) {
				// 홈페이지 오픈
				JOptionPane.showMessageDialog(loginFrame, "로그인에 성공하였습니다.");
			} else {
				JOptionPane.showMessageDialog(loginFrame, "일치하는 정보가 없습니다.");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
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

	@Override
	public void readDate() {

	}

	@Override // 유저 정보 전체 조회
	public List<ResponseInfo> selectAllUserInfo() {
		String sql = " 	select userNo, id, password, userName, userPhoneNumber, userYear "
				+ 	" from userInfo ";
		
		List<ResponseInfo> list = new ArrayList<>();
		
		try {
			rs = dbHelper.getConnection().prepareStatement(sql).executeQuery();
			while(rs.next()) {
				ResponseInfo userInfo = new ResponseInfo();
				userInfo.setUserName(rs.getString("userName"));
				userInfo.setUserPw(rs.getString("userPw"));
				userInfo.setHotelNo(rs.getString("hotelNo"));
				userInfo.setRoomNo(rs.getString("roomNo"));
				userInfo.setReservationNo(rs.getString("reservationNo"));
				list.add(userInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}	

	
	// 호텔 정보 저장하기 (트랜잭션)
	@Override
	public boolean insertHotelInfo(ResquestInfo req) {
		boolean flag = true;
		
		try {
			dbHelper.getConnection().setAutoCommit(flag);
			
			// 숙소 정보 저장하기
			String hotelSql = "insert into hotel values "
					+ " (0, '?', '?', '?') ";
			psmt = dbHelper.getConnection().prepareStatement(hotelSql);
			psmt.setInt(1,req.getHotelNo());
			psmt.setString(2, req.getHotelName());
			psmt.setString(3, req.getAddress());
			psmt.setString(4, req.getTelPhone());
			psmt.executeUpdate();
			

			
			String roomSql = " insert into room values(0,'?', ?, ?, ?) ";
			
			// 방 정보 저장하기
			psmt = dbHelper.getConnection().prepareStatement(roomSql);
			psmt.setInt(1, req.getRoomId());
			psmt.setString(1, req.getHotelName());
			psmt.setInt(1, req.getRoomNo());
			psmt.setInt(1, req.getHotelNo());
			psmt.setInt(1, req.getPrice());
			psmt.executeUpdate();
			
			dbHelper.getConnection().commit(); // db에 반영
			dbHelper.getConnection().setAutoCommit(true); // 원상복구
		} catch (SQLException e) {
			try {
				dbHelper.getConnection().rollback();
				System.out.println("롤백했습니다.");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

		}finally {
			try {
				rs.close();
				psmt.close();
				dbHelper.connectionClose();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return flag;
	}

}

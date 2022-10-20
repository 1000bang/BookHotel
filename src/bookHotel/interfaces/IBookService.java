package bookHotel.interfaces;

import java.util.List;

import bookHotel.Frame.LoginFrame;
import bookHotel.Frame.RoomUpdateFrame;
import bookHotel.dto.ResponseInfo;
import bookHotel.dto.ResquestInfo;

public interface IBookService {

	/*
	 * List<ResponseInfo> selectAllInfo(); List<ResponseInfo> selectUserInfo();
	 * List<ResponseInfo> selectHotelInfo(); List<ResponseInfo>
	 * selectBookInfoByUserName(String userName);
	 * 
	 * boolean insertHotel(RequestHotel requestHotel); void updateHotel(String
	 * oldPrice, String newPrice); void deleteHotel(int hotelNo);
	 * 
	 */

	void selectLoginInfo(LoginFrame login);


	// 유저 정보 전체 조회
	List<ResponseInfo> selectAllUserInfo();

	// 호텔 정보 저장하기
	boolean insertHotelInfo(ResquestInfo req);

	// 회원가입
	void signIn(ResquestInfo req);
	
	// 호텔 이름 수정하기(확인후 삭제)
//	boolean hotelNameUpdate(String oldHotelName, String changeName);

	// 호텔 주소 수정하기(삭제)
//	boolean hotelAddressUpdate(String hotelName, String changeAddress);
	
	// 관리자 페이지 권리
	
	// 호텔 정보 수정하기
	void updateHotel(String hotelNo, String newHotelName, String newAddress, String newTelPhone);
	
	// 객실 정보 수정하기
	// 객실정보 삭제하기
	// 에약자 조회
	
	
	
	
	void updateRoom(String roomId, String newDayPrice, String newNightPrice, String newRoomNo );

	   
	   void searchRoom(RoomUpdateFrame roomUpdateFrame);
	
}


package bookHotel.interfaces;

import java.util.List;

import bookHotel.Frame.BookFrame;
import bookHotel.Frame.HotelUpdateFrame;
import bookHotel.Frame.JoinFrame;
import bookHotel.Frame.LoginFrame;
import bookHotel.Frame.RoomUpdateFrame;
import bookHotel.Frame.SearchBookFrame;
import bookHotel.dto.LoginUserInfo;
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

	// 호텔이름으로 (호텔 번호, 호텔 이름, 보유방의 수, 총예약 수) 조회하기 (1행)
	void bookSearchByHotelName(SearchBookFrame searchBookFrame);

	// 호텔번호로 (호텔 번호, 호텔 이름, 보유방의 수, 총예약 수) 조회하기 (1행)
	void bookSearchByHotelNo(SearchBookFrame searchBookFrame);

	// 회원이름으로 (회원번호, 호텔이름, 방 호수, 가격) 조회하기 (예약 현황) (다중행)
	void reservationSearchByUserName(SearchBookFrame searchBookFrame);

	// 호텔이름으로 (회원번호, 호텔이름, 방 호수, 가격) 조회하기 (예약 현황) (다중행)
	void reservationSearchByUserNo(SearchBookFrame searchBookFrame);

	// 호텔번호로 (방Id, 호수, 호텔이름, 가격) (다중행)
	void roomInfoSearchByHotelNo(SearchBookFrame searchBookFrame);


	// 유저이름으로 (유저번호, 유저이름, 전화번호, 생년월일) (1행)
	void userInfoSearchByUserName(SearchBookFrame searchBookFrame);

	// 유저고유번호로 (유저번호, 유저이름, 전화번호, 생년월일) (1행)
	void userInfoSearchByUserNo(SearchBookFrame searchBookFrame);

	void selectLoginInfo(LoginFrame login, LoginUserInfo userInfo);

	// 유저 정보 전체 조회
	List<ResponseInfo> selectAllUserInfo();

	// 호텔 정보 저장하기
	boolean insertHotelInfo(HotelUpdateFrame hotel);

	
	// 호텔 정보 수정하기
	void updateHotel(String hotelNo, String newHotelName, String newAddress, String newTelPhone);
	
	// 호텔 정보 삭제하기
	void deleteHotel(String hotelNo);
	
	void deleteRoom(RoomUpdateFrame room);
	
	// 예약하다
	void book(BookFrame book);
	void insertRoom(RoomUpdateFrame roomUpdateFrame);

	void signIn(JoinFrame join);


	void updateRoom(String roomId, String newDayPrice, String newRoomNo);

	void searchRoom(RoomUpdateFrame roomUpdateFrame);

}

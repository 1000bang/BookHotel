package bookHotel.interfaces;

import java.util.List;

import bookHotel.Frame.LoginFrame;
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

	void readDate();

	// 유저 정보 전체 조회
	List<ResponseInfo> selectAllUserInfo();

	// 호텔 정보 저장하기
	boolean insertHotelInfo(ResquestInfo req);

}

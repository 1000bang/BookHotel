package bookHotel.interfaces;

import java.util.List;

import bookHotel.Frame.LoginFrame;
import bookHotel.dto.ResponseInfo;


public interface IBookService {

	/*
	 * List<ResponseInfo> selectAllInfo();
		List<ResponseInfo> selectUserInfo();
		List<ResponseInfo> selectHotelInfo();
		List<ResponseInfo> selectBookInfoByUserName(String userName);
		
	boolean insertHotel(RequestHotel requestHotel);
	void updateHotel(String oldPrice, String newPrice);
	void deleteHotel(int hotelNo);
	 
	 */
	
	
	void selectLoginInfo(LoginFrame login);
	void readDate();
	

}

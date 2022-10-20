package bookHotel.dto;

import lombok.Data;

@Data
// 응답할 데이터에서 필요한 정보 
public class ResponseInfo {

	 private String userName; //고객이름
	 private String userPw;
	 private String hotelNo;
	 private String roomNo;
	 private String reservationNo;
	 private String id;
	 private String password;
	 private String userPhoneNumber;
	 private String userYear;
}

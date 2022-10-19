package bookHotel.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
// db컬럼명 
public class ResquestInfo {

	private String userName; 
	private String password;
	private int hotelNo;
	private int roomNo;
	private String reservationNo;
	private int userNo;
	private int roomId;
	private Timestamp checkInDate;
	private int reservationNumber;
	private int reviewNo;
	private String content;
	private int rating;
	private String hotelName;
	private int price;
	private String id;
	private String userPhoneNumber;
	private String userYear;
	private String address;
	private String telPhone;

}

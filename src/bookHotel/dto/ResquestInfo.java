package bookHotel.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
// db컬럼명 
public class ResquestInfo {

	public String userName;
	public String password;
	public int userNo;
	public String id;
	public String userPhoneNumber;
	public String userYear;
	private int hotelNo;
	private int roomNo;
	private String reservationNo;
	private int roomId;
	private Timestamp checkInDate;
	private int reservationNumber;
	private int reviewNo;
	private String content;
	private int rating;
	private String hotelName;
	private int dayPrice;
	private int nightPrice;
	private String address;
	private String telPhone;

}

 package bookHotel.dto;

public class UserInfo {

	public String userNo;
	public String id;
	public String passWord;
	public String userName;
	public String userPhoneNumber;
	public String useryear;
	
	private UserInfo() {
		
	}
	
	private static UserInfo instance = new UserInfo();
	
	public static UserInfo getInstance() {
		
		
		
		
		
		return instance;
		
	}
	
	
}

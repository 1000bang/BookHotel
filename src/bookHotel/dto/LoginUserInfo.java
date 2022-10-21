package bookHotel.dto;

import lombok.Getter;
import lombok.Setter;

// 싱글톤으로 굳이 만들려는 이유는 
// 로그인한 유저에 정보를 dto 담기 위해서 .. 프로그램 세상에서 오직 하나만 존재하면 되기 때문에 

class  User {
	
	public String userNo;
	public String id;
	public String passWord;
	public String userName;
	public String userPhoneNumber;
	public String useryear;

}

class Customer extends User {
	
}

@Getter
@Setter
public class LoginUserInfo extends User {
	
	
	
	
	private LoginUserInfo() {
		
	}

	private static LoginUserInfo instance = new LoginUserInfo();

	public synchronized static LoginUserInfo getInstance() {
		if (instance == null) {
			instance = new LoginUserInfo();
		}
		return instance;
	}

}

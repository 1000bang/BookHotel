package bookHotel.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {

	private static final String DB_HOST = "192.168.7.96";
	private static final int DB_PORT = 3306;
	private static final String DB_DATABASE_NAME = "yanolja";

	
	private static final String DB_CHARSET = "UTF-8";
	private static final String DB_USER_NAME = "tester";
	//비번 바꿔 !
	private static final String DB_PASSWORD = "dlfdl123";

	// Single ton
	private static DBHelper dbHelper;
	private Connection conn;

	private DBHelper() {

	} 

	public static DBHelper getInstance() {
		if (dbHelper == null) {
			dbHelper = new DBHelper();
		}
		return dbHelper;
	}

	public Connection getConnection() {
		if (conn == null) {
			String urlFormat = "jdbc:mysql://%s:%d/%s?serverTimezone=Asia/Seoul&characterEncoding=%s";
			String url = String.format(urlFormat, DB_HOST, DB_PORT, DB_DATABASE_NAME, DB_CHARSET);

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, DB_USER_NAME, DB_PASSWORD);
				Statement stmt = conn.createStatement();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				try {
					connectionClose();  
				} catch (Exception e2) {
					e.printStackTrace();
				}
			}
		}
		return conn;
	}

	public void connectionClose() {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}

}

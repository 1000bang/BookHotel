package bookHotel.Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import bookHotel.BookService;
import bookHotel.RoundedButton;
import bookHotel.RoundedPass;
import bookHotel.RoundedTextField;
import bookHotel.dto.LoginUserInfo;
import bookHotel.utils.DBHelper;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginFrame extends JFrame implements ActionListener {
	private JLabel logo;
	private JLabel idLabel;
	private JLabel passWord;

	private RoundedButton logIn;
	private RoundedButton join; 

	private RoundedTextField id;
	private RoundedPass pw;
	BookService bookService;
	LoginUserInfo userInfo;

 
	public LoginFrame() { 
		initData();
		setInitLayout();
		addActionListener();
		this.userInfo = LoginUserInfo.getInstance();
	}

	private void initData() {
		setTitle("LogIn");
		setSize(600, 1300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logo = new JLabel(new ImageIcon("images/logo.png"));
		idLabel = new JLabel("아이디 ");
		passWord = new JLabel("비밀번호");

		id = new RoundedTextField("");
		pw = new RoundedPass("");

		logIn = new RoundedButton(" 로그인");
		join = new RoundedButton(" 회원가입");
		bookService = new BookService();

	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(null);
		getContentPane().setBackground(Color.white);
		logo.setBounds(200, 50, 150, 100);
		this.getContentPane().add(logo);
		
		//셋라벨 메서드 호출 !
		setLabel(idLabel, 20, 200, 150, 20);
		setLabel(passWord, 20, 300, 100, 20);

		// textfiled
		id.setBounds(20, 235, 500, 50);
		id.setFont(new Font(getName(), Font.PLAIN, 20));
		this.getContentPane().add(id);
		// password text
		pw.setBounds(20, 335, 500, 50);
		id.setFont(new Font(getName(), Font.PLAIN, 20));
		this.getContentPane().add(pw);
		// 로그인버튼
		logIn.setBounds(20, 450, 500, 70);
		this.getContentPane().add(logIn);
		// 회원가입 버튼
		join.setBounds(20, 540, 500, 70);
		this.getContentPane().add(join);

	}

	private void addActionListener() {
		logIn.addActionListener(this);
		join.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * 로그인버튼을 누르면 BookSerivce의 selectLoginInfo메서드를 호출
		 * id text와 pw text의 의 값과 일치하는 데이터가 있으면 mainpageFrame으로 이동
		 *
		 * 회원가입 버튼을 누르면 회원가입 창으로 이동
		 *
		*/
		if (e.getSource() == logIn) {
			bookService.selectLoginInfo(this, userInfo);		
		} else if (e.getSource() == join) {
			dispose();
			new JoinFrame();
		} 

	}

	
	//셋사이즈 셋로케 에드 메서드화 !!
	private void setLabel(JLabel label, int x, int y, int w, int h) {
		label.setBounds(x, y, w, h);
		label.setFont(new Font(getName(), Font.PLAIN, 15));
		this.getContentPane().add(label);
	}

	public static void main(String[] args) {
		new LoginFrame();


	}

}

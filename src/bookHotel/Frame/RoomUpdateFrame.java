package bookHotel.Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bookHotel.BookService;
import bookHotel.RoundedButton;
import bookHotel.RoundedPass;
import bookHotel.RoundedTextField;
import bookHotel.interfaces.IBookService;
import bookHotel.utils.Define;
import lombok.Getter;
import lombok.Setter;

/*
 * 
 * Columns:
roomId int AI PK 
hotelName varchar(20) 
roomNo int 
hotelNo int 
price int 



조회 / 수정 됨

삭제/ 추가 안됨




 */
@Getter
@Setter
public class RoomUpdateFrame extends JFrame implements ActionListener {

	private JLabel logo;
	private JButton goBack;
	private JLabel roomId;
	private JLabel hotelNo;
	private JLabel roomNo;
	private JLabel dayPrice;

	private JLabel warningHotelNo;
	private JLabel warningHotelName;
	private JLabel warningMain;
	private JLabel warningMain_2;

	private RoundedTextField roomIdText;
	private RoundedTextField hotelNoText;
	private RoundedTextField roomNoText;
	private RoundedTextField priceText;
	JTextField textField;
	private RoundedButton update;
	private RoundedButton search;
	private RoundedButton delete;
	private RoundedButton insert;

	BookService bookService;

	public RoomUpdateFrame() {

		initData();
		setInitLayout();
		addActionListener();
	}

	private void initData() {
		setTitle("LogIn");
		setSize(600, 1300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		logo = new JLabel(new ImageIcon("images/logo.png"));
		goBack = new JButton(new ImageIcon("images/goback.png"));
		hotelNo = new JLabel("호텔 번호");
		roomNo = new JLabel("호실 번호");
		dayPrice = new JLabel("가격 ");
		roomId = new JLabel("방 고유번호 ");

		hotelNoText = new RoundedTextField("");
		roomNoText = new RoundedTextField("");
		priceText = new RoundedTextField("");
		roomIdText = new RoundedTextField("");
		textField = new JTextField("방 고유번호 입력하고 조회 !!");
		update = new RoundedButton("수정하기");
		search = new RoundedButton("조회하기");
		delete = new RoundedButton("삭제하기");
		insert = new RoundedButton("추가하기");

		warningHotelNo = new JLabel("* HotelNo는 ");
		warningHotelName = new JLabel("* 비밀번호는 5글자 이상 적어주세요. ");

		warningMain = new JLabel(" * 조회할 때는 방의 고유번호만 입력하세요 ");
		warningMain_2 = new JLabel(" * 업데이트할 때는 고유번호와 호텔번호를 수정할 수 없습니다.");
		bookService = new BookService();
	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(null);
		getContentPane().setBackground(Color.white);
		goBack.setBounds(0, 0, 70, 70);
		goBack.setBorderPainted(false);
		goBack.setContentAreaFilled(false);
		this.getContentPane().add(goBack);

		logo.setBounds(200, 0, 150, 100);
		this.getContentPane().add(logo);

		// 컴포넌트 위치
		warningMain.setForeground(Color.red);
		warningMain_2.setForeground(Color.red);
		setLabel(warningMain, 20, 100, 300, 20);
		setLabel(warningMain_2, 20, 130, 400, 20);

		setLabel(roomId, 20, 180, 100, 20);
		setLabel(hotelNo, 20, 260, 100, 20);
		setLabel(roomNo, 20, 340, 100, 20);
		setLabel(dayPrice, 20, 420, 100, 20);

		setText(roomIdText, 20, 200, 500, 50, 20);
		setText(hotelNoText, 20, 280, 500, 50, 20);
		setText(roomNoText, 20, 360, 500, 50, 20);
		setText(priceText, 20, 440, 500, 50, 20);
		setText(textField, 20, 155, 500, 20, 14);

		// 회원가입 버튼 +80
		update.setBounds(20, 600, 500, 70);
		this.getContentPane().add(update);

		search.setBounds(20, 520, 500, 70);
		this.getContentPane().add(search);

		delete.setBounds(20, 680, 500, 70);
		this.getContentPane().add(delete);

		insert.setBounds(20, 760, 500, 70);
		this.getContentPane().add(insert);

	}

	private void addActionListener() {
		update.addActionListener(this);
		search.addActionListener(this);
		delete.addActionListener(this);
		insert.addActionListener(this);
		goBack.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == search) {
			// 버튼을 누르면 데이터를 확인함 조건을 충족시키지 못하면 옵션창이 뜨고
			// 충족되면 insert & option 회원가입에 성공하셨습니다 !!!!

			// 방번호로 호텔 정보 조회하기 메서드 호출
			bookService.searchRoom(this);
		
			roomIdText.setEnabled(false);

		} // end of search button
		else if (e.getSource() == update) {
			bookService.updateRoom(roomIdText.getText(), priceText.getText(), roomNoText.getText(), this);
		} else if (e.getSource() == delete) {
			bookService.deleteRoom(this);
		} else if (e.getSource() == insert) {
			bookService.insertRoom(this);
		} else if (e.getSource() == goBack) {
			dispose();
			new MasterFrame();
		}
	}

	private void setLabel(JLabel label, int x, int y, int w, int h) {
		label.setBounds(x, y, w, h);
		this.getContentPane().add(label);
		label.setFont(new Font(getName(), Font.PLAIN, 15));
	}

	private void setText(JTextField txt, int x, int y, int w, int h, int s) {
		txt.setBounds(x, y, w, h);
		this.getContentPane().add(txt);
		txt.setFont(new Font(getName(), Font.PLAIN, s));
	}

}

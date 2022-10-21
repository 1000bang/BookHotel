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

import bookHotel.RoundedButton;
import bookHotel.RoundedPass;
import bookHotel.RoundedTextField;
import bookHotel.utils.Define;

/*
 * hotelNo int AI PK 
hotelName varchar(20) 
address varchar(30) 
telPhone varchar(30)
 */
public class HotelUpdateFrame extends JFrame implements ActionListener {

	private JLabel logo;
	private JLabel hotelNo;
	private JLabel hotelName;
	private JLabel hotelAddress;
	private JLabel telPhone;
	private JLabel warningHotelNo;
	private JLabel warningHotelName;
	private JLabel warningMain;
	private JLabel warningMain_2;

	private RoundedTextField hotelNoText;
	private RoundedTextField hotelNameText;
	private RoundedTextField hotelAddressText;
	private RoundedTextField telPhoneText;

	private RoundedButton update;
	private RoundedButton search;
	private RoundedButton delete;
	private RoundedButton insert;
	private JButton goBack;

	public HotelUpdateFrame() {
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
		hotelName = new JLabel("호텔 이름");
		hotelAddress = new JLabel("호텔 주소");
		telPhone = new JLabel("호텔 전화 번호");

		hotelNoText = new RoundedTextField("");
		hotelNameText = new RoundedTextField("");
		hotelAddressText = new RoundedTextField("");
		telPhoneText = new RoundedTextField("");

		update = new RoundedButton("수정하기");
		search = new RoundedButton("조회하기");
		delete = new RoundedButton("삭제하기");
		insert = new RoundedButton("추가하기");

		warningHotelNo = new JLabel("* HotelNo는 ");
		warningHotelName = new JLabel("* 비밀번호는 5글자 이상 적어주세요. ");

		warningMain = new JLabel(" * 조회할 때는 호텔 이름만 입력하세요 ");
		warningMain_2 = new JLabel(" * 업데이트할 때는 호텔 번호를 비워두세요 ");

	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(null);
		getContentPane().setBackground(Color.white);
		
		goBack.setBounds(0,0,70,70);
		goBack.setBorderPainted(false);
		goBack.setContentAreaFilled(false);
		this.getContentPane().add(goBack);
		
		logo.setBounds(200, 0, 150, 100);
		this.getContentPane().add(logo);

		// 컴포넌트 위치
		warningMain.setForeground(Color.red);
		warningMain_2.setForeground(Color.red);
		setLabel(warningMain, 20, 100, 300, 20);
		setLabel(warningMain_2, 20, 130, 300, 20);

		setLabel(hotelNo, 20, 180, 100, 20);
		setLabel(hotelName, 20, 260, 100, 20);
		setLabel(hotelAddress, 20, 340, 100, 20);
		setLabel(telPhone, 20, 420, 100, 20);

		setText(hotelNoText, 20, 200, 500, 50);
		setText(hotelNameText, 20, 280, 500, 50);
		setText(hotelAddressText, 20, 360, 500, 50);
		setText(telPhoneText, 20, 440, 500, 50);

		// 회원가입 버튼
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
			if ((hotelNoText.getText().equals("")) == false) {
				JOptionPane.showMessageDialog(this, "호텔 번호로 " + Define.CANNOTSEARCH);
			} else if ((hotelAddressText.getText().equals("")) == false) {
				JOptionPane.showMessageDialog(this, "호텔 주소로 " + Define.CANNOTSEARCH);
			} else if ((telPhoneText.getText().equals("")) == false) {
				JOptionPane.showMessageDialog(this, "호텔 번호로 " + Define.CANNOTSEARCH);
			} else {
				// 호텔 이름으로 호텔 정보 조회하기 메서드 호출

				hotelNoText.setEnabled(false);
			}
		} // end of search button
		else if (e.getSource() == update) {

		} else if (e.getSource() == delete) {

		} else if (e.getSource() == insert) {

		}else if (e.getSource() == goBack) {
			dispose();
			new MasterFrame();
		}

	}

	private void setLabel(JLabel label, int x, int y, int w, int h) {
		label.setBounds(x, y, w, h);
		this.getContentPane().add(label);
		label.setFont(new Font(getName(), Font.PLAIN, 15));
	}

	private void setText(RoundedTextField txt, int x, int y, int w, int h) {
		txt.setBounds(x, y, w, h);
		this.getContentPane().add(txt);
		txt.setFont(new Font(getName(), Font.PLAIN, 20));
	}

}

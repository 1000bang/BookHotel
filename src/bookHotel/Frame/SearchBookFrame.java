package bookHotel.Frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bookHotel.BookService;
import bookHotel.RoundedButton;
import bookHotel.RoundedTextField;
import bookHotel.dto.ResponseInfo;
import lombok.Getter;

@Getter
public class SearchBookFrame extends JFrame implements ActionListener {

	/*
	 * reservationNumber int AI PK roomNo int hotelNo int userNo int
	 */

	// final
	private JButton goBack;
	private final String HOTEL = "호텔정보 조회";
	private final String BOOK = "예약정보 조회";
	private final String ROOM = "방정보 조회";
	private final String USER = "유저 조회";

	private JComboBox<String> combo;
	private JComboBox<String> combo2;
	private JComboBox<String> comboHotel;
	private JComboBox<String> comboBook;
	private JComboBox<String> comboRoom;
	private JComboBox<String> comboUser;
	String[] comboItem = { "---------------", HOTEL, BOOK, ROOM, USER };
	String[] comboItemNone = { "---" };
	String[] comboItemHotel = { "---", "호텔이름", "호텔번호" };
	String[] comboItemBook = { "---", "유저이름", "유저번호" };
	String[] comboItemRoom = { "---", "방고유번호" }; // 방아이디 방호수 가격
	String[] comboItemUser = { "---", "유저 이름", "유저 번호" };
	private RoundedButton search;
	private JTextField textField;
	private JTextField textField2;

	private JLabel info;
	private JLabel infonext;
	private JLabel info_2;
	private JLabel info_2next;
	private JLabel info_3;
	private JLabel info_3next;
	private JLabel info_4;
	private JLabel info_4next;
	private JLabel info_5;
	private JLabel info_5next;

	private JLabel logo;
	BookService bookService;
	ResponseInfo responseInfo;
	// JScrollPane sp = new JScrollPane();

	public SearchBookFrame() {
		initData();
		setInitLayout();
		addActionListener();
		// responseInfo.getInstance();
	}

	private void initData() {
		setTitle("메인 홈페이지");
		setSize(600, 1300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logo = new JLabel(new ImageIcon("images/logo.png"));
		goBack = new JButton(new ImageIcon("images/goback.png"));
		combo = new JComboBox<>(comboItem);
		combo2 = new JComboBox<>(comboItemNone);
		textField = new JTextField("");
		textField2 = new JTextField("");
		comboHotel = new JComboBox<>(comboItemHotel);
		comboBook = new JComboBox<>(comboItemBook);
		comboRoom = new JComboBox<>(comboItemRoom);
		comboUser = new JComboBox<>(comboItemUser);
		search = new RoundedButton("예약자 조회");
		info = new JLabel();
		infonext = new JLabel();
		info_2 = new JLabel();
		info_2next = new JLabel();
		info_3 = new JLabel();
		info_3next = new JLabel();
		info_4 = new JLabel();
		info_4next = new JLabel();
		info_5 = new JLabel();
		info_5next = new JLabel();
		bookService = new BookService();

	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(null);
		// 1. 로고 메인 패널에 붙이기
		this.getContentPane().setBackground(Color.white);

		goBack.setBounds(0, 0, 70, 70);
		goBack.setBorderPainted(false);
		goBack.setContentAreaFilled(false);
		this.getContentPane().add(goBack);
		logo.setBounds(200, 0, 150, 100);
		this.getContentPane().add(logo);

		setCompo(combo, 35, 150, 500, 20);
		setCompo(combo2, 35, 185, 80, 20);
		setCompo(textField, 120, 186, 420, 20);

		setCompo(comboHotel, 35, 185, 80, 20);
		setCompo(comboBook, 35, 185, 80, 20);
		setCompo(comboRoom, 35, 185, 80, 20);
		setCompo(comboUser, 35, 185, 80, 20);
		setCompo(search, 35, 450, 500, 70);

		setCompo(info, 35, 220, 80, 20);
		setCompo(info_2, 35, 250, 80, 20);
		setCompo(info_3, 35, 280, 80, 20);
		setCompo(info_4, 35, 310, 80, 20);
		setCompo(info_5, 35, 340, 80, 20);
		setCompo(infonext, 120, 220, 300, 20);
		setCompo(info_2next, 120, 250, 300, 20);
		setCompo(info_3next, 120, 280, 300, 20);
		setCompo(info_4next, 120, 310, 300, 20);
		setCompo(info_5next, 120, 340, 300, 20);
		// setCompo(textField2, 120, 340, 300, 20);

	}

	private void addActionListener() {

		search.addActionListener(this);
		goBack.addActionListener(this);

		combo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object getItem = e.getItem();
				if (getItem == HOTEL) {
					visible(false, false, false, false, true);
				} else if (getItem == BOOK) {
					visible(false, true, false, false, false);
				} else if (getItem == ROOM) {
					visible(false, false, true, false, false);
				} else if (getItem == USER) {
					visible(false, false, false, true, false);
				}
				repaint();

			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * 
		 *
		 */
		if (e.getSource() == search) {
			if (combo.getSelectedItem().equals(HOTEL)) {
				info.setText("호텔 번호");
				info_2.setText("호텔 이름");
				info_3.setText("보유 방의 수");
				info_4.setText("총 예약 수");
				System.out.println(combo2.getSelectedItem());
				System.out.println(comboHotel.getSelectedItem());

				if (comboHotel.getSelectedItem().equals("호텔이름")) {
					bookService.bookSearchByHotelName(this);
				} else if (comboHotel.getSelectedItem().equals("호텔번호")) {
					bookService.bookSearchByHotelNo(this);
				}

			} else if (combo.getSelectedItem().equals(BOOK)) {
				info.setText("회원 번호");
				info_2.setText("호텔 이름");
				info_3.setText("방 호수");
				info_4.setText("가격");
				if (comboBook.getSelectedItem().equals("유저이름")) {
					// 유저이름으로 검색
					bookService.reservationSearchByUserName(this);
				} else if (comboBook.getSelectedItem().equals("유저번호")) {
					// 유저번호로 검색
					bookService.reservationSearchByUserNo(this);
				}
			} else if (combo.getSelectedItem().equals(ROOM)) {
				info.setText("방 고유 번호");
				info_2.setText("방 호수 ");
				info_3.setText("호텔이름");
				info_4.setText("가격");
				if (comboRoom.getSelectedItem().equals("방고유번호")) {
					// 방 번호 검색
					bookService.roomInfoSearchByHotelNo(this);
				}
			} else if (combo.getSelectedItem().equals(USER)) {
				info.setText("유저 번호");
				info_2.setText("유저 이름");
				info_3.setText("전화번호");
				info_4.setText("생년월일");
				System.out.println("123");
				if (comboUser.getSelectedItem().equals("유저 이름")) {
					// 유저이름으로 검색
					bookService.userInfoSearchByUserName(this);
				} else if (comboUser.getSelectedItem().equals("유저 번호")) {
					// 유저번호로 검색
					bookService.userInfoSearchByUserNo(this);
				}
			}

		} else if (e.getSource() == goBack) {
			dispose();
			new MasterFrame();
		}
	}

	// 셋사이즈 셋로케 에드 메서드화 !!
	private void setCompo(JComponent label, int x, int y, int w, int h) {
		label.setBounds(x, y, w, h);
		this.getContentPane().add(label);
	}

	private void visible(boolean combo, boolean book, boolean room, boolean user, boolean hotel) {
		combo2.setVisible(combo);
		comboBook.setVisible(book);
		comboRoom.setVisible(room);
		comboUser.setVisible(user);
		comboHotel.setVisible(hotel);
	}

}

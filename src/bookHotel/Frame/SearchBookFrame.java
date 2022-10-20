package bookHotel.Frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bookHotel.RoundedButton;
import bookHotel.RoundedTextField;

public class SearchBookFrame extends JFrame implements ActionListener {

	/*
	 * reservationNumber int AI PK roomNo int hotelNo int userNo int
	 */

	private JComboBox<String> combo;
	private JComboBox<String> combo2;
	private JComboBox<String> comboHotel;
	private JComboBox<String> comboBook;
	private JComboBox<String> comboRoom;
	private JComboBox<String> comboUser;
	String[] comboItem = { "---------------", "호텔정보 조회 ", "예약정보 조회 ", "방정보 조회 ", "유저 조회 " };
	String[] comboItemNone = { "---" };
	String[] comboItemHotel = { "---", "호텔이름 ", "호텔번호 ", "방 호수 ", "방 고유번호 " };
	String[] comboItemBook = { "---", "예약  이름 ", "호텔번호 ", "방 호수 ", "방 고유번호 " };
	String[] comboItemRoom = { "---", "방  이름 ", "호텔번호 ", "방 호수 ", "방 고유번호 " };
	String[] comboItemUser = { "---", "유저 이름 ", "호텔번호 ", "방 호수 ", "방 고유번호 " };
	private RoundedButton search;
	private JTextField text;
	
	private JLabel Id;
	private JLabel name;
	private JLabel room;
	private JLabel username;

	private JLabel logo;

	// JScrollPane sp = new JScrollPane();

	public SearchBookFrame() {
		initData();
		setInitLayout();
		addActionListener();
	}

	private void initData() {
		setTitle("메인 홈페이지");
		setSize(600, 1300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logo = new JLabel(new ImageIcon("images/logo.png"));
		combo = new JComboBox<>(comboItem);
		combo2 = new JComboBox<>(comboItemNone);
		text = new JTextField("");
		comboHotel = new JComboBox<>(comboItemHotel);
		comboBook = new JComboBox<>(comboItemBook);
		comboRoom = new JComboBox<>(comboItemRoom);
		comboUser = new JComboBox<>(comboItemUser);
		search = new RoundedButton("예약자 조회");

	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(null);
		// 1. 로고 메인 패널에 붙이기
		this.getContentPane().setBackground(Color.white);
		logo.setBounds(200, 0, 150, 100);
		this.getContentPane().add(logo);

		setCompo(combo, 35, 150, 500, 30);
		setCompo(combo2, 35, 185, 80, 30);
		setCompo(text, 120, 190, 400, 20);

		setCompo(comboHotel, 35, 185, 80, 30);

		setCompo(comboBook, 35, 185, 80, 30);

		setCompo(comboRoom, 35, 185, 80, 30);

		setCompo(comboUser, 35, 185, 80, 30);

		setCompo(search, 35, 450, 500, 70);

	}

	private void addActionListener() {

		search.addActionListener(this);
		combo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object getItem = e.getItem();
				if (getItem == "호텔정보 조회 ") {
					visible(false, false, false, false, true);
				} else if (getItem == "예약정보 조회 ") {
					visible(false, true, false, false, false);
				} else if (getItem == "방정보 조회 ") {
					visible(false, false, true, false, false);
				} else if (getItem == "유저 조회 ") {
					visible(false, false, false, true, false);
				}repaint();

				
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
	
	
	public static void main(String[] args) {
		new SearchBookFrame();

	}

}

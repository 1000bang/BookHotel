package bookHotel.Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JComponent.AccessibleJComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import bookHotel.BookService;
import bookHotel.RoundedButton;
import bookHotel.RoundedPass;
import bookHotel.RoundedTextField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainPageFrame extends JFrame implements ActionListener {
	private JLabel logo;

	private JPanel panel;

	private JButton moveLeft;
	private JButton moveRight;
	private RoundedButtonHere userInfo;
	private RoundedButtonHere hotel;
	private RoundedButtonHere review;
	private RoundedButtonHere logout;

	// 리뷰란
	private JLabel reviewLabel;
	private JLabel writerLabel;
	private JLabel writerLabel_1;
	private JLabel hotelLabel;
	private JLabel hotelLabel_1;
	private JLabel contentslabel;
	private JTextArea contentsField;

	// 나의 정보란
	private JLabel userInfomation;
	private JLabel userNo;
	private JLabel userNoLabel;
	private JLabel userId;
	private JLabel userIdLabel;
	private JLabel userPw;
	private JLabel userName;
	private JLabel userNameLabel;
	private JLabel userPhoneNumber;
	private JLabel userPhoneNumberLabel;
	private JLabel userBirth;
	private JLabel userBirth_Label;

	private JButton hotelPanelMain;
	private JButton hotelPanelMainB;
	private ImageIcon hotelPanel3;
	private ImageIcon hotelPanel4;
	private ImageIcon hotelPanel5;
	private ImageIcon hotelPanel6;
	private ImageIcon hotelPanel7;
	private ImageIcon hotelPanel8;

	// JScrollPane sp = new JScrollPane();

	public MainPageFrame() {
		initData();
		setInitLayout();
		addActionListener();

	}

	private void initData() {
		setTitle("메인 홈페이지");
		setSize(600, 1300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logo = new JLabel(new ImageIcon("images/logo.png"));

		panel = new JPanel();

		hotelPanelMain = new JButton(new ImageIcon("images/hotel1.png"));
		hotelPanelMainB = new JButton(new ImageIcon("images/hotel1.png"));
		hotelPanel3 = new ImageIcon("images/hotel2.png");
		hotelPanel4 = new ImageIcon("images/hotel2.png");
		hotelPanel5 = new ImageIcon("images/hotel3.png");
		hotelPanel6 = new ImageIcon("images/hotel3.png");
		hotelPanel7 = new ImageIcon("images/hotel1.png");
		hotelPanel8 = new ImageIcon("images/hotel1.png");
		moveLeft = new JButton(new ImageIcon("images/left.png"));
		moveRight = new JButton(new ImageIcon("images/right.png"));
		hotel = new RoundedButtonHere("호텔 보기");
		review = new RoundedButtonHere("리뷰 관리");
		userInfo = new RoundedButtonHere("내 정보");
		logout = new RoundedButtonHere("로그 아웃");

		// 리뷰란
		reviewLabel = new JLabel("나의 리뷰");
		writerLabel = new JLabel("작성자 : ");
		writerLabel_1 = new JLabel("dsafsaf");
		hotelLabel = new JLabel("호텔 이름 : ");
		hotelLabel_1 = new JLabel("sdaf");
		contentslabel = new JLabel("작성글 ");
		contentsField = new JTextArea();

		// 나의정보란

		userInfomation = new JLabel("나의 정보");
		userNo = new JLabel("유저 번호");
		userNoLabel = new JLabel("ㅁㄴㅇㄴㅁㅇ");
		userId = new JLabel("유저 ID");
		userIdLabel = new JLabel("asdasd");
		userName = new JLabel("유저 이름");
		userNameLabel = new JLabel("132165");
		userPhoneNumber = new JLabel("휴대폰 번호");
		userPhoneNumberLabel = new JLabel("00230");
		userBirth = new JLabel("생년월일");
		userBirth_Label = new JLabel("12546");

	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(null);
		this.getContentPane().setBackground(Color.white);
		logo.setBounds(200, 0, 150, 100);
		this.add(logo);

		// 탭 만들기
		hotel.setBounds(110, 120, 70, 30);
		this.add(hotel);
		review.setBounds(180, 120, 70, 30);
		this.add(review);
		userInfo.setBounds(250, 120, 70, 30);
		this.add(userInfo);
		logout.setBounds(320, 120, 70, 30);
		this.add(logout);

		// 로고 메인 패널에 붙이기
		panel.setLayout(null);
		panel.setBounds(110, 150, 350, 760);
		panel.setBackground(Color.white);
		panel.setBorder(new EtchedBorder(Color.GRAY, Color.DARK_GRAY));
		this.getContentPane().add(panel);

		// 좌우 버튼 붙이기
		moveLeft.setBorderPainted(false);
		moveLeft.setContentAreaFilled(false);
		moveRight.setBorderPainted(false);
		moveRight.setContentAreaFilled(false);
		moveLeft.setBounds(0, 400, 80, 150);
		this.add(moveLeft);
		moveRight.setBounds(490, 400, 80, 150);
		this.add(moveRight);

		// 호텔 사진 붙이기
		hotelPanelMain.setBorder(new EtchedBorder(Color.GRAY, Color.DARK_GRAY));
		hotelPanelMain.setContentAreaFilled(false);
		hotelPanelMainB.setBorder(new EtchedBorder(Color.GRAY, Color.DARK_GRAY));
		hotelPanelMainB.setContentAreaFilled(false);
		setCompo(hotelPanelMain, 0, 0, 350, 380, panel);
		setCompo(hotelPanelMainB, 0, 380, 350, 380, panel);

		// review part

		review(reviewLabel, 25, 20, 20, 300, 30);
		review(writerLabel, 12, 20, 80, 80, 30);
		review(writerLabel_1, 12, 110, 80, 300, 30);
		review(hotelLabel, 12, 20, 120, 80, 30);
		review(hotelLabel_1, 12, 110, 120, 300, 30);
		review(contentslabel, 12, 20, 160, 300, 30);

		contentsField.setVisible(false);
		contentsField.setBackground(Color.LIGHT_GRAY);
		contentsField.setBorder(new EtchedBorder(Color.GRAY, Color.DARK_GRAY));
		contentsField.setBounds(20, 200, 300, 400);
		panel.add(contentsField);

		// userinfo
		review(userInfomation, 25, 20, 20, 300, 30);
		
		review(userNo, 15, 20, 100, 80, 30);
		review(userNoLabel, 15, 110, 100, 300, 30);
		review(userId, 15, 20, 140, 80, 30);
		review(userIdLabel, 15, 110, 140, 300, 30);
		review(userName, 15, 20, 180, 300, 30);
		review(userNameLabel, 15, 110, 180, 300, 30);
		review(userPhoneNumber, 15, 20, 220, 300, 30);
		review(userPhoneNumberLabel, 15, 110, 220, 300, 30);
		review(userBirth, 15, 20, 260, 300, 30);
		review(userBirth_Label, 15, 110, 260, 300, 30);

	}

	private void review(JLabel label, int size, int x, int y, int w, int h) {
		label.setVisible(false);
		label.setFont(new Font(getName(), Font.BOLD, size));
		label.setBounds(x, y, w, h);
		panel.add(label);
	}

	private void addActionListener() {
		moveLeft.addActionListener(this);
		moveRight.addActionListener(this);
		hotel.addActionListener(this);
		review.addActionListener(this);
		userInfo.addActionListener(this);
		logout.addActionListener(this);
		hotelPanelMain.addActionListener(this);
		hotelPanelMainB.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == moveLeft) {
			hotelPanelMain.setIcon(hotelPanel3);
			hotelPanelMainB.setIcon(hotelPanel5);
		} else if (e.getSource() == moveRight) {
			hotelPanelMain.setIcon(hotelPanel6);
			hotelPanelMainB.setIcon(hotelPanel7);

		} else if (e.getSource() == logout) {
			dispose();
			new LoginFrame();
		} else if (e.getSource() == hotel) {
			hotelVisible(true);
			reviewVisible(false);
			userVisible(false);

		} else if (e.getSource() == review) {
			
			hotelVisible(false);
			reviewVisible(true);
			userVisible(false);

		} else if (e.getSource() == userInfo) {
			hotelVisible(false);
			reviewVisible(false);
			userVisible(true);

		} else if (e.getSource() == hotelPanelMain) {
			new BookFrame();
		}else if (e.getSource() == hotelPanelMainB) {
			new BookFrame();
		}
		repaint();

	}

	public void reviewVisible(boolean vis) {
		reviewLabel.setVisible(vis);
		writerLabel.setVisible(vis);
		hotelLabel.setVisible(vis);
		reviewLabel.setVisible(vis);
		contentslabel.setVisible(vis);
		contentsField.setVisible(vis);
		writerLabel_1.setVisible(vis);
		hotelLabel_1.setVisible(vis);
	}

	public void hotelVisible(boolean vis) {
		moveLeft.setVisible(vis);
		moveRight.setVisible(vis);
		hotelPanelMain.setVisible(vis);
		hotelPanelMainB.setVisible(vis);
	}

	public void userVisible(boolean vis) {

		userInfomation.setVisible(vis);
		userNo.setVisible(vis);
		userNoLabel.setVisible(vis);
		userId.setVisible(vis);
		userIdLabel.setVisible(vis);
		userName.setVisible(vis);
		userNameLabel.setVisible(vis);
		userPhoneNumber.setVisible(vis);
		userPhoneNumberLabel.setVisible(vis);
		userBirth.setVisible(vis);
		userBirth_Label.setVisible(vis);

	}

	// 셋사이즈 셋로케 에드 메서드화 !!
	private void setCompo(JComponent label, int x, int y, int w, int h, JPanel panel) {
		label.setBounds(x, y, w, h);
		panel.add(label);
	}

	public class RoundedButtonHere extends JButton {

		public RoundedButtonHere() {
			super();
			decorate();
		}

		public RoundedButtonHere(String text) {
			super(text);
			decorate();
		}

		public RoundedButtonHere(Action action) {
			super(action);
			decorate();
		}

		public RoundedButtonHere(Icon icon) {
			super(icon);
			decorate();
		}

		public RoundedButtonHere(String text, Icon icon) {
			super(text, icon);
			decorate();
		}

		protected void decorate() {
			setBorderPainted(false);
			setOpaque(false);
		}

		@Override
		protected void paintComponent(Graphics g) {
			Color c = new Color(255, 153, 204); // 배경색 결정
			Color o = new Color(255, 255, 255); // 글자색 결정
			int width = getWidth();
			int height = getHeight();
			Graphics2D graphics = (Graphics2D) g;
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			if (getModel().isArmed()) {
				graphics.setColor(c.darker());
			} else if (getModel().isRollover()) {
				graphics.setColor(c.brighter());
			} else {
				graphics.setColor(c);
			}
			graphics.fillRoundRect(0, 0, width, height, 10, 10);
			FontMetrics fontMetrics = graphics.getFontMetrics();
			Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
			int textX = (width - stringBounds.width) / 2;
			int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
			graphics.setColor(o);
			graphics.setFont(new Font(getName(), Font.BOLD, 15));
			graphics.drawString(getText(), textX - 10, textY);
			graphics.dispose();
			super.paintComponent(g);
		}
	}

	public static void main(String[] args) {
		new MainPageFrame();

	}

}
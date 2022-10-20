package bookHotel.Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
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
import javax.swing.ScrollPaneConstants;
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

	private JButton hotelPanel1;
	private JButton hotelPanel2;
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
	
		hotelPanel1 = new JButton(new ImageIcon("images/hotel1.png"));
		hotelPanel2 = new JButton(new ImageIcon("images/hotel1.png"));
		hotelPanel3 = new ImageIcon("images/hotel2.png");
		hotelPanel4 = new ImageIcon("images/hotel2.png");
		hotelPanel5 = new ImageIcon("images/hotel3.png");
		hotelPanel6 = new ImageIcon("images/hotel3.png");
		hotelPanel7 = new ImageIcon("images/hotel1.png");
		hotelPanel8 = new ImageIcon("images/hotel1.png");
		moveLeft = new JButton(new ImageIcon("images/left.png"));
		moveRight = new JButton(new ImageIcon("images/right.png"));
	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(null);
		this.getContentPane().setBackground(Color.white);
		logo.setBounds(200, 0, 150, 100);
		this.add(logo);

		// 1. 로고 메인 패널에 붙이기

		panel.setBounds(30, 100, 520, 1400);
		panel.setBackground(Color.white);
		this.getContentPane().add(panel);

		panel.setLayout(null);
		moveLeft.setBorderPainted(false);
		moveLeft.setContentAreaFilled(false);
		moveRight.setBorderPainted(false);
		moveRight.setContentAreaFilled(false);
		setCompo(moveLeft, 0, 250, 80, 150, panel);
		setCompo(moveRight, 440, 250, 80, 150, panel);
		setCompo(hotelPanel1, 80, 0, 350, 380, panel);
		setCompo(hotelPanel2, 80, 380, 350, 380, panel);
//		setCompo(hotelPanel3, 0, 250, 240, 250, panel);
//		setCompo(hotelPanel4, 240, 250, 240, 250, panel);
//		setCompo(hotelPanel5, 0, 500, 240, 250, panel);
//		setCompo(hotelPanel6, 0, 500, 240, 250, panel);	
//		setCompo(hotelPanel7, 0, , 240, 250, panel);
//		setCompo(hotelPanel8, 0, 0, 240, 250, panel);

	}

	private void addActionListener() {
		moveLeft.addActionListener(this);
		moveRight.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * 로그인버튼을 누르면 BookSerivce의 selectLoginInfo메서드를 호출 id text와 pw text의 의 값과 일치하는
		 * 데이터가 있으면 mainpageFrame으로 이동
		 *
		 * 회원가입 버튼을 누르면 회원가입 창으로 이동
		 *
		 */
		if (e.getSource() == moveLeft) {
			hotelPanel1.setIcon(hotelPanel3);
			hotelPanel2.setIcon(hotelPanel5);
		} else if (e.getSource() == moveRight) {
			hotelPanel1.setIcon(hotelPanel6);
			hotelPanel2.setIcon(hotelPanel7);

		}

	}

	// 셋사이즈 셋로케 에드 메서드화 !!
	private void setCompo(JComponent label, int x, int y, int w, int h, JPanel panel) {
		label.setBounds(x, y, w, h);
		panel.add(label);
	}

	public static void main(String[] args) {
		new MainPageFrame();

	}

}
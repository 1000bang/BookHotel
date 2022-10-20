package bookHotel.Frame;

import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
	private JPanel panel_main;

	private JLabel image1;
	private JLabel image2;
	private JLabel image3;
	private JLabel image4;
	private JLabel image5;
	private JLabel image6;
	private JLabel image7;
	private JLabel image8;

//	private JButton image1_1;
//	private JButton image2_1;
//	private JButton image3_1;
//	private JButton image4_1;
//	private JButton image5_1;
//	private JButton image6_1;
//	private JButton image7_1;
//	private JButton image8_1;

	private JButton hotelPanel1;
	private JButton hotelPanel2;
	private JButton hotelPanel3;
	private JButton hotelPanel4;
	private JButton hotelPanel5;
	private JButton hotelPanel6;
	private JButton hotelPanel7;
	private JButton hotelPanel8;

	JScrollPane scroll;

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
		panel_main = new JPanel();
		scroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		hotelPanel1 = new JButton(new ImageIcon("images/hotel1.png"));
//		hotelPanel2 = new JButton(new ImageIcon("images/hotel1.png"));
//		hotelPanel3 = new JButton(new ImageIcon("images/hotel1.png"));
//		hotelPanel4 = new JButton(new ImageIcon("images/hotel1.png"));
//		hotelPanel5 = new JButton(new ImageIcon("images/hotel1.png"));
//		hotelPanel6 = new JButton(new ImageIcon("images/hotel1.png"));
//		hotelPanel7 = new JButton(new ImageIcon("images/hotel1.png"));
//		hotelPanel8 = new JButton(new ImageIcon("images/hotel1.png"));
//		
//
//		hotelPanel1.setBounds(0, 0, 240, 250);
//		hotelPanel2.setBounds(240, 0, 240, 250);
//		hotelPanel3.setBounds(0, 250, 240, 250);
//		hotelPanel4.setBounds(240, 250, 240, 250);
//		hotelPanel5.setBounds(0, 500, 240, 250);
//		hotelPanel6.setBounds(240, 500, 240, 250);
//		hotelPanel7.setBounds(0, 750, 240, 250);
//		hotelPanel8.setBounds(240, 750, 240, 250);
		ImageIcon icon = new ImageIcon("images/hotel1.png");
		image1 = new JLabel(new ImageIcon("images/hotel1.png"));
		image2 = new JLabel(new ImageIcon("images/hotel1.png"));
		image3 = new JLabel(new ImageIcon("images/hotel1.png"));
		image4 = new JLabel(new ImageIcon("images/hotel1.png"));
		image5 = new JLabel(new ImageIcon("images/hotel1.png"));
		image6 = new JLabel(new ImageIcon("images/hotel1.png"));
		image7 = new JLabel(new ImageIcon("images/hotel1.png"));
		image8 = new JLabel(new ImageIcon("images/hotel1.png"));
		


		image1.setBounds(0, 0, 240, 250);
		image2.setBounds(240, 0, 240, 250);
		image3.setBounds(0, 250, 240, 250);
		image4.setBounds(240, 250, 240, 250);
		image5.setBounds(0, 500, 240, 250);
		image6.setBounds(240, 500, 240, 250);
		image7.setBounds(0, 750, 240, 250);
		image8.setBounds(240, 750, 240, 250);

		
	}

	private void setInitLayout() {

		setLayout(null);
		panel.setLayout(null);

//		scroll.add(hotelPanel1);
//		scroll.add(hotelPanel2);
//		scroll.add(hotelPanel3);
//		scroll.add(hotelPanel4);
//		scroll.add(hotelPanel5);
//		scroll.add(hotelPanel6);
//		scroll.add(hotelPanel7);
//		scroll.add(hotelPanel8);



		// 1. 로고 메인 패널에 붙이기
		this.getContentPane().setBackground(Color.white);
		this.add(logo);
		logo.setBounds(200, 0, 150, 100);
		this.add(panel);
		panel.setBounds(40, 80, 500, 2000);
		add(panel);
//		scroll.setBackground(Color.white);
//		scroll.setBounds(0, 0, 500, 800);
		

		// scroll.setForeground(Color.white);

		// scroll.setBorder(new TitledBorder(new MatteBorder(5, 5, 5, 5,
		// Color.LIGHT_GRAY), "호텔 검색 "));
		scroll.createHorizontalScrollBar();
		scroll.setOpaque(false);
		
//	scroll.insets(image1);
		scroll.add(image2);
		scroll.add(image3);
		scroll.add(image4);
		scroll.add(image5);
		scroll.add(image6);
		scroll.add(image7);
		scroll.add(image8);
		setVisible(true);
		
		setContentPane(scroll);
		
	}

	


	private void addActionListener() {
//		image1_1.addActionListener(this);
//		image2_1.addActionListener(this);
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
		if (e.getSource() == hotelPanel1) {

		} else if (e.getSource() == hotelPanel2) {
			dispose();
			new JoinFrame();
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
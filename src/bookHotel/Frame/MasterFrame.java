package bookHotel.Frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bookHotel.RoundedButton;

public class MasterFrame extends JFrame implements ActionListener {
	
	private RoundedButton hotelUpdate;
	private RoundedButton roomUpdate;
	private RoundedButton none;
	private RoundedButton search;

	private JLabel logo;
	private JButton goBack;
 
	public MasterFrame() {
		initData();
		setInitLayout();
		addActionListener();
	}

	private void initData() {
		setTitle("메인 홈페이지");
		setSize(600, 1300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logo = new JLabel(new ImageIcon("images/logo.png"));
		goBack = new JButton(new ImageIcon("images/goback.png"));
		hotelUpdate = new RoundedButton("호텔 정보 수정하기");
		roomUpdate = new RoundedButton("객실 정보 수정하기");
		search = new RoundedButton("정보 검색하기");
		none = new RoundedButton("----");
		

	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(null);
		// 1. 로고 메인 패널에 붙이기
		this.getContentPane().setBackground(Color.white);
		goBack.setBounds(0,0,70,70);
		goBack.setBorderPainted(false);
		goBack.setContentAreaFilled(false);
		this.getContentPane().add(goBack);
		
		logo.setBounds(200, 0, 150, 100);
		this.getContentPane().add(logo);
		
		setCompo(hotelUpdate, 35, 150,  500, 70);
		setCompo(roomUpdate, 35, 250,  500, 70);
		setCompo(none, 35, 450,  500, 70);
		setCompo(search, 35, 350,  500, 70);
		
	}

	private void addActionListener() {
		hotelUpdate.addActionListener(this);
		roomUpdate.addActionListener(this);
		search.addActionListener(this);
		none.addActionListener(this);
		goBack.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * 각 버튼의 프레임이 켜지면 창 닫기 dispose
		 *
		 */
		if (e.getSource() == hotelUpdate) {
			//호텔 수정 
			dispose();
			new HotelUpdateFrame();
			
		} else if (e.getSource() == roomUpdate) {
			//룸수정
			dispose();
			new RoomUpdateFrame();
			
		}else if (e.getSource() == search) {
			//조회
			dispose();
			new SearchBookFrame();
			
		}else if (e.getSource() == none) {
			
		}
		
		else if (e.getSource() == goBack) {
			dispose();
			new MainFrame();
		}

	}

	// 셋사이즈 셋로케 에드 메서드화 !!
	private void setCompo(JComponent label, int x, int y, int w, int h) {
		label.setBounds(x, y, w, h);
		this.getContentPane().add(label);
	}


}

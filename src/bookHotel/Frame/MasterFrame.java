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

	private RoundedButton image1_1;
	private RoundedButton image2_1;
	private RoundedButton image3_1;
	private RoundedButton image4_1;
	private RoundedButton image5_1;

	private JLabel logo;

	// JScrollPane sp = new JScrollPane();

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

		image1_1 = new RoundedButton("호텔 정보 수정하기");
		image2_1 = new RoundedButton("객실 정보 수정하기");
		image3_1 = new RoundedButton("객실 정보 삭제하기");
		image4_1 = new RoundedButton("예약자 조회");

	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(null);
		// 1. 로고 메인 패널에 붙이기
		this.getContentPane().setBackground(Color.white);
		logo.setBounds(200, 0, 150, 100);
		this.getContentPane().add(logo);
		
		setCompo(image1_1, 35, 150,  500, 70);
		setCompo(image2_1, 35, 250,  500, 70);
		setCompo(image3_1, 35, 350,  500, 70);
		setCompo(image4_1, 35, 450,  500, 70);
		
	}

	private void addActionListener() {
		image1_1.addActionListener(this);
		image2_1.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * 
		 *
		 */
		if (e.getSource() == image1_1) {
			//호텔 수정 
			
			
		} else if (e.getSource() == image2_1) {
			//룸수정
			dispose();
			new RoomUpdateFrame();
			
		}

	}

	// 셋사이즈 셋로케 에드 메서드화 !!
	private void setCompo(JComponent label, int x, int y, int w, int h) {
		label.setBounds(x, y, w, h);
		this.getContentPane().add(label);
	}

	public static void main(String[] args) {
		new MasterFrame();

	}

}

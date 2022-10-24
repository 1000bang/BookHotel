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
import javax.swing.JPanel;
import javax.swing.JTextField;

import bookHotel.BookService;
import bookHotel.RoundedButton;
import bookHotel.RoundedTextField;
import bookHotel.Frame.UserHomeFrame.RoundedButtonHere;
import bookHotel.utils.Define;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookFrame extends JFrame implements ActionListener{
	private JLabel logo;
	
	private JLabel hotelName;
	private JLabel hotelNameText;
	private JLabel roomName;
	private JTextField roomNameText;
	JLabel reservationDate;
	BookService bookService;
	JButton cal;
	private JButton reservation ;
	
	public BookFrame() {
		initData();
		setInitLayout();
		addActionListener();
		this.bookService = BookService.getInstance();
	}

	private void initData() {
		setTitle("메인 홈페이지");
		setSize(600, 500);
		logo = new JLabel(new ImageIcon("images/logo.png"));
		
		hotelName = new JLabel("호텔 이름 ");
		hotelNameText = new JLabel("asdsa");
		roomName = new JLabel(" 방 번호 ");
		roomNameText = new JTextField();
		cal = new JButton("날짜 선택 ");
		reservationDate = new JLabel("");
		
		reservation = new JButton(new ImageIcon("images/bookButton.png"));
		
		
		
	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(null);
		this.getContentPane().setBackground(Color.white);
		logo.setBounds(200, 0, 150, 100);
		this.add(logo);
		
		setCompo(hotelName, 60, 150, 100, 20);
		hotelName.setFont(new Font(getName(), Font.BOLD, 15));
		setCompo(hotelNameText, 200, 150, 200, 20);
	
	
		setCompo(roomName, 60, 210, 100, 20);
		roomName.setFont(new Font(getName(), Font.BOLD, 15));
		setCompo(roomNameText, 190, 210, 200, 20);
		
		
		setCompo(cal, 30, 270, 120, 20);
		cal.setFont(new Font(getName(), Font.BOLD, 15));
		cal.setBorderPainted(false);
		cal.setContentAreaFilled(false);
		setCompo(reservationDate, 200, 270, 200, 20);
		
		
		
		
		setCompo(reservation, 200, 370, 170, 75);
		reservation.setBorderPainted(false);
		reservation.setContentAreaFilled(false);
	}
	
	
	private void addActionListener() {
		cal.addActionListener(this);
		reservation.addActionListener(this);
	}
	 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cal) {
			new CalendarFrame(this);
		}else if (e.getSource() == reservation) {
			bookService.book(this);
			
		}
		
	}
	
	private void setCompo(JComponent label, int x, int y, int w, int h) {
		label.setBounds(x, y, w, h);
		this.add(label);
	}

	
}

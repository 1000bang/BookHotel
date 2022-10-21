package bookHotel.Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import bookHotel.BookService;
import bookHotel.RoundedButton;
import bookHotel.RoundedTextField;
import bookHotel.utils.Define;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookFrame extends JFrame {

	public BookFrame() {
		initData();
		setInitLayout();
		addActionListener();
	}

	private void initData() {
		setTitle("메인 홈페이지");
		setSize(600, 500);
		
	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(null);
	}
	
	
	private void addActionListener() {
		
	}
}

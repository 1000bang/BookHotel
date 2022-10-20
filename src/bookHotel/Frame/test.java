package bookHotel.Frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class test extends JFrame{

	public test() {
		setBounds(100,100,450,300);
		Container controlHost = getContentPane();
		controlHost.setLayout(new BorderLayout());
		
		JPanel txtgrid = new JPanel();
		GridLayout gl = new GridLayout(25, 40);
		txtgrid.setLayout(gl);
		for (int i = 0; i < 10001; i++) {
			txtgrid.add(new JTextField(String.valueOf(i)));
		}
		
		JScrollPane jsp = new JScrollPane(txtgrid);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		controlHost.add(jsp);
		
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		new test();

	}

}

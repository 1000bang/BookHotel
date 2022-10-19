package bookHotel.Frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import bookHotel.RoundedButton;
import bookHotel.RoundedPass;
import bookHotel.RoundedTextField;

public class JoinFrame extends JFrame implements ActionListener {

	private JLabel logo;
	private JLabel idOrEmail;
	private JLabel passWord;
	private JLabel passWordCheck;
	private JLabel name;
	private JLabel phoneNumber;
	private JLabel birth;
	private JLabel warning;
	private JLabel warningmsg;
	
	private RoundedTextField idText;
	private RoundedPass pwText;
	private RoundedPass pwcheck;
	private RoundedTextField nameText;
	private RoundedTextField phoneNumberText;
	private RoundedTextField birthText;

	private RoundedButton join;

	// JScrollPane sp = new JScrollPane();

	public JoinFrame() {
		initData();
		setInitLayout();
		addActionListener();

	}

	private void initData() {
		setTitle("LogIn");
		setSize(600, 1300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		logo = new JLabel(new ImageIcon("images/logo.png"));
		idOrEmail = new JLabel("아이디");
		passWord = new JLabel("비밀번호");
		passWordCheck = new JLabel("비밀번호 확인");
		name = new JLabel("이름");
		phoneNumber = new JLabel("전화번호");
		birth = new JLabel("생년월일");
		warning = new JLabel("* 비밀번호는 5글자 이상 적어주세요");
		warningmsg = new JLabel("* 비밀번호와 일치하는 문자를 적어주세요. ");
		idText = new RoundedTextField("아이디를 5글자 이상 적어주세요 ");
		nameText = new RoundedTextField("이름");
		phoneNumberText = new RoundedTextField("전화번호");
		birthText = new RoundedTextField("생년월일");
		pwcheck = new RoundedPass("비밀번호를 5글자 이상 적어주세요");
		pwText = new RoundedPass("비밀번호를 다시 적어주세요 ");
		join = new RoundedButton(" 회원가입");

	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(null);
		getContentPane().setBackground(Color.white);
		logo.setBounds(200, 0, 150, 100);
		this.getContentPane().add(logo);

		// 컴포넌트 위치
		setLabel(idOrEmail, 20, 150, 150, 20);
		setLabel(passWord, 20, 230, 100, 20);
		setLabel(passWordCheck, 20, 310, 100, 20);
		setLabel(name, 20, 390, 100, 20);
		setLabel(phoneNumber, 20, 470, 100, 20);
		setLabel(birth, 20, 550, 100, 20);
		setText(idText, 20, 170, 500, 50);
		setPw(pwText, 20, 255, 500, 50);
		setPw(pwcheck, 20, 330, 500, 50);
		setText(nameText, 20, 410, 500, 50);
		setText(phoneNumberText, 20, 490, 500, 50);
		setText(birthText, 20, 570, 500, 50);
		// 회원가입 버튼
		join.setBounds(20, 650, 500, 70);
		this.getContentPane().add(join);

	}

	private void addActionListener() {
		join.addActionListener(this);
		idText.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == join) {
			// 버튼을 누르면 데이터를 확인함 조건을 충족시키지 못하면 옵션창이 뜨고
			// 충족되면 insert & option 회원가입에 성공하셨습니다 !!!!
			if (idText.getText().length() < 5) {
				JOptionPane.showMessageDialog(this, "아이디가 너무 짧습니다.");
			} else if (pwText.getText().length() < 5) {
				JOptionPane.showMessageDialog(this, "비밀번호가 너무 짧습니다.");
			} else if ((pwText.getText().equals(pwcheck.getText())) == false) {
				JOptionPane.showMessageDialog(this, "비밀 번호가 일치하지 않습니다.");
			}

		}

	}

	private void setLabel(JLabel label, int x, int y, int w, int h) {
		label.setBounds(x, y, w, h);
		this.getContentPane().add(label);
		label.setFont(new Font(getName(), Font.PLAIN, 15));
	}

	private void setText(RoundedTextField txt, int x, int y, int w, int h) {
		txt.setBounds(x, y, w, h);
		this.getContentPane().add(txt);
		txt.setFont(new Font(getName(), Font.PLAIN, 20));
	}

	private void setPw(JPasswordField pw, int x, int y, int w, int h) {
		pw.setBounds(x, y, w, h);
		this.getContentPane().add(pw);
		pw.setFont(new Font(getName(), Font.PLAIN, 20));
	}

}

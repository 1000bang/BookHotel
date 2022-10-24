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

import bookHotel.BookService;
import bookHotel.RoundedButton;
import bookHotel.RoundedPass;
import bookHotel.RoundedTextField;
import lombok.Data;
import lombok.Getter;

public class JoinFrame extends JFrame implements ActionListener {

	private JLabel logo;
	private JButton goBack;
	private JLabel id;
	private JLabel passWord;
	private JLabel passWordCheck;
	private JLabel name;
	private JLabel phoneNumber;
	private JLabel birth;
	private JLabel warningId;
	private JLabel warningPw;
	private JLabel warningpwCheck;
	private JLabel warningName;

	private RoundedTextField idText;
	private RoundedPass pwText;
	private RoundedPass pwcheck;
	private RoundedTextField nameText;
	private RoundedTextField phoneNumberText;
	private RoundedTextField birthText;

	private RoundedButton join;

	BookService bookService;

	public JoinFrame() {
		initData();
		setInitLayout();
		addActionListener();
		this.bookService = BookService.getInstance();
	}

	public RoundedTextField getIdText() {
		return idText;
	}

	public void setIdText(RoundedTextField idText) {
		this.idText = idText;
	}

	public RoundedPass getPwText() {
		return pwText;
	}

	public void setPwText(RoundedPass pwText) {
		this.pwText = pwText;
	}

	public RoundedPass getPwcheck() {
		return pwcheck;
	}

	public void setPwcheck(RoundedPass pwcheck) {
		this.pwcheck = pwcheck;
	}

	public RoundedTextField getNameText() {
		return nameText;
	}

	public void setNameText(RoundedTextField nameText) {
		this.nameText = nameText;
	}

	public RoundedTextField getPhoneNumberText() {
		return phoneNumberText;
	}

	public void setPhoneNumberText(RoundedTextField phoneNumberText) {
		this.phoneNumberText = phoneNumberText;
	}

	public RoundedTextField getBirthText() {
		return birthText;
	}

	public void setBirthText(RoundedTextField birthText) {
		this.birthText = birthText;
	}

	private void initData() {
		setTitle("LogIn");
		setSize(600, 1300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		logo = new JLabel(new ImageIcon("images/logo.png"));
		goBack = new JButton(new ImageIcon("images/goback.png"));
		id = new JLabel("아이디");
		passWord = new JLabel("비밀번호");
		passWordCheck = new JLabel("비밀번호 확인");
		name = new JLabel("이름");
		phoneNumber = new JLabel("전화번호");
		birth = new JLabel("생년월일");

		idText = new RoundedTextField("");
		nameText = new RoundedTextField("");
		phoneNumberText = new RoundedTextField("");
		birthText = new RoundedTextField("");
		pwcheck = new RoundedPass("");
		pwText = new RoundedPass("");
		join = new RoundedButton(" 회원가입");

		warningId = new JLabel("* 아이디를 5글자 이상 적어주세요");
		warningPw = new JLabel("* 비밀번호는 5글자 이상 적어주세요. ");
		warningpwCheck = new JLabel("* 비밀번호와 일치하게 적어주세요. ");
		warningName = new JLabel("* 이름을 필수 값입니다.  ");
		
	}

	private void setInitLayout() {
		setVisible(true);
		setLayout(null);
		getContentPane().setBackground(Color.white);
		goBack.setBounds(0, 0, 70, 70);
		goBack.setBorderPainted(false);
		goBack.setContentAreaFilled(false);
		this.getContentPane().add(goBack);

		logo.setBounds(200, 0, 150, 100);
		this.getContentPane().add(logo);

		// 컴포넌트 위치
		setLabel(id, 20, 150, 100, 20);
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
		goBack.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == join) {
			// 버튼을 누르면 데이터를 확인함 조건을 충족시키지 못하면 옵션창이 뜨고
			// 충족되면 insert & option 회원가입에 성공하셨습니다 !!!!
			if (idText.getText().length() < 5) {
				JOptionPane.showMessageDialog(this, "아이디가 너무 짧습니다.");
				warningId.setForeground(Color.red);
				setLabel(warningId, 120, 150, 300, 20);
			} else if (idText.getText().length() >= 5) {
				warningId.setVisible(false);
				if (pwText.getText().length() < 5) {
					JOptionPane.showMessageDialog(this, "비밀번호가 너무 짧습니다.");
					warningPw.setForeground(Color.red);
					setLabel(warningPw, 120, 230, 300, 20);
				} else if (pwText.getText().length() >= 5) {
					warningPw.setVisible(false);
					if ((pwText.getText().equals(pwcheck.getText())) == false) {
						JOptionPane.showMessageDialog(this, "비밀 번호가 일치하지 않습니다.");
						warningpwCheck.setForeground(Color.red);
						setLabel(warningpwCheck, 120, 310, 300, 20);
					} else if (((pwText.getText().equals(pwcheck.getText())) == true)) {
						warningpwCheck.setVisible(false);
						if (nameText.getText().equals("")) {
							System.out.println("here");
							JOptionPane.showMessageDialog(this, "이름을 입력하지 않았습니다.");
							warningName.setForeground(Color.red);
							setLabel(warningName, 120, 410, 300, 20);
						} else if (nameText != null) { 
							warningName.setVisible(false);// 회원가입 완료
							JOptionPane.showMessageDialog(this, "회원가입 완료.");
							// insert 메서드 호 출
							bookService.signIn(this);
							dispose();
							new MainFrame();
						}
					}
				}
			}
		} // end of join if
		else if (e.getSource() == goBack) {
			dispose();
			new MainFrame();
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

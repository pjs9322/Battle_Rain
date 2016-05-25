package display_Set;

import java.awt.Button;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;

import display_Sub.Alart_Mass;
import display_Sub.User_Join;

public class Login extends display_Set {
	private static final long serialVersionUID = 1L;
	
	private TextField userID = new TextField(20);
	private TextField password = new TextField(20);
	private Button login_Button = new Button("로그인");
	private Button join_Button = new Button("회원가입");

	private Alart_Mass msg;
	private User_Join join;
	
	public Login() {
		super();
		this.add(userID);
		this.add(password);
		this.add(login_Button);
		this.add(join_Button);
	}

	@Override
	public void draw(Graphics g) {
//		g.drawImage(toolkit.getImage(constant.L_I_Route[0]), 0, 0, this);
//		g.drawImage(toolkit.getImage(constant.L_I_Route[1]), 770, 474, this);
	}

	@Override
	public void init_Parts() {		
		this.userID.setLocation(430, 470);
		this.userID.setSize(330, 45);
		this.userID.setFont(new Font("userID", 0, 36));
		
		this.password.setLocation(430, 530);
		this.password.setSize(330, 45);
		this.password.setFont(new Font("password", 0, 36));
		this.password.setEchoChar('●');

		this.login_Button.setLocation(770, 470);
		this.login_Button.setSize(120, 45);
		this.login_Button.addActionListener(this);
		this.login_Button.setActionCommand("user_Login");
		
		this.join_Button.setLocation(770, 530);
		this.join_Button.setSize(120, 45);
		this.join_Button.addActionListener(this);
		this.join_Button.setActionCommand("user_Join");
	}

	public void user_Login() {
		String alart = this.control.user_Login(userID.getText(), password.getText());
		if (alart != null) {
			if (this.msg == null) {
				this.msg = new Alart_Mass(alart);
			} else if (!this.msg.isDisplayable()) {
				this.msg = new Alart_Mass(alart);
			} else if (!this.msg.isFocused()) {
				this.msg.requestFocus();
			}
		}
	}

	public void user_Join() {
		if (this.join == null) {
			this.join = new User_Join(control);
		} else if (!this.join.isDisplayable()) {
			this.join = new User_Join(control);
		} else if (!this.join.isFocused()) {
			this.join.requestFocus();
		}
	}
}

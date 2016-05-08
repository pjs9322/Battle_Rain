package display_Set;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;

import main.constant;

public class Login extends display_Set {
	private static final long serialVersionUID = 1L;
	
	private TextField userText = new TextField(20);
	private TextField passwordText = new TextField(20);
	
	public Login() {
		this.add(userText);
		this.add(passwordText);
	}

	@Override
	public void draw(Graphics g) {
		this.label_Set();
		g.drawImage(toolkit.getImage(constant.L_I_Route[0]), 0, 0, this);
	}

	public void label_Set() {
		this.userText.setLocation(436, 473);
		this.userText.setSize(335, 47);
		this.userText.setFont(new Font("userID", 0, 36));
		
		this.passwordText.setLocation(436, 534);
		this.passwordText.setSize(335, 47);
		this.passwordText.setFont(new Font("passWord", 0, 36));
		this.passwordText.setEchoChar('��');
	}
}

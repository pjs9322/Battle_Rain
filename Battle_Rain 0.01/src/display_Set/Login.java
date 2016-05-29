package display_Set;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import main.constant;
import display_Sub.Alart_Mass;
import display_Sub.User_Join;

public class Login extends display_Set {
	private static final long serialVersionUID = 1L;
	
	private JTextField userID = new JTextField(15);
	private JPasswordField password = new JPasswordField(15);
	private JButton login_Button = new JButton();
	private JButton join_Button = new JButton();
	private JButton exit_Button = new JButton();

	private Alart_Mass msg;
	private User_Join join;
	
	public Login() {
		super();
		this.add(userID);
		this.add(password);
		this.add(login_Button);
		this.add(join_Button);;
		this.add(exit_Button);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(toolkit.getImage(constant.L_I_Route[0]), 0, 0, this);
	}

	@Override
	public void init_Parts() {
		this.userID.setBounds(385, 400, 150, 21);
		this.userID.setBorder(null);
		this.userID.registerKeyboardAction(this, "user_Login",
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), JComponent.WHEN_FOCUSED);
		
		this.password.setBounds(385, 425, 150, 21);
		this.password.setBorder(null);
		this.password.registerKeyboardAction(this, "user_Login",
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), JComponent.WHEN_FOCUSED);
	
		this.login_Button.setBounds(191, 494, 107, 36);
		this.login_Button.setBorderPainted(false);
		this.login_Button.setIcon(new ImageIcon(constant.L_I_Route[1]));
		this.login_Button.setPressedIcon(new ImageIcon(constant.L_I_Route[2]));
		this.login_Button.addActionListener(this);
		this.login_Button.setActionCommand("user_Login");

		this.join_Button.setBounds(344, 494, 107, 36);
		this.join_Button.setBorderPainted(false);
		this.join_Button.setIcon(new ImageIcon(constant.L_I_Route[3]));
		this.join_Button.setPressedIcon(new ImageIcon(constant.L_I_Route[4]));
		this.join_Button.addActionListener(this);
		this.join_Button.setActionCommand("user_Join");
		
		this.exit_Button.setBounds(491, 494, 107, 36);
		this.exit_Button.setBorderPainted(false);
		this.exit_Button.setIcon(new ImageIcon(constant.L_I_Route[5]));
		this.exit_Button.setPressedIcon(new ImageIcon(constant.L_I_Route[6]));
		this.exit_Button.addActionListener(this);
		this.exit_Button.setActionCommand("exit");
		
		this.userID.requestFocus();
	}

	@SuppressWarnings("deprecation")
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
		this.userID.setText(null);
		this.password.setText(null);
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

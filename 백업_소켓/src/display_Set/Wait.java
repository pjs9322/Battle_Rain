package display_Set;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import main.constant;
import model.Audio;
import display_Sub.Sub_Frame;

public class Wait extends display_Set {
	private static final long serialVersionUID = 1L;
	
	private ButtonGroup group = new ButtonGroup();
	private Vector<JRadioButton> chara_Button = new Vector<JRadioButton>();
	
	private JButton make_Room_Button = new JButton();
	private JButton search_Room_Button = new JButton();
	private JButton logout_Button = new JButton();
	private JButton exit_Button = new JButton();
	
	public Wait() {
		super();
		
		this.add(make_Room_Button);
		this.add(search_Room_Button);
		this.add(logout_Button);
		this.add(exit_Button);

		for (int i = 0; i < constant.C_I_Route.length; i++) {
			chara_Button.add(new JRadioButton());
			group.add(chara_Button.get(i));
			this.add(chara_Button.get(i));
		}
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(toolkit.getImage(constant.W_I_Route[0]), 0, 0, this);
		if (this.control != null) {
			g.drawImage(toolkit.getImage(constant.C_I_Route[(int) this.control.getUserData().get("icon")]), 0, 0, this);
			g.setColor(Color.WHITE);
			g.drawString((String) this.control.getUserData().get("name"), 90, 305);
		}
	}

	@Override
	public void init_Parts() {
		this.search_Room_Button.setBounds(656, 247, 107, 50);
		this.search_Room_Button.setBorderPainted(false);
		this.search_Room_Button.setIcon(new ImageIcon(constant.W_I_Route[1]));
		this.search_Room_Button.setPressedIcon(new ImageIcon(constant.W_I_Route[2]));
		this.search_Room_Button.addActionListener(this);
		this.search_Room_Button.setActionCommand("search_Room");
		
		this.make_Room_Button.setBounds(656, 326, 107, 50);
		this.make_Room_Button.setBorderPainted(false);
		this.make_Room_Button.setIcon(new ImageIcon(constant.W_I_Route[3]));
		this.make_Room_Button.setPressedIcon(new ImageIcon(constant.W_I_Route[4]));
		this.make_Room_Button.addActionListener(this);
		this.make_Room_Button.setActionCommand("make_Room");

		this.logout_Button.setBounds(656, 411, 107, 50);
		this.logout_Button.setBorderPainted(false);
		this.logout_Button.setIcon(new ImageIcon(constant.W_I_Route[5]));
		this.logout_Button.setPressedIcon(new ImageIcon(constant.W_I_Route[6]));
		this.logout_Button.addActionListener(this);
		this.logout_Button.setActionCommand("user_Logout");

		this.exit_Button.setBounds(656, 493, 107, 50);
		this.exit_Button.setBorderPainted(false);
		this.exit_Button.setIcon(new ImageIcon(constant.W_I_Route[7]));
		this.exit_Button.setPressedIcon(new ImageIcon(constant.W_I_Route[8]));
		this.exit_Button.addActionListener(this);
		this.exit_Button.setActionCommand("exit");
		
		for (int i = 0; i < constant.C_I_Route.length; i++) {
			int x = 61 + (i%2) * 85;
			int y = 352 + (i/2) * 56;
			chara_Button.get(i).setBounds(x, y, 75, 50);
			chara_Button.get(i).setContentAreaFilled(false);
			chara_Button.get(i).addActionListener(this);
			chara_Button.get(i).setActionCommand("chara_Change");
		}
	}
	
	public void make_Room() {
		if (this.subFrame == null) {
			this.subFrame = new Sub_Frame(control, "Room_Make");
		} else if (!this.subFrame.isDisplayable()) {
			this.subFrame = new Sub_Frame(control, "Room_Make");
		} else if (!this.subFrame.isFocused()) {
			this.subFrame.requestFocus();
		}
		if (!constant.delay) {
			new Audio(constant.M_Route[4], false);
			constant.delay = true;
		}
	}

	public void search_Room() {
		if (this.subFrame == null) {
			this.subFrame = new Sub_Frame(control, "Room_Search");
		} else if (!this.subFrame.isDisplayable()) {
			this.subFrame = new Sub_Frame(control, "Room_Search");
		} else if (!this.subFrame.isFocused()) {
			this.subFrame.requestFocus();
		}
		if (!constant.delay) {
			new Audio(constant.M_Route[4], false);
			constant.delay = true;
		}
	}

	public void user_Logout() {
		if (!constant.delay) {
			new Audio(constant.M_Route[4], false);
			constant.delay = true;
		}
		this.control.user_Logout();
	}
	
	public void chara_Change() {
		if (!constant.delay) {
			new Audio(constant.M_Route[3], false);
			for (int i = 0; i < constant.C_I_Route.length; i++) {
				if (chara_Button.get(i).isSelected()) {
					this.control.setChara(i);
					constant.delay = true;
					this.repaint();
					return;
				}
			}
			constant.delay = true;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

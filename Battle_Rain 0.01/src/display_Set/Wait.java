package display_Set;

import java.awt.Graphics;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import main.constant;
import display_Sub.Room_Make;
import display_Sub.Room_Search;

public class Wait extends display_Set {
	private static final long serialVersionUID = 1L;
	
	private ButtonGroup group = new ButtonGroup();
	private Vector<JRadioButton> chara_Button = new Vector<JRadioButton>();
	
	private JButton make_Room_Button = new JButton();
	private JButton search_Room_Button = new JButton();
	private JButton logout_Button = new JButton();
	private JButton exit_Button = new JButton();

	private Room_Make make;
	private Room_Search search;

	private int chara = 0;
	
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
		g.drawImage(toolkit.getImage(constant.C_I_Route[chara]), 0, 0, this);
	}

	@Override
	public void init_Parts() {
		this.search_Room_Button.setBounds(656, 177, 107, 50);
		this.search_Room_Button.setBorderPainted(false);
		this.search_Room_Button.setIcon(new ImageIcon(constant.W_I_Route[1]));
		this.search_Room_Button.setPressedIcon(new ImageIcon(constant.W_I_Route[2]));
		this.search_Room_Button.addActionListener(this);
		this.search_Room_Button.setActionCommand("search_Room");
		
		this.make_Room_Button.setBounds(656, 264, 107, 50);
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
		if (this.make == null) {
			this.make = new Room_Make(control);
		} else if (!this.make.isDisplayable()) {
			this.make = new Room_Make(control);
		} else if (!this.make.isFocused()) {
			this.make.requestFocus();
		}
	}

	public void search_Room() {
		if (this.search == null) {
			this.search = new Room_Search(control);
		} else if (!this.search.isDisplayable()) {
			this.search = new Room_Search(control);
		} else if (!this.search.isFocused()) {
			this.search.requestFocus();
		}
	}

	public void user_Logout() {
		this.control.user_Logout();
	}
	
	public void chara_Change() {
		for (int i = 0; i < constant.C_I_Route.length; i++) {
			if (chara_Button.get(i).isSelected()) {
				this.chara = i;
				this.repaint();
				return;
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

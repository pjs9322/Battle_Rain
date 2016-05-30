package display_Set;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import com.mongodb.DBObject;

import main.constant;
import model.Audio;
import display_Sub.Sub_Frame;

public class Wait extends display_Set {
	private static final long serialVersionUID = 1L;
	
	private ButtonGroup group = new ButtonGroup();
	private Vector<JRadioButton> chara_Button = new Vector<JRadioButton>();
	
	private JButton roomList1 = new JButton();
	private JButton roomList2 = new JButton();
	private JButton roomList3 = new JButton();
	private JButton roomList4 = new JButton();
	
	private JButton make_Room_Button = new JButton();
	private JButton search_Room_Button = new JButton();
	private JButton logout_Button = new JButton();
	private JButton exit_Button = new JButton();
	
	Vector<DBObject> roomList =  new Vector<DBObject>();
	
	public Wait() {
		super();

		this.add(roomList1);
		this.add(roomList2);
		this.add(roomList3);
		this.add(roomList4);
		
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
			
			this.roomList = this.getControl().readRoomList();
			for (int i = 0; i < roomList.size(); i++) {
				g.drawImage(toolkit.getImage(constant.W_I_Route[10]), 312, 161 + (i * 105), this);
				g.setColor(Color.BLACK);
				g.drawString((String) this.roomList.get(i).get("rname"), 445, 189 + (i * 105));
				if (((boolean) this.roomList.get(i).get("mode"))) {
					g.drawImage(toolkit.getImage(constant.W_I_Route[11]), 319, 169 + (i * 105), this);
				} else {
					g.drawImage(toolkit.getImage(constant.W_I_Route[12]), 319, 169 + (i * 105), this);
				}
				g.drawString((int) this.roomList.get(i).get("ucount") + "/4", 526, 222 + (i * 105));
				if ((int) this.roomList.get(i).get("ucount") >= 4) {
					g.drawImage(toolkit.getImage(constant.W_I_Route[13]), 390, 207 + (i * 105), this);
				} else {
					if ((int) this.roomList.get(i).get("state") != 0) {
						g.drawImage(toolkit.getImage(constant.W_I_Route[14]), 390, 207 + (i * 105), this);
					} else {
						g.drawImage(toolkit.getImage(constant.W_I_Route[15]), 390, 207 + (i * 105), this);
					}
				}
			}
		}
	}

	@Override
	public void init_Parts() {
		this.roomList1.setBounds(312, 161, 272, 81);
		this.roomList1.setContentAreaFilled(false);
		this.roomList1.addActionListener(this);
		this.roomList1.setActionCommand("enterRoom1");
		
		this.roomList2.setBounds(312, 266, 272, 81);
		this.roomList2.setContentAreaFilled(false);
		this.roomList2.addActionListener(this);
		this.roomList2.setActionCommand("enterRoom2");
		
		this.roomList3.setBounds(312, 371, 272, 81);
		this.roomList3.setContentAreaFilled(false);
		this.roomList3.addActionListener(this);
		this.roomList3.setActionCommand("enterRoom3");
		
		this.roomList4.setBounds(312, 476, 272, 81);
		this.roomList4.setContentAreaFilled(false);
		this.roomList4.addActionListener(this);
		this.roomList4.setActionCommand("enterRoom4");
		
		this.search_Room_Button.setBounds(656, 247, 107, 50);
		this.search_Room_Button.setBorderPainted(false);
		this.search_Room_Button.setIcon(new ImageIcon(constant.W_I_Route[1]));
		this.search_Room_Button.setPressedIcon(new ImageIcon(constant.W_I_Route[2]));
		this.search_Room_Button.addActionListener(this);
		this.search_Room_Button.setActionCommand("search_Room");
		
		this.make_Room_Button.setBounds(656, 328, 107, 50);
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
	public void enterRoom1() {
		if (this.roomList.get(0) != null) {
			this.getControl().Room_Search((String)this.roomList.get(0).get("rname"));
		}
	}
	
	public void enterRoom2() {
		if (this.roomList.get(1) != null) {
			this.getControl().Room_Search((String)this.roomList.get(1).get("rname"));
		}
	}
	
	public void enterRoom3() {
		if (this.roomList.get(2) != null) {
			this.getControl().Room_Search((String)this.roomList.get(2).get("rname"));
		}
	}
	
	public void enterRoom4() {
		if (this.roomList.get(3) != null) {
			this.getControl().Room_Search((String)this.roomList.get(3).get("rname"));
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
		while (true) {
			this.repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
	}
}

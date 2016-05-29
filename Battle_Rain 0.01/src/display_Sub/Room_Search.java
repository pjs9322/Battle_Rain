package display_Sub;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import main.constant;
import model.Audio;
import control.Control;

public class Room_Search extends Sub_Panel {
	private static final long serialVersionUID = 1L;

	private JTextField roomName = new JTextField(15);

	private JButton ok_Button = new JButton();
	private JButton cancel_Button = new JButton();
	
	public Room_Search() {
		super();
		
		this.add(roomName);
		this.add(ok_Button);
		this.add(cancel_Button);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(toolkit.getImage(constant.SF_I_Route[5]), 0, 0, this);
	}

	@Override
	public void init_Parts() {
		this.roomName.setBounds(115, 32, 150, 21);
		this.roomName.setBorder(null);
		this.roomName.registerKeyboardAction(this, "Search",
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), JComponent.WHEN_FOCUSED);

		this.ok_Button.setBounds(51, 81, 75, 34);
		this.ok_Button.setBorderPainted(false);
		this.ok_Button.setIcon(new ImageIcon(constant.SF_I_Route[0]));
		this.ok_Button.setPressedIcon(new ImageIcon(constant.SF_I_Route[1]));
		this.ok_Button.addActionListener(this);
		this.ok_Button.setActionCommand("Search");

		this.cancel_Button.setBounds(154, 81, 75, 34);
		this.cancel_Button.setBorderPainted(false);
		this.cancel_Button.setIcon(new ImageIcon(constant.SF_I_Route[2]));
		this.cancel_Button.setPressedIcon(new ImageIcon(constant.SF_I_Route[3]));
		this.cancel_Button.addActionListener(this);
		this.cancel_Button.setActionCommand("Cancel");
		
		this.roomName.requestFocus();
	}
	
	public void init_View(Sub_Frame sub_Frame, Control control) {
		super.init_View(sub_Frame, control);
		this.frame.setTitle("방 찾기");
		this.frame.setSize(295, 152);
	}

	public void Search() {
		String alart = this.control.Room_Search(this.roomName.getText());
		if (!constant.delay) {
			new Audio(constant.M_Route[4], false);
			if (alart != null) {
				JOptionPane.showMessageDialog(this, alart, "경고", 0);
			} else {
				this.frame.dispose();
			}
			constant.delay = true;
		}
	}
}
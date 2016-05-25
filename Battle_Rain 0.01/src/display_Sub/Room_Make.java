package display_Sub;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import control.control;

public class Room_Make extends Sub_Frame {
	private static final long serialVersionUID = 1L;

	private JLabel roomName = new JLabel("방 제목");
	private JTextField roomText = new JTextField(15);

	private JButton ok_Button = new JButton("입장");
	private JButton cancel_Button = new JButton("취소");
	
	public Room_Make(control control) {
		super(control, 250, 110);
		
		this.setTitle("방 만들기");
		
		this.panel_NORTH.add(roomName);
		this.panel_NORTH.add(roomText);

		this.panel_SOUTH.add(ok_Button);
		this.panel_SOUTH.add(cancel_Button);

		this.ok_Button.setText("생성");
		this.ok_Button.addActionListener(actionListener);
		this.ok_Button.setActionCommand("Make");
		
		this.cancel_Button.setText("취소");
		this.cancel_Button.addActionListener(actionListener);
		this.cancel_Button.setActionCommand("Cancel");
	}

	public void Make() {
		String alart = this.control.Room_Make(this.roomText.getText());
		if (alart != null) {
			JOptionPane.showMessageDialog(this, alart, "경고", 0);
		} else {
			this.dispose();
		}
	}
	
	public void Cancel() {
		this.dispose();
	}
}

package display_Sub;

import control.control;

public class Room_Make extends Sub_Frame {
	private static final long serialVersionUID = 1L;
	
	public boolean complete = false;
	
	public Room_Make(control control) {
		super(control);
		
		this.setTitle("�� �����");
		this.Sub_Lable.setName("�� ����");

		this.ok_Button.setText("����");
		this.ok_Button.addActionListener(actionListener);
		this.ok_Button.setActionCommand("Make");
		
		this.cancel_Button.setText("���");
		this.cancel_Button.addActionListener(actionListener);
		this.cancel_Button.setActionCommand("Cancel");
		
		this.repaint();
	}

	public void Make() {
		this.control.Room_Make();
		this.dispose();
	}
	
	public void Cancel() {
		this.dispose();
	}
}
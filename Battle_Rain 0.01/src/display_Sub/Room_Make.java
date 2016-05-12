package display_Sub;

import control.control;

public class Room_Make extends Sub_Frame {
	private static final long serialVersionUID = 1L;
	
	public boolean complete = false;
	
	public Room_Make(control control) {
		super(control);
		
		this.setTitle("规 父甸扁");
		this.Sub_Lable.setName("规 力格");

		this.ok_Button.setText("积己");
		this.ok_Button.addActionListener(actionListener);
		this.ok_Button.setActionCommand("Make");
		
		this.cancel_Button.setText("秒家");
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

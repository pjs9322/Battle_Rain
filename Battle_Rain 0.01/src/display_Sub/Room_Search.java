package display_Sub;

import control.control;

public class Room_Search extends Sub_Frame {
	private static final long serialVersionUID = 1L;
	
	public boolean complete = false;
	
	public Room_Search(control control) {
		super(control);

		this.setTitle("�� ã��");
		this.Sub_Lable.setName("�� ����");

		this.ok_Button.setText("����");
		this.ok_Button.addActionListener(actionListener);
		this.ok_Button.setActionCommand("Search");
		
		this.cancel_Button.setText("���");
		this.cancel_Button.addActionListener(actionListener);
		this.cancel_Button.setActionCommand("Cancel");
	}


	public void Search() {
		
	}
	
	public void Cancel() {
		
	}
}
package display_Sub;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Alart_Mass extends Sub_Frame {
	private static final long serialVersionUID = 1L;

	private JLabel alart = new JLabel();
	private JButton ok_Button = new JButton("가입");
	
	public Alart_Mass(String massage) {
		super(null, 250, 100);
		this.setTitle("경고");
		
		this.panel_NORTH.add(alart);
		this.panel_SOUTH.add(ok_Button);
		
		this.alart.setText(massage);

		this.ok_Button.setText("확인");
		this.ok_Button.addActionListener(actionListener);
		this.ok_Button.setActionCommand("Cancel");
	}
	
	public void Cancel() {
		this.dispose();
	}
}

package display_Sub;

import javax.swing.JFrame;

import main.constant;
import control.Control;

public class Sub_Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Sub_Panel panel;
	
	public Sub_Frame(Control control, String name) {
		
		Class<?> Next_Display = null;
			try {
				Next_Display = Class.forName(constant.S_Route + name);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				panel = (Sub_Panel) Next_Display.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			panel.init_View(this, control);
			this.add(panel);

		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
}

package display_Sub;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.control;

public class Sub_Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	protected ActionListener actionListener;
	protected control control;
	
	private Sub_Frame sub_style;

	protected JPanel panel_NORTH = new JPanel();
	protected JPanel panel_SOUTH = new JPanel();
	
	public Sub_Frame(control control, int sub_Width, int sub_Height) {
		this.actionListener = new ActionHandler();
		this.control = control;
		this.sub_style = this;

		this.add(panel_NORTH);
		this.add(panel_SOUTH, BorderLayout.SOUTH);
		
		this.setSize(sub_Width, sub_Height);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
				invoke(sub_style, e.getActionCommand(), null);
		}
	}

	public static Object invoke(Object obj, String methodName, Object[] parameter) {
		Method[] methods = obj.getClass().getMethods();
		
		for(Method method: methods) {
			if(method.getName().equals(methodName)) {
				try {
					if (method.getReturnType().getName().equals("void")) {
						method.invoke(obj, parameter);        
					} else {
						return method.invoke(obj, parameter);
					}
				} catch(IllegalAccessException | InvocationTargetException e) {}
			}
		}
		return null;
	}
}

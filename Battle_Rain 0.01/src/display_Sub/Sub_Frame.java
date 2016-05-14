package display_Sub;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.control;

public class Sub_Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	protected ActionListener actionListener;
	protected control control;

	private Sub_Frame sub_style;
	private JPanel panel = new JPanel();

	protected JLabel Sub_Lable = new JLabel();
	protected JTextField Sub_Text = new JTextField(15);
	
	protected JButton ok_Button = new JButton();
	protected JButton cancel_Button = new JButton();
	
	public Sub_Frame(control control) {
		this.actionListener = new ActionHandler();
		this.control = control;
		this.sub_style = this;

		this.panel.add(Sub_Lable);
		this.panel.add(Sub_Text);
		this.panel.add(ok_Button);
		this.panel.add(cancel_Button);
		this.add(panel);
		
		this.setSize(250, 120);
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
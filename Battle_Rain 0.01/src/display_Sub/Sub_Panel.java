package display_Sub;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JPanel;

import main.constant;
import model.Audio;
import control.Control;

public abstract class Sub_Panel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	protected Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	protected Sub_Frame frame;
	protected Control control;
	
	private Sub_Panel sub_style;
	
	public Sub_Panel() {
		this.sub_style = this;
	}

	public void paint(Graphics g) {
		this.draw(g);
		this.init_Parts();
	}
	
	public abstract void draw(Graphics g);
	public abstract void init_Parts();

	public void init_View(Sub_Frame sub_Frame, Control control) {
		// TODO Auto-generated method stub
		this.frame = sub_Frame;
		this.control = control;
	}
	
	public void Cancel() {
		if (!constant.delay) {
			new Audio(constant.M_Route[4], false);
			constant.delay = true;
		}
		this.frame.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		invoke(sub_style, e.getActionCommand(), null);
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
package display_Set;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JPanel;

import main.constant;
import model.Audio;
import view.Display;
import control.Control;
import display_Sub.Sub_Frame;

abstract public class display_Set extends JPanel implements ActionListener, Runnable {
	private static final long serialVersionUID = 1L;
	protected Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	protected Display display;
	protected Control control;
	public Control getControl() { return this.control; }
	
	protected Thread myThread;

	private display_Set view_style;
	
	protected Audio sound;
	protected Sub_Frame subFrame;

	private Image buff_Image;											// ���� ���۸��� ���� �̹���
	private Graphics buff_G;											// ���� ���۸��� ���� �׷���

	
	public display_Set() {
		this.view_style = this;
		this.myThread = new Thread(this);
	}

	public void paint(Graphics g) {
		buff_Image = createImage(constant.F_Width, constant.F_Width);	// ������ ��ü�� ������� ���� �̹����� ����
		buff_G = buff_Image.getGraphics();								// ������ �׷��� ��ü ȹ��
		update(g);														// �׷��� ������Ʈ
	}

	public void update(Graphics g) {
		Draw_Graphics();												// �׸� �����͸� ����
		g.drawImage(buff_Image, 0, 0, this);							// �׸� �������� ������ �Ϸ�� �� ���� �̹����� ��ü�Ѵ�.
	}

	public void Draw_Graphics() { // ������ �׸����� �׸� �κ�
		buff_G.clearRect(0, 0, constant.F_Width, constant.F_Width);		// ���� �ʱ�ȭ
		this.draw(buff_G);												// �ܺ� �̹��� �׸�
		this.init_Parts();												// ������� �߰�
	}

	public abstract void draw(Graphics g);
	public abstract void init_Parts();
	public void init_View(Display display, Control control) {
		this.display = display;
		this.control = control;
	}
	
	public void exit() {
		if (!constant.delay) {
			new Audio(constant.M_Route[4], false);
			constant.delay = true;
		}
		System.exit(1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			invoke(view_style, e.getActionCommand(), null);
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
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
import control.control;

public abstract class display_Set extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	protected Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	protected ActionListener actionListener;
	protected control control;

	private display_Set view_style;

	private Image buff_Image;											// 더블 버퍼링용 버퍼 이미지
	private Graphics buff_G;											// 더블 버퍼링용 버퍼 그래픽

	public display_Set() {
		this.view_style = this;
	}

	public void paint(Graphics g) {
		buff_Image = createImage(constant.F_Width, constant.F_Width);	// 프레임 전체를 기반으로 버퍼 이미지를 생성
		buff_G = buff_Image.getGraphics();								// 버퍼의 그래픽 객체 획득
		update(g);														// 그래픽 업데이트
	}

	public void update(Graphics g) {
		Draw_Graphics();												// 그림 데이터를 전송
		g.drawImage(buff_Image, 0, 0, this);							// 그림 데이터의 전송이 완료될 때 기존 이미지와 교체한다.
	}

	public void Draw_Graphics() { // 실제로 그림들을 그릴 부분
		buff_G.clearRect(0, 0, constant.F_Width, constant.F_Width);		// 버퍼 초기화
		this.draw(buff_G);												// 외부 이미지 그림
		this.init_Parts();												// 구성요소 추가
	}

	public abstract void draw(Graphics g);
	public abstract void init_Parts();
	public void init_View(control control) {this.control = control;}

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

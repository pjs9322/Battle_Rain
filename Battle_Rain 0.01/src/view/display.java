package view;

import javax.swing.JFrame;

import main.constant;
import main.constant.STATE;
import display_Set.display_Set;

public class display extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	private STATE state = STATE.Login;
	public display_Set Current_Display;
	private Thread thread;
	
	public void setState(STATE state) {
		this.state = state;
	}
	
	public display() {										// ������ �ۼ�
		thread = new Thread(this);							// ������ ����
		thread.start();										// ������ ����
		this.setTitle(constant.F_Title);						// ������ �̸� ����
		this.setSize(constant.F_Width, constant.F_Height); 	// ������ ������ ����
		this.setVisible(true);								// ������ ����ȭ �ɼ� ����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// â ����� ���α׷� ����
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {	
				// ���� state�� ���� �����ִ� display�� �޶�����.
		        Class<?> Next_Display = Class.forName(constant.V_Route + state.toString());
		        // ���� state ���� ���� Next_Display Ŭ������ ����
		        Current_Display = (display_Set) Next_Display.newInstance();
		        // Next_Display Ŭ������ ���� Ŭ���� ����� �ֵ��
				this.add(Current_Display);
				Thread.sleep(50);
				// 50 millisecond (0.05sec) �ֱ� ����
				repaint();
				// ���ŵ� �����Ϳ� ���� �̹��� ���� �ۼ�
			}
		} catch (Exception e) {}
		// ���ӵ� ������� ���� ���� �߻��� ������ run�̹Ƿ� ��� ������ ���� �ƹ��͵� ���� ����
	}
}
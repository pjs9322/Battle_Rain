package view;

import javax.swing.JFrame;

import main.constant;
import main.constant.STATE;
import model.Audio;
import control.Control;
import display_Set.display_Set;

public class Display extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private display_Set Current_Display;
	private Thread thread;
	
	private Control control;
	public Audio BGM;
	
	private STATE current_State;
	private STATE state = STATE.Login;
	public void setState(STATE state) {	this.state = state; }
	
	public Display() {										// ������ �ۼ�
		this.control = new Control();
		this.thread = new Thread(this);						// ������ ����
		this.thread.start();								// ������ ����
		this.setTitle(constant.F_Title);					// ������ �̸� ����
		this.setSize(constant.F_Width, constant.F_Height); 	// ������ ������ ����
		this.setLocationRelativeTo(null);					// ������ ���� �����
		this.setVisible(true);								// ������ ����ȭ �ɼ� ����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// â ����� ���α׷� ����
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				// ���� state�� ���� �����ִ� display�� �޶�����.
		        Class<?> Next_Display = Class.forName(constant.V_Route + state.name());
		        // ���� state ���� ���� Next_Display Ŭ������ ����
		        if (current_State != state) {
			        Current_Display = (display_Set) Next_Display.newInstance();
			        Current_Display.init_View(this, control);
			        current_State = state;
			        if (this.BGM != null) {
			        	this.BGM.stop();
			        }
			        switch (state) {
			        case Login:
			        case Room:
			        	this.BGM = new Audio(constant.M_Route[0],true);
			        	break;
			        case Wait:
			        	this.BGM = new Audio(constant.M_Route[1],true);
			        	break;
			        }
					this.add(Current_Display);
					this.setVisible(true);
			        // state_code�� ���� ����� ��� (Current_Display�� Next_Display�� �޶����� ���)�� �����.
		        }
		        if (this.control.getNextState() != this.state) {
		        	this.setState(this.control.getNextState());
		        	this.remove(Current_Display);
			        // Current_Display ���� �������� state ������ ������ ��� �����Ѵ�.
		        }
		        if (constant.delay) {
					Thread.sleep(500);
					constant.delay = false;
		        }
				Thread.sleep(50);
				// 50 millisecond (0.05sec) �ֱ� ����
			}
		} catch (Exception e) {}
	}
}
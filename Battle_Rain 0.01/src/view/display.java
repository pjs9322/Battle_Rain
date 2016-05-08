package view;

import javax.swing.JFrame;

import main.constant;
import main.constant.STATE;
import display_Set.display_Set;

public class display extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	public display_Set Current_Display;
	private int state_code = 0;
	private Thread thread;

	private STATE state = STATE.Login;
	
	public void setState(STATE state) {
		this.state = state;
	}
	
	public display() {										// 생성자 작성
		thread = new Thread(this);							// 스레드 생성
		thread.start();										// 스레드 실행
		this.setTitle(constant.F_Title);					// 프레임 이름 지정
		this.setSize(constant.F_Width, constant.F_Height); 	// 프레임 사이즈 지정
		this.setVisible(true);								// 프레임 가시화 옵션 설정
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 창 종료시 프로그램 종료
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				// 현재 state에 따라 보여주는 display가 달라진다.
		        Class<?> Next_Display = Class.forName(constant.V_Route + state.name());
		        // 현재 state 값에 따라 Next_Display 클래스를 생성
		        if (state_code != state.ordinal()) {
			        Current_Display = (display_Set) Next_Display.newInstance();
			        state_code = state.ordinal();
					this.add(Current_Display);
			        // state_code의 값이 변경될 경우 (Current_Display와 Next_Display가 달라지는 경우)에 덮어쓴다.
		        }
				Thread.sleep(50);
				// 50 millisecond (0.05sec) 주기 갱신
				repaint();
				// 갱신된 데이터에 따라 이미지 새로 작성
			}
		} catch (Exception e) {}
		// 연속된 통신으로 인한 에러 발생을 가정한 run이므로 모든 에러에 대해 아무것도 하지 않음
	}
}

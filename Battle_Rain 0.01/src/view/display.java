package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import display_Set.display_Set;

public class display extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;

	public static enum STATE {
		Login, Wait, Room, Playing
	}

	private STATE state = STATE.Login;

	public display_Set display_Set;
	
	private Thread thread;
	
	public void setState(STATE state) {
		this.state = state;
	}
	
	public display() {				// 생성자 작성
		display_Set = new display_Set();
		
		thread = new Thread(this);	// 스레드 생성
		thread.start();				// 스레드 실행

		this.setTitle("한글 께임");		// 프레임의 이름 지정
		this.setSize(1280, 720); 	// 프레임 사이즈 지정
		this.setVisible(true);		// 가시화 옵션 설정
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 종료시 프로그램 종료
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				// 현재 state에 따라 보여주는 display가 달라진다.
				Thread.sleep(50);
				// 50 milli sec 주기로 갱신
				repaint();
				// 갱신된 데이터에 따라 이미지 새로 그리기
			}
		} catch (Exception e) {}
		// 에러상황 발생을 가정한 try문이므로 모든 에러에 대해 아무것도 하지 않음
	}
}

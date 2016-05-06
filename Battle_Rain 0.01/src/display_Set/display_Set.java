package display_Set;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class display_Set extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Toolkit toolkit = Toolkit.getDefaultToolkit();

	private Image buff_Image;						// 더블 버퍼링용 버퍼 이미지
	private Graphics buff_Graphics;					// 더블 버퍼링용 버퍼 그래픽

	public display_Set() {
		paint(this.getGraphics());
	}
	
	public void paint(Graphics g) {					// 프레임이 생성되면 자동으로 그림이 그려진다.
		buff_Image = createImage(1280, 720);		// 프레임 전체 사이즈만큼의 이미지를 버퍼에 저장
		buff_Graphics = buff_Image.getGraphics();	// 버퍼이미지를 통해 버퍼 그래픽 객체 획득
		update_Display(g);
	}

	public void update_Display(Graphics g) {		// 이미지 갱신 품질을 고려하여 더블 버퍼링 기법을 사용
		Draw_Display(g);								// 그림 데이터를 전송
		g.drawImage(buff_Image, 0, 0, this);		// 드로잉 작업이 완전히 종료되면 기존 이미지와 교체한다.
	}

	public void Draw_Display(Graphics g) {
		buff_Graphics.clearRect(0, 0, 1280, 720);	// 프레임 전체 사이즈만큼의 전체 이미지 지우기
		this.draw(g);								// 새로 그린다.
	}

	private void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(toolkit.getImage("rsc/시작_화면.gif"), 0, 0, this);
	}
}

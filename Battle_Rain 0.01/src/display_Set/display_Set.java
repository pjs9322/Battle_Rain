package display_Set;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import main.constant;

public abstract class display_Set extends JPanel {
	private static final long serialVersionUID = 1L;
	protected Toolkit toolkit = Toolkit.getDefaultToolkit();

	private Image buff_Image; // 더블 버퍼링용 버퍼 이미지
	private Graphics buff_G; // 더블 버퍼링용 버퍼 그래픽
	
//	public void paint(Graphics g) {
//		g.clearRect(0, 0, constant.F_Width, constant.F_Width); // 초기화
//		this.draw(g);
//	}

	public abstract void draw(Graphics g);

	public void paint(Graphics g) { // 프레임이 생성되면 자동으로 그림이 그려진다.
		buff_Image = createImage(constant.F_Width, constant.F_Width);
		buff_G = buff_Image.getGraphics(); // 버퍼의 그래픽 객체를 얻기
		update(g); // 그래픽 업데이트
	}

	public void update(Graphics g) { // 바로 이미지를 띄우면 로딩으로 인한 깜박임이 발생하여 더블 버퍼링 기법
		Draw_Char(); // 그림 데이터를 전송
		g.drawImage(buff_Image, 0, 0, this); // 그림 데이터의 전송이 완료될 때 기존 이미지와 교체한다.
	}

	public void Draw_Char() { // 실제로 그림들을 그릴 부분
		buff_G.clearRect(0, 0, constant.F_Width, constant.F_Width); // 초기화
		this.draw(buff_G);
	}
}

package display_Set;

import java.awt.Button;
import java.awt.Graphics;

public class Room extends display_Set {
	private static final long serialVersionUID = 1L;

	private Button start_Button = new Button("게임 시작");
	
	public Room() {
		super();
		this.add(start_Button);
	}
	
	@Override
	public void draw(Graphics g) {
		
	}

	@Override
	public void init_Parts() {
		this.start_Button.addActionListener(actionListener);
		this.start_Button.setActionCommand("game_Start");
	}
	
	public void game_Start() {
		this.control.game_Start();
	}
}

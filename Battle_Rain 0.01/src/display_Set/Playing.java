package display_Set;

import java.awt.Graphics;
import java.awt.Label;

public class Playing extends display_Set {
	private static final long serialVersionUID = 1L;

	private Label playing = new Label("게임 진행중");
	
	public Playing() {
		super();
		this.add(playing);
	}
	
	@Override
	public void draw(Graphics g) {
		
	}

	@Override
	public void init_Parts() {
		
	}
}

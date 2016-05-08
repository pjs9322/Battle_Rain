package display_Set;

import java.awt.Button;
import java.awt.Graphics;

public class Wait extends display_Set {
	private static final long serialVersionUID = 1L;

	private Button make_Room_Button = new Button("방 만들기");
	private Button search_Room_Button = new Button("방 검색");
	private Button logout_Button = new Button("로그아웃");

	public Wait() {
		super();
		this.add(make_Room_Button);
		this.add(search_Room_Button);
		this.add(logout_Button);
	}
	
	@Override
	public void draw(Graphics g) {
		
	}

	@Override
	public void init_Parts() {
		this.make_Room_Button.addActionListener(actionListener);
		this.make_Room_Button.setActionCommand("make_Room");

		this.search_Room_Button.addActionListener(actionListener);
		this.search_Room_Button.setActionCommand("search_Room");
		
		this.logout_Button.addActionListener(actionListener);
		this.logout_Button.setActionCommand("user_logout");
	}
	
	public void make_Room() {
		
	}

	public void search_Room() {
		
	}
	
	public void user_logout() {
		state_code = 1;
	}
}

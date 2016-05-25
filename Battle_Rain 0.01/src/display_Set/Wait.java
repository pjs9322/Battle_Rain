package display_Set;

import java.awt.Button;
import java.awt.Graphics;

import display_Sub.Room_Make;
import display_Sub.Room_Search;

public class Wait extends display_Set {
	private static final long serialVersionUID = 1L;

	private Button make_Room_Button = new Button("방 만들기");
	private Button search_Room_Button = new Button("방 검색");
	private Button logout_Button = new Button("로그아웃");

	private Room_Make make;
	private Room_Search search;

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
		this.make_Room_Button.addActionListener(this);
		this.make_Room_Button.setActionCommand("make_Room");
		this.search_Room_Button.addActionListener(this);
		this.search_Room_Button.setActionCommand("search_Room");
		this.logout_Button.addActionListener(this);
		this.logout_Button.setActionCommand("user_Logout");
	}
	
	public void make_Room() {
		if (this.make == null) {
			this.make = new Room_Make(control);
		} else if (!this.make.isDisplayable()) {
			this.make = new Room_Make(control);
		} else if (!this.make.isFocused()) {
			this.make.requestFocus();
		}
	}

	public void search_Room() {
		if (this.search == null) {
			this.search = new Room_Search(control);
		} else if (!this.search.isDisplayable()) {
			this.search = new Room_Search(control);
		} else if (!this.search.isFocused()) {
			this.search.requestFocus();
		}
	}
	
	public void user_Logout() {
		this.control.user_Logout();
	}
}

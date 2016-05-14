package control;

import main.constant.STATE;

public class control {
	
	private STATE next_State = STATE.Login;
	public STATE getNext_State() { return next_State; }
	public void setNext_State(STATE state) { this.next_State = state; }
	
	public void user_Login(String userID, String password) {
		if (userID.endsWith("pjs9322") && password.equals("1111")) {
			this.next_State = STATE.Wait;
		}
	}
	
	public void user_Logout() {
		this.next_State = STATE.Login;
	}

	public void user_Join(String userID, String password) {
		System.out.println(userID + " " + password);
	}

	public void Room_Make() {
		this.next_State = STATE.Room;
	}
	
	public void game_Start() {
		this.next_State = STATE.Playing;
	}
}

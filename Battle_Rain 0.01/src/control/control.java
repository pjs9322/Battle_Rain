package control;

public class control {
	public boolean user_Login(String userID, String password) {
		if (userID.endsWith("pjs9322") && password.equals("1111")) {
			return true;
		}
		return false;
	}

	public void user_Join(String userID, String password) {
		System.out.println(userID + " " + password);
	}
}
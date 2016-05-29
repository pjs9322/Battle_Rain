package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;

public class User_Model extends Model implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String userID;
	private String password;

	@SuppressWarnings("unchecked")
	public String user_Login(String userID, String password) throws ClassNotFoundException, IOException {
		
		Vector<User_Model> user_List = (Vector<User_Model>) this.read("rsc\\data_File\\user_List");
		
		this.userID = userID;
		this.password = password;
		
		for (User_Model user: user_List) {
			if (this.userID.equals(user.userID) && this.password.equals(user.password)) {
				return null;
			}
		}
		return "계정 정보가 올바르지 않습니다.";
	}
	
	@SuppressWarnings("unchecked")
	public String user_Join(String userID, String password) throws ClassNotFoundException, IOException {
//		this.save("rsc\\data_File\\user_List", new Vector<User_Model>());
//		return null;
		Vector<User_Model> user_List = (Vector<User_Model>) this.read("rsc\\data_File\\user_List");
		
		this.userID = userID;
		this.password = password;
		
		if (this.userID.equals("") || this.password.equals("") || this.userID.contains(" ") || this.password.contains(" ")) {
			return "계정 정보가 올바르지 않습니다.";
		} else {
			for (User_Model user: user_List) {
				if (this.userID.equals(user.userID)) {
					return "계정 정보가 중복됩니다.";
				}
			}
			user_List.add(this);
			this.save("rsc\\data_File\\user_List", user_List);
		}
		return null;
	}
}

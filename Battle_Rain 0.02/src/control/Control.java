package control;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

import main.constant;
import main.constant.STATE;
import model.Audio;
import model.Database;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Control {
	private Database db;
	private DBObject userData;
	public DBObject getUserData() { return userData; }
	public void setChara(int chara) { this.userData.put("icon", chara); }

	private DBObject roomData;
	public String getRoomIP() { return (String) this.roomData.get("hostIP"); }

	private boolean roomState; // false �� Ŭ���̾�Ʈ
	public boolean getRoomState() {return roomState;}

	private Vector<RWord> wordList = new Vector<RWord>();
	public Vector<RWord> getWordList() { return wordList; }
	public void setWordList(Vector<RWord> wordList) { this.wordList = wordList; }

	private STATE nextState = STATE.Login;
	public STATE getNextState() { return nextState; }

	public Control() {
		this.userData = null;
		this.roomData = null;
		this.roomState = false;

		this.db = new Database();
		this.db.connect();
	}

	public String user_Login(String userID, String password) {
		String alart = null;
		if (userID.equals("") || password.equals("")) {
			alart = "ID�� ��й�ȣ�� �Է����ּ���.";
			return alart;
		}
		//�Է¹��� ID�� DB�� �ִ� Object(column)�� �޾ƿ´�.
		DBObject userData = db.find("User", "id", userID);
		//ȸ�����Ե� ������ �����ϰ� ��й�ȣ�� ��ġ�ϸ� ���Ƿ� �����Ѵ�.
		if (userData != null && userData.get("password").equals(password)) {
			this.userData = userData;
			this.nextState = STATE.Wait;
		} else {
			alart = "���� ������ ��ġ���� �ʽ��ϴ�.";
		}
		return alart;
	}

	public void user_Logout() {
		if (this.nextState.equals(STATE.Wait)) {
			this.userData = null;
			this.nextState = STATE.Login;
		}
	}

	public String user_Join(String userID, String password, String name) {
		String alart = null;
		if (userID.equals("") || password.equals("") || name.equals("")) {
			alart = "�׸��� ���� �Է����ּ���.";
			return alart;
		}
		if (userID.contains(" ") || password.contains(" ") || name.contains(" ")) {
			alart = "������ ���Ե� �� �����ϴ�.";
			return alart;
		}
		//�Է¹��� ID�� DB�� �������� ������ ������ DB�� �����Ѵ�.
		DBObject userData1 = db.find("User", "id", userID);
		DBObject userData2 = db.find("User", "name", name);
		if (userData1 == null) {
			if (userData2 == null) {
				BasicDBObject tempBasicDBObj = new BasicDBObject("id", userID)
				.append("password", password)
				.append("name", name)
				.append("icon", 0);      //ĳ���� ������ [0,7] default 0
				db.insert("User",  tempBasicDBObj);
			} else {
				alart = "�ߺ��� �г��Ӵϴ�.";
			}
		} else {
			alart = "ID�� �ߺ��˴ϴ�.";
		}
		return alart;
	}

	public Vector<DBObject> readRoomList() {
		DBCursor cursor = db.find("Room");
		Vector<DBObject> temp = new Vector<DBObject>();
		for (int i = 0; cursor.hasNext() && i < 4; i++) {
			temp.add(cursor.next());
		}
		return temp;
	}

	public String Room_Make(String roomName, boolean mode) {
		String alart = null;
		if (this.nextState.equals(constant.STATE.Wait)) {
			if (roomName.replaceAll(" ", "").equals("")) {
				alart = "�� ������ �Է����ּ���.";
				return alart;
			}
			DBObject roomData = db.find("Room", "master", (String) this.userData.get("name"));
			// �̹� �ڱⰡ ���� ���� ������ DB���� �ش� ���� ����

			//�Է¹��� �������� DB�� �������� �ʴ´ٸ� �� ������ DB�� �߰��ϰ� �� ������ �����Ѵ�.
			roomData = db.find("Room", "rname", roomName);
			if (roomData == null) {
				BasicDBObject tempBasicDBObj = null;
				try {
					tempBasicDBObj = new BasicDBObject("rname", roomName)
					.append("master", this.userData.get("name"))
					.append("mode", mode)   //���� ��� [0,1] default 0
					.append("state", 0)
					.append("ucount", 1)
					.append("hostIP", InetAddress.getLocalHost().getHostAddress());
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				db.insert("Room",  tempBasicDBObj);
				this.roomState = true;
				this.nextState = STATE.Room;
			} else {
				alart = "�ߺ��� �� �����Դϴ�.";
			}
		}
		return alart;
	}

	public String Room_Search(String roomName) {
		String alart = null;
		if (this.nextState.equals(constant.STATE.Wait)) {
			if (roomName.replaceAll(" ", "").equals("")) {
				alart = "�� ������ �Է����ּ���.";
				return alart;
			}
			//�Է¹��� �������� �����ϸ� �� ������ �����Ѵ�.
			DBObject roomData = db.find("Room", "rname", roomName);
			if (roomData != null) {
				this.nextState = STATE.Room;
				this.roomData = roomData;
			} else {
				alart = "�������� �״� ���Դϴ�.";
			}
		}
		return alart;
	}

	public void room_exit() {
		if (this.nextState.equals(STATE.Room)) {
			this.nextState = STATE.Wait;
		}
	}
	
	public void word_Make() {
		DBCursor cursor = db.find("Word");
		String[] temp = new String[5887];
		for (int i = 0; cursor.hasNext(); i++) {
			temp[i] = (String) cursor.next().get("word");
		}
		
		for (int i = 0; i < 2000; i++) {
			wordList.add(new RWord(temp[(int) (Math.random()*5887)], i));
		}
	}
	
	public boolean removeWord(String text) {
		for (RWord word: wordList) {
			if (word.word.equals(text) && word.Y > 0 && word.Y < 473) {
				if (!constant.delay) {
					new Audio(constant.M_Route[5], false);
					constant.delay = true;
				}
				wordList.remove(word);
				return true;
			}
		}
		return false;
	}
	
	public class RWord implements Serializable {
		private static final long serialVersionUID = 1L;
		
		public String word;
		public boolean effect;
		public int X;
		public int Y;
		
		public RWord (String word, int i) {
			this.word = word;
			this.effect = Math.random() < 0.05;
			this.X = (int) (Math.random()*420);
			this.Y = -12 * i;
		}
	}
}

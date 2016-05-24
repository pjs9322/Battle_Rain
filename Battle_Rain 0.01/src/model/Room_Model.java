package model;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.Vector;

public class Room_Model extends Model implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String roomName;
	private String host_IP;

	@SuppressWarnings("unchecked")
	//방 만들기, 방 목록 테이블에 데이터 추가
	public String Room_Make(String roomName) throws ClassNotFoundException, IOException {

		Vector<Room_Model> room_List = (Vector<Room_Model>) this.read("rsc\\data_File\\room_List");

		this.roomName = roomName;
		this.host_IP = InetAddress.getLocalHost().getHostAddress();
		
		if (this.roomName.equals("")) {
			return "방 제목을 입력해주세요.";
		} else {
			for (int i=0; i<room_List.size(); i++) {
				if (this.host_IP.equals(room_List.get(i).host_IP)) {
					room_List.remove(i);
				}
			}
			for (Room_Model room: room_List) {
				if (this.roomName.equals(room.roomName)) {
					return "중복된 방 제목입니다.";
				}
			}
			room_List.add(this);
			this.save("rsc\\data_File\\room_List", room_List);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	//방 찾기, 테이블에서 방 찾기
	public String Room_Search(String roomName) throws ClassNotFoundException, IOException {
		
		Vector<Room_Model> room_List = (Vector<Room_Model>) this.read("rsc\\data_File\\room_List");

		this.roomName = roomName;

		if (this.roomName.equals("")) {
			return "방 제목을 입력해주세요.";
		} else {
			for (Room_Model room: room_List) {
				if (this.roomName.equals(room.roomName)) {
					this.host_IP = room.host_IP;
					return null;
				}
			}
			return "일치하는 방이 없습니다.";
		}
	}
}

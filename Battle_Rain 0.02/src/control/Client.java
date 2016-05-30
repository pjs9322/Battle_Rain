package control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import com.mongodb.DBObject;

import control.Control.RWord;
import display_Set.Room;

public class Client {
	private Room room;
	private String msg;
	private Socket socket;
	private DBObject userData;
	private DataOutputStream output;

	public void start(DBObject userData, String hostIP, Room room) {
		try {
			socket = new Socket(hostIP, 7777);
			this.room = room;
			this.userData = userData;
			new ObjectOutputStream(this.socket.getOutputStream()).writeObject(userData);
			this.output = new DataOutputStream(socket.getOutputStream());
			
			ClientReceiver clientReceiver = new ClientReceiver(socket);
			ClientFileReceiver fileReceiver = new ClientFileReceiver(socket);
			clientReceiver.start();
			fileReceiver.start();
		} catch (IOException e) {}
	}

	public void send(String text) {
		// TODO Auto-generated method stub
		try {
			output.writeUTF(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gameStart(Object object) {
		@SuppressWarnings("unchecked")
		Vector<RWord> list = (Vector<RWord>) object;
		room.getControl().setWordList(list);
		room.game_Start();
		System.out.println("game start");
		System.out.println(list.get(1));
		System.out.println(room.getControl().getWordList().get(0));
	}

	class ClientReceiver extends Thread {
		Socket socket;
		DataInputStream input;

		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				input = new DataInputStream(this.socket.getInputStream());
			} catch (IOException e) {}
		}

		@Override
		public void run() {
			while (input != null) {
				try {
					System.out.println(input.readUTF());
				} catch (IOException e) {}
			}
		}
	}
	
	class ClientFileReceiver extends Thread {
		Socket socket;
		ObjectInputStream input;

		public ClientFileReceiver(Socket socket) {
			this.socket = socket;
			try {
				input = new ObjectInputStream(this.socket.getInputStream());
			} catch (IOException e) {}
		}

		@Override
		public void run() {
				try {
					try {
						Object object = input.readObject();
						if(!room.getPlayingState()) {
							gameStart(object);
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {}
		}
	}
}
package control;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import com.mongodb.DBObject;

import display_Set.Room.RainField;

public class Server {
	private HashMap<String, DataOutputStream> clients;
	private HashMap<String, ObjectOutputStream> starters;
	private ServerSocket serverSocket;
	private RainField rainField;

	public Server() {
		clients = new HashMap<String, DataOutputStream>();
		starters = new HashMap<String, ObjectOutputStream>();

		// 여러 스레드에서 접근할 것이므로 동기화
		Collections.synchronizedMap(clients);
	}

	public void start(RainField rainFeild) {
		try {
			Socket socket;

			this.rainField = rainFeild;

			// 리스너 소켓 생성
			serverSocket = new ServerSocket(7777);

			// 클라이언트와 연결되면
			while (true) {
				// 통신 소켓을 생성하고 스레드 생성(소켓은 1:1로만 연결된다)
				socket = serverSocket.accept();
				ServerReceiver receiver = new ServerReceiver(socket);
				receiver.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void sendToAll(String message) {
		Iterator<String> it = clients.keySet().iterator();

		while (it.hasNext()) {
			try {
				DataOutputStream dos = clients.get(it.next());
				dos.writeUTF(message);
				System.out.println(message);
			} catch (Exception e) {}
		}
	}

	public void gameStart() {
		Iterator<String> it = starters.keySet().iterator();

		while (it.hasNext()) {
			try {
				ObjectOutputStream oos = starters.get(it.next());
				oos.writeObject(rainField.getControl().getWordList());
				System.out.println("send word");
			} catch (Exception e) {}
		}
	}

	class ServerReceiver extends Thread {
		Socket socket;
		DataInputStream input;
		DataOutputStream output;
		DBObject clientData;

		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				input = new DataInputStream(socket.getInputStream());
				output = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
			}
		}

		@Override
		public void run() {
			String name = "";
			try {
				// 클라이언트가 서버에 접속하면 대화방에 알린다.
				clientData = (DBObject) new ObjectInputStream(socket.getInputStream()).readObject();
				rainField.userList.add(clientData);
				name = (String)clientData.get("name");
				System.out.println(name);

				clients.put(name, output);
				starters.put(name, new ObjectOutputStream(socket.getOutputStream()));

				// 메세지 전송
				while (input != null) {
					sendToAll(input.readUTF());
				}
			} catch (IOException | ClassNotFoundException e) {
			} finally {
				// 접속이 종료되면
				clients.remove(name);
				rainField.userList.remove(clientData);
			}
		}
	}
}
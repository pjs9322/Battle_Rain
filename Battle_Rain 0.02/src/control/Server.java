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

		// ���� �����忡�� ������ ���̹Ƿ� ����ȭ
		Collections.synchronizedMap(clients);
	}

	public void start(RainField rainFeild) {
		try {
			Socket socket;

			this.rainField = rainFeild;

			// ������ ���� ����
			serverSocket = new ServerSocket(7777);

			// Ŭ���̾�Ʈ�� ����Ǹ�
			while (true) {
				// ��� ������ �����ϰ� ������ ����(������ 1:1�θ� ����ȴ�)
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
				// Ŭ���̾�Ʈ�� ������ �����ϸ� ��ȭ�濡 �˸���.
				clientData = (DBObject) new ObjectInputStream(socket.getInputStream()).readObject();
				rainField.userList.add(clientData);
				name = (String)clientData.get("name");
				System.out.println(name);

				clients.put(name, output);
				starters.put(name, new ObjectOutputStream(socket.getOutputStream()));

				// �޼��� ����
				while (input != null) {
					sendToAll(input.readUTF());
				}
			} catch (IOException | ClassNotFoundException e) {
			} finally {
				// ������ ����Ǹ�
				clients.remove(name);
				rainField.userList.remove(clientData);
			}
		}
	}
}
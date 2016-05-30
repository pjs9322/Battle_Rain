package control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import com.mongodb.DBObject;

public class Client {
	private String name;
	private Socket socket;
	private DBObject userData;

	public void start(DBObject userData, String hostIP) {
		try {
			socket = new Socket(hostIP, 7777);
			
			this.userData = userData;

			ClientReceiver clientReceiver = new ClientReceiver(socket);
			ClientSender clientSender = new ClientSender(socket);

			clientReceiver.start();
			clientSender.start();
		} catch (IOException e) {}
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

	class ClientSender extends Thread {
		Socket socket;
		DataOutputStream output;

		public ClientSender(Socket socket) {
			this.socket = socket;
			try {
				output = new DataOutputStream(this.socket.getOutputStream());
				output.writeUTF(userData);
			} catch (Exception e) {
			}
		}

		@Override
		public void run() {
			Scanner sc = new Scanner(System.in);
			String msg = "";

			while (output != null) {
				try {
					msg = sc.nextLine();
					if(msg.equals("exit"))
						System.exit(0);

					output.writeUTF("[" + name + "]" + msg);
				} catch (IOException e) {}
			}
		}
	}
}
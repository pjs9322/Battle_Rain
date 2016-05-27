package control;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Server implements Runnable {
   private HashMap<String, DataOutputStream> clients;
   private ServerSocket serverSocket;
   private String myName;

   public static void main(String[] args) {
      new Server().start();
   }

   public Server() {
      clients = new HashMap<String, DataOutputStream>();

      // ���� �����忡�� ������ ���̹Ƿ� ����ȭ
      Collections.synchronizedMap(clients);
   }

   public void start() {
      try {
         Socket socket;

         // ������ ���� ����
         serverSocket = new ServerSocket(7777);
         System.out.println("���� �����Ǿ����ϴ�.");
         myName = new Scanner(System.in).nextLine();

         // server thread
         Thread myThread = new Thread(this);
         myThread.start();
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

   @Override
   public void run() {
      // TODO Auto-generated method stub
      while(true) {
         Scanner sc = new Scanner(System.in);
         String msg = "";
         msg = sc.nextLine();
         sendToAll("[" + myName + "]" + msg);
         
//         sendToAll("SCORE1/010");
//         sendToAll("SCORE2/020");
//         sendToAll("SCORE3/030");
         sendToAll("REMOVE/near");
      }
   }


   public void sendToAll(String message) {
      Iterator<String> it = clients.keySet().iterator();

      while (it.hasNext()) {
         try {
            DataOutputStream dos = clients.get(it.next());
            dos.writeUTF(message);
            if(message.contains("REMOVE")) {
               char[] msg = new char[10];
               message.getChars(7, message.length(), msg, 0);
               System.out.println(msg);
               if(msg.toString().equals("near")) {
                  System.out.println("check");
               }
            } else {
               System.out.println(message);
            }
         } catch (Exception e) {
         }
      }
   }


   class ServerReceiver extends Thread {
      Socket socket;
      DataInputStream input;
      DataOutputStream output;

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
            name = input.readUTF();
            sendToAll("#" + name + "[" + socket.getInetAddress() + ":"
                  + socket.getPort() + "]" + "���� ��ȭ�濡 �����Ͽ����ϴ�.");

            clients.put(name, output);
            sendToAll(name + "[" + socket.getInetAddress() + ":"
                  + socket.getPort() + "]" + "���� ��ȭ�濡 �����Ͽ����ϴ�.");
            sendToAll("���� " + (clients.size()+1) + "���� ��ȭ�濡 ���� ���Դϴ�.");

            // �޼��� ����
            while (input != null) {
               sendToAll(input.readUTF());
            }
         } catch (IOException e) {
         } finally {
            // ������ ����Ǹ�
            clients.remove(name);
            sendToAll("#" + name + "[" + socket.getInetAddress() + ":"
                  + socket.getPort() + "]" + "���� ��ȭ�濡�� �������ϴ�.");
            sendToAll(name + "[" + socket.getInetAddress() + ":"
                  + socket.getPort() + "]" + "���� ��ȭ�濡�� �������ϴ�.");
            sendToAll("���� " + (clients.size()+1) + "���� ��ȭ�濡 ���� ���Դϴ�.");
         }
      }
   }

}
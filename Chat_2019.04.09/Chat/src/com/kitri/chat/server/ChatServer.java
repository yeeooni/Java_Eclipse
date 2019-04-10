package com.kitri.chat.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import com.kitri.chat.util.ChatConstance;
import com.sun.xml.internal.fastinfoset.stax.events.StartDocumentEvent;

public class ChatServer implements Runnable {
	ServerSocket ss;
	Vector<ChatClient> list = new Vector<ChatServer.ChatClient>();

	public ChatServer() {

		try {
			ss = new ServerSocket(ChatConstance.PORT);
			System.out.println("Ŭ���̾�Ʈ ���� �����.");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	// Ŭ���̾�Ʈ ���� ó��
	@Override
	public void run() {
		while (true) {
			try {
				Socket socket = ss.accept();
				System.out.println("Ŭ���̾�Ʈ ���� ���� :: " + socket);
				new ChatClient(socket).start();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	// inner class
	class ChatClient extends Thread {
		String name;
		BufferedReader in;
		OutputStream out;

		public ChatClient(Socket socket) {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = socket.getOutputStream();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		// Ŭ���̾�Ʈ �޼��� ó��
		@Override
		public void run() {
			boolean flag = true;
			while (flag) {
				try {
					String msg = in.readLine();
					System.out.println("Ŭ���̾�Ʈ�� ���� �޼���" + msg);// protocol|�޼�������.
					StringTokenizer st = new StringTokenizer(msg, "|");
					int protocol = Integer.parseInt(st.nextToken());

					switch (protocol) {
						case ChatConstance.CS_CONNECT: {
							name = st.nextToken();
//							int size = list.size();
//							
//							for(int i= 0; i < size; i++) {
//								ChatClient cc = list.get(i);
//							}
							
							//���� for��
							for(ChatClient cc : list) {
								 cc.out.write((ChatConstance.SC_CONNECT + "|" + name + "\n").getBytes());
							}
							list.add(this);
							
							for(ChatClient cc : list) {
								 out.write((ChatConstance.SC_CONNECT + "|" + cc.name + "\n").getBytes());
							}
					}break;
					
						case ChatConstance.CS_ALL: {
							String tmp = st.nextToken();
							
							for(ChatClient cc : list) {
								 cc.out.write((ChatConstance.SC_MESSAGE + "|[" + name + "] " + tmp + "\n").getBytes());
							}
					}
						break;
						case ChatConstance.CS_TO: {

					}
						break;
						case ChatConstance.CS_PAPER: {

					}
						break;
						case ChatConstance.CS_RENAME: {

					}
						break;
						case ChatConstance.CS_DISCONNECT: {

					}
						break;
					}
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

		}
	}

	public static void main(String[] args) {
//		ChatServer cs = new ChatServer();
//		Thread t = new Thread(cs); 
//		t.start();

		// ���� �ڵ庸�� �� ��������
		new Thread(new ChatServer()).start();

	}

}

package com.kitri.chat.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import com.kitri.chat.client.Paper;
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
		Socket socket;
		Paper paper;

		public ChatClient(Socket socket) {
			try {
				this.socket = socket;
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

						// ���� for��
						multicast(ChatConstance.SC_CONNECT + "|" + name);

						list.add(this);

						for (ChatClient cc : list) {
							unicast(ChatConstance.SC_CONNECT + "|" + cc.name);
						}
					}
						break;

					case ChatConstance.CS_ALL: {
						String tmp = st.nextToken();
						multicast(ChatConstance.SC_MESSAGE + "|[" + name + "] " + tmp);
					}
						break;
					case ChatConstance.CS_TO: {
//						250|ȫ�浿|�ȳ��ϼ���.
						String to = st.nextToken();// ȫ�浿
						String tmp = st.nextToken();// �ȳ��ϼ���.
						for (ChatClient cc : list) {
							if (cc.name.equals(to)) {
								cc.unicast(ChatConstance.SC_MESSAGE + "|��" + name + "��" + tmp);
								break;
							}
						}
					}
						break;
					case ChatConstance.CS_PAPER: {
//						300|�޴»��|�ȳ��ϼ���.
						String to = st.nextToken();
						String tmp = st.nextToken();
						for(ChatClient cc : list) {
							if(cc.name.equals(to)) {
								cc.unicast(ChatConstance.SC_PAPER + "|" + name + "|" + tmp);
							}
							
//							if(tmp.equals(to)) {
//								paper.setVisible(true);
//							}
						}	
					}
						break;
					case ChatConstance.CS_RENAME: {
//						400|�����Ҵ�ȭ��
						String afterId = st.nextToken();
						multicast(ChatConstance.SC_RENAME + "|" + name + "|" + afterId);
						name = afterId;
					}
						break;
						
					case ChatConstance.CS_DISCONNECT: {
//						900|
						multicast(ChatConstance.SC_DISCONNECT + "|" + name); // ��������� ���� �����°��� �޼����� �˷��ش�.
						list.remove(this); // ����Ʈ���� ���� �����.
						flag = false; //while���� �����Ų��.
						in.close(); // �Է��� �ݴ´�.
						out.close(); // ����� �ݴ´�.
						socket.close(); //������ �ݴ´�.
						
					}
						break;
					}
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

		// ��ü�޼��� ������ �޼ҵ�
		private void multicast(String msg) {

			for (ChatClient cc : list) {
				cc.unicast(msg);
			}
		}

		private void unicast(String msg) {
			try {
				out.write((msg + "\n").getBytes());
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}// �̳�Ŭ���� ������ ��

	public static void main(String[] args) {
//		ChatServer cs = new ChatServer();
//		Thread t = new Thread(cs); 
//		t.start();

		// ���� �ڵ庸�� �� ��������
		new Thread(new ChatServer()).start();

	}

}

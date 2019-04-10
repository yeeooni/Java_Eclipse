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
			System.out.println("클라이언트 접속 대기중.");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	// 클라이언트 접속 처리
	@Override
	public void run() {
		while (true) {
			try {
				Socket socket = ss.accept();
				System.out.println("클라이언트 접속 성공 :: " + socket);
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

		// 클라이언트 메세지 처리
		@Override
		public void run() {
			boolean flag = true;
			while (flag) {
				try {
					String msg = in.readLine();
					System.out.println("클라이언트가 보낸 메세지" + msg);// protocol|메세지형식.
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

						// 향상된 for문
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
//						250|홍길동|안녕하세요.
						String to = st.nextToken();// 홍길동
						String tmp = st.nextToken();// 안녕하세요.
						for (ChatClient cc : list) {
							if (cc.name.equals(to)) {
								cc.unicast(ChatConstance.SC_MESSAGE + "|☆" + name + "☆" + tmp);
								break;
							}
						}
					}
						break;
					case ChatConstance.CS_PAPER: {
//						300|받는사람|안녕하세요.
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
//						400|변경할대화명
						String afterId = st.nextToken();
						multicast(ChatConstance.SC_RENAME + "|" + name + "|" + afterId);
						name = afterId;
					}
						break;
						
					case ChatConstance.CS_DISCONNECT: {
//						900|
						multicast(ChatConstance.SC_DISCONNECT + "|" + name); // 모든사람에게 내가 나가는것을 메세지로 알려준다.
						list.remove(this); // 리스트에서 나를 지운다.
						flag = false; //while문을 종료시킨다.
						in.close(); // 입력을 닫는다.
						out.close(); // 출력을 닫는다.
						socket.close(); //소켓을 닫는다.
						
					}
						break;
					}
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

		// 전체메세지 보내는 메소드
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

	}// 이너클래스 끝나는 곳

	public static void main(String[] args) {
//		ChatServer cs = new ChatServer();
//		Thread t = new Thread(cs); 
//		t.start();

		// 위에 코드보다 더 간결해짐
		new Thread(new ChatServer()).start();

	}

}

package com.kitri.chat.client;

import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.kitri.chat.util.ChatConstance;

public class Login extends JFrame implements ActionListener, Runnable, ListSelectionListener {

	String myid;
	String selName;

	BufferedReader in;
	OutputStream out;

	JLabel ip;
	JLabel name;
	JTextField ipTf;
	JTextField nameTf;
	JButton cancel;
	JButton ok;

	Chat chat;
	Paper paper;
	Rename rename;
	Socket socket;

	public Login() {
		super("Login!!");
		initGUI();

		chat = new Chat();
		paper = new Paper();
		rename = new Rename();

		// 로그인창 이벤트 등록
		nameTf.addActionListener(this);
		ok.addActionListener(this);
		cancel.addActionListener(this);

		// 채팅창 이벤트 등록
		chat.globalsend.addActionListener(this);
		chat.whomsend.addActionListener(this);
		chat.paper.addActionListener(this);
		chat.rename.addActionListener(this);
		chat.exit.addActionListener(this);
		chat.list.addListSelectionListener(this);
		chat.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				closeProcess();
			}

		});

		// paper창 이벤트 등록
		chat.paper.addActionListener(this);
		paper.ok.addActionListener(this);
		paper.answer.addActionListener(this);
		paper.cancel.addActionListener(this);

		// 대화명변경 이벤트 등록

		chat.rename.addActionListener(this); // 대화명변경버튼
		rename.ok.addActionListener(this); // 변경
		rename.cancel.addActionListener(this);
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			getContentPane().setBackground(new java.awt.Color(244, 243, 242));
			{
				ip = new JLabel();
				getContentPane().add(ip);
				ip.setText("\uc544\uc774\ud53c");
				ip.setBounds(12, 12, 60, 30);
				ip.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				ip.setFocusable(false);
				ip.setRequestFocusEnabled(false);
				ip.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				name = new JLabel();
				getContentPane().add(name);
				name.setText("\ub300\ud654\uba85");
				name.setBounds(12, 47, 60, 30);
				name.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				name.setFocusable(false);
				name.setRequestFocusEnabled(false);
				name.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				ipTf = new JTextField();
				getContentPane().add(ipTf);
				ipTf.setBounds(78, 12, 209, 30);
			}
			{
				nameTf = new JTextField();
				getContentPane().add(nameTf);
				nameTf.setBounds(78, 47, 209, 30);
			}
			{
				ok = new JButton();
				getContentPane().add(ok);
				ok.setText("\ub85c\uadf8\uc778");
				ok.setBounds(83, 83, 61, 32);
				ok.setBackground(new java.awt.Color(237, 236, 233));
				ok.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
			}
			{
				cancel = new JButton();
				getContentPane().add(cancel);
				cancel.setText("\ucde8 \uc18c");
				cancel.setBounds(153, 83, 59, 32);
				cancel.setBackground(new java.awt.Color(237, 236, 233));
				cancel.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
			}
			pack();
			this.setSize(303, 150);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Login inst = new Login();
				inst.setLocationRelativeTo(null);
				inst.setResizable(false);
				inst.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob == nameTf || ob == ok) {
			connectProcess();
		} else if (ob == cancel) {
			System.exit(0);
		} else if (ob == chat.globalsend) {
			globalsendProcess();
		} else if (ob == chat.whomsend) {
			whomsendProcess();
		} else if (ob == chat.paper) {
			paper.setVisible(true);
			myid = nameTf.getText().trim();
			paper.from.setText(myid);
			selName = chat.list.getSelectedValue();
			paper.to.setText(selName);
		} else if (ob == paper.ok) {
//			System.out.println("출력중");
			paperProcess();
		} else if( ob == paper.answer) {
			paperProcess();
		} else if (ob == paper.cancel) {
			paper.setVisible(false);
		} else if (ob == chat.rename) {
			rename.setVisible(true);
			myid = nameTf.getText().trim();
			rename.oldname.setText(myid);
		} else if (ob == rename.ok) {
			renameProcess();
		} else if (ob == rename.cancel) {
			rename.setVisible(false);
		}

		else if (ob == chat.exit) {
			closeProcess();
		}

	}

	private void renameProcess() {
//		400|변경할대화명
		String newName = rename.newname.getText();
		
		myid = newName;
		
		sendMassage(ChatConstance.CS_RENAME + "|" + newName);

		if (newName.isEmpty()) {
			return;
		}
		rename.setVisible(false);
	}

//	1.메세지를 얻어낸다.
//	2.server 메시지 전송
	private void paperProcess() {
//		300|받는사람|메세지

		String to = paper.to.getText().trim();
		String msg = paper.letter.getText().trim();
		if (msg.isEmpty()) {
			return;
		}
		if (myid.equals(selName)) {
			JOptionPane.showMessageDialog(paper, "자기자신에게 쪽지를 보낼 수 없습니다.", "대상자 오류", JOptionPane.ERROR_MESSAGE);
			return;
		}
		sendMassage(ChatConstance.CS_PAPER + "|" + to + "|" + msg);
		
		paper.letter.setText("");

	}

	private void closeProcess() {
		sendMassage(ChatConstance.CS_DISCONNECT + "|");
	}

//	1.메세지 얻어낸다.(유효성검사 대상자, 자신, 메세지)
//	2.server 메세지 전송
	private void whomsendProcess() {
		String msg = chat.whomsend.getText().trim();
		chat.whomsend.setText("");
		if (msg.isEmpty()) {
			return;
		}

		String to = chat.whom.getText();
		if (to.isEmpty()) {
			JOptionPane.showMessageDialog(chat, "대상자를 선택하세요.", "대상자 오류", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (to.equals(myid)) {
			JOptionPane.showMessageDialog(chat, "자기자신에게 귓속말?", "대상자 오류", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		sendMassage(ChatConstance.CS_TO + "|" + to + "|" + msg);
		viewMessage("★" + to + "★" + msg);

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		String selName = chat.list.getSelectedValue();
		chat.whom.setText(selName);
	}

//	1.메세지 얻어낸다.(유효성검사)
//	2.server 메세지 전송
	private void globalsendProcess() {
		String msg = chat.globalsend.getText().trim();
		chat.globalsend.setText("");
		if (msg.isEmpty()) {
			return;
		}
//		200|안녕하세요
		sendMassage(ChatConstance.CS_ALL + "|" + msg);
	}

//	1. ip, 대화명을 얻어낸다. (대화명의 유효성검사 3글자 이상)
//	2. 1의 ip를 얻어내서 소켓을 생성한다.
//	3. IO(BufferedReader, OutputStream) 생성.
//	4. login 창 닫고, chat 창 열리기
//	5. server로 메세지 전송
//	6. Thread 실행

	private void connectProcess() {
		String ip = ipTf.getText().trim();
		myid = nameTf.getText().trim();

		if (myid.length() < 3) {
			JOptionPane.showMessageDialog(this, "대화명 3자이상입니다.", "대화명오류", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			socket = new Socket(ip, ChatConstance.PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = socket.getOutputStream();
			this.setVisible(false);
			chat.setVisible(true);
			// 서버에서 받는 메소드는 BufferedReader이기때문에
			// Enter를 반드시 보내야 Server에서 받을 때 데이터의 끝을 알 수 있다.
			// 100|대화명
			sendMassage(ChatConstance.CS_CONNECT + "|" + myid);
			new Thread(this).start();
		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void sendMassage(String msg) {
		try {
			out.write((msg + "\n").getBytes());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		boolean flag = true;
		while (flag) {
			try {
				String msg = in.readLine();
				System.out.println("서버가 보낸 메세지" + msg);// protocol|메세지형식.
				StringTokenizer st = new StringTokenizer(msg, "|");
				int protocol = Integer.parseInt(st.nextToken());

				switch (protocol) {
				case ChatConstance.SC_CONNECT: {
//					100|접속자대화명.
					String tmp = st.nextToken();
					viewMessage("[알림]" + tmp + "님이 접속하였습니다.");
					chat.listData.add(tmp);
					chat.list.setListData(chat.listData);
				}
					break;

				case ChatConstance.SC_MESSAGE: {
//					200|[김의연] 안녕하세요
//					200|☆김의연☆ 안녕하세요
					String tmp = st.nextToken(); // 200|[김의연] 안녕하세요
					viewMessage(tmp);
				}
					break;

				case ChatConstance.SC_PAPER: {
//						300|보내는사람|안녕하세요
					String from = st.nextToken();
					String tmp = st.nextToken();
					paper.card.show(paper.cardp, "answer");
					paper.to.setText(myid);
					paper.from.setText(from);
					paper.letter.setText(tmp);
					
					paper.setVisible(true);		
				}
					break;

				case ChatConstance.SC_RENAME: {
//						400|변경전대화명|변경후대화명
					String oldName = st.nextToken();
					String newName = st.nextToken();
					int index = chat.listData.indexOf(oldName);
					chat.listData.setElementAt(newName, index);
					chat.list.setListData(chat.listData);
					viewMessage("[알림]" + oldName + "님이 " + newName + "으로 변경되었습니다.");
				}
					break;

				case ChatConstance.SC_DISCONNECT: {
//					900|나가는사람
					String tmp = st.nextToken(); // 나가는사람
					if (!tmp.equals(myid)) {
						viewMessage("[알림] " + tmp + "님이 접속을 종료했습니다.");
						chat.listData.remove(tmp);
						chat.list.setListData(chat.listData);
					} else {
						flag = false; // while문을 종료시킨다.
						in.close(); // 입력을 닫는다.
						out.close(); // 출력을 닫는다.
						socket.close(); // 소켓을 닫는다.
						System.exit(0);// 창을 안보이게 한다.
					}

				}
					break;
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	private void viewPaper(String to, String msg) {
		paper.letter.append(msg + "\n");
		paper.letter.setCaretPosition(paper.letter.getDocument().getLength());
	}

	private void viewMessage(String msg) {
		chat.area.append(msg + "\n");
		chat.area.setCaretPosition(chat.area.getDocument().getLength());
	}
}

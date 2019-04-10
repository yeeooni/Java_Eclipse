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

		// �α���â �̺�Ʈ ���
		nameTf.addActionListener(this);
		ok.addActionListener(this);
		cancel.addActionListener(this);

		// ä��â �̺�Ʈ ���
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

		// paperâ �̺�Ʈ ���
		chat.paper.addActionListener(this);
		paper.ok.addActionListener(this);
		paper.answer.addActionListener(this);
		paper.cancel.addActionListener(this);

		// ��ȭ���� �̺�Ʈ ���

		chat.rename.addActionListener(this); // ��ȭ�����ư
		rename.ok.addActionListener(this); // ����
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
//			System.out.println("�����");
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
//		400|�����Ҵ�ȭ��
		String newName = rename.newname.getText();
		
		myid = newName;
		
		sendMassage(ChatConstance.CS_RENAME + "|" + newName);

		if (newName.isEmpty()) {
			return;
		}
		rename.setVisible(false);
	}

//	1.�޼����� ����.
//	2.server �޽��� ����
	private void paperProcess() {
//		300|�޴»��|�޼���

		String to = paper.to.getText().trim();
		String msg = paper.letter.getText().trim();
		if (msg.isEmpty()) {
			return;
		}
		if (myid.equals(selName)) {
			JOptionPane.showMessageDialog(paper, "�ڱ��ڽſ��� ������ ���� �� �����ϴ�.", "����� ����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		sendMassage(ChatConstance.CS_PAPER + "|" + to + "|" + msg);
		
		paper.letter.setText("");

	}

	private void closeProcess() {
		sendMassage(ChatConstance.CS_DISCONNECT + "|");
	}

//	1.�޼��� ����.(��ȿ���˻� �����, �ڽ�, �޼���)
//	2.server �޼��� ����
	private void whomsendProcess() {
		String msg = chat.whomsend.getText().trim();
		chat.whomsend.setText("");
		if (msg.isEmpty()) {
			return;
		}

		String to = chat.whom.getText();
		if (to.isEmpty()) {
			JOptionPane.showMessageDialog(chat, "����ڸ� �����ϼ���.", "����� ����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (to.equals(myid)) {
			JOptionPane.showMessageDialog(chat, "�ڱ��ڽſ��� �ӼӸ�?", "����� ����", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		sendMassage(ChatConstance.CS_TO + "|" + to + "|" + msg);
		viewMessage("��" + to + "��" + msg);

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		String selName = chat.list.getSelectedValue();
		chat.whom.setText(selName);
	}

//	1.�޼��� ����.(��ȿ���˻�)
//	2.server �޼��� ����
	private void globalsendProcess() {
		String msg = chat.globalsend.getText().trim();
		chat.globalsend.setText("");
		if (msg.isEmpty()) {
			return;
		}
//		200|�ȳ��ϼ���
		sendMassage(ChatConstance.CS_ALL + "|" + msg);
	}

//	1. ip, ��ȭ���� ����. (��ȭ���� ��ȿ���˻� 3���� �̻�)
//	2. 1�� ip�� ���� ������ �����Ѵ�.
//	3. IO(BufferedReader, OutputStream) ����.
//	4. login â �ݰ�, chat â ������
//	5. server�� �޼��� ����
//	6. Thread ����

	private void connectProcess() {
		String ip = ipTf.getText().trim();
		myid = nameTf.getText().trim();

		if (myid.length() < 3) {
			JOptionPane.showMessageDialog(this, "��ȭ�� 3���̻��Դϴ�.", "��ȭ�����", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			socket = new Socket(ip, ChatConstance.PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = socket.getOutputStream();
			this.setVisible(false);
			chat.setVisible(true);
			// �������� �޴� �޼ҵ�� BufferedReader�̱⶧����
			// Enter�� �ݵ�� ������ Server���� ���� �� �������� ���� �� �� �ִ�.
			// 100|��ȭ��
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
				System.out.println("������ ���� �޼���" + msg);// protocol|�޼�������.
				StringTokenizer st = new StringTokenizer(msg, "|");
				int protocol = Integer.parseInt(st.nextToken());

				switch (protocol) {
				case ChatConstance.SC_CONNECT: {
//					100|�����ڴ�ȭ��.
					String tmp = st.nextToken();
					viewMessage("[�˸�]" + tmp + "���� �����Ͽ����ϴ�.");
					chat.listData.add(tmp);
					chat.list.setListData(chat.listData);
				}
					break;

				case ChatConstance.SC_MESSAGE: {
//					200|[���ǿ�] �ȳ��ϼ���
//					200|�ٱ��ǿ��� �ȳ��ϼ���
					String tmp = st.nextToken(); // 200|[���ǿ�] �ȳ��ϼ���
					viewMessage(tmp);
				}
					break;

				case ChatConstance.SC_PAPER: {
//						300|�����»��|�ȳ��ϼ���
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
//						400|��������ȭ��|�����Ĵ�ȭ��
					String oldName = st.nextToken();
					String newName = st.nextToken();
					int index = chat.listData.indexOf(oldName);
					chat.listData.setElementAt(newName, index);
					chat.list.setListData(chat.listData);
					viewMessage("[�˸�]" + oldName + "���� " + newName + "���� ����Ǿ����ϴ�.");
				}
					break;

				case ChatConstance.SC_DISCONNECT: {
//					900|�����»��
					String tmp = st.nextToken(); // �����»��
					if (!tmp.equals(myid)) {
						viewMessage("[�˸�] " + tmp + "���� ������ �����߽��ϴ�.");
						chat.listData.remove(tmp);
						chat.list.setListData(chat.listData);
					} else {
						flag = false; // while���� �����Ų��.
						in.close(); // �Է��� �ݴ´�.
						out.close(); // ����� �ݴ´�.
						socket.close(); // ������ �ݴ´�.
						System.exit(0);// â�� �Ⱥ��̰� �Ѵ�.
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

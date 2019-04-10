package com.kitri.chat.client;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Chat extends javax.swing.JFrame implements ListSelectionListener {
	JScrollPane jScrollPane1;
	JTextField whomsend;
	JLabel whom;
	JTextField globalsend;
	JButton exit;
	JButton rename;
	JButton paper;
	JList<String> list;
	Vector<String> listData;
	JTextArea area;

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				Chat inst = new Chat();
//				inst.setLocationRelativeTo(null);
//				inst.setResizable(false);
//				inst.setVisible(true);
//			}
//		});
//	}
	
	public Chat() {
		super("Funny Chat!!!!");
		listData = new Vector<String>();
		initGUI();
		
		globalsend.requestFocus();
		
		setLocationRelativeTo(null);
		setResizable(false);
//		setVisible(true);
		
		list.addListSelectionListener(this);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			getContentPane().setBackground(new java.awt.Color(244,243,242));
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1);
				jScrollPane1.setBounds(7, 9, 391, 243);
				{
					area = new JTextArea();
					jScrollPane1.setViewportView(area);
//					area.setPreferredSize(new java.awt.Dimension(388, 240));
//					area.setEditable(false);
					area.setFocusable(false);
				}
			}
			{
				list = new JList<String>(listData);
				getContentPane().add(list);				
				list.setBounds(404, 9, 113, 205);
			}
			{
				paper = new JButton();
				getContentPane().add(paper);
				paper.setText("\ucabd\uc9c0\ubcf4\ub0b4\uae30");
				paper.setBounds(404, 220, 114, 32);
				paper.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				paper.setBackground(new java.awt.Color(237,236,233));
			}
			{
				rename = new JButton();
				getContentPane().add(rename);
				rename.setText("\ub300\ud654\uba85\ubcc0\uacbd");
				rename.setBounds(404, 258, 114, 32);
				rename.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				rename.setBackground(new java.awt.Color(237,236,233));
			}
			{
				exit = new JButton();
				getContentPane().add(exit);
				exit.setText("\ub098\uac00\uae30");
				exit.setBounds(404, 296, 114, 32);
				exit.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				exit.setBackground(new java.awt.Color(237,236,233));
			}
			{
				globalsend = new JTextField();
				getContentPane().add(globalsend);
				globalsend.setBounds(7, 258, 391, 32);
			}
			{
				whom = new JLabel();
				getContentPane().add(whom);
				whom.setBounds(7, 296, 90, 32);
			}
			{
				whomsend = new JTextField();
				getContentPane().add(whomsend);
				whomsend.setBounds(104, 296, 294, 32);
			}
			pack();
			this.setSize(541, 372);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		String selName = list.getSelectedValue();
		whom.setText(selName);
	}

}

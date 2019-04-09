package com.kitri.chat.client;
import java.awt.CardLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.SwingUtilities;

public class Paper extends javax.swing.JFrame {
	JLabel fl;
	JLabel tl;
	JLabel to;
	JTextArea letter;
	JButton answer;
	JPanel cardp;
	JButton cancel;
	JButton ok;
	JScrollPane jScrollPane1;
	JLabel from;
	
	CardLayout card;

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				Paper inst = new Paper();
//				inst.setLocationRelativeTo(null);
//				inst.setResizable(false);
//				inst.setVisible(true);
//			}
//		});
//	}
	
	public Paper() {
		super("쪽지보내기");
		initGUI();
		
		setLocationRelativeTo(null);
		setResizable(false);
//		setVisible(true);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			getContentPane().setBackground(new java.awt.Color(244,243,242));
			{
				fl = new JLabel();
				getContentPane().add(fl);
				fl.setText("From");
				fl.setBounds(5, 6, 42, 23);
				fl.setHorizontalAlignment(SwingConstants.CENTER);
				fl.setHorizontalTextPosition(SwingConstants.CENTER);
			}
			{
				tl = new JLabel();
				getContentPane().add(tl);
				tl.setText("To");
				tl.setHorizontalAlignment(SwingConstants.CENTER);
				tl.setHorizontalTextPosition(SwingConstants.CENTER);
				tl.setBounds(134, 6, 36, 23);
			}
			{
				from = new JLabel();
				getContentPane().add(from);
				from.setBounds(47, 6, 90, 23);
				from.setBackground(new java.awt.Color(255,255,230));
				from.setOpaque(true);
			}
			{
				to = new JLabel();
				getContentPane().add(to);
				to.setBounds(166, 6, 102, 23);
				to.setBackground(new java.awt.Color(255,255,230));
				to.setOpaque(true);
			}
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1);
				jScrollPane1.setBounds(5, 35, 265, 185);
				{
					letter = new JTextArea();
					jScrollPane1.setViewportView(letter);
				}
			}
			{
				cancel = new JButton();
				getContentPane().add(cancel);
				cancel.setText("\ucde8\uc18c");
				cancel.setBounds(147, 223, 48, 32);
				cancel.setBackground(new java.awt.Color(237,236,233));
				cancel.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			}
			{
				cardp = new JPanel();
				card = new CardLayout();
				cardp.setLayout(card);
				getContentPane().add(cardp);
				cardp.setBounds(86, 223, 58, 32);
				{
					ok = new JButton();
					cardp.add(ok, "ok");
					ok.setText("\ubcf4\ub0b4\uae30");
					ok.setBounds(12, 218, 62, 32);
					ok.setBackground(new java.awt.Color(237,236,233));
					ok.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
					ok.setPreferredSize(new java.awt.Dimension(62, 32));
				}
				{
					answer = new JButton();
					cardp.add(answer, "answer");
					answer.setText("\ub2f5\uc7a5");
					card.show(cardp, "ok");
				}
			}
			pack();
			this.setSize(289, 298);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}

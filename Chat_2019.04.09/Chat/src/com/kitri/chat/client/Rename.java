package com.kitri.chat.client;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.SwingUtilities;

public class Rename extends javax.swing.JFrame {
	JLabel old;
	JTextField newname;
	JButton cancel;
	JButton ok;
	JLabel oldname;
	JLabel newl;

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				Rename inst = new Rename();
//				inst.setLocationRelativeTo(null);
//				inst.setResizable(false);
//				inst.setVisible(true);
//			}
//		});
//	}
	
	public Rename() {
		super("대화명변경!!!");
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
				old = new JLabel();
				getContentPane().add(old);
				old.setText("\uae30\uc874\ub300\ud654\uba85");
				old.setBounds(12, 12, 72, 30);
				old.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				newl = new JLabel();
				getContentPane().add(newl);
				newl.setText("\ubcc0\uacbd\ub300\ud654\uba85");
				newl.setBounds(12, 48, 72, 30);
				newl.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				oldname = new JLabel();
				getContentPane().add(oldname);
				oldname.setBounds(96, 12, 179, 30);
				oldname.setHorizontalAlignment(SwingConstants.CENTER);
				oldname.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				oldname.setBackground(new java.awt.Color(255,255,230));
				oldname.setOpaque(true);
			}
			{
				newname = new JTextField();
				getContentPane().add(newname);
				newname.setBounds(96, 48, 179, 30);
			}
			{
				ok = new JButton();
				getContentPane().add(ok);
				ok.setText("\ubcc0\uacbd");
				ok.setBounds(76, 84, 61, 32);
				ok.setBackground(new java.awt.Color(237,236,233));
				ok.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			}
			{
				cancel = new JButton();
				getContentPane().add(cancel);
				cancel.setText("\ucde8\uc18c");
				cancel.setBounds(148, 84, 61, 32);
				cancel.setBackground(new java.awt.Color(237,236,233));
				cancel.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			}
			pack();
			this.setSize(303, 160);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}

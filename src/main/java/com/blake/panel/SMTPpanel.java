package com.blake.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.blake.type.Service;

public class SMTPpanel implements ActionListener{
	public JPanel panel = new JPanel();
	private Service service;
	
	Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int)screensize.getWidth();
	int height = (int)screensize.getHeight();

	JButton send = new JButton("SEND");
	//JButton put = new JButton("PUT");
	JButton retr = new JButton("RETRIVE");
	JTextField field1 = new JTextField();
	JTextField field2 = new JTextField();
	JTextArea area = new JTextArea(15,49);

	public JPanel getPanel() {
		panel.setLayout(new BorderLayout());
		
		JPanel top = new JPanel();
		top.setPreferredSize(new Dimension((7*width)/8-45 ,90));
		top.setBackground(Color.PINK);
		top.setLayout(new FlowLayout());
		top.add(new JLabel(" From: "));
		top.add(field1);
		top.add(new JLabel("    To  : "));
		top.add(field2);
		top.add(send);top.add(retr);
		send.addActionListener(this);
		retr.addActionListener(this);
		field1.setPreferredSize(new Dimension(480,20));
		field1.setText("zhangjie@sina.com");field1.setEditable(false);
		field2.setPreferredSize(new Dimension(480,20));
		field2.setText("2674633372@qq.com");
		panel.add(top,BorderLayout.NORTH);
		
		JPanel center = new JPanel();
		//center.setLayout(new FlowLayout());
		center.setBackground(Color.gray);
		area.setText("blake:~$init ...\n");
		area.append("blake:~$done\n");
		area.append("blake:~$connecting to server...\n");
		center.add(new JScrollPane(area));
		area.append("blake:~$done\n");
		
		panel.add(center,BorderLayout.CENTER);
		
		return panel;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==send){
			if(!field2.getText().equals("")){
				area.append("blake:~$waiting ... \n");
				try {
					service.doSend(field2.getText(),area.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				area.append("blake:~$ done\n");
			}else{
				area.append("blake:~$ no destination\n");
			}
		}
		if(e.getSource()==retr){
			
		}
	}

}

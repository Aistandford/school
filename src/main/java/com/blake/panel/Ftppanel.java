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

public class Ftppanel implements ActionListener{
	private Service service;
	
	Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int)screensize.getWidth();
	int height = (int)screensize.getHeight();
	
	JPanel panel = new JPanel();
	JButton start = new JButton("START");
	//JButton put = new JButton("PUT");
	JButton hold = new JButton("HOLD ON");
	JButton continued = new JButton("CONTINUE");
	JTextField field1 = new JTextField();
	JTextField field2 = new JTextField();
	JTextArea area = new JTextArea(15,49);

	public JPanel getPanel() {
		panel.setLayout(new BorderLayout());
		
		JPanel top = new JPanel();
		top.setPreferredSize(new Dimension((7*width)/8-45 ,90));
		top.setBackground(Color.PINK);
		top.setLayout(new FlowLayout());
		top.add(new JLabel(" GET: "));
		top.add(field1);
		top.add(new JLabel(" PUT: "));
		top.add(field2);
		top.add(start);top.add(hold);top.add(continued);
		start.addActionListener(this);
		hold.addActionListener(this);
		continued.addActionListener(this);
		field1.setPreferredSize(new Dimension(480,20));
		field1.setText("get.txt");
		field2.setPreferredSize(new Dimension(480,20));
		field2.setText("put.txt");
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

	public void actionPerformed(ActionEvent eve) {
		if(eve.getSource()==start){
			area.append("blake:~$start\n");
			area.append("blake:~$waiting .........\n");
			if(!field1.getText().equals("")){
				try {
					area.append("blake:~$step 1 : get "+field1.getText()+"\n");
					if(service.doGet(field1.getText())){
						area.append("blake:~$done\n");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(!field2.getText().equals("")){
				area.append("blake:~$step 2 : put "+field2.getText()+"\n");
				try {
					if(service.doPut(field2.getText())){
						area.append("blake:~$done\n");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(eve.getSource()==hold){
			service.setHold(true);
			area.append("blake:~$hold\n");
		}
		if(eve.getSource()==continued){
			service.setHold(false);
			area.append("blake:~$continued\n");
		}
	}
	
	public void setService(Service s){
		this.service=s;
	}

}

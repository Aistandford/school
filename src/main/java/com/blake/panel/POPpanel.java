package com.blake.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.JSONObject;

import com.blake.type.POPService;
import com.blake.type.Service;

public class POPpanel{
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
	JTextArea area = new JTextArea(19,48);

	public JPanel getPanel() {
		panel.setLayout(new BorderLayout());
		
		JPanel top = new JPanel();
		top.setPreferredSize(new Dimension((7*width)/8-45 ,30));
		top.setBackground(Color.PINK);
		top.setLayout(new FlowLayout());
		top.add(new JLabel("total:"));
		top.add(field1);

		field1.setPreferredSize(new Dimension(480,20));
		field1.setEditable(false);
		//field2.setPreferredSize(new Dimension(480,20));
		//field2.setText("2674633372@qq.com");
		panel.add(top,BorderLayout.NORTH);
		
		JPanel center = new JPanel();
		//center.setLayout(new FlowLayout());
		center.setBackground(Color.gray);
		center.add(new JScrollPane(area));
		getContent();
		
		panel.add(center,BorderLayout.CENTER);
		
		return panel;
	}

	private void getContent() {
		if(service==null){
			service = new POPService();
		}
		JSONObject json=service.doReceive(null,null);
		String a[]=String.valueOf(json.get("num")).split("\"");
		field1.setText(a[1]);

		area.append("the latest mail is :");
		area.append("\n\n");
		String s = String.valueOf(json.get("data"));
		String d[]=s.split("####");
		for(int i=0;i<d.length;i++){
			area.append(d[i]+"\n");
		}
	}

	public void setService(Service service) {
		this.service = service;
	}

}

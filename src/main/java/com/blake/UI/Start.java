/**
 * @author blake
 */
package com.blake.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.blake.panel.Ftppanel;
import com.blake.panel.POPpanel;
import com.blake.panel.SMTPpanel;
import com.blake.type.FTPService;
import com.blake.type.POPService;
import com.blake.type.SMTPService;
import com.blake.type.Service;

public class Start extends JFrame implements ActionListener{
	
	/**
	 * @category main
	 */
	private static final long serialVersionUID = 3440502593888523932L;
	
	Service service;
	
	//屏幕大小
	Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int)screensize.getWidth();
	int height = (int)screensize.getHeight();
	//component
	JButton ftp = new JButton();
	JButton smtp = new JButton();
	JButton pop = new JButton();
	JPanel right = new JPanel();
	JPanel left = new JPanel();
	static JPanel ftppanel ;//=  new JPanel();
	JPanel smtppanel;// =  new JPanel();
	JPanel poppanel ;//=  new JPanel();
	JPanel panel =new JPanel();

	static {
		Ftppanel f =  new Ftppanel();
		ftppanel = f.getPanel();
		Service service = new FTPService();
		f.setService(service);
	}
	
	public Start(){
		this.setTitle("SYSTEM INTEGRATION");
		this.setLocation(width/4, height/4);
		this.setSize(width/2, height/2);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void init(){
		//左边框架
		left.setLayout(new GridLayout(7,1));
		ftp.setText("FTP");
		ftp.setSize(new Dimension(width/8-100,30));
		smtp.setText("SMTP");
		smtp.setSize(new Dimension(width/8-100,30));
		pop.setText("POP");
		pop.setSize(new Dimension(width/8-100,30));
		left.add(new JLabel("   Please select"));
		left.add(ftp);
		left.add(new JLabel(""));
		left.add(smtp);
		left.add(new JLabel(""));
		left.add(pop);
		left.add(new JLabel(""));
		//右边框架，默认ftp
		addRight(ftppanel);
		
		//this.repaint();
		panel.setLayout(new BorderLayout());
		panel.add(left,BorderLayout.WEST);
		left.setPreferredSize(new Dimension(width/8-50,height/2));
		//left.setBackground(Color.MAGENTA);  //设置颜色
		left.setBorder(BorderFactory.createTitledBorder(""));
		panel.add(right,BorderLayout.CENTER);
		right.setBorder(BorderFactory.createTitledBorder("FTP CLIENT"));
		ftp.addActionListener(this);
		smtp.addActionListener(this);
		pop.addActionListener(this);
		this.add(panel);
	}
	
	private void addRight(JPanel panel) {
		right=panel;
		right.setBackground(Color.CYAN); //设置颜色
		right.setBorder(BorderFactory.createTitledBorder(""));
		right.setPreferredSize(new Dimension((7*width)/8-50,height/2));
	}

	public static void main(String args[]){
		Start s =  new Start();
		s.init();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ftp){
			if(ftppanel==null){
				Ftppanel f =  new Ftppanel();
				ftppanel =  f.getPanel();
				service = new FTPService();
				f.setService(service);
				
				panel.remove(right);
				addRight(ftppanel);
				right.setBorder(BorderFactory.createTitledBorder("FTP CLIENT"));
				panel.add(right,BorderLayout.CENTER);
				
				panel.revalidate();
				panel.updateUI();
				panel.repaint();
				poppanel=null;
				smtppanel=null;
			}else{
				System.out.println("already is");
			}			
		}
		if(e.getSource()==smtp){
			if(smtppanel==null){
				SMTPpanel f =new SMTPpanel();
				smtppanel = f.getPanel();
				service = new SMTPService();
				f.setService(service);
				
				panel.remove(right);
				addRight(smtppanel);
				right.setBorder(BorderFactory.createTitledBorder("SMTP CLIENT"));
				panel.add(right,BorderLayout.CENTER);
				
				panel.updateUI();
				panel.repaint();
				panel.revalidate();
				ftppanel=null;
				poppanel=null;
			}else{
				System.out.println("already is");
				return;
			}
		}
		if(e.getSource()==pop){
			if(poppanel==null){
				POPpanel f =new POPpanel();
				poppanel = f.getPanel();
				service = new POPService();
				f.setService(service);
				
				panel.remove(right);
				addRight(poppanel);
				right.setBorder(BorderFactory.createTitledBorder("POP CLIENT"));
				panel.add(right,BorderLayout.CENTER);
				
				panel.updateUI();
				panel.repaint();
				panel.revalidate();
				ftppanel=null;
				smtppanel=null;
			}else{
				System.out.println("already is");
			}
		}
	}
	

}

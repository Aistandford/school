package com.blake.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import sun.misc.BASE64Encoder;

import com.blake.util.GlobalConfiguration;


public class SMTPClient {
	/*
	 * 	SMTP的基本命令集：

		HELO　　 向服务器标识用户身份
		MAIL　　　初始化邮件传输mail from: <xxx>
		RCPT　　　标识单个的邮件接收人；常在MAIL命令后面可有多个rcpt to: <xxx>
		DATA　　　在单个或多个RCPT命令后，表示所有的邮件接收人已标识，初始化数据传输，以.结束。
		NOOP　　 无操作，服务器应响应OK
		RSET　　　重置会话，当前传输被取消
		QUIT　　　结束会话
	 */
    //以下三项请在使用时改成真实的信箱地址
    //并且注意，SMTPServer和receiver必须是同一个服务器
    //private static String sender = "sender";
    //private static String receiver = "receiver";
    //private static String SMTPServer = "smtpserver";
    
    public boolean Send(String content) throws UnknownHostException, IOException{
    	PrintWriter output;  
    	BufferedReader input;  
        Socket socket = null;  
        String user = "2674633372@qq.com"; 
    	
         
        socket = new Socket("smtp.126.com", 25);  
        output = new PrintWriter(new OutputStreamWriter(socket  
                .getOutputStream()));  
        input = new BufferedReader(new InputStreamReader(socket  
                .getInputStream()));  
        System.out.println(input.readLine());  
        output.println("helo 126");  
        output.flush();  
        System.out.println(input.readLine());  
        //验证登陆  
        output.println("auth login");  
        output.flush();  
        System.out.println(input.readLine());  
        //用户名  
        output.println(new BASE64Encoder().encode(GlobalConfiguration.mailname.getBytes()));  
        output.flush();  
        System.out.println(input.readLine());  
        //密码  
        output.println(new BASE64Encoder().encode(GlobalConfiguration.mailpass.getBytes()));  
        output.flush();  
        System.out.println(input.readLine());  
        //发件人  
        output.println("mail from: <hello_mydream@126.com>");  
        output.flush();  
        System.out.println(input.readLine());  
        //收件人  
        output.println("rcpt to: <" + user + ">");  
        output.flush();  
        System.out.println(input.readLine());  

        //内容  
        output.println("data");  
        output.flush();  
        System.out.println(input.readLine());  
        String con = "From: 网易邮箱<myjob@126.com\r\n";  
        con += "To: <" + user + ">\r\n";  
        con = con + "Subject: 网易邮箱提醒\r\n";  
        con = con + "Content-Type: text/plain;charset=\"utf-8\"\r\n";  
        con = con + "\r\n";  
        con = con + "网易邮箱提醒您，有新邮件，请接收\r\n";  
        con = con + ".\r\n";  
        output.println(con);  
        output.flush();  
        System.out.println(input.readLine());   
        socket.close();  
        input.close();  
        output.close();  
        System.out.println("Done"); 
        
		return true;
    }
}
package com.blake.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONArray;
import org.json.JSONObject;

import com.blake.util.GlobalConfiguration;

public class POP3Client {
	   private static String POP3Server = "pop.126.com";
	   public JSONObject get() {
		   	int POP3Port = 110;
		   	JSONObject json = new JSONObject();
	        Socket client = null;
	        try {
	            // 向POP3服务程序建立一个套接字连接。
	            client = new Socket(POP3Client.POP3Server, POP3Port);
	            // 创建一个BufferedReader对象，以便从套接字读取输出。
	            InputStream is = client.getInputStream();
	            BufferedReader sockin = new BufferedReader(new InputStreamReader(is));
	            // 创建一个PrintWriter对象，以便向套接字写入内容。
	            OutputStream os = client.getOutputStream();
	            PrintWriter sockout = new PrintWriter(os, true);
	            // 显示同SMTP服务程序的握手过程。
	            System.out.println("S:" + sockin.readLine());
	            sockout.println("user " + GlobalConfiguration.mailname);
	            System.out.println("S:" + sockin.readLine());
	            sockout.println("pass " + GlobalConfiguration.mailpass);
	            System.out.println("S:" + sockin.readLine());
	            sockout.println("stat");
	            String temp[] = sockin.readLine().split(" ");
	            int count = Integer.parseInt(temp[1]);//得到信箱中共有多少封邮件
	            json.append("num", String.valueOf(count));
	            JSONArray atrray =  new JSONArray();
	            StringBuffer sb = null;
	            for (int i = 1; i < count + 1; i++) {//依次打印出邮件的内容
	                sockout.println("retr " + i);
	                JSONObject te = new JSONObject();
	                System.out.println("以下为第" + i + "封邮件的内容");
	                sb = new StringBuffer();
	                while (true) {
	                    String reply = sockin.readLine();
	                    sb.append(reply+"####");
	                    //System.out.println(reply);
	                    if (reply.toLowerCase().equals(".")) {
	                        break;
	                    }
	                    te.append(String.valueOf(i), sb);
	                }
	                atrray.put(te);
	            } 
	            json.append("data", sb.toString());
	        } catch (IOException e) {
	            System.out.println(e.toString());
	        } finally {
	            try {
	                if (client != null) {
	                    client.close();
	                }
	            } catch (IOException e) {}
	        }
	        
			return json;
	    }
	}
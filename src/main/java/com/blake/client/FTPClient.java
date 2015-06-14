package com.blake.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.blake.util.GlobalConfiguration;

public class FTPClient {
	boolean hold = false;

	public boolean put(String name) throws UnknownHostException, IOException {
		Socket socket = new Socket(GlobalConfiguration.url,21);

        InputStream is = socket.getInputStream();  
        OutputStream os = socket.getOutputStream();
        //接收初始链接信息
        byte[] buffer = new byte[10000];
        int length = is.read(buffer);
        String s = new String(buffer, 0, length);
        
        //发送用户名
        String str = "USER blake\n";
        os.write(str.getBytes());
        //得到返回值
        length = is.read(buffer);
        s = new String(buffer, 0, length);
        
        //发送密码
        str = "PASS 193535\n";
        os.write(str.getBytes());
        //得到返回值
        length = is.read(buffer);
        s = new String(buffer, 0, length);

        //发送切换文件夹指令
        str = "CWD /home/blake\n";
        os.write(str.getBytes());
        //得到返回值
        length = is.read(buffer);
        s = new String(buffer, 0, length);

        //设置模式
        str = "EPSV ALL\n";
        os.write(str.getBytes());
        //得到返回值
        length = is.read(buffer);
        s = new String(buffer, 0, length);
      
        //得到被动监听信息
        str = "EPSV\n";
        os.write(str.getBytes());
        //得到返回值
        length = is.read(buffer);
        s = new String(buffer, 0, length);

        //取得FTP被动监听的端口号
        String portlist=s.substring(s.indexOf("(|||")+4,s.indexOf("|)"));
       // 实例化ShowList线程类，链接FTP被动监听端口号
        PutFile sl=new PutFile();
        sl.port=Integer.parseInt(portlist);
        sl.start();
        
        //执行Retrive命令
        str = "STOR put.txt\n";
        os.write(str.getBytes());
        //得到返回值

        //关闭链接
        is.close();
        os.close();
        socket.close();
		return true;
        
	}

	public boolean Get(String string) throws UnknownHostException, IOException {
		Socket socket = new Socket(GlobalConfiguration.url,21);

        InputStream is = socket.getInputStream();  
        OutputStream os = socket.getOutputStream();
        //接收初始链接信息
        byte[] buffer = new byte[10000];
        int length = is.read(buffer);
        String s = new String(buffer, 0, length);
        
        //发送用户名
        String str = "USER blake\n";
        os.write(str.getBytes());
        //得到返回值
        length = is.read(buffer);
        s = new String(buffer, 0, length);
        
        //发送密码
        str = "PASS 193535\n";
        os.write(str.getBytes());
        //得到返回值
        length = is.read(buffer);
        s = new String(buffer, 0, length);

        //发送切换文件夹指令
        str = "CWD /home/blake\n";
        os.write(str.getBytes());
        //得到返回值
        length = is.read(buffer);
        s = new String(buffer, 0, length);

        //设置模式
        str = "EPSV ALL\n";
        os.write(str.getBytes());
        //得到返回值
        length = is.read(buffer);
        s = new String(buffer, 0, length);
      
        //得到被动监听信息
        str = "EPSV\n";
        os.write(str.getBytes());
        //得到返回值
        length = is.read(buffer);
        s = new String(buffer, 0, length);

        //取得FTP被动监听的端口号
        String portlist=s.substring(s.indexOf("(|||")+4,s.indexOf("|)"));
       // 实例化ShowList线程类，链接FTP被动监听端口号
        GetFile sl=new GetFile();
        sl.port=Integer.parseInt(portlist);
        sl.start();
        
        //执行Retrive命令
        str = "RETR get.txt\n";
        os.write(str.getBytes());
        //得到返回值

        //关闭链接
        is.close();
        os.close();
        socket.close();
        
		return true;
	}
}

class GetFile extends Thread{
	 public int port=0;
	 public void run(){
	     try{
	         Socket socket = new Socket("127.0.0.1",this.port);
	         InputStream is = socket.getInputStream();
	         //OutputStream os = socket.getOutputStream();
	         OutputStream fileos = new FileOutputStream(GlobalConfiguration.localPath+"/"+"get.txt");
	         byte[] buffer;
	         buffer = new byte[4*1024];
	         int len = 0,count = 0;
	         count = is.available();
	         try {
	 			while(count>0&&(len = is.read(buffer)) !=-1)
	 			{
	 				fileos.write(buffer,0,len);
	 				count = is.available();
	 			}
	 			fileos.flush();
	 		} catch (IOException e) {
	 			e.printStackTrace();
	 		}
	        fileos.close(); 
	        is.close();
	        socket.close();
	     }
	     catch(Exception ex){
	    	 
	     }

}
}
class PutFile extends Thread{
	 public int port=0;
	 public void run(){
	     try{
	         Socket socket = new Socket("127.0.0.1",this.port);
	         OutputStream os = socket.getOutputStream();
	         File file = new File("/home/blake/workspace/put.txt");
	         InputStream in = null;
	         byte[] buffer;
	         buffer = new byte[4*1024];
	         try {
	             // 一次读一个字节
	             in = new FileInputStream(file);
	             int tempbyte;
	             while ((tempbyte = in.read(buffer)) != -1) {
		 			Thread.sleep(100);
	                os.write(buffer, 0, tempbyte);
	             }
	             os.flush();
	             in.close();
	         } catch (IOException e) {
	             e.printStackTrace();
	             return;
	         }
	         os.close(); 
	         socket.close();
	     }
	     catch(Exception ex){
	    	 ex.printStackTrace();
	     }
	 }
}
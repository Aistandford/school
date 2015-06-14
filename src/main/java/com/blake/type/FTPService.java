package com.blake.type;

import java.io.IOException;
import org.json.JSONObject;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import com.blake.client.FTPClient;
import com.blake.util.GlobalConfiguration;

public class FTPService extends Service {
		//远程命令包括
		//USER    PORT    RETR    ALLO    DELE    SITE    XMKD    CDUP    FEAT<br>
		//PASS    PASV    STOR    REST    CWD     STAT    RMD     XCUP    OPTS<br>
		//ACCT    TYPE    APPE    RNFR    XCWD    HELP    XRMD    STOU    AUTH<br>
		//REIN    STRU    SMNT    RNTO    LIST    NOOP    PWD     SIZE    PBSZ<br>
		//QUIT    MODE    SYST    ABOR    NLST    MKD     XPWD    MDTM    PROT<br>

	private boolean hold = false;
	
	@Override
	public boolean doPut(String name) throws IOException {
		if(connectUsingSocketput(name)){
			return true;
		}else if(connectUsingFTPClientput(name)){
			return true;
		}
		return false;
	}


	private boolean connectUsingSocketput(String name) throws IOException {
		
		if(new FTPClient().put(name)){
			return true;
		}
		return false;
	}
	
	private boolean connectUsingFTPClientput(String name) {
		return false;
	}

	public boolean doGet(String string) throws Exception{
		
		if(connectUsingSocket(string)){
			return true;
		}else if(connectUsingFTPClient(string)){
			return true;
		}
		return false;
		
	}

	private boolean connectUsingSocket(String string) throws IOException  {
		
		if(new FTPClient().Get(string)){
			return true;
		}
		return false;
		
		
	}

	private boolean connectUsingFTPClient(String string) {
		try {
			FtpClient ftp = FtpClient.create("127.0.0.1");
			ftp.login(GlobalConfiguration.username, GlobalConfiguration.password.toCharArray());//登录
		} catch (FtpProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}


	public boolean isHold() {
		return hold;
	}


	public void setHold(boolean hold) {
		this.hold = hold;
	}


	@Override
	public boolean doSend(String text, String text2) {
		return false;
	}


	@Override
	public JSONObject doReceive(String text, String text2) {
		return null;
	}

}

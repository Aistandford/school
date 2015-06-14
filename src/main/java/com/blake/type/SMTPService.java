package com.blake.type;

import java.io.IOException;

import org.json.JSONObject;

import com.blake.client.SMTPClient;

public class SMTPService extends Service {
	

	@Override
	public boolean doSend(String from, String content) throws IOException {
		if(connectUsingSocket(from,content)){
			return true;
		}
		return false;
	}

	private boolean connectUsingSocket(String to, String content) throws IOException {
		
		if(new SMTPClient().Send(content)){
			return true;
		}
		
		return false;
		
	}

	@Override
	public boolean doGet(String text) throws Exception {
		return false;
	}

	@Override
	public boolean doPut(String text) throws IOException {
		return false;
	}

	@Override
	public JSONObject doReceive(String text, String text2) {
		return null;
	}

}

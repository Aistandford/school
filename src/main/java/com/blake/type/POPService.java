package com.blake.type;

import java.io.IOException;

import org.json.JSONObject;

import com.blake.client.POP3Client;

public class POPService extends Service {

	@Override
	public boolean doGet(String text) throws Exception {
		return false;
	}

	@Override
	public boolean doPut(String text) throws IOException {
		return false;
	}

	@Override
	public boolean doSend(String text, String text2) {
		return false;
	}

	@Override
	public JSONObject doReceive(String text, String text2) {
		JSONObject json = new POP3Client().get();
		return json;
	}

}

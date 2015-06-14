package com.blake.type;

import java.io.IOException;

import org.json.JSONObject;

public abstract class Service {
	
	protected boolean hold = false;

	public abstract boolean doGet(String text) throws Exception ;

	public abstract boolean doPut(String text) throws IOException ;
	
	public abstract boolean doSend(String text, String text2) throws IOException ;
	
	public abstract JSONObject doReceive(String text, String text2) ;

	public boolean isHold() {
		return hold;
	}

	public void setHold(boolean hold) {
		this.hold = hold;
	}

}

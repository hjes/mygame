package com.ai.bean;

public class User {
	private String socketIp;
	private int socketPort;
	
	private String myUserkey;
	private String myPkey;
	
	public String getMyUserkey() {
		return myUserkey;
	}
	public void setMyUserkey(String myUserkey) {
		this.myUserkey = myUserkey;
	}
	public String getMyPkey() {
		return myPkey;
	}
	public void setMyPkey(String myPkey) {
		this.myPkey = myPkey;
	}
	public String getSocketIp() {
		return socketIp;
	}
	public void setSocketIp(String socketIp) {
		this.socketIp = socketIp;
	}
	public int getSocketPort() {
		return socketPort;
	}
	public void setSocketPort(int socketPort) {
		this.socketPort = socketPort;
	}
	
	
}

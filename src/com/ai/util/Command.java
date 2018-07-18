package com.ai.util;

import java.util.Map;
import java.util.Set;

public class Command {
	private CmdType ctp;
	private int token;
	private String commandstr;
	private String command;
	private int dataLength;
	private byte[] outputArr;
	
	private Map<String,String> sendparm;
	
	
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public CmdType getCtp() {
		return ctp;
	}

	public void setCtp(CmdType ctp) {
		this.ctp = ctp;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	public String getCommandstr() {
		return commandstr;
	}

	public void setCommandstr(String commandstr) {
		this.commandstr = commandstr;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public byte[] getOutputArr() {
		return outputArr;
	}

	public void setOutputArr(byte[] outputArr) {
		this.outputArr = outputArr;
	}

	public Map<String, String> getSendparm() {
		return sendparm;
	}

	public void setSendparm(Map<String, String> sendparm) {
		this.sendparm = sendparm;
	}

	public Command (String cmd,Map<String,String> param){
		ctp=getCurCmdType(cmd);
		token++;
		command=cmd;
		sendparm=param;
		String params="";
		if(param!=null){
			for (String key : param.keySet()) {
				if(!params.equals("")){
					params+="&";
				}
				params+=key+"="+param.get(key);
			}
		}
		int nDataLength = 4/*��ͷ����ʶ����*/+ 32/*����*/+ 4/*����*/+ params.length();
		outputArr=new byte[nDataLength];
		 int m = nDataLength - 4;
         byte[] bLen = new byte[4];
         bLen[3] = (byte)(m & 0xFF);
         bLen[2] = (byte)((m & 0xFF00) >> 8);
         bLen[1] = (byte)((m & 0xFF0000) >> 16);
         bLen[0] = (byte)((m >> 24) & 0xFF);
         byte[] cmdbyte=cmd.getBytes();
         byte[] bToken = new byte[4];
         bToken[3] = (byte)(token & 0xFF);
         bToken[2] = (byte)((token & 0xFF00) >> 8);
         bToken[1] = (byte)((token & 0xFF0000) >> 16);
         bToken[0] = (byte)((token >> 24) & 0xff);
         byte[] bLst = params.getBytes();
         System.arraycopy(bLen, 0, outputArr, 0, 4);
         System.arraycopy(cmdbyte, 0, outputArr, 4,cmdbyte.length);
         System.arraycopy(bToken, 0, outputArr, 36,4);
         System.arraycopy(bLst, 0, outputArr, 40,bLst.length);
         
	}
	
	public CmdType getCurCmdType(String cmd){
		CmdType ctp_=CmdType.UnKnown;
		if(cmd.equals("login_user")){
			ctp_=CmdType.login;
		}else if(cmd.equals("building@upgradeBuilding")){
			ctp_=CmdType.building1;
		}else if(cmd.equals("player@game")){
			ctp_=CmdType.Heartbeat1;
		}else if(cmd.equals("player@ttest")){
			ctp_=CmdType.Heartbeat2;
		}
		return ctp_;
	}
	
	public enum CmdType{ UnKnown,building1,login,Heartbeat1,Heartbeat2};
}

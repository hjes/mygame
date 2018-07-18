package com.ai.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SockeUtil {
	 	private static Socket socket = null;
	    private static OutputStream os = null;
	    private static InputStream is = null;    
	    private boolean flag=false;
	    ExecutorService exec = Executors.newFixedThreadPool(2);
	 public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}
	 
	public SockeUtil(final String clientIp,final int clientPort,final String pk2) {
		// TODO Auto-generated constructor stub
		exec.execute(
		 new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("----------");
						while(true){
							  if (socket == null || socket.isClosed()) { 
								  System.out.println("socket success");
			                        socket = new Socket(clientIp, clientPort); // 连接socket
			                        os = socket.getOutputStream();
				                   sendHeartbeat(pk2);
			                    }
							  Thread.sleep(1000);
							  is = socket.getInputStream();
							  int size = is.available();
			                    if (size <= 0) {
			                        continue;
			                    }
			                    byte[] resp = new byte[size];
			                    is.read(resp);
			                    getZipByteArr(resp);
						}
					} catch (Exception e) {
						System.out.println(1111111);
					}finally {
						System.out.println(222222);
					}
					System.out.println("55555555");
				}
			}));
	}   
	 private List<Map<String,String>> getZipByteArr(byte[] recvarr) throws UnsupportedEncodingException{
		 List<Map<String,String>>  	lst=new ArrayList<>();
		  int index = 0;
		  while (index!=-1) {
			  int nPos = 4 + 32 + 4 + index;//起始位置
              int nTotalLen = (int)(recvarr[index + 0] << 24) + (int)(recvarr[index + 1] << 16) + (int)(recvarr[index + 2] << 8) + (int)(recvarr[index + 3]);
              int nLen = nTotalLen - 32 - 4;
              if (nLen > recvarr.length - index)
              {// 格式不正确的报文不解析
                 // byte[] temparr = new byte[recvarr.length - index];
                  //System.arraycopy(recvarr, index, temparr, 0, recvarr.length - index);
                  // 打印 出错字符串
                  String str = StringUtils.bytesToHex(recvarr);
                  //String StringMessage = encoder.GetString(recvarr, index, 40);
                  System.out.println("无法解析的报文,字节数组=" + str + ";iindex=" + index);
                  return lst;
              }
              else if (nLen < 0){
            	  break;
              }
              byte[] outputarr = new byte[nLen];
              System.arraycopy(recvarr, nPos, outputarr, 0, nLen);

              byte[] cmd = new byte[32];
              System.arraycopy(recvarr, index + 4, cmd, 0, 32);
              String cmdstr = new String(cmd);
              // 将字节流解析为字符串
              byte[] arrDescrypt = ZLibUtils.decompress(outputarr);
              if (arrDescrypt != null)
              {
                  String outputstr;
				try {
					outputstr = new String(arrDescrypt,"UTF-8");
					LogUtils.logBox("报文解析:" + cmdstr + "=" +outputstr);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                 
              }
              if (index + nTotalLen + 4 < recvarr.length)
              {
                  index += nTotalLen + 4;
              }
              else
                  index = -1;
		}
		 return lst;
	 }
	 
	/**
     * 发送socket请求
     * @param clientIp
     * @param clientPort
     * @param msg
     * @return
	 * @throws IOException 
	 * @throws UnknownHostException 
     */
    public   String tcpPost(byte[] msg) throws UnknownHostException, IOException{
           // 客户端的输出流
    		while(socket==null){
    			try {
					Thread.sleep(500);
					System.out.println("socket wait");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
           os.write(msg);
           return "";
    }
    
    /**
     * 发送心跳包
     */
    public void sendHeartbeat(String  pk2) {
    	  Map<String, String> map=new HashMap<>();
          map.put("pkey",pk2);
          final Command cmd=new Command("player@game",map); 
        try {
        	exec.execute(new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(4 * 1000);// 10s发送一次心跳
                            if(flag)
                            tcpPost(cmd.getOutputArr());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

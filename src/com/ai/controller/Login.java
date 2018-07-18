package com.ai.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.swing.DefaultComboBoxModel;

import com.ai.bean.Phistory;
import com.ai.bean.Player;
import com.ai.bean.PlayerInfo;
import com.ai.bean.User;
import com.ai.util.Command;
import com.ai.util.LogUtils;
import com.ai.util.SockeUtil;
import com.ai.util.StringUtils;
import com.ai.util.webClient;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gc.ui.U_Main;


public class Login {
	private static WebClient myclient = null;
	private static User u=new User();
	static{
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);  
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void login(String url, String uname, String pwd) throws Exception {
		LogUtils.logstatus("正在登陆....");
		myclient = webClient.getWebClient();
		HtmlPage page = myclient.getPage(url);
		HtmlForm form = page.getForms().get(0);
		HtmlTextInput username = (HtmlTextInput) form.getInputByName("username");
		HtmlPasswordInput password = (HtmlPasswordInput) form.getInputByName("password");
		username.setValueAttribute("qq1605000002");
		password.setValueAttribute("111222333.");
		HtmlPage retPage = null;
		DomNodeList<HtmlElement> inputs = form.getElementsByTagName("input");
		for (HtmlElement htmlElement : inputs) {
			if (htmlElement.getAttribute("type").equals("submit")) {
				retPage = htmlElement.click();
				break;
			}
		}
		page = myclient.getPage("http://gc.aoshitang.com/");
		LogUtils.logstatus("获取历史登录服务器...");
		page = myclient.getPage(
				"http://gc.aoshitang.com/getsession.xhtml?x=0.30230397457159963&isgameLogin=true&top=1&gameid=gcld&source=");
		String userserlverlist = page.getBody().asText();
		JSONObject userserverJson = JSONObject.parseObject(userserlverlist);
		@SuppressWarnings("static-access")
		List<Phistory> historylist = userserverJson.parseArray(userserverJson.getString("phistory"), Phistory.class);
		System.out.println("server list :");
		int i=0;
		if (historylist != null) {
			String[] servlerList=new String[historylist.size()];
			for (Phistory p : historylist) {
				servlerList[i]=p.getServerName();
				LogUtils.logBox(p.getServerName() + ":" + p.getServerUrl());
				i++;
			}
			U_Main.cbxServerlist.setModel(new DefaultComboBoxModel(servlerList));
		}
		page=myclient.getPage("http://gc.aoshitang.com/gm.xhtml?gid=gcld&server=1668");
        DomNode iframe = page.querySelector("frame[name='gameFrame']");
        String src = StringUtils.getStringByregEx(iframe.asXml(),"c=\"(?<value>.*?)\"");
        src=src.substring(3, src.length()-1).replace("&amp;", "&");
        page=myclient.getPage(src);
        Cookie ticket = myclient.getCookieManager().getCookie("ticket");
        u.setMyUserkey(ticket.getValue());
        u.setMyPkey(ticket.getValue());
        //"AC03699FDF5C9ACD654B760A050BBBF4"
        //"9A967D5A0646F148A44C5BD350ADE679
        System.out.println("游戏登录成功！");
        //http://s1668.gc.aoshitang.com/root/gateway.action?command=player@getPlayerList
         JSONObject player = webClient.db2String("http://s1668.gc.aoshitang.com/root/gateway.action?command=player@getPlayerList");
         System.out.println();
         List<Player> playerlist = JSONObject.parseArray(player.getJSONObject("action").getJSONObject("data").getString("playerList"), Player.class);
         if(playerlist!=null) {
        	 i=0;
        	 String[] role=new String[playerlist.size()];
        	 for (Player player2 : playerlist) {
				role[i]=player2.getPlayerName();
				i++;
			}
        	 U_Main.cbxrolelist.setModel(new DefaultComboBoxModel(role));
         }
         player=webClient.db2String("http://s1668.gc.aoshitang.com/root/gateway.action?command=player@getPlayerInfo&version=11.01.17.1&info="+Math.random()*9999+1+"&playerId="+playerlist.get(0).getPlayerId());
         PlayerInfo pleyerinfo = JSONObject.parseObject(player.getJSONObject("action").getJSONObject("data").getString("player"), PlayerInfo.class);
         initRoleInfo(pleyerinfo);
         String str = webClient.getpage("http://s1668.gc.aoshitang.com/Config.xml");
         String socketServiceUrl = StringUtils.getStringByregEx(str,"socketServiceUrl value=\"(?<value>.*?)\"");
         socketServiceUrl=socketServiceUrl.replace("socketServiceUrl value=", "").replace("\"", "");
         String[] sockets = socketServiceUrl.split(":");
         u.setSocketIp(sockets[0]);
         u.setSocketPort(Integer.parseInt(sockets[1]));
         Map<String, String> map=new HashMap<>();
         map.put("userkey",u.getMyUserkey());
         Command cmd=new Command("login_user",map); 
         byte[] sendarr = cmd.getOutputArr();
         SockeUtil socket=new SockeUtil(u.getSocketIp(), u.getSocketPort(),pleyerinfo.getPkey2());
         String resut = socket.tcpPost(sendarr);
         socket.setFlag(true);
	}
	//更新界面角色资源信息
	public static void initRoleInfo(PlayerInfo player) {
		U_Main.lblName.setText(player.getPlayerName());
		U_Main.lbllv.setText(player.getPlayerLv());
		U_Main.lblviplv.setText(player.getVipLv());
		U_Main.lblbt.setText(player.getForces());
		U_Main.lbljb.setText(player.getGold());
		U_Main.lblls.setText(player.getFood());
		U_Main.lblmc.setText(player.getCopper());
		U_Main.lblyb.setText(player.getIron());
		
	}
}

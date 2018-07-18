package com.ai.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.gc.ui.U_Main;

public class LogUtils {
	/**
	 * 设置软件底部status
	 * @param content
	 */
	public static void logstatus(final String content) {
		U_Main.lblsoftstatus.setText(content);
	}
	/**
	 * 设置游戏日志
	 * @param content
	 */
	public static void logBox(String content) {
		U_Main.txtlog.append("【"+new SimpleDateFormat("HH:mm:ss").format(new Date())+"】:"+content+"\r\n");
	}
}

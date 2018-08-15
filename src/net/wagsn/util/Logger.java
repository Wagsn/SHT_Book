/**
 * @file Logger.java
 * @package util
 * @project WSC
 * @author Wagsn
 * @date 2018年8月12日
 * @version 1.0.0
 */
package net.wagsn.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * 用来打印日志到项目根目录下的[log]文件下。
 * TODO 可配置
 * @class Logger
 * @package util
 * @project WSC
 * @author Wagsn
 * @date 2018年8月12日
 * @version 1.0.0
 */
public final class Logger {
	
	/**
	 * 最终日志对象，用于外部调用
	 */
	public static final Logger log = new Logger();
	
	@Test
	public void testLog() throws Exception {
		methodStart();
		err("错误消息！");
		methodEnd();
	}
	
	/**
	 * 方法开始
	 */
	public void methodStart() {
		System.out.println(">>Start-> "+parentMethodName());
	}
	
	/**
	 * 方法结束
	 */
	public void methodEnd() {
		System.out.println("<<End-> "+parentMethodName());
	}
	
	/**
	 * 获取当前方法名称
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String currMethodNmae() {
		return Thread.currentThread().getStackTrace()[2].toString();
	}
	
	/**
	 * 返回父调用函数
	 */
	private static String parentMethodName() {
		return Thread.currentThread().getStackTrace()[3].toString();
	}
	
	/**
	 * 时间格式化
	 */
	public static final SimpleDateFormat timeSDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * 文件名采用日期
	 */
	public static final SimpleDateFormat nameSDF= new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 日志文件夹路径
	 */
	public static final String logPath ="./log";

	/**
	 * 将日志打印到[./log/yyyy-MM-dd-HH-mm-ss.log]文件里面，并在控制台打印日志
	 * 打印一般消息
	 * @param content
	 */
	public static void info(String content) {
		Date date = new Date();
		System.out.println("--Log-> Info-> "+content);
		FileUtil.saveTxt(logPath+"/" + nameSDF.format(date)+".log", "["+timeSDF.format(date)+"] INFO "+content+"\r\n", true);  // append
	}
	
	/**
	 * 打印警告消息
	 * @param content
	 */
	public static void warn(String content) {
		Date date = new Date();
		System.out.println("--Log-> Warn-> "+content);
		FileUtil.saveTxt(logPath+"/" + nameSDF.format(date)+".log", "["+timeSDF.format(date)+"] WARN "+content+"\r\n", true);  // append
	}
	
	/**
	 * 打印错误消息
	 * @param content
	 */
	public static void err(String content) {
		Date date = new Date();
		System.err.println("--Log-> Error-> "+content);
		FileUtil.saveTxt(logPath+"/" + nameSDF.format(date)+".log", "["+timeSDF.format(date)+"] ERROR "+content+"\r\n", true);  // append
	}
}

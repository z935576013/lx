package com.sdx.lx.common.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 服务器信息缓存
 * @author yeegor
 *
 */
public class ServerInfo {
	
	private static final String LOCALHOST = "127.0.0.1";
	
	private String ip;
	
	private ServerInfo() {
		//ip = ip(); 
		ip = getRealIp(); 
	}
	
	public static ServerInfo single() {
		return Inner.instance;
	}
	
	static class Inner {
		private static final ServerInfo instance = new ServerInfo();
	}
	
	public String getIp() {
		return ip;
	}
	
	private String getRealIp() {
		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		String netip = null;// 外网IP

		Enumeration<NetworkInterface> netInterfaces = null;
		try {
			netInterfaces = NetworkInterface
					.getNetworkInterfaces();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		if (null == netInterfaces) {
			return LOCALHOST;
		}
		
		InetAddress ip = null;
		boolean finded = false;// 是否找到外网IP
		while (netInterfaces.hasMoreElements() && !finded) {
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> address = ni.getInetAddresses();
			while (address.hasMoreElements()) {
				ip = address.nextElement();
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {// 外网IP
					netip = ip.getHostAddress();
					finded = true;
					break;
				} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
					localip = ip.getHostAddress();
				}
			}
		}

		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}
	
//	private String ip() {
//		InetAddress addr = null;
//		try {
//			addr = InetAddress.getLocalHost();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
//
//		if(addr == null){
//			return "";
//		}
//		
//		String ipAddrStr = "";
//		byte[] ipAddr = addr.getAddress();
//		
//		for (int i = 0; i < ipAddr.length; i++) {
//			if (i > 0) {
//				ipAddrStr += ".";
//			}
//			ipAddrStr += ipAddr[i] & 0xFF;
//		}
//		
//		return ipAddrStr;
//	}

}

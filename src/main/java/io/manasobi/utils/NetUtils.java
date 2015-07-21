package io.manasobi.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

import io.manasobi.exception.NetUtilsException;

/**
 * 해당 시스템의 IP 및 MacAddress에 대한 정보를 조회하는 Utils.
 * 
 * @author manasobi
 * @since 1.0.0
 */
public final class NetUtils {
	
	private NetUtils() { }
	
	/**
	 * 해당 PC의 로컬 IP를 가져온다.
	 * 
	 * @return 해당 PC의 로컬 IP
	 */
	public static String getLocalIp() {
		
		InetAddress addr = null;
		
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			throw new NetUtilsException(e.getMessage());
		}
		
		return addr.getHostAddress();
	}
	
	/**
	 * 해당 PC의 맥어드레스를 가져온다.
	 * 
	 * @return 해당 PC의 맥어드레스
	 */
	public static String getMacAddress() {

		String macAddr = "";
		
		try {
			
			NetworkInterface netInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
		   
			byte[] mac = netInterface.getHardwareAddress();

			for (int i = 0; i < mac.length; i++) {
				macAddr += String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
			}
		} catch (Exception e) {
			throw new NetUtilsException(e.getMessage());
		}
		
		return macAddr;
	}
	
	/**
	 * 해당 PC의 호스트네임을 가져온다.
	 * 
	 * @return 해당 PC의 호스트네임
	 */
	public static String getHostname() {

		String hostname = "";
		
		InetAddress addr = null;
		
		try {			
			addr = InetAddress.getLocalHost();
			hostname = addr.getHostName();			
		} catch (UnknownHostException e) {
			throw new NetUtilsException(e.getMessage());
		}
		
		return hostname;
	}
	
}

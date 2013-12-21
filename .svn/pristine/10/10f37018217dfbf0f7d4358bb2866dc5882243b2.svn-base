package com.mmclub.pcmedia;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPSocket {

	private DatagramSocket clientSocket = null;
	private final int PORT = 12345;
	private InetAddress serverAddr;
	
	public UDPSocket(String ip) throws SocketException {
		try {
			serverAddr = InetAddress.getByName(ip);
			clientSocket = new DatagramSocket(PORT);
			// clientSocket = new DatagramSocket(PORT, serverAddr);
			// clientSocket.setSoTimeout(3000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	// ½«flaot×ª»»Îªbyte[]
	private byte[] floatToByte(float f) {
		int i = Float.floatToIntBits(f);
		byte[] res = new byte[4];
		res[0] = (byte)((i >> 24) & 0xFF);
		res[1] = (byte)((i >> 16) & 0xFF);
		res[2] = (byte)((i >> 8) & 0xFF); 
		res[3] = (byte)( i & 0xFF);
		return res;
	}
	
	public boolean SendCoord(float offsetX, float offsetY) {
		byte[] byteX = floatToByte(offsetX);
		byte[] byteY = floatToByte(offsetY);
		byte[] b = new byte[8];
		for (int i=0; i<4; i++) b[i] = byteX[i];
		for (int i=4; i<8; i++) b[i] = byteY[i-4];
		return Send(b);
	}
	
	public boolean SendByte(byte b) {
		byte[] data = new byte[]{b};
		return Send(data);
	}
	
	public boolean SendString(String msg) {
		byte[] data = msg.getBytes();
		return Send(data);
	}
	
	private boolean Send(byte[] data) {
		DatagramPacket dataPackage = new DatagramPacket(data , data.length, serverAddr, PORT);
		try {
			clientSocket.send(dataPackage);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
		
	public void closeSocket(){
		clientSocket.close();
	}
}

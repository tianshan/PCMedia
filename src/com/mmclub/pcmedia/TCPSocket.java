package com.mmclub.pcmedia;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.util.Log;

public class TCPSocket {
	private static Socket clientsocket;
	private static int PORT = 12345;
	private PrintWriter out;
	
	public TCPSocket(String str) throws IOException{
		
		try{
			InetAddress serverAddr = InetAddress.getByName(str);
			clientsocket = new Socket(serverAddr, PORT);
			out = new PrintWriter( 
		    		new BufferedWriter( 
		    				new OutputStreamWriter(clientsocket.getOutputStream())),true);
		}catch(UnknownHostException e){
			Log.i("socket", e.toString());
		}
	}
	
	public boolean Send(String message){
		try {
		    out.println(message);
		    out.flush();
			// OutputStream out = clientsocket.getOutputStream();
			// out.write(message.getBytes());
		} catch(Exception e) {
			Log.i("tag", "send");
		}
		return true;
	}
	
	public void closeSocket(){
		try{
			clientsocket.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}

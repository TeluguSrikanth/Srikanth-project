package com.srikanth.javachatapp;

import java.io.IOException;
import java.net.Socket;

public class ChatClient {

	public static void main(String[] args) throws IOException {
	      Socket socket = new Socket("localhost", 5000); // Connect to server on port 5000
	      System.out.println("Connected to server.");
	      // Additional code to handle communication will be added later
	  }
	
	
	
	
	
}

package com.srikanth.javachatapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	public static void main(String[] args) throws IOException {
	      ServerSocket serverSocket = new ServerSocket(2000); // Port number 2000
	      System.out.println("Server started. Waiting for clients...");
	      Socket clientSocket = serverSocket.accept();
	      System.out.println("Client connected.");
	      // Additional code to handle communication will be added later
	  }
	
}

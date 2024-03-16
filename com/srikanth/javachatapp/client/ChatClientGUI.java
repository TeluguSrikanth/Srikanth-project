package com.srikanth.javachatapp.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ChatClientGUI extends JFrame{

	private JTextArea messageArea;
	  private JTextField textField;
	  private JButton exitButton;
	  private ChatClient client;
	  private ChatClient2 client2;

	  public ChatClientGUI() {
	      super("Chat Application");
	      setSize(400, 500);
	      setDefaultCloseOperation(EXIT_ON_CLOSE);
	      Color backgroundColor = new Color(240, 240, 240);
	      Color buttonColor = new Color(75, 75, 75);
	      Color textColor = new Color(50, 50, 50);
	      Font textFont = new Font("Arial", Font.PLAIN, 14);
	      Font buttonFont = new Font("Arial", Font.BOLD, 12);
	      
	      messageArea = new JTextArea();
	      messageArea.setEditable(false);
	      messageArea.setBackground(backgroundColor);
	      messageArea.setForeground(textColor);
	      messageArea.setFont(textFont);
	      JScrollPane scrollPane = new JScrollPane(messageArea);
	      add(scrollPane, BorderLayout.CENTER);

	      String name = JOptionPane.showInputDialog(this, "Enter your name:", "Name Entry", JOptionPane.PLAIN_MESSAGE);
	      this.setTitle("Chat Application - " + name);
	      textField = new JTextField();
	      textField.setFont(textFont);
	      textField.setForeground(textColor);
	      textField.setBackground(backgroundColor);
	      textField.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	              String message = "[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + name + ": "
	                      + textField.getText();
	              client.sendMessage(message);
	              client2.sendMessage(message);
	              textField.setText("");
	              
	          }
	      });
	      exitButton = new JButton("Exit");
	      exitButton.setFont(buttonFont);
	      exitButton.setBackground(buttonColor);
	      exitButton.setForeground(Color.WHITE);
	      exitButton.addActionListener(e -> {
	          String departureMessage = name + " has left the chat.";
	          client.sendMessage(departureMessage);
	          client2.sendMessage(departureMessage);
	          try {
	              Thread.sleep(1000);
	          } catch (InterruptedException ie) {
	              Thread.currentThread().interrupt();
	          }
	          System.exit(0);
	      });
	      
	      
	      JPanel bottomPanel = new JPanel(new BorderLayout());
	      bottomPanel.setBackground(backgroundColor);
	      bottomPanel.add(textField, BorderLayout.CENTER);
	      bottomPanel.add(exitButton, BorderLayout.EAST);
	      add(bottomPanel, BorderLayout.SOUTH);
	      try {
	          this.client = new ChatClient("127.0.0.1", 5000, this::onMessageReceived);
	          client.startClient();
	          this.client2 = new ChatClient2("127.0.0.1", 5000, this::onMessageReceived);
	          client2.startClient();
	      } catch (IOException e) {
	          e.printStackTrace();
	          JOptionPane.showMessageDialog(this, "Error connecting to the server", "Connection error",
	                  JOptionPane.ERROR_MESSAGE);
	          System.exit(1);
	      }
	  }
	  
	  private void onMessageReceived(String message) {
	      SwingUtilities.invokeLater(() -> messageArea.append(message + "\n"));
	  }

	  public static void main(String[] args) {
	      SwingUtilities.invokeLater(() -> {
	          new ChatClientGUI().setVisible(true);
	      });
	  }
	              
	              
	
}

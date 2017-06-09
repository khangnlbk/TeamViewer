package server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatServer extends JFrame implements Runnable{
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private ServerSocket listener;
	private JTextField textField;
	private JTextArea textArea;

	public ChatServer(int chat) {
		ServerSocket listener = null;
		try {
			listener = new ServerSocket(chat);

			socket = listener.accept();
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Chat");
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		textField = new JTextField();
		panel.add(textField, BorderLayout.NORTH);
		textField.setColumns(30);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				out.println("Server : " + textField.getText());
				textArea.append("Server : " + textField.getText() + "\n");
				textField.setText("");
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		setContentPane(panel);
		new Thread(this).start();
		setSize(400, 300);
		setVisible(true);
	}

	public void updateMessage() {
		String input;
		try {
			input = in.readLine();
			textArea.append(input + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true)
			updateMessage();
	}
}

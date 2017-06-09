package client;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class RecordAndChat extends JFrame {
	LoadImage loader = new LoadImage();
	private Socket screensocket;
	private int screen;
	private JTextField textField;
	private String ip;
	private JLabel lbl;
	private PrintWriter out;
	private BufferedReader in;
	private Socket socket;
	private JTextArea textArea;
	
	public RecordAndChat(String ip, int screen, int chat) {
		this.screen = screen;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("Record And Chat");
		this.ip = ip;
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));

		textField = new JTextField();
		panel.add(textField, BorderLayout.NORTH);
		textField.setColumns(30);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				out.println("Client : "+textField.getText());
				textArea.append("Client : "+textField.getText()+"\n");
                textField.setText("");
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		setSize(1000,402);
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		BufferedImage load = null;
		load = loader.loadImage("LoadingScreenResize.png");
		ImageIcon loadScreen = new ImageIcon(load);
		lbl = new JLabel();
		lbl.setIcon(loadScreen);
		lbl.setHorizontalAlignment(SwingConstants.CENTER);

		try {
			socket = new Socket(ip, chat);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Chat c = new Chat();
		c.start();
		ScreenThread st = new ScreenThread();
		st.start();
		panel_1.add(lbl);
		setResizable(true);
	}

	class Chat extends Thread{
		@Override
		public void run() {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			while(true){
				updateMessage();
			}
		}
	}
	public void updateMessage(){
		while(true){
			String input = null;
			try {
				input = in.readLine();
				System.out.println(input);
				textArea.append(input+"\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public ImageIcon getIcon(JLabel label, BufferedImage image) {
		int x = label.getSize().width;
		int y = label.getSize().height;
		int ix = image.getWidth();
		int iy = image.getHeight();
		int dx, dy;
		if (x / y > ix / iy) {
			dy = y;
			dx = dy * ix / iy;
		} else {
			dx = x;
			dy = dx * iy / ix;
		}
		ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(dx, dy, image.SCALE_SMOOTH));
		return imageIcon;
	}
	
	class ScreenThread extends Thread {
		public void run() {
			while (true) {
				try {
					screensocket = new Socket(ip, screen);
					InputStream in = screensocket.getInputStream();
					BufferedImage img = ImageIO.read(in);
					lbl.setText("");
					lbl.setIcon(getIcon(lbl, img));
				} catch (Exception e) {
					System.out.println(e);
				}
			}

		}
	}

}

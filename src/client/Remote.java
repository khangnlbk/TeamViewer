package client;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Remote extends JFrame {
	private Socket screensocket;
	private String ip;
	private JLabel l;
	private int screen;
	public Remote(String ip, int screen, int mousemove, int mouseclick, int keyboard) {
		this.screen = screen;
		LoadImage loader = new LoadImage();
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.ip = ip;
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				try {
					Socket client = new Socket(ip, mousemove);
					OutputStream out = client.getOutputStream();
					out.write((x + "#" + y).getBytes());
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					Socket client = new Socket(ip, mouseclick);
					OutputStream out = client.getOutputStream();
					int b = e.getButton();
					int cc = e.getClickCount();
					if (cc == 1)
						out.write((b + "").getBytes());
					else if (cc == 2)
						out.write(("4").getBytes());
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				try {
					Socket client = new Socket(ip, keyboard);
					OutputStream out = client.getOutputStream();
					int keycode = e.getKeyCode();
					out.write(("keypressed#" + keycode).getBytes());
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}

			public void keyReleased(KeyEvent e) {
				try {
					Socket client = new Socket(ip, keyboard);
					OutputStream out = client.getOutputStream();
					int keycode = e.getKeyCode();
					out.write(("keyreleased#" + keycode).getBytes());
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int sw = (int) d.getWidth();
		int sh = (int) d.getHeight();
		setSize(sw, sh);
		setVisible(true);
		ScreenThread st = new ScreenThread();
		
		BufferedImage load = loader.loadImage("LoadingScreen.png");
		ImageIcon loadScreen = new ImageIcon(load);
		l = new JLabel();
		l.setIcon(loadScreen);
		l.setHorizontalAlignment(JLabel.CENTER);
		add(l);
		st.start();
	}

	class ScreenThread extends Thread {
		public void run() {
			while (true) {
				try {
					screensocket = new Socket(ip, screen);
					InputStream in = screensocket.getInputStream();
					BufferedImage img = ImageIO.read(in);
					l.setText("");
					ImageIcon icon = new ImageIcon(img);
					l.setIcon(icon);
				} catch (Exception e) {
					System.out.println(e);
				}
			}

		}
	}
}

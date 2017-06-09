package server;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;

public class ScreenThread implements Runnable {
	private ServerSocket server;

	public ScreenThread(int screen) {
		try {
			server = new ServerSocket(screen);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				Socket client = server.accept();
				OutputStream out = client.getOutputStream();
				BufferedImage img = getScreenShot();
				ImageIO.write(img, "png", out);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public BufferedImage getScreenShot() {
		BufferedImage img = null;
		try {
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			Robot rt = new Robot();
			img = rt.createScreenCapture(new Rectangle(d));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return img;
	}
}

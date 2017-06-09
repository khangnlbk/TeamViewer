package client;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadImage {
	public BufferedImage loadImage(String fileName) {
		fileName = "img/" + fileName;
		BufferedImage im = null;
		try {
			im = ImageIO.read(getClass().getClassLoader().getResource(fileName));
		} catch (IOException e) {
			System.out.println("Error loading " + fileName);
		}
		return im;
	}
}

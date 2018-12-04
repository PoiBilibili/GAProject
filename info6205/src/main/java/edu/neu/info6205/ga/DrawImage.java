package edu.neu.info6205.ga;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class DrawImage {

	public DrawImage() {
		// TODO Auto-generated constructor stub
	}

	public static void draw(int[] rgbs, int width, int height, String filename) throws IOException {
		File file = new File("images/bmptest.bmp");
		BufferedImage bufImage = null;
		bufImage = ImageIO.read(file);
		bufImage.setRGB(0, 0, width, height, rgbs, 0, width);
		ImageIO.write(bufImage, "JPEG", new File(filename));
	}

}

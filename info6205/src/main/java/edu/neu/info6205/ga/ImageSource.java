package edu.neu.info6205.ga;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageSource {
	private final int BLACK = -16777216;
	private final int WHITE = -1;

	private BufferedImage bufImage;
	private static int width;
    private static int height;
	private int[][] rgbarr;
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public ImageSource(String filename) {
		bufImage = getImage(filename);
		width = bufImage.getWidth();
		height = bufImage.getHeight();
		rgbarr = new int[width][height];
		for(int i = 0; i< width; i++)
			for(int j = 0; j < height; j++)
				rgbarr[i][j] = bufImage.getRGB(i, j);
		toBlackWhite();
	}

	public BufferedImage getImage(String filename) {
		File file = new File(filename);
		BufferedImage bufImage = null;
		try {
			bufImage = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bufImage;
	}

	public void printImage(String filename) throws IOException {
        int[] rgbs = bufImage.getRGB(0, 0, width, height, null, 0, width);
        bufImage.setRGB(0, 0, width, height, rgbs, 0, width);
        // 把修改过的 bufImage 保存到本地
        ImageIO.write(bufImage, "JPEG", new File(filename));
	}

	public int[] getRGBArray(){
		return bufImage.getRGB(0, 0, width, height, null, 0, width);
	}

	public void setRGB(int x, int y, int rgb) {
		bufImage.setRGB(x, y, rgb);
		rgbarr[x][y] = rgb;
	}
	public void setRGB(int[] arr) {
		for(int i = 0; i<arr.length; i++)
			bufImage.setRGB(i%width, i/width, arr[i]);
	}
	public void toBlackWhite() {
		for(int i = 0; i< width; i++)
			for(int j = 0; j < height; j++)
				if(Math.abs(rgbarr[i][j] - BLACK) >= Math.abs(rgbarr[i][j] - WHITE))
					setRGB(i,j, WHITE);
				else setRGB(i,j, BLACK);
	}
	public static void main(String[] args) throws IOException {
		ImageSource img = new ImageSource("./images/bmptest.bmp");
		img.printImage("./images///bmptest.jpg");
	}
}

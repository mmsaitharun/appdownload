package com.murphy.imageprocessing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

public class ImageProcessing {

	public ImageDto concatImages(Images images) {
		System.err.println("Image Processing started1");
		ImageDto dto = new ImageDto();
		int w = 0;
		int h = 0;
		int type = 0;
		System.err.println("test1");
		List<ImageDto> imageList = images.getImages();
		System.err.println("test2");
		List<BufferedImage> bufferedImages = new ArrayList<BufferedImage>();
		System.err.println("test3");
		for (ImageDto imageDto : imageList) {
			System.err.println("base64 :" + imageDto.getBase64());
			BufferedImage image = createImageFromBase64(imageDto.getBase64());
			if (image.getWidth() > w) {
				w = image.getWidth();
			}
			h = h + image.getHeight();
			type = image.getType();
			bufferedImages.add(image);
		}
		System.err.println("test4 :");
		BufferedImage concatenatedImage = new BufferedImage(w, h, type);
		Graphics g = concatenatedImage.getGraphics();
		g.drawImage(bufferedImages.get(0), 0, 0, null);
		g.drawImage(bufferedImages.get(1), 0, bufferedImages.get(0).getHeight(), null);
		try {
			dto.setBase64(createBase64FromImage(concatenatedImage, "JPEG"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dto.setFileType("JPEG");
		return dto;
	}

	private BufferedImage createImageFromBase64(String base64) {

		byte[] imageData = Base64.decodeBase64(base64.getBytes());
		ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
		try {
			return ImageIO.read(bais);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String createBase64FromImage(BufferedImage combined, String type) throws UnsupportedEncodingException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(combined, type, baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] bytes = baos.toByteArray();
		String resultbase64 = new String(Base64.encodeBase64(bytes), "UTF-8");
		return resultbase64;
	}

	// private BufferedImage resize(BufferedImage img, int height, int width) {
	// Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	// BufferedImage resized = new BufferedImage(width, height,
	// BufferedImage.TYPE_INT_ARGB);
	// Graphics2D g2d = resized.createGraphics();
	// g2d.drawImage(tmp, 0, 0, null);
	// g2d.dispose();
	// return resized;
	// }
}

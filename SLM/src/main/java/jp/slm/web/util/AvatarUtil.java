package jp.slm.web.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import jp.slm.business.bean.Avatar;

import org.springframework.web.multipart.MultipartFile;

public class AvatarUtil {
	
	public static void main(String[] args) throws Exception {
		String dir = "D:\\Documents and Settings\\rDurocher\\Bureau\\slm\\img\\";
		byte[] data = getScaledImage(new File(dir + "slm-logo.png"));
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(dir + "test-avatar-100.jpg"));
			fos.write(data);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}
	
	public static byte[] getScaledImage(File file) {
		BufferedImage image = null;
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			image = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) try {
				is.close();
			} catch (IOException ignore) {}
		}
		if (image != null) {
			int height = image.getHeight();
			int width = image.getWidth();
			if (height != width || width > Avatar.SIZE) {
				image = scaleImage(image, Avatar.SIZE);
			}
		}
		return getImageData(image);
	}
	
	public static byte[] getScaledImage(MultipartFile file) {
		BufferedImage image = null;
		InputStream is = null;
		try {
			is = file.getInputStream();
			image = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) try {
				is.close();
			} catch (IOException ignore) {}
		}
		if (image != null) {
			int height = image.getHeight();
			int width = image.getWidth();
			if (height != width || width > Avatar.SIZE) {
				image = scaleImage(image, Avatar.SIZE);
			}
		}
		return getImageData(image);
	}
	
	private static byte[] getImageData(BufferedImage image) {
		byte[] imageData = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", baos);
			baos.flush();
			imageData = baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException ignore) {}
			}
		}
		return imageData;
	}
	
	private static BufferedImage scaleImage(BufferedImage originalImage, int size) {
		BufferedImage scaledBI = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = scaledBI.createGraphics();
		g.drawImage(originalImage, 0, 0, size, size, null);
		g.dispose();
		return scaledBI;
	}
}

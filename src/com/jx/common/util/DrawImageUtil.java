package com.jx.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jx.common.config.Logger;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class DrawImageUtil {
	protected static Logger logger = Logger.getLogger(AppUtil.class);

	public static void main(String[] args) {
		String filePath = PathUtil.getProjectPath() + "static/file/image/qrcode";
		System.out.println(filePath);
		pressImage(filePath+"/tx.png", filePath+"/cooler.jpg", filePath+"/cooler1.jpg", 561, 1946, 160, 160);
		pressImage(filePath+"/11.jpg", filePath+"/cooler1.jpg", filePath+"/cooler1.jpg", 233, 1520, 211, 211);
	}
	
	/**
	 * 生成后台验证码
	 * @param output
	 * @return
	 */
	public static String drawImg(ByteArrayOutputStream output) {
		String verificationCode = "";
		for (int i = 0; i < 4; i++) {
			verificationCode += RandomUtil.randomChar();
		}
		int width = 70;
		int height = 25;
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		Font font = new Font("Times New Roman", Font.PLAIN, 20);
		Graphics2D g = bi.createGraphics();
		g.setFont(font);
		Color color = new Color(66, 2, 82);
		g.setColor(color);
		g.setBackground(new Color(226, 226, 240));
		g.clearRect(0, 0, width, height);
		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D bounds = font.getStringBounds(verificationCode, context);
		double x = (width - bounds.getWidth()) / 2;
		double y = (height - bounds.getHeight()) / 2;
		double ascent = bounds.getY();
		double baseY = y - ascent;
		g.drawString(verificationCode, (int) x, (int) baseY);
		g.dispose();
		try {
			ImageIO.write(bi, "jpg", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return verificationCode;
	}
	
	/**
     * 打印文字水印图片
     * 
     * @param pressText --文字
     * @param targetImgSrc -- 目标图片
     * @param fontName -- 字体名
     * @param fontStyle -- 字体样式
     * @param color -- 字体颜色
     * @param fontSize -- 字体大小
     * @param x -- 偏移量
     * @param y
     */
	public static void pressText(String pressText, String targetImgSrc, Color color,
    		String fontName, int fontStyle, int fontSize, int x, int y) {
        try {
            File file = new File(targetImgSrc);
            Image src = ImageIO.read(file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);
            g.setColor(color);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.drawString(pressText, x, y);
            g.dispose();
            FileOutputStream out = new FileOutputStream(targetImgSrc);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
	
	/**
     * 图片合成
     * 
     * @param pressImgSrc --水印文件
     * @param targetImgSrc --目标文件
     * @param x	--x坐标
     * @param y --y坐标
     */
    public final static void pressImage(String pressImgSrc, String targetImgSrc, String outImgSrc, 
            int x, int y, int pressW, int pressH) {
        try {
            //目标文件
            File file = new File(targetImgSrc);
            Image src = ImageIO.read(file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);

            //水印文件
            File filebiao = new File(pressImgSrc);
            Image src_biao = ImageIO.read(filebiao);
            g.drawImage(src_biao, x, y, pressW, pressW, null);
            //水印文件结束
            
            g.dispose();
            FileOutputStream out = new FileOutputStream(outImgSrc);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
}

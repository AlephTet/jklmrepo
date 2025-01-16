

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class ImageManager 
{
	public static final int BG = 0;
	public static final int EXP = 1;
	public static final int BOMB = 2;
	public static final int SPARK = 3;
	public static final int NEXUS_BG = 4;
	public static final int NEXUS_PORTRAIT = 5;
	public static final int EXPLOSION = 6;
	private static BufferedImage[] sprites;
	private static BufferedImage[] bombs;
	private static BufferedImage[] spark;
	public static void load() throws IOException {
		sprites = new BufferedImage[7];
		bombs = new BufferedImage[20];
		sprites[BG] = ImageIO.read(new File("assets/gradient.png"));
		sprites[EXP] = ImageIO.read(new File("assets/bb.png"));
		//sprites[BOMB] = resize(sprites[BOMB],sprites[BOMB].getWidth()*2,sprites[BOMB].getHeight()*2);
		sprites[BOMB] = ImageIO.read(new File("assets/bomb.png"));
		sprites[BOMB] = resize(sprites[BOMB],(int)(sprites[BOMB].getWidth()*1.25),(int)(sprites[BOMB].getHeight()*1.25));
		for(int i = 0;i<10;i++)
		{
			bombs[i] = resize(sprites[BOMB],(int)(sprites[BOMB].getWidth()*1.25)+i,(int)(sprites[BOMB].getHeight()*1.25)+i);
		}
		for(int i = 19;i>=10;i--)
		{
			bombs[i] = resize(sprites[BOMB],(int)(sprites[BOMB].getWidth()*1.25)+i-10,(int)(sprites[BOMB].getHeight()*1.25)+i-10);
		}
		sprites[SPARK] = ImageIO.read(new File("assets/spark.png"));
		for(int i = 0;i<=100;i++)
		{
			
		}
		sprites[NEXUS_BG] = ImageIO.read(new File("assets/nexus_background.png"));
		sprites[NEXUS_PORTRAIT] = ImageIO.read(new File("assets/nexus_portrait.png"));
		sprites[EXPLOSION] = ImageIO.read(new File("assets/explosion.png"));
	}
	public static BufferedImage getSprite(int id) 
	{
		return sprites[id];
	}
	public static BufferedImage getBomb(int t) 
	{
		return bombs[t];
	}
	public static int getH()
	{
		return sprites[EXP].getHeight();
	}
	public static int getW()
	{
		return sprites[EXP].getWidth();
	}
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    
	    return dimg;
	}  
}

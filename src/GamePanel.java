

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel 
{
	private static final int TIMER_RATE = 20;
	private static final Random random = new Random();
	private Timer timer;
	private long lastTime;
	private ArrayList<String> syll;
	private ArrayList<String>  words;
	public String userWord;
	public String currentSyll = "";
	private static Image bomb;
	private int count = 0;
	private static int pc = 0;
	Rectangle rec = null;
	public void rs()
	{
		currentSyll = syll.get(random.nextInt(syll.size()));
	}
	public GamePanel(MainMenu mainMenu) 
	{
		super();
		Font customFont = null;
		try {
			words = (ArrayList<String>) Files.readAllLines(Paths.get("assets/wordlist.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			syll = (ArrayList<String>) Files.readAllLines(Paths.get("syllables500.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bomb = ImageManager.getSprite(ImageManager.BOMB);
		try {
			GraphicsEnvironment ge = 
					GraphicsEnvironment.getLocalGraphicsEnvironment();
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("A.ttf"));
			ge.registerFont(customFont);
		} catch (Exception e) 
		{
		}
		rec = new Rectangle(0,60,mainMenu.getWidth(), mainMenu.getHeight()-170);
		setForeground(Color.WHITE);
		setFont(new Font("Verdana", Font.BOLD, 50));
		EffectManager.resetEffectManager();
		rs();
		timer = new Timer(TIMER_RATE, new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				long delta = System.currentTimeMillis() - lastTime;
				lastTime = System.currentTimeMillis();
				tick(delta);
				count++;
				if(count >=390)
					{
						count=0;
						rs();
						EffectManager.getEffectManager().addEffect(new Explosion(getWidth()/2+22, getHeight()/2-28));
					}
				repaint();
			}
		});
		lastTime = System.currentTimeMillis();
		play();
	}
	public boolean isRunning() 
	{
		return timer.isRunning();
	}
	public void pause() 
	{
		timer.stop();
	}
	public void play() 
	{
		timer.start();
		lastTime = System.currentTimeMillis();
	}
	public void check()
	{
		if(userWord.contains(currentSyll.toLowerCase()) && words.contains(userWord.toUpperCase()))
		{
			pause();
			lastTime = 0;
			count = 0;
			words.remove(userWord.toUpperCase());
			timer.start();
			rs();
			repaint();
		}
		else if(userWord.equals("/suicide"))
		{
			pause();
			lastTime = 0;
			count = 0;
			timer.start();
			rs();
			repaint();
		}
		else if(userWord.equals("a"))
		{
			pause();
			count = 389;
			timer.start();
			rs();
			repaint();
		}
	}
	public void tick(long delta) 
	{
		EffectManager.getEffectManager().tick(delta);
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(ImageManager.getSprite(ImageManager.BG), 0, 0, getWidth(),getHeight(),null);
		drawCenteredImage(g,ImageManager.getBomb(pc),rec);
		pc++;
		if(pc==20)
		{
			pc=0;
		}
		drawCenteredSpark(g,ImageManager.getSprite(ImageManager.SPARK), ImageManager.getBomb(pc), rec);
		drawCenteredString(g,currentSyll,rec,new Font("Verdana", Font.BOLD, 40));
		//g.drawString(currentSyll, getWidth()/2-50, getHeight()/2-50);
		EffectManager.getEffectManager().paintEffects(g2);
	}
	public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) 
	{
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(font);
	    g.drawString(text, x, y-90);
	}
	public void drawCenteredSpark(Graphics g, Image i, Image u, Rectangle rect) 
	{
	    int x = rect.x + (rect.width + u.getWidth(this)) / 2;
	    int y = rect.y + ((rect.height - u.getHeight(this)) / 2) ;
	    g.drawImage(i, x-65, y-125,this);
	}
	public void drawCenteredImage(Graphics g, Image i, Rectangle rect) 
	{
	    int x = rect.x + (rect.width - i.getWidth(this)) / 2;
	    int y = rect.y + ((rect.height - i.getHeight(this)) / 2) ;
	    g.drawImage(i, x, y-90,this);
	}
}

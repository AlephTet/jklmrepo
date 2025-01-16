

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Explosion extends JPanel implements EffectManager.Effect
{
	private static final int EXPLOSION_TIME = 650;
	private static final int EXPLOSION_XFRAMES = 4;
	private static final int EXPLOSION_YFRAMES = 2;
	private static final int HEIGHT = (int)(ImageManager.getH()/EXPLOSION_YFRAMES);
	private static final int WIDTH = (int) (ImageManager.getW()/EXPLOSION_XFRAMES);
	private long lifespan = 0;
	private int x, y;
	private int count = 0;
	private boolean isDone = false;
	private BufferedImage visual;
	public Explosion(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	@Override
	public void tick(long delta) 
	{
		lifespan += delta;
		BufferedImage spriteSheet = ImageManager.getSprite(ImageManager.EXP);
		
		int frame = (int) lifespan / (EXPLOSION_TIME / (EXPLOSION_XFRAMES * EXPLOSION_YFRAMES));
		
		int frameX = frame % EXPLOSION_XFRAMES;
		int frameY = frame / EXPLOSION_XFRAMES;
				if (frameY >= EXPLOSION_YFRAMES)
		{
			isDone = true;
			return;
		}
		visual = spriteSheet.getSubimage(frameX * WIDTH, frameY * HEIGHT+50, WIDTH-70, HEIGHT-50);
	}
	@Override
	public boolean done() {
		return isDone;
	}
	@Override
	public void paint(Graphics2D g) 
	{
		g.drawImage(visual, x - WIDTH/2, y - HEIGHT/2, WIDTH, HEIGHT, null);	
	}
	
}

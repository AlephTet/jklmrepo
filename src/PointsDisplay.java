import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class PointsDisplay implements EffectManager.Effect 
{
	private static final double FLOAT_SPEED = -0.04;
	private static final int TOTAL_LIFE = 1000;
	private String text;
	private Color color;
	private double x;
	private double y;
	private long lifespan = 0;
	private boolean done = false;
	public PointsDisplay(double x, double y, String text, Color color) {
		this.text = text;
		this.color = color;
		
		this.x = x;
		this.y = y;
	}
	@Override
	public void tick(long delta) {
		y += FLOAT_SPEED * delta;
		lifespan += delta;
		
		if (lifespan > TOTAL_LIFE)
			done = true;
	}
	@Override
	public void paint(Graphics2D g) 
	{
		g.setColor(color);
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
		FontMetrics fontMetrics = g.getFontMetrics();
		int strWidth = fontMetrics.stringWidth(text);
		g.drawString(text, (int) x - strWidth / 2, (int) y);
	}
	@Override
	public boolean done() {
		return done;
	}
}

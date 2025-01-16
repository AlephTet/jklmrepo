import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Component;
import java.awt.Graphics;
public class RoundBorder extends LineBorder
{
	int arcWidth=35,arcHeight=35;
	Color fillColor=new Color(19,15,15,255);
	public RoundBorder() 
	{
		super(Color.red);
	}
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
	{
		g.setColor(fillColor);
		g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	}
}
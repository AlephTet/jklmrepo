import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Panel extends JPanel implements MouseListener, MouseMotionListener
{
	private BufferedImage background = null;
	private BufferedImage bomb = null;
	private File file = null;
	ArrayList<String> words = null;
	ArrayList<String> syllable = null;
	private static boolean isPlaying = true;
	protected static int mouseX;
	protected static int mouseY;

	public Panel()
	{
		addMouseListener( this );
		addMouseMotionListener( this );
		mouseX = 0;
		mouseY = 0;
		try {
			background = ImageIO.read(new File("C:\\Users\\1887700\\Downloads\\gradient.jpeg"));
			file = new File("C:\\Users\\1887700\\Downloads\\wordlist.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			words = (ArrayList<String>) Files.readAllLines(Paths.get("C:\\Users\\1887700\\Downloads\\wordlist2.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			syllable = (ArrayList<String>) Files.readAllLines(Paths.get("syllables500.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sound.initialize();
	}
	public void showBoard(Graphics g)	
	{
		
	}
	public void processUserInput(int k)
	{
		repaint();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); 
		g.drawImage(background, 0, 0, null);
		showBoard(g);
	}
	private class Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			repaint();
		}
	}

	public void mouseClicked( MouseEvent e )
	{
		int button = e.getButton();
		repaint();
	}

	public void mousePressed( MouseEvent e )
	{}

	public void mouseReleased( MouseEvent e )
	{}

	public void mouseEntered( MouseEvent e )
	{}

	public void mouseMoved( MouseEvent e)
	{
		repaint();
	}

	public void mouseDragged( MouseEvent e)
	{}

	public void mouseExited( MouseEvent e )
	{}

}

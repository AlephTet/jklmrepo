
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
public class JKLMDriver 
{
	static JFrame screen;
	public static void main(String[]args)
	{
		new JKLMDriver();
	}
	public JKLMDriver()
	{
		try {
			ImageManager.load();
		} catch (IOException e) 
		{
			System.exit(0);
		}
		MainMenu mainMenu = new MainMenu();
		mainMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainMenu.setVisible(true);
		JPanel backgroundPanel = new JPanel() 
		{
            @Override
            protected void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon(ImageManager.getSprite(ImageManager.BG)).getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
	}
	public static class listen implements KeyListener 
	{ 
		public void keyTyped(KeyEvent e)
		{ }

		public void keyPressed(KeyEvent e)
		{ }

		public void keyReleased(KeyEvent e)
		{
		}
	}
}

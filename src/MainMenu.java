

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JFrame 
{
	private static final Color bgColor = new Color(221,220,226,255);
	public MainMenu() 
	{
		super("JKLM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		getContentPane().setBackground(bgColor);
		JButton playE = new JButton("Join Game");
		playE.addActionListener(new PlayGameListener());
		playE.setBackground(Color.GREEN);
		playE.setBounds(500,500,100,50);
		add(playE);
		
		JButton quit = new JButton("Quit Game");
		quit.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
	}
	private class PlayGameListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			setVisible(false);
			GameFrame g = new GameFrame(MainMenu.this);
			g.setVisible(true);
			g.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
	}
}



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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private static final String SERVER_IP = "100.36.119.115";
	private static final int SERVER_PORT = 443;
	public MainMenu() 
	{
		super("JKLM");
		String response = "";
		try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
			String command = "fetch_rooms";
			out.println(command);
			response = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String rooms = response;
		Map<String, String> map = new HashMap<>();
		ArrayList<Integer> list = new ArrayList<>();
		rooms = rooms.substring(16);
		while(rooms.length()>15)
		{
			//System.out.println(rooms.substring(rooms.indexOf("gameId")+9,rooms.indexOf("gameId")+13));
			if(rooms.substring(rooms.indexOf("gameId")+9,rooms.indexOf("gameId")+13).equals("bomb"))
			{
				System.out.println(rooms.substring(rooms.indexOf("name")+7,rooms.indexOf(",",rooms.indexOf("name")+7)-1)+" "+rooms.substring(rooms.indexOf("roomCode")+11,rooms.indexOf("roomCode")+15));
				map.put(rooms.substring(rooms.indexOf("name")+7,rooms.indexOf(",",rooms.indexOf("name")+7)-1), rooms.substring(rooms.indexOf("roomCode")+11,rooms.indexOf("roomCode")+15));
				list.add(Integer.parseInt(rooms.substring(rooms.indexOf("playerCount")+13,rooms.indexOf(",",rooms.indexOf("playerCount")+13))));
				System.out.println(rooms.substring(rooms.indexOf("playerCount")+13,rooms.indexOf(",",rooms.indexOf("playerCount")+13)));
			}
			rooms = rooms.substring(rooms.indexOf("}")+1);
		}
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

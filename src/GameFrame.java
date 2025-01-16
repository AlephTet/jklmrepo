import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GameFrame extends JFrame implements ActionListener
{
	private GamePanel gamePanel;
	private MainMenu mainMenu;
	private JTextField textField; 

	public GameFrame(MainMenu mainMenu) 
	{
		super("JKLM");
		Font font1 = new Font(Font.SERIF, Font.BOLD, 28);
		this.mainMenu = mainMenu;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(120, 87, 199));
		headerPanel.setSize(mainMenu.getWidth(),60);
		add(headerPanel);
		JPanel footerPanel = new JPanel();
		footerPanel.setBackground(new Color(46,41,37,255));
		footerPanel.setBounds(0,mainMenu.getHeight()-110,mainMenu.getWidth(),110);
		add(footerPanel);
		textField = new JTextField();
		textField.setBounds(mainMenu.getWidth()/2-(mainMenu.getWidth()/8),mainMenu.getHeight()-103,mainMenu.getWidth()/4,60);
		textField.setBackground(new Color(19,15,15,255));
		textField.setFont(font1);
		textField.setForeground(Color.WHITE);
		textField.addActionListener(this);
		textField.setCaretColor(Color.WHITE);
		textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.addKeyListener(new KeyListener() 
		{

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) 
			{
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && textField.getText().isEmpty()) 
				{
                    e.consume();
                }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
        add(textField);
        gamePanel = new GamePanel(mainMenu);
		gamePanel.setBounds(0,60,mainMenu.getWidth(), mainMenu.getHeight()-170);
		add(gamePanel);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
	    if (e.getSource()==textField)
	    {
	    	gamePanel.userWord = textField.getText();
	    	gamePanel.check();
	    	textField.setText("");
	    }
	}
	@Override
	public void dispose() 
	{
		super.dispose();
		
		gamePanel.pause();
		
		mainMenu.setVisible(true);
	}
}

import java.awt.*;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame { 

private static final int FONT_SIZE = 20;
private static final int NUMBER_OF_REPEATS = 5;
private static final String TEXT = new String("0ￋｾ1ﾤ2ﾳ34米56ﾵ789/*-+/<ｸ>?;:[]~!@#保$%話^&*()+=ab道cdﾦￄefｦghￗijｺﾜklﾩmￕﾨnﾘopﾚqrﾀstｹuv山wﾞxyﾦz");
private static JPanel panel = new JPanel(null);
private static Random random = new Random();
private static JLabel label[] = new JLabel[NUMBER_OF_REPEATS];

public MainFrame() {        
    this.add(panel);
    panel.setBackground(Color.BLACK);
}

public void scroll() {

    //array to hold x coordinates for the labels
    int[] random_x = new int[NUMBER_OF_REPEATS];

    //create an infinite loop
    while (true) {

        //initialise all the labels to random characters
        for (int i = 0; i < NUMBER_OF_REPEATS; i++) {
            int character_initial = random.nextInt(TEXT.length());
            random_x[i] = random.nextInt(panel.getWidth() / FONT_SIZE) - 1;
            label[i] = new JLabel("" + TEXT.charAt(character_initial));
            panel.add(label[i]);
            label[i].setFont(new Font("monospaced", Font.PLAIN, FONT_SIZE));
            label[i].setForeground(new Color(0, 255, 0));
        }

        // change the text of the labels and their position
        for (int j = 0; j < (panel.getHeight() / FONT_SIZE) * 2; j++) {
            int character = random.nextInt(TEXT.length());

            //move each character
            for (int i = 0; i < NUMBER_OF_REPEATS; i++) {
                label[i].setBounds(random_x[i] * FONT_SIZE, j * (FONT_SIZE / 2), FONT_SIZE, FONT_SIZE);
                label[i].setText("" + TEXT.charAt(character));
                label[i].setForeground(new Color(0, 255 - (j * 5), 0));
                Color colour = label[i].getForeground();
                if (colour.getGreen() <= 80) {
                    panel.remove(label[i]);
                    j = (panel.getHeight() / FONT_SIZE) * 2;
                }
            }
            // pause between each character
            try {
                Thread.sleep(75);
            } catch (Exception e) {
            }
        }
    }
}

public static void main(String[] args) {
    MainFrame frame = new MainFrame();
    frame.setVisible(true);
    frame.setSize(600, 400);
    frame.setResizable(false);
    frame.setMinimumSize(new Dimension(300, 200));
    frame.setLocationRelativeTo(null);
    frame.setTitle("Matrix Code Emulator by Ricco");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    frame.scroll();
}
}
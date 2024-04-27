import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class WordleDriver
{
   public static void main(String[] arg)
   {
      JFrame frame = new JFrame("Wordle");
      frame.setSize(600, 600);
      frame.setLocation(430, 150);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new WordlePanel());
      frame.setVisible(true);
   }
}
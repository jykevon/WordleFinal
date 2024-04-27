import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GreenLetter extends Letter
{
   private char myChar;
   public GreenLetter(char c)
   {
      super(c);
   }
   public void setColor(JButton[][] l, int x, int y)
   {
    l[x][y].setBackground(new Color(107, 170, 99));
   }
}

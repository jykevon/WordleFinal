import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class YellowLetter extends Letter
{
   private char myChar;
   public YellowLetter(char c)
   {
      super(c);
   }
   public void setColor(JButton[][] l, int x, int y)
   {
    l[x][y].setBackground(new Color(212, 180, 52));
   }
}

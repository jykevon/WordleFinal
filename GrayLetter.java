import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GrayLetter extends Letter
{
   private char myChar;
   public GrayLetter(char c)
   {
      super(c);
   }
   public void setColor(JButton[][] l, int x, int y)
   {
    l[x][y].setBackground(Color.gray);
   }
}

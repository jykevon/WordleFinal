import javax.swing.*;
public abstract class Letter extends JPanel
{
   private char myChar; 
   public Letter(char c)
   {
      myChar = c;
   }
   public abstract void setColor(JButton[][] l, int x, int y);
}

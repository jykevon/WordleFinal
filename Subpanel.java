import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.DecimalFormat;
import java.io.*;
public class Subpanel extends JPanel
{
   private int N = 6;
   private JButton[][] words;
   private String[] list;
   private String[] average;
   private int numitems;
   private int count;
   public Subpanel()
   {
      setLayout(new GridLayout(6, 5));
      words = new JButton[N][N];
      for(int r = 0; r < N ; r++)
         for(int c = 0; c < N-1; c++)
         {
            words[r][c] = new JButton();
            words[r][c].setOpaque(true);
            words[r][c].setFont(new Font("Arial", Font.BOLD, 20));
            words[r][c].setForeground(Color.white);
            words[r][c].setHorizontalAlignment(SwingConstants.CENTER);
            words[r][c].setBackground(Color.white);
            add(words[r][c]);
         }
   }
   public void reset()
   {
      for(int r = 0; r < N ; r++)
         for(int c = 0; c < N-1; c++)
         {
            words[r][c].setBackground(Color.white);
            words[r][c].setText("");
         }
   }
   public void display(JTextField tf, int t, int r)
   {
      String s = tf.getText();
      s = s.toUpperCase();
      char c = s.charAt(r);
      String w = ("" + c);
      words[t][r].setText(w);
   }
   public String setWord()
   {
   
      Scanner scanner = null;
      try{
         scanner = new Scanner(new File("wordlist.txt"));
      }
      catch(FileNotFoundException e)
      {
      }
      numitems = scanner.nextInt();
      list = new String[numitems];
      for(int x = 0; x < numitems; x++)
      {
         list[x] = scanner.next();
      }
      scanner.close();
      int index = (int)(Math.random() * numitems + 1);
      return list[index];
   }
   public void check(JTextField tf, String word, String text, int y)
   {
      boolean bad = false;
      for(char c : text.toCharArray())
      {
         if(!Character.isLetter(c)) 
         {
            JOptionPane.showMessageDialog(null, "Word is not valid. Try again!");
            return;
         }
      }
      if((text.length()>5)|| (text.length()<5))
      {
         JOptionPane.showMessageDialog(null, "Word is not valid. Try again!");
      }
      else{
         count = 0;
         for(int x = 0; x < 5; x++)
         {
            char c = word.charAt(x);
            char t = text.charAt(x);
            GreenLetter green = new GreenLetter(c);
            GrayLetter gray = new GrayLetter(c);
            YellowLetter yellow = new YellowLetter(c);
            
            if(!word.contains("" + t))
               gray.setColor(words, y, x);
            else
            {
               if(t == c && (!(text.substring(0, x).contains("" + t)) || word.substring(0, x).contains("" + t)))
               {
                  green.setColor(words, y, x);
               }
               else
                  if(!word.substring(0, x).contains("" + t) && text.substring(0, x).contains("" + t))
                  {
                     gray.setColor(words, y, x);
                  }
                  else
                     yellow.setColor(words, y, x);      
            }
            display(tf, y, x);
         }
      }
   }
   public void save(int x)
   {
      Scanner infile = null;
      try{
        
         infile = new Scanner(new File("average.txt"));
      }
      catch(Exception e)
      {
         JOptionPane.showMessageDialog(null,"The file could not be created.");
      }
      infile.next();
      int games = infile.nextInt();
      games = games + 1;
      infile.next();
      double average = infile.nextDouble();
      infile.close();
      PrintWriter outfile = null;
      try{
        
         outfile = new PrintWriter(new FileWriter("average.txt"));
      
      }
      catch(Exception e)
      {
         JOptionPane.showMessageDialog(null,"The file could not be created.");
      }
      DecimalFormat d = new DecimalFormat("0.00");
      average = (average + (x+1))/ games;
      outfile.println("Games: " + games);
      outfile.println("Average: " + d.format(average));
      outfile.close();
   }
   public int winlose(String word, String text, int y)
   {
      if(word.equalsIgnoreCase(text))
         return 1;
      else if (y > 4 && !word.equalsIgnoreCase(text))
         return 2;
   
      return -1;
   }
   public void win(String word, int y)
   {
   
      for(int u = 0; u < 5; u++)
      {
         char c = word.charAt(u);
         GreenLetter green = new GreenLetter(c);
         GrayLetter gray = new GrayLetter(c);
         YellowLetter yellow = new YellowLetter(c);
         green.setColor(words, y, u);
      }
      PrintWriter outfile = null;
      JOptionPane.showMessageDialog(null, "Congrats, you won in " + (y + 1)+ " turns!");
      save(y);        
   }
   public void lose(int y, String s)
   {
      
      Scanner infile = null;
      try{
            
         infile = new Scanner(new File("average.txt"));
      }
      catch(Exception e)
      {
         JOptionPane.showMessageDialog(null,"The file could not be created.");
      }
      infile.next();
      int games = infile.nextInt();
      games = games + 1;
      infile.next();
      double average=infile.nextDouble();
            
      infile.close();
            
      PrintWriter outfile = null;
      try{
            
         outfile = new PrintWriter(new FileWriter("average.txt"));
            
      }
      catch(Exception e)
      {
         JOptionPane.showMessageDialog(null,"The file could not be created.");
      }
      DecimalFormat d = new DecimalFormat("0.00");
      outfile.println("Games: " + games);
      outfile.println("Average: " + d.format(average));
      outfile.close();        
      JOptionPane.showMessageDialog(null, "You lost :(\nWord was: " + s);
   }
}
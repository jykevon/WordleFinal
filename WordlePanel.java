import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
public class WordlePanel extends JPanel
{
   private Subpanel sub;
   private JPanel bottom;
   private ImagePanel top;
   private ImageIcon image;
   private JTextField tf;
   private String theword;
   private JButton button, retry, exit, stats;
   private int turn;
   public WordlePanel()
   {
      JOptionPane.showMessageDialog(null, "Welcome to Wordle!\nHow To Play:\nEach guess must be a valid 5 letter word.\nThe colors of the tiles change to show how close your guess was to the word.\n\nGreen means the letter is in the right place.\nYellow shows the letter is in the word, but not in the correct spot.\nGray shows that the letter isnt in the word.");
      setLayout(new BorderLayout());
      sub = new Subpanel();
      add(sub, BorderLayout.CENTER);
      
      ImageIcon wordle = new ImageIcon("wordle.jpg");
      JLabel top = new JLabel(wordle);
      top.setSize(100, 200);
      add(top, BorderLayout.NORTH);
      
      bottom = new JPanel();
      add(bottom, BorderLayout.SOUTH);
      button = new JButton("Enter");
      button.addActionListener(new WordListener());
      tf = new JTextField(25);
      bottom.add(tf);
      bottom.add(button);
      retry  = new JButton("Retry");
      retry.addActionListener(new RetryListener());
      bottom.add(retry);
      exit = new JButton("Exit");
      exit.addActionListener(new ExitListener());
      bottom.add(exit);
      stats = new JButton("Stats");
      stats.addActionListener(new StatsListener());
      bottom.add(stats);
      turn = 0;
      theword = sub.setWord();
    //   System.out.print(theword);
   }
   private class ExitListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         System.exit(0);
      }
   }
   private class StatsListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {  
         try{
            Scanner scanner = new Scanner(new File("average.txt"));
            String games = scanner.nextLine();
            String average = scanner.nextLine();
            JOptionPane.showMessageDialog(null, games + "\n" + average);
         }
         catch(Exception v){
            System.out.println("error");
         }
      }
   }
   private class WordListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {       
         String s = tf.getText();
         boolean bad = false;
         for(char c : s.toCharArray())
         {
            if(!Character.isLetter(c)) 
            {
               bad = true;
            }
         }
         sub.check(tf, theword, s, turn);
         if(sub.winlose(theword, s, turn)==1)
            sub.win(theword, turn);
         if (sub.winlose(theword, s, turn)==2)
            sub.lose(turn, theword);
         if(s.length() == 5 && !bad)
            turn++;
         tf.setText("");
      }
      
   }
   private class RetryListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {  
         theword = sub.setWord();
         turn = 0;
         sub.reset();
         tf.setText("");
      }
   }
}

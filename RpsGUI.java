/**
 *@author Cameron Arakaki
 *@program RockPaperScissorsGUI --- This program implements a Rock Paper Scissors game to be used by it's
 *                                  It's driver class RPS.java
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

// class for RockPaperScissors frame
public class RpsGUI extends JFrame {

   // strings
   private String outcome = "";
   private String compChoice = "";
   private String userChoice = "";
   
   // counters
   private int userWins = 0;
   private int compWins = 0;
   private int draws = 0;
   private int rounds = 0;
   
   // buttons
   private JButton instructionsB = new JButton("Instructions");
   private JButton rockB = new JButton("Jan! (Rock)");
   private JButton paperB = new JButton("Ken! (Paper)");
   private JButton scissorB = new JButton("Po! (Scissors)");
   private JButton exitB = new JButton("Exit");
   
   // labels
   private JLabel titleL = new JLabel("JAN KEN PO!", SwingConstants.CENTER); // static label
   private JLabel titleMsgL = new JLabel("You vs Computer!", SwingConstants.CENTER); // static label
   private JLabel clickToPlayL = new JLabel("Click below to play!", SwingConstants.CENTER); // static label
   private JLabel compChoseL = new JLabel("\tComp Chose: ", SwingConstants.LEFT); // dynamic label
   private JLabel outcomeL = new JLabel("You: ", SwingConstants.LEFT); // dynamic label
   private JLabel userChoseL = new JLabel("\tYou Chose: ", SwingConstants.LEFT); // dynamic label
   private JLabel compWinL = new JLabel("\tComp Wins: 0", SwingConstants.LEFT); // dynamic label
   private JLabel drawL = new JLabel("\tDraws: 0", SwingConstants.LEFT); // dynamic label
   private JLabel userWinL = new JLabel("\tYour Wins: 0", SwingConstants.LEFT); // dynamic label 
   private JLabel roundL = new JLabel("\tRound: 0", SwingConstants.LEFT); // dynamic label
   
   // static variables    
   private final int WIDTH = 650;
   private final int HEIGHT = 400;
   
    // constructor
   public RpsGUI() {
      this.setSize(WIDTH, HEIGHT);  
      this.setTitle("Jan Ken Po");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(false);
      this.setResizable(false);
   }
   
   public void initialize() {
      
      // event buttons
      ActionListener listener = new EventHandler();
      instructionsB.addActionListener(listener);
      rockB.addActionListener(listener);
      paperB.addActionListener(listener);
      scissorB.addActionListener(listener);
      exitB.addActionListener(listener);
      
      // frame      
      JPanel mainWindow = new JPanel();
      mainWindow.setLayout(new BorderLayout());
      
      // top
      JPanel top = new JPanel();
      top.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
      top.setLayout(new GridLayout(1, 3));
      mainWindow.add("North", top);
      titleL.setFont(new Font("Helvetica", Font.BOLD, 24));     
      top.add(instructionsB);
      top.add(titleL);
      top.add(titleMsgL);
      
      // center
      JPanel center = new JPanel();
      center.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
      center.setLayout(new GridLayout(1, 3));
      mainWindow.add("Center", center);
      
      // center left
      JPanel centerLeft = new JPanel();
      centerLeft.setBackground(Color.yellow);
      center.add(centerLeft);
      centerLeft.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
      centerLeft.setLayout(new GridLayout(6, 1));
      JLabel empty1 = new JLabel("", SwingConstants.CENTER);
      JLabel empty2 = new JLabel("", SwingConstants.CENTER);
      centerLeft.add(clickToPlayL);
      centerLeft.add(rockB);
      centerLeft.add(empty1);               
      centerLeft.add(paperB);
      centerLeft.add(empty2);         
      centerLeft.add(scissorB);
      
      // center mid
      JPanel centerMid = new JPanel();
      center.add(centerMid);
      centerMid.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
      centerMid.setLayout(new GridLayout(3, 1));
      centerMid.add(compChoseL);
      centerMid.add(outcomeL);
      centerMid.add(userChoseL);
      
      // center right
      JPanel centerRight = new JPanel();
      center.add(centerRight);
      centerRight.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
      centerRight.setLayout(new GridLayout(3, 1));
      centerRight.add(compWinL);
      centerRight.add(drawL);
      centerRight.add(userWinL);
      
      // bottom
      JPanel bottom = new JPanel();
      bottom.setBorder(BorderFactory.createEmptyBorder(10,30,30,30));
      bottom.setLayout(new GridLayout(1, 3));
      mainWindow.add("South", bottom);
      JLabel empty0 = new JLabel("", SwingConstants.CENTER);
      bottom.add(empty0);
      bottom.add(roundL);
      bottom.add(exitB);
      
      this.add(mainWindow);
   }
   
   // event handler inner class
   private class EventHandler implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         File scoresFile = new File("scores.txt");
         String outcome = "";
         int userWinTick = 0;
         int compWinTick = 0;
         int drawTick = 0;
         int userChoice = -1;
         int compChoice = -1;
         Random randomNum = new Random();            
         
         // instructions event
         if (event.getSource() == instructionsB) {
            JOptionPane.showMessageDialog(new JFrame(),
                               "How To Play:\n\n" + 
                               "Begin by clicking " +                                   
                               "\"Jan\", \"Ken\", or \"Po\" (rock, paper, scissors)\n" +
                               "The computer will automatically choose as well.\n\n" +
                               "Rock > Scissors, Paper > Rock, Scissors > Paper.\n\n" +
                               "Click \"Exit\" to terminate program.\n\n" +
                               "File \"scores.txt\" is stored in same folder as RPS.java",
                               "Instructions",
                                JOptionPane.INFORMATION_MESSAGE);                                                                         
         }
                                 
         // JButtons event (0 = Jan, 1 = Ken, 2 = Po)
         if (event.getSource() == rockB) {
            rounds++;
            compChoice = randomNum.nextInt(3);
            userChoice = 0;
         }         
         if (event.getSource() == paperB) {
            rounds++;
            compChoice = randomNum.nextInt(3);
            userChoice = 1;               
         }         
         if (event.getSource() == scissorB) {
            rounds++;
            compChoice = randomNum.nextInt(3);
            userChoice = 2;               
         }
                  
         
         // outcome checker
         switch(compChoice) {            
            case 0:
               if (userChoice == 1) {
                  outcome = "WIN!";
                  userWins++;
                  userWinTick++;
               }
               if (userChoice == 2) {
                  outcome = "LOSE!";
                  compWins++;
                  compWinTick++;
               }
               break;            
            case 1:
               if (userChoice == 2) {
                  outcome = "WIN!";
                  userWins++;
                  userWinTick++;
               }
               if (userChoice == 0) {
                  outcome = "LOSE!";
                  compWins++;
                  compWinTick++;
               }
               break;            
            case 2:
               if (userChoice == 0) {
                  outcome = "WIN!";
                  userWins++;
                  userWinTick++;
               }
               if (userChoice == 1) {
                  outcome = "LOSE!";
                  compWins++;
                  compWinTick++;                     
               }
               break;
         }          
         if ((userChoice == compChoice) && (userChoice != -1)) {
            outcome = "DRAW!";
            draws++;
            drawTick++;
         }         
         outcomeL.setText("You: " + outcome);
         roundL.setText("Round: " + rounds);
         drawL.setText("Draws: " + draws);
         userWinL.setText("Your Wins: " + userWins);
         compWinL.setText("Comp Wins: " + compWins);
         
         // output file "scores.txt". Output to root folder
         try {
            FileWriter fw = new FileWriter(scoresFile, true);
            PrintWriter pw = new PrintWriter(fw);
            if ((event.getSource() == rockB) || 
               (event.getSource() == paperB) || 
               (event.getSource() == scissorB)) {
               
               char win = 'W';
               char lose = 'L';
               char draw = 'T';
                                 
               String roundCount = "Round: " + rounds;
               pw.println(roundCount);
               
               if (userWinTick == 1) {
                  String roundScore = "User: " + win + " Computer: " + lose + " Ties: \n";
                  pw.println(roundScore);
               }
               if (compWinTick == 1) {
                  String roundScore = "User: " + lose + " Computer: " + win + " Ties: \n";
                  pw.println(roundScore);
               }
               if (drawTick == 1) {
                  String roundScore = "User:  " + " Computer:  "  + " Ties: " + draw + "\n";
                  pw.println(roundScore);
               }
               
               String newHighScore = "\nNew Final Score: " + 
                                     "\nUser: " + userWins + 
                                     "\nComp: " + compWins + 
                                     "\nTie: " + draws + "\n";
               pw.println(newHighScore);                                    
                        
               pw.close();
            }               
         }
         catch (IOException IOE) {            
         }
         
         // display user choice
         switch(userChoice) {
            case 0:
               userChoseL.setText("You Chose: Rock!");
               break;
            case 1:
               userChoseL.setText("You Chose: Paper!");
               break;
            case 2:
               userChoseL.setText("You Chose: Scissors!");
               break;
         }
         
         // display comp choice
         switch(compChoice) {
            case 0:
               compChoseL.setText("Comp Chose: Rock!");
               break;
            case 1:
               compChoseL.setText("Comp Chose: Paper!");
               break;
            case 2:
               compChoseL.setText("Comp Chose: Scissors!");
               break;
         }
         
         // exit event
         if (event.getSource() == exitB) {
            System.exit(0);
         }            
      }
   }
}
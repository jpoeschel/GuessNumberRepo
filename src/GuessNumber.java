import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * GuessNumber class
 * GUI for number guessing game
 * @author Johnathan Poeschel
 * @version  1.0, 11, Oct, 2019
 */
public class GuessNumber {
    /**
     * integer distance between the number guessed and the correct answer
     */
    private int difference = 0;
    /**
     * boolean to check if the number guessed is the first guess
     */
    private boolean first = true;
    /**
     * JLabel that displays either "Invalid Input", "Correct", "Too High", or "Too Low"
     */
    private JLabel statement = new JLabel("");
    /**
     * JButton with text "Reset" that resets game
     */
    private JButton reset = new JButton("Reset");
    /**
     * Random object generates random
     */
    private Random randNum = new Random();
    /**
     * integer to store random number generated
     */
    private int newRand = randNum.nextInt(1000)+1;
    /**
     * constructor
     */
    public GuessNumber() {
        System.out.println(newRand);
        JFrame frame = new JFrame("Guess Number");
        frame.setSize(500,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JLabel label = new JLabel("I have a number between 1-1000, please enter an integer guess");
        JPanel panel = new JPanel();
        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(100,50));
        text.addKeyListener(new KeyAdapter()
        {
            /**
             * watches for a key to be pressed
             * @param e KeyEvent set to enter key
             */
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String userInput = text.getText();
                    if (userInput.matches("[0-9]+")) {
                        int convertedUserInput = Integer.parseInt(userInput);
                        if(convertedUserInput > 0 && convertedUserInput <1001) {
                            statement.setText("");
                            if(first) {
                                difference = Math.abs(convertedUserInput-newRand);
                                first = false;
                                if(Math.abs(convertedUserInput-newRand) == 0){
                                    panel.setBackground(Color.yellow);
                                    text.setEditable(false);
                                    statement.setText("Correct on FiRsT tRy");
                                    panel.add(reset);
                                }
                            }
                            else {
                                if(convertedUserInput > newRand){
                                    statement.setText("Too High");
                                }
                                if(convertedUserInput < newRand){
                                    statement.setText("Too Low");
                                }
                                if(convertedUserInput == newRand){
                                    statement.setText("Correct");
                                    panel.add(reset);
                                }
                                if(Math.abs(convertedUserInput-newRand) < difference){
                                    panel.setBackground(Color.red);
                                    difference = Math.abs(convertedUserInput-newRand);
                                }
                                if(Math.abs(convertedUserInput-newRand) > difference){
                                    panel.setBackground(Color.blue);
                                    difference = Math.abs(convertedUserInput-newRand);
                                }
                                if(Math.abs(convertedUserInput-newRand) == 0){
                                    panel.setBackground(Color.green);
                                    text.setEditable(false);
                                }
                            }
                            text.setText(null);
                        }
                        else {
                            text.setText(null);
                            statement.setText("Invalid Input");
                        }
                    }
                    else {
                        text.setText(null);
                        statement.setText("Invalid Input");
                    }
                }
            }
        });
        reset.addActionListener(new ActionListener() {
            /**
             * watches for action performed from button
             * @param e button click
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                statement.setText("");
                first = true;
                text.setEditable(true);
                newRand = randNum.nextInt(1000) + 1;
                System.out.println(newRand);
                panel.setBackground(Color.white);
                panel.remove(reset);
            }
        });
        panel.add(label);
        panel.add(text);
        panel.add(statement);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}

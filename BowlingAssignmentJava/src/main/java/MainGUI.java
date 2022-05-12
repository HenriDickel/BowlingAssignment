import GUIComponents.CustomRectangle;
import GUIComponents.CustomTriangle;
import Logic.Frame;
import Logic.GUIFrameCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class MainGUI extends JFrame implements KeyListener {

    //Fields
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JPanel mainPanel;
    private JButton resetButton;
    private JPanel scorePanel;
    private JButton a0Button;
    private JButton a10Button;
    private JPanel keyboardPanel;
    private JPanel buttonPanel1;
    private JPanel buttonPanel2;

    //Scores
    private List<Integer> scores;
    private Logic.Frame[] frames;

    //Color
    private final Color backgroundColor = new Color(100, 100, 100);
    private final Color keyboardColor = new Color(64, 64, 64);

    public MainGUI() {
        super("Bowling Assignment");

        //Create scores object
        scores = new ArrayList<>();

        //Set listeners
        resetButton.addActionListener(e -> resetButtonClicked());
        a0Button.addActionListener(e -> numberButtonClicked(0));
        a1Button.addActionListener(e -> numberButtonClicked(1));
        a2Button.addActionListener(e -> numberButtonClicked(2));
        a3Button.addActionListener(e -> numberButtonClicked(3));
        a4Button.addActionListener(e -> numberButtonClicked(4));
        a5Button.addActionListener(e -> numberButtonClicked(5));
        a6Button.addActionListener(e -> numberButtonClicked(6));
        a7Button.addActionListener(e -> numberButtonClicked(7));
        a8Button.addActionListener(e -> numberButtonClicked(8));
        a9Button.addActionListener(e -> numberButtonClicked(9));
        a10Button.addActionListener(e -> numberButtonClicked(10));

        //Key listener
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);

        //Color
        mainPanel.setBackground(backgroundColor);
        keyboardPanel.setBackground(keyboardColor);
        buttonPanel1.setBackground(keyboardColor);
        buttonPanel2.setBackground(keyboardColor);

        //Create UI
        this.setBounds(0,0,850, 400);
        this.setMinimumSize(new Dimension(850,400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //Load score grid
        reloadUI();
    }

    /**
     * Recreates the UI by rebuilding the score grid
     */
    public void reloadUI(){
        //Calculate frames
        frames = GUIFrameCalculator.getAllFrames(scores);

        //Create score grid
        reloadScoreGrid();

        //Check buttons
        checkButtons();
    }

    /**
     * OnClick event listener for all number buttons
     * @param number The number of balls that were hit
     */
    public void numberButtonClicked(int number){
        scores.add(number);
        reloadUI();
    }

    /**
     * OnClick event listener for the reset button. Restarts the game.
     */
    public void resetButtonClicked(){
        scores = new ArrayList<>();
        reloadUI();
    }

    /**
     * Check which buttons are valid to be clicked
     */
    public void checkButtons(){
        //Get current frame
        Logic.Frame currFrame = frames[0];
        for(int i = 0; i < frames.length - 1; i++){
            if(frames[i].getInputScores() != null){
                if(frames[i].getInputScores().length == 2 || frames[i].isStrike()){
                    currFrame = frames[i+1];
                }
            }
        }

        //No input done for this frame so all inputs are valid
        if(currFrame.getInputScores() == null){
            enableAllButtons();
        }
        else{
            //Last number
            int lastNumber = currFrame.getInputScores()[0];
            if(currFrame.isLastFrame() && currFrame.getInputScores().length == 2) lastNumber = currFrame.getInputScores()[1];

            //Check if input can be used
            if(lastNumber > 0) a10Button.setEnabled(false);
            if(lastNumber > 1) a9Button.setEnabled(false);
            if(lastNumber > 2) a8Button.setEnabled(false);
            if(lastNumber > 3) a7Button.setEnabled(false);
            if(lastNumber > 4) a6Button.setEnabled(false);
            if(lastNumber > 5) a5Button.setEnabled(false);
            if(lastNumber > 6) a4Button.setEnabled(false);
            if(lastNumber > 7) a3Button.setEnabled(false);
            if(lastNumber > 8) a2Button.setEnabled(false);
            if(lastNumber > 9) a1Button.setEnabled(false);

        }

        //Check last frame (If at least one input was made)
        if(currFrame.isLastFrame() && currFrame.getInputScores() != null){
            //Strike (One or two actions left)
            if(currFrame.isStrike()){
                if(currFrame.getInputScores().length == 3) endGame();   //Game ended
                else if(currFrame.getInputScores().length == 1) enableAllButtons(); //First action after strike
                else if(currFrame.getInputScores().length == 2 && currFrame.getInputScores()[1] == 10) enableAllButtons(); //Double strike on last frame
            }
            //Spare (Only one action left)
            else if(currFrame.isSpare()){
                if(currFrame.getInputScores().length == 3) endGame();   //Game ended
                else enableAllButtons();
            }
            else{
                if(currFrame.getInputScores().length == 2) endGame();   //Game ended
            }
        }

        //Reset button
        resetButton.setEnabled(frames[0].getInputScores() != null);
    }

    /**
     * Set all buttons to 'enabled'
     */
    private void enableAllButtons(){
        a0Button.setEnabled(true);
        a1Button.setEnabled(true);
        a2Button.setEnabled(true);
        a3Button.setEnabled(true);
        a4Button.setEnabled(true);
        a5Button.setEnabled(true);
        a6Button.setEnabled(true);
        a7Button.setEnabled(true);
        a8Button.setEnabled(true);
        a9Button.setEnabled(true);
        a10Button.setEnabled(true);
    }

    /**
     * Ends the game by disabling all buttons and showing a popup dialog, notifying the user
     */
    private void endGame(){

        //Disable buttons
        a10Button.setEnabled(false);
        a9Button.setEnabled(false);
        a8Button.setEnabled(false);
        a7Button.setEnabled(false);
        a6Button.setEnabled(false);
        a5Button.setEnabled(false);
        a4Button.setEnabled(false);
        a3Button.setEnabled(false);
        a2Button.setEnabled(false);
        a1Button.setEnabled(false);
        a0Button.setEnabled(false);

        //Dialog
        JOptionPane.showMessageDialog(mainPanel,
                "Congratulations, you have scored " + frames[9].getFrameScore() + " points!\nIf you want to play again, click reset.",
                "Game ended",
                JOptionPane.PLAIN_MESSAGE);
    }


    /**
     * Creates the score grid
     */
    private void reloadScoreGrid(){

        //Create Panel
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.setBackground(backgroundColor);

        //Add all UI-Frames
        for (Logic.Frame frame : frames) panel.add(createFramePanel(frame));

        //Check if a panel was already added
        if(scorePanel.getComponentCount() > 0) scorePanel.remove(0);

        //Add to UI
        scorePanel.add(panel);
        scorePanel.revalidate();
        scorePanel.repaint();
    }

    /**
     * Creates a single frame panel
     * @param frame The frame that will be displayed
     * @return Returns a JPanel representing one frame
     */
    private JPanel createFramePanel(Frame frame){

        //Create Panel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 20;   //Width
        c.ipady = 20;   //Height

        //First number text
        String firstNumberString = "  ";
        if(frame.getInputScores() != null) firstNumberString = String.valueOf(frame.getInputScores()[0]);

        //First number label
        final JLabel firstNumberLabel = new JLabel(firstNumberString, SwingConstants.CENTER);
        firstNumberLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(firstNumberLabel, c);

        //Second number
        //Strike
        if(frame.isStrike() && !frame.isLastFrame()){
            CustomRectangle rect = new CustomRectangle();
            rect.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 1;
            c.gridy = 0;
            panel.add(rect, c);
        }
        //Spare
        else if(frame.isSpare()) {
            CustomTriangle triangle = new CustomTriangle();
            triangle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 1;
            c.gridy = 0;
            panel.add(triangle, c);
        }
        else{
            //Second number text
            String secondNumberString = "  ";
            if(frame.getInputScores() != null && frame.getInputScores().length > 1) secondNumberString = String.valueOf(frame.getInputScores()[1]);

            //Second number label
            final JLabel secondNumberLabel = new JLabel(secondNumberString, SwingConstants.CENTER);
            secondNumberLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = 0;
            panel.add(secondNumberLabel, c);
        }

        //Last frame - third number
        if(frame.isLastFrame()){
            //Third number text
            String thirdNumberString = "  ";
            if(frame.getInputScores() != null && frame.getInputScores().length > 2) thirdNumberString = String.valueOf(frame.getInputScores()[2]);

            //Third number label
            final JLabel thirdNumberLabel = new JLabel(thirdNumberString, SwingConstants.CENTER);
            thirdNumberLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 2;
            c.gridy = 0;
            panel.add(thirdNumberLabel, c);
        }


        //Frame score text
        String frameScoreString = "  ";
        if(frame.isFinished()) frameScoreString = String.valueOf(frame.getFrameScore());
        if(!frame.isFinished() && !frame.isEmpty()) frameScoreString = "?"; //Current frame but unfinished

        //Frame score label
        final JLabel frameScoreLabel = new JLabel(frameScoreString, SwingConstants.CENTER);
        frameScoreLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = frame.isLastFrame() ? 3 : 2;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(frameScoreLabel, c);

        //Return result
        return panel;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Map the keyboard to the buttons
        if (e.getKeyCode() == KeyEvent.VK_0) a0Button.doClick();
        if (e.getKeyCode() == KeyEvent.VK_1) a1Button.doClick();
        if (e.getKeyCode() == KeyEvent.VK_2) a2Button.doClick();
        if (e.getKeyCode() == KeyEvent.VK_3) a3Button.doClick();
        if (e.getKeyCode() == KeyEvent.VK_4) a4Button.doClick();
        if (e.getKeyCode() == KeyEvent.VK_5) a5Button.doClick();
        if (e.getKeyCode() == KeyEvent.VK_6) a6Button.doClick();
        if (e.getKeyCode() == KeyEvent.VK_7) a7Button.doClick();
        if (e.getKeyCode() == KeyEvent.VK_8) a8Button.doClick();
        if (e.getKeyCode() == KeyEvent.VK_9) a9Button.doClick();

        if (e.getKeyCode() == KeyEvent.VK_NUMPAD0) a0Button.doClick();
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) a1Button.doClick();
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD2) a2Button.doClick();
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD3) a3Button.doClick();
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) a4Button.doClick();
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD5) a5Button.doClick();
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD6) a6Button.doClick();
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD7) a7Button.doClick();
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD8) a8Button.doClick();
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD9) a9Button.doClick();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

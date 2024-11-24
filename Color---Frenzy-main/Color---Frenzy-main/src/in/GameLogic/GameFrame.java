package in.GameLogic;

import java.awt.*;
import javax.swing.*;

import in.TimerForGame.CountDownTimer;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;


public class GameFrame extends JFrame implements ActionListener, MouseListener {

    public int PlayerScore = 0, correctBoxes = 0;
    int _gameLevel = 1;
    JPanel _gridPanel;
    JLabel _backgroundImage, _level, _boxCount, _timerClock, _score;
    JButton _exit, _next, _start;
    int _rows = 7, _columns = 9, boxCount, _currentGameLevelForPrint, _gameCountDown = 30;
    int _totalCells = _rows * _columns;
    JLabel[] boxes = new JLabel[_totalCells];
    GridManagement gridManagement;
    Integer[] userInput, correctAnswer;
    Timer timer;
    boolean[] isClicked;
    CountDownTimer countDownTimer;
    boolean _timeOver;
    ScoreUpdation scoreUpdation;
    
    // private GameFrame gameFrame;
    public GameFrame(ScoreUpdation scoreUpdation) {
        this.scoreUpdation = scoreUpdation;
        initializeFrame();
        initializeComponents();
    }
    

    public GameFrame() {
        initializeFrame();
        initializeComponents();
    }



    public void initializeFrame() {

        // Adding the image for the background
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("images/Background.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(1560, 1150, Image.SCALE_SMOOTH);
        ImageIcon finalImage = new ImageIcon(image);

        // adding the image JLabel for Resizing
        _backgroundImage = new JLabel(finalImage);
        _backgroundImage.setBounds(0, 0, 1560, 1150);
        add(_backgroundImage);


        JLabel _title = new JLabel("Color Frenzy: To increase Focus Attention");
        _title.setFont(new Font("System", Font.BOLD, 35));
        _title.setForeground(Color.white);
        _title.setBounds(30, 30, 900, 50);
        _backgroundImage.add(_title);



        // defining the grid
        _gridPanel = new JPanel(new GridLayout(_rows, _columns, 10, 10));
        _gridPanel.setBounds(450, 150, 1100, 850);
        _gridPanel.setBackground(Color.DARK_GRAY);
        _gridPanel.setOpaque(false);
        _backgroundImage.add(_gridPanel);


        // Level Information

        _level = new JLabel("Current Level: " + _gameLevel);
        _currentGameLevelForPrint = _gameLevel;
        _level.setBounds(40, 165, 270, 30);
        currentLevelUpdataion(_currentGameLevelForPrint);
        _backgroundImage.add(_level); 
        

        JPanel _boxForLevel = new JPanel(new GridLayout(1, 1));
        _boxForLevel.setBounds(30, 155, 400, 50);
        _boxForLevel.setBackground(Color.yellow);
        _backgroundImage.add(_boxForLevel);



        JLabel timerLabel = new JLabel("TOTAL TIME LEFT");
        timerLabel.setFont(new Font("System", Font.BOLD, 25));
        timerLabel.setForeground(Color.white);
        timerLabel.setBounds(100, 500, 400, 50);
        _backgroundImage.add(timerLabel);
        
        
        _timerClock = new JLabel("" + _gameCountDown);
        _timerClock.setFont(new Font("System", Font.BOLD, 50));
        _timerClock.setForeground(Color.white);
        _timerClock.setBounds(180, 560, 300, 60);
        _backgroundImage.add(_timerClock);
        countDownTimer = new CountDownTimer(_gameCountDown, _timerClock, this);
        

        JLabel score = new JLabel("Total Score");
        score.setFont(new Font("System", Font.BOLD, 25));
        score.setForeground(Color.white);
        score.setBounds(130, 750, 400, 50);
        _backgroundImage.add(score);

        _score = new JLabel("" + PlayerScore);
        _score.setFont(new Font("System", Font.BOLD, 50));
        _score.setForeground(Color.white);
        _score.setBounds(190, 800, 300, 60);
        _backgroundImage.add(_score);

        // Defining the Buttons
        _exit = new JButton("Exit");
        _exit.setFont(new Font("System", Font.BOLD, 20));
        _exit.setForeground(Color.BLACK);
        _exit.setFocusable(false);
        _exit.setBackground(Color.YELLOW);
        _exit.setBounds(1350, 1050, 150, 40);
        _exit.addActionListener(this);
        _exit.addMouseListener(this);
        _backgroundImage.add(_exit);
        
        _start = new JButton("Start");
        _start.setFont(new Font("System", Font.BOLD, 20));
        _start.setForeground(Color.BLACK);
        _start.setFocusable(false);
        _start.setBackground(Color.YELLOW);
        _start.setBounds(250, 1050, 150, 40);
        _start.addActionListener(this);
        _start.addMouseListener(this);
        _backgroundImage.add(_start);
        
        _next = new JButton("Next");
        _next.setFont(new Font("System", Font.BOLD, 20));
        _next.setForeground(Color.BLACK);
        _next.setFocusable(false);
        _next.setBackground(Color.YELLOW);
        _next.setBounds(50, 1050, 150, 40);
        _next.addActionListener(this);
        _next.addMouseListener(this);
        _backgroundImage.add(_next);



        // Frame
        this.setTitle("Attention Detection Game");
        this.setSize(1560, 1150);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setVisible(true);

    }

    public void initializeComponents() {
        // Writting the GameLogic
        gridManagement = new GridManagement(boxes, _totalCells, _gridPanel, this);
        gridManagement.CreateGrid();
        
        // Initializing the UserInput and the CorrectAnswer array 
        userInput = new Integer[gridManagement.getNumberOfBoxes(_gameLevel)];
        correctAnswer = new Integer[gridManagement.getNumberOfBoxes(_gameLevel)];
        isClicked = new boolean[_totalCells];
        
        // This is the box Count
        boxCount= gridManagement.getNumberOfBoxes(_gameLevel);
        _boxCount = new JLabel("Number of Boxes Left: " + boxCount);
        _boxCount.setBounds(40, 315, 400, 30);
        _backgroundImage.add(_boxCount);
        boxCountUpdation(boxCount);

        _gridPanel.revalidate();    // these two line is ensuring that the grid is printed 
        _gridPanel.repaint();       // successfully as intended

        JPanel _backGroundForBoxNumber = new JPanel(new GridLayout(1, 1));
        _backGroundForBoxNumber.setBounds(30, 290, 400, 75);
        _backGroundForBoxNumber.setBackground(Color.cyan);
        _backGroundForBoxNumber.setFocusable(false);
        _backgroundImage.add(_backGroundForBoxNumber);
    }
    


    public void boxCountUpdation(int boxCount) {
        _boxCount.setText("Number of Boxes Left: " + boxCount);
        _boxCount.setForeground(Color.black);
        _boxCount.setFont(new Font("System", Font.BOLD, 25));
        
        _backgroundImage.revalidate();
        _backgroundImage.repaint();
        
    }

    public void currentLevelUpdataion(int currentLevel) {
        _level.setText("Current Level: " + currentLevel);
        _level.setFont(new Font("Arial", Font.BOLD, 30));
        _level.setForeground(Color.black);
        

        _backgroundImage.revalidate();
        _backgroundImage.repaint();
    }
                                        

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == _exit) {
            System.exit(0);
        } else if(e.getSource() == _start) {
            gridManagement.changeBoxColor(Color.cyan, _gameLevel);
            startTimer();
            countDownTimer.startTimer();

            isPlayerOutOfTime();
        } else if(e.getSource() == _next) {
            restartForNextLevel();
        }
    }
    
    public void startTimer() {
        if(timer != null) {
            timer.cancel();
        }
        
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                gridManagement.repaintGrid();       // changes the color of the grid to gray again
                if(countDownTimer.isTimeOver()) {
                    isPlayerOutOfTime();
                }

            }
        };
        timer.schedule(task, 2000);
    }
    
    
    int idx = 0; // help for filling the value of the UserInput in the correct index
    @Override
    public void mouseClicked(MouseEvent e) {
        isPlayerOutOfTime();
        for (int i = 0; i < _totalCells; i++) {
            if (e.getSource().equals(boxes[i])) {
                if (isClicked[i]) {
                    // If the box is already clicked, deselect it
                    for (int k = 0; k < userInput.length; k++) {
                        if (userInput[k] != null && userInput[k] == i) {
                            userInput[k] = null; // Remove the deselected box from userInput
                            boxCountUpdation(++boxCount);
                            isPlayerOutOfTime();
                            break;
                        }
                    }

                    // Reset `idx` to point to the next empty slot in `userInput`
                    idx = 0;
                    for (int k = 0; k < userInput.length; k++) {
                        if (userInput[k] != null) {
                            idx++;
                        }
                    }

                    isClicked[i] = false; // Mark the box as unselected
                    boxes[i].setBackground(Color.darkGray); // Reset the box color
                    isPlayerOutOfTime();
                    return;
                }

                // Handle selection of a box
                isClicked[i] = true; // Mark the box as selected
                for (int j = 0; j < userInput.length; j++) {
                    if (userInput[j] == null) {
                        userInput[j] = i; // Store the index of the selected box
                        boxCountUpdation(--boxCount);
                        idx++;
                        break;
                    }
                }

                boxes[i].setBackground(Color.yellow); // Highlight the selected box
                checkForWin(); // Check if the user has won
            }
        }
    }


    public void isPlayerOutOfTime() {
        if(countDownTimer.isTimeOver()) {
            int choice = JOptionPane.showOptionDialog(null, "YOU ARE OUT OF TIME FOR LEVEL " + _gameLevel,
                "Time Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null,
                new String[] {"Retry", "Exit"}, "Next Level");

            if(choice == JOptionPane.YES_OPTION) {
                restartGame();
            } else {
                System.exit(0);
            }
        }
    }
    

    public void countBoxes() {
        for(int i=0; i<userInput.length; i++) {
            if(correctAnswer[i].equals(userInput[i])) {
                correctBoxes++;
            }
        }
    }

    public boolean checkForWin() {
        for(int i=0; i<userInput.length; i++) {
            if(userInput[i] == null)
            return false;
        }
        isPlayerOutOfTime();
        correctAnswer = gridManagement.getCorrectBoxes();
        Arrays.sort(userInput, Collections.reverseOrder());
        if(Arrays.equals(userInput, correctAnswer)) {
            countDownTimer.stopTimer();
            countBoxes();
            nextLevel();
            return true;
        } else {
            countBoxes();
            countDownTimer.stopTimer();
            int choice = JOptionPane.showOptionDialog(null, "You Lost at Level " + _gameLevel,
            "Level Faild",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE, null,
            new String[] {"Retry", "Exit"}, "Next Level");
            
            if(choice == JOptionPane.YES_OPTION) {
                restartGame();
            } else {
                System.exit(0);
            }
        }
        return false;
    }

    public void nextLevel() {
        int choice = JOptionPane.showOptionDialog(null, "You have Completed Level: " + _gameLevel + " Sucessfully",
            "Level Complete",   // title of the dialog box
            JOptionPane.YES_NO_OPTION,  // Option type Yes or No
            JOptionPane.INFORMATION_MESSAGE, null,  // Message Type
            new String[]{"Next Level", "Exit",}, "Next Level");     // Options
            


        if(choice == JOptionPane.YES_OPTION) {
            PlayerScore += (countDownTimer.getPlayerScore() + (correctBoxes * 2));
            _score.setText("" + PlayerScore);
            scoreUpdation.setNewScore(PlayerScore);
            restartForNextLevel();
        } else {
            System.exit(0);
        }
    }

    public void restartForNextLevel() {
        _gridPanel.repaint();
        _gameLevel++;
        currentLevelUpdataion(++_currentGameLevelForPrint);
        userInput = new Integer[gridManagement.getNumberOfBoxes(_gameLevel)];
        correctAnswer = new Integer[gridManagement.getNumberOfBoxes(_gameLevel)];
        isClicked = new boolean[_totalCells];
        idx = 0;
        gridManagement.repaintGrid();
        gridManagement.changeBoxColor(Color.CYAN, _gameLevel);
        boxCount = gridManagement.getNumberOfBoxes(_gameLevel);
        boxCountUpdation(boxCount);
        startTimer();
        restartTheTimer();
    }

    // public void restartTheTimer(boolean timeOver) {
    //     _gameCountDown = 30;
    //     countDownTimer = new CountDownTimer(_gameCountDown, _timerClock);
    //     countDownTimer.startTimer();
    
    // }

    public void restartTheTimer() {
        if (countDownTimer != null) {
            countDownTimer.stopTimer();
            countDownTimer.secondsLimit = 30;  // Reset the timer's countdown value
            countDownTimer.startTimer();       // Reuse the same CountDownTimer object
        }
    }
    
    
    
    public void restartGame() {
        _gridPanel.repaint();
        userInput = new Integer[gridManagement.getNumberOfBoxes(_gameLevel)];
        correctAnswer = new Integer[gridManagement.getNumberOfBoxes(_gameLevel)];
        isClicked = new boolean[_totalCells];
        idx = 0;
        currentLevelUpdataion(_currentGameLevelForPrint);
        gridManagement.repaintGrid();
        gridManagement.changeBoxColor(Color.CYAN, _gameLevel);
        boxCount = gridManagement.getNumberOfBoxes(_gameLevel);
        boxCountUpdation(boxCount);
        PlayerScore = 0;
        startTimer();
        restartTheTimer();

    }
    
    
    @Override
    public void mousePressed(MouseEvent e) {
        
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        
        
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == _exit) {
            changeButtonColor(_exit);
        } else if(e.getSource() == _next) {
            changeButtonColor(_next);
        } else if(e.getSource() == _start) {
            changeButtonColor(_start);
        }
    }

    // for changing the color the button during the hover of the cursor
    public void changeButtonColor(JButton button) {
        button.setForeground(Color.white);
        button.setBackground(Color.black);
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == _next) {
            revertButtonColor(_next);
        } else if(e.getSource() == _exit) {
            revertButtonColor(_exit);
        } else if(e.getSource() == _start) {
            revertButtonColor(_start);
        }
        
    }
    
    // for chaning the color of the button to original 
    public void revertButtonColor(JButton button) {
        button.setForeground(Color.black);
        button.setBackground(Color.yellow);
    }
    
    public static void main(String[] args) {
        new GameFrame();
    }

}

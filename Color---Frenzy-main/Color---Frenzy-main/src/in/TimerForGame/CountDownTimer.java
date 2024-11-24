package in.TimerForGame;

import javax.swing.*;
import java.awt.event.*;

import in.GameLogic.GameFrame;

public class CountDownTimer extends JFrame {

    public int secondsLimit;
    Timer countDown;
    JLabel label;
    boolean flag;
    private int scoreDueToTime = 0;
    GameFrame gameFrame;
    

    public CountDownTimer(int secondsLimit, JLabel label, GameFrame gameFrame) {
        this.secondsLimit = secondsLimit;
        this.label = label;
        this.gameFrame = gameFrame;
    }
    
    public void startTimer() {
        flag = false;
        countDown = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(secondsLimit > 0) {
                    label.setText("" + secondsLimit);
                    // Checking for the Score of the Player
                    if(secondsLimit <= 5) {
                        scoreDueToTime = 1;
                    } else if(secondsLimit <= 10) {
                        scoreDueToTime = 2;
                    } else if(secondsLimit <= 15) {
                        scoreDueToTime = 3;
                    } else if(secondsLimit <= 20) {
                        scoreDueToTime = 4;
                    } else if(secondsLimit <= 25) {
                        scoreDueToTime = 5;
                    } else if(secondsLimit <= 30) {
                        scoreDueToTime = 6;
                    }
                    secondsLimit--;
                    
                } else {
                    label.setText("Lost");
                    countDown.stop();
                    flag = true;
                    notifyTimeOver();
                }
            }
        });
        countDown.start();
    }

    public int getPlayerScore() {
        return scoreDueToTime;
    }

    public void stopTimer() {
        if(countDown != null) {
            countDown.stop();
        }
    }

    public boolean isTimeOver() {
        return flag;
    }

    public void notifyTimeOver() {
        if(isTimeOver()) {
            gameFrame.isPlayerOutOfTime();
            
        }
    }

    
}

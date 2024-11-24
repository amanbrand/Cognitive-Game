package in.GameLogic;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GridManagement implements MouseListener {

    private int _totalCells;
    private JLabel[] boxes;
    private JPanel _gridPanel;
    private Integer[] randomBoxes;
    private GameFrame gameFrameInstance;

    
    public GridManagement(JLabel[] boxes, int _totalCells, JPanel _gridPanel, GameFrame gameFrameInstance) {
        this.boxes = boxes;
        this._totalCells = _totalCells;
        this._gridPanel = _gridPanel;
        this.gameFrameInstance = gameFrameInstance;
    }


    // for the operations on the grid
    public void CreateGrid() {
        for(int i=0; i<_totalCells; i++) {
            boxes[i] = new JLabel();
            boxes[i].setOpaque(true);
            boxes[i].setBackground(Color.DARK_GRAY);
            boxes[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            boxes[i].addMouseListener(gameFrameInstance);
            boxes[i].setFocusable(true);
            _gridPanel.add(boxes[i]);
        }
    }

    public void repaintGrid() {
        for(int i=0; i<_totalCells; i++) {
            boxes[i].setBackground(Color.DARK_GRAY);
        }
    }


    // for getting the number of the cells according to the levels
    public int getNumberOfBoxes(int level) {
        switch (level) {
            case 1:
                return 5;
            case 2:
                return 7;
            case 3:
                return 10;
            case 4:
                return 12;
            case 5:
                return 15;
            case 6:
                return 18;
            case 7: 
                return 20;
            default:
                JOptionPane.showMessageDialog(null, "You Have Completed the Game.");
                System.exit(0); // if you are at more than level 7
                return 0;
        }
    }

    // for getting the random box numbers
    public Integer[] randomBoxNumber(JLabel[] totalBoxes, int level) {
        randomBoxes = new Integer[getNumberOfBoxes(level)];
        Random random = new Random();
        boolean isPresent;
        int randomNumber = 0, idx = 0;
        
        while(randomBoxes[getNumberOfBoxes(level)-1] == null) {
            isPresent = false;
            randomNumber = random.nextInt(_totalCells);
            // linear search for checking the duplicate values
            for(int j=0; j<randomBoxes.length; j++) {   // Due to the linear search according to me 
                if(randomBoxes[j] != null && randomBoxes[j].equals(randomNumber)) {    // the 0th box won't ever get painted. 
                    isPresent = true;
                    break;
                }
            }
            if(!isPresent) {
                randomBoxes[idx++] = randomNumber;
            }

        }
        return randomBoxes;
    }

    public Integer[] getCorrectBoxes() {

        Arrays.sort(randomBoxes, Collections.reverseOrder());
        return randomBoxes;
    }


    



    // for changing the color of the boxes
    public void changeBoxColor(Color someColor, int level) {
        Integer[] randomBox = randomBoxNumber(boxes, level);
        for(int i=0; i<randomBox.length; i++) {
            Integer boxToPaint = randomBox[i];
            boxes[boxToPaint].setBackground(someColor);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}

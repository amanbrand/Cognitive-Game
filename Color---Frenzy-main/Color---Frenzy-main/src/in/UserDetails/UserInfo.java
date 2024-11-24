package in.UserDetails;

import java.awt.*;
import javax.swing.*;
import in.GameLogic.GameFrame;                // this is the local class
import in.GameLogic.ScoreUpdation;

import java.awt.event.*;


public class UserInfo extends JFrame implements ActionListener {
    
    JLabel _backGroundImage;
    JButton _exit, _submit;
    JTextField _name, _age;
    JRadioButton _male, _female, _other;
    UserInfo userInfo;

    // These are the Player Details
    public String PlayerName = null;
    public int PlayerAge = 0;
    public String PlayerGender = null;


    public UserInfo() {

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("images/userPage.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(1200, 1200, Image.SCALE_SMOOTH);
        ImageIcon finalImage = new ImageIcon(image);

        _backGroundImage = new JLabel(finalImage);
        _backGroundImage.setBounds(0, 0, 1200, 1150);
        add(_backGroundImage);


        JLabel _greeting = new JLabel("Brain Game: Color Frenzy :)");
        _greeting.setBounds(50, 50, 600, 50);
        _greeting.setForeground(Color.yellow);
        _greeting.setFont(new Font("System", Font.BOLD, 30));
        _backGroundImage.add(_greeting);

        JLabel _info = new JLabel("Player Details:");
        _info.setBounds(75, 200, 600, 50);
        _info.setForeground(Color.blue);
        _info.setFont(new Font("System", Font.BOLD, 30));
        _backGroundImage.add(_info);
        


        // Player Name
        JLabel _playerName = new JLabel("Player Name");
        _playerName.setBounds(100, 300, 150, 50);
        _playerName.setForeground(Color.cyan);
        _playerName.setFont(new Font("System", Font.BOLD, 20));
        _backGroundImage.add(_playerName);
        
        
        _name = new JTextField("Enter Your Name");
        _name.setBackground(Color.DARK_GRAY);
        _name.setForeground(Color.white);
        _name.setFont(new Font("Arial", Font.BOLD, 15));
        _name.setBounds(275, 300, 300, 40);
        _name.setCaretColor(Color.white);
        _name.setHorizontalAlignment(JTextField.CENTER);
        _backGroundImage.add(_name);

        _name.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(_name.getText().equals("Enter Your Name")) {
                    _name.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(_name.getText().isEmpty()) {
                    _name.setText("Enter Your Name");
                }
            }
        });
        
        

        // Player Age
        JLabel _playerAge = new JLabel("Player Age");
        _playerAge.setBounds(100, 375, 150, 50);
        _playerAge.setForeground(Color.cyan);
        _playerAge.setFont(new Font("System", Font.BOLD, 20));
        _backGroundImage.add(_playerAge);
        
        
        _age = new JTextField("Enter Your Age");
        _age.setBackground(Color.DARK_GRAY);
        _age.setForeground(Color.white);
        _age.setFont(new Font("Arial", Font.BOLD, 15));
        _age.setBounds(275, 375, 300, 40);
        _age.setCaretColor(Color.white);
        _age.setHorizontalAlignment(JTextField.CENTER);
        _backGroundImage.add(_age);
        
        _age.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(_age.getText().equals("Enter Your Age")) {
                    _age.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(_age.getText().isEmpty()) {
                    _age.setText("Enter Your Age");
                }
            }
        });


        
        // Player Gender
        JLabel _playerGender = new JLabel("Gender");
        _playerGender.setBounds(100, 450, 150, 50);
        _playerGender.setForeground(Color.cyan);
        _playerGender.setFont(new Font("System", Font.BOLD, 20));
        _backGroundImage.add(_playerGender);
        
        
        _male = new JRadioButton("Male");
        _male.setBounds(275, 469, 80, 20);
        _male.setForeground(Color.white);
        _male.setFont(new Font("Arial", Font.BOLD, 18));
        _male.setOpaque(false);
        _male.setFocusable(false);
        _male.addActionListener(this);
        _backGroundImage.add(_male);
        
        _female = new JRadioButton("Female");
        _female.setBounds(375, 469, 100, 20);
        _female.setForeground(Color.white);
        _female.setFont(new Font("Arial", Font.BOLD, 18));
        _female.setOpaque(false);
        _female.setFocusable(false);
        _female.addActionListener(this);
        _backGroundImage.add(_female);  
        
        _other = new JRadioButton("Prefer not to say");
        _other.setBounds(275, 510, 200, 20);
        _other.setForeground(Color.white);
        _other.setFont(new Font("Arial", Font.BOLD, 18));
        _other.setOpaque(false);
        _other.setFocusable(false);
        _other.addActionListener(this);
        _backGroundImage.add(_other);


        ButtonGroup _genderGroup = new ButtonGroup();
        _genderGroup.add(_male);
        _genderGroup.add(_female);
        _genderGroup.add(_other);


        // JButton --> Exit
        _exit = new JButton("Exit");
        _exit.setFont(new Font("System", Font.BOLD, 20));
        _exit.setForeground(Color.BLACK);
        _exit.setFocusable(false);
        _exit.setBackground(Color.YELLOW);
        _exit.setBounds(50, 800, 150, 40);
        _exit.addActionListener(this);
        _backGroundImage.add(_exit);
        
        _submit = new JButton("Submit");
        _submit.setFont(new Font("System", Font.BOLD, 20));
        _submit.setForeground(Color.BLACK);
        _submit.setFocusable(false);
        _submit.setBackground(Color.YELLOW);
        _submit.setBounds(350, 800, 150, 40);
        _submit.addActionListener(this);
        _backGroundImage.add(_submit);


        this.setTitle("User Details");
        this.setSize(1000, 1000);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == _exit) {
            System.exit(0);
        } else if(e.getSource() == _submit) {
            // this is for error checking from the user side
            // if(!_name.getText().isEmpty() || !_age.getText().isEmpty() || (!_male.isSelected() && !_female.isSelected() && !_other.isSelected())) {
            //     JOptionPane.showMessageDialog(null, "PLEASE ENTER ALL THE VALUES");
            //     return;
            // }
            
            if(_male.isSelected()) {
                PlayerGender = "Male";
            } else if(_female.isSelected()) {
                PlayerGender = "Female";
            } else if(_other.isSelected()) {
                PlayerGender = "Preferred Unknown";
            }
            try {
                PlayerName = _name.getText();
                PlayerAge = Integer.parseInt(_age.getText());
                
                if(PlayerName.isEmpty() || PlayerAge <= 0) {
                    throw new CustomException("Details not filled correctly");
                }

            } catch(Exception exception) {
                System.out.println("Error: " + exception.getMessage());
            }

            
            DatabaseConnection DBConnection = new DatabaseConnection();
            DBConnection.insertIntoDatabase(PlayerName, PlayerAge, PlayerGender, 0);
            

            // ScoreUpdation scoreUpdation = new ScoreUpdation();
            // scoreUpdation.setUserData(this, DBConnection);

            ScoreUpdation scoreUpdation = new ScoreUpdation(this, DBConnection);

            this.setVisible(false);
            new GameFrame(scoreUpdation);
        }
    }

    public static void main(String[] args) {
        new UserInfo();
    }
}

package in.GameLogic;


import in.UserDetails.DatabaseConnection;
import in.UserDetails.UserInfo;


public class ScoreUpdation {

    private UserInfo userInformation;
    DatabaseConnection DBConnection;
    private int CalculatedScore = 0;

    public ScoreUpdation(UserInfo userInfo, DatabaseConnection DBConnection) {
        this.userInformation = userInfo;
        this.DBConnection = DBConnection;
    }

    
    public void setNewScore(int newScore) {
        this.CalculatedScore = newScore;
        DBConnection.UpdateScoreValue(CalculatedScore, userInformation.PlayerName, userInformation.PlayerAge, userInformation.PlayerGender);
    }

}

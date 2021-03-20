package FY21_Interview;

public class ScoreCard {

}


class Score {

    int scoreValue;
    String scoreUser;

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public String getScoreUser() {
        return scoreUser;
    }

    public void setScoreUser(String scoreUser) {
        this.scoreUser = scoreUser;
    }

    public Score(int scoreValue, String scoreUser) {
        this.scoreValue = scoreValue;
        this.scoreUser = scoreUser;
    }
}

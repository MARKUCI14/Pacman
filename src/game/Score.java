package game;

public class Score {
    private int score;

    public Score(){
        this.score = 0;
    }

    public void incrementScore(){
        this.score += 10;
    }

    public int get(){
        return score;
    }
}


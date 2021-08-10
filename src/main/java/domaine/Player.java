package domaine;

import domaine.exception.ChangeScoreNotAuthorized;
import lombok.Getter;

import static domaine.Scores.*;

@Getter
public class Player {

    private final String name;
    private String score;
    private Integer set;

    public Player(String name) {
        this.name = name;
        this.score = "0";
        this.set = 0;
    }

    public void addNewPoint() {
        if (ZERO.getValue().equals(score))
            score = FIFTEEN.getValue();
        else if (FIFTEEN.getValue().equals(score))
            score = THIRTY.getValue();
        else if (THIRTY.getValue().equals(score))
            score = FORTY.getValue();
        else if (FORTY.getValue().equals(score) || ADVANTAGE.getValue().equals(score) )
            score = WIN_GAME.getValue();

    }

    public void advantage() {
        if(!FORTY.getValue().equals(score)){
            throw new ChangeScoreNotAuthorized(String.format("you can't change the score to ADVANTAGE for they player %s", name));
        }
        this.score = ADVANTAGE.getValue();
    }

    public void backToEquality() {
        if(!ADVANTAGE.getValue().equals(score)){
            throw new ChangeScoreNotAuthorized(String.format("you can't back the score to FORTY for the player %s", name));
        }
        this.score = FORTY.getValue();
    }

    public boolean isDeuce(Player otherPlayer){
        return FORTY.getValue().equals(score) && otherPlayer.getScore().equals(score);
    }

    public boolean isTheWinner() {
        return WIN_GAME.getValue().equals(score);
    }

    public boolean isAdvantage() {
        return ADVANTAGE.getValue().equals(score);
    }

}

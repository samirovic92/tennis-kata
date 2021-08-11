package domaine;

import domaine.exception.ChangeScoreNotAuthorized;
import lombok.Getter;

import static domaine.Scores.*;

@Getter
public class Player {

    private final String name;
    private String point;
    private Integer game;
    private Integer tieBreak;

    public Player(String name) {
        this.name = name;
        this.point = "0";
        this.game = 0;
    }

    public void addNewPoint() {
        if (ZERO.getValue().equals(point))
            point = FIFTEEN.getValue();
        else if (FIFTEEN.getValue().equals(point))
            point = THIRTY.getValue();
        else if (THIRTY.getValue().equals(point))
            point = FORTY.getValue();
        else if (FORTY.getValue().equals(point) || ADVANTAGE.getValue().equals(point) )
            point = WIN_GAME.getValue();

    }

    public void advantage() {
        if(!FORTY.getValue().equals(point)){
            throw new ChangeScoreNotAuthorized(String.format("you can't change the score to ADVANTAGE for they player %s", name));
        }
        this.point = ADVANTAGE.getValue();
    }

    public void backToDeuce() {
        if(!ADVANTAGE.getValue().equals(point)){
            throw new ChangeScoreNotAuthorized(String.format("you can't back the score to FORTY for the player %s", name));
        }
        this.point = FORTY.getValue();
    }

    public boolean isDeuce(Player otherPlayer){
        return FORTY.getValue().equals(point) && otherPlayer.getPoint().equals(point);
    }

    public boolean wonTheMatch(Player otherPlayer) {
        return (game == 6 && (game - otherPlayer.getGame()) >= 2) || game == 7;
    }

    public boolean wonTheGame() {
        return WIN_GAME.getValue().equals(point);
    }

    public boolean hasAdvantage() {
        return ADVANTAGE.getValue().equals(point);
    }

    public void winNewGame() {
        this.game++;
        this.point = ZERO.getValue();
    }

    public void loseGame() {
        this.point = ZERO.getValue();
    }
}

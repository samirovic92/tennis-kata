package domaine;

import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static domaine.Scores.*;

@Getter
public class Player {

    private String name;
    private String score;
    private Integer set;
    private List<String> possibleScores =
            List.of(ZERO.getValue(), FIFTEEN.getValue(), THIRTY.getValue(), FORTY.getValue(), WIN_GAME.getValue());

    public Player(String name) {
        this.name = name;
        this.score = "0";
        this.set = 0;
    }

    public void addNewPoint() {
        int index = possibleScores.indexOf(score);
        if (!Objects.equals(possibleScores.size(), index + 1)){
            score = possibleScores.get(index + 1);
        }
    }

    public boolean isTheWinner() {
        return WIN_GAME.getValue().equals(score);
    }
}

package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static domaine.Scores.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

    private Game game;
    @BeforeEach
    public void setup() {
        game = new Game();
    }

    @Test
    public void should_start_Game() {
        // Given
        String playerName1 = "Player 1";
        String playerName2 = "Player 2";

        // when
        game.startGame(playerName1, playerName2);

        // Then
        assertEquals(game.getScore(playerName1), ZERO.getValue());
        assertEquals(game.getScore(playerName2), ZERO.getValue());
        assertEquals(game.getSet(playerName2), 0);
        assertEquals(game.getSet(playerName2), 0);
    }

    @Test
    public void add_new_point_to_player() {
        // Given
        String playerName1 = "Player 1";
        String playerName2 = "Player 2";
        game.startGame(playerName1, playerName2);

        // when
        game.scorePoint(playerName1);
        game.scorePoint(playerName1);
        game.scorePoint(playerName1);
        game.scorePoint(playerName2);

        // Then
        assertEquals(game.getScore(playerName1), FORTY.getValue());
        assertEquals(game.getScore(playerName2), FIFTEEN.getValue());
    }

    @Test
    public void should_return_the_winner(){

        // Given
        String playerName1 = "Player 1";
        String playerName2 = "Player 2";
        game.startGame(playerName1, playerName2);

        // when
        game.scorePoint(playerName1);
        game.scorePoint(playerName1);
        game.scorePoint(playerName1);
        game.scorePoint(playerName2);
        game.scorePoint(playerName1);

        // Then
        assertEquals(game.getScore(playerName1), WIN_GAME.getValue());
        assertEquals(game.getScore(playerName2), FIFTEEN.getValue());
        assertEquals(game.winner(), String.format("%s win the game", playerName1));
    }
}
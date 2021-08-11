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
        winGameForThePlayer(playerName1);
        winGameForThePlayer(playerName2);

        winGameForThePlayer(playerName1);
        winGameForThePlayer(playerName2);

        winGameForThePlayer(playerName1);
        winGameForThePlayer(playerName2);

        winGameForThePlayer(playerName1);
        winGameForThePlayer(playerName2);

        winGameForThePlayer(playerName1);
        winGameForThePlayer(playerName1);

        // Then
        assertEquals(game.getSet(playerName1), 6);
        assertEquals(game.getSet(playerName2), 4);
        assertEquals(game.winner(), String.format("%s win the game", playerName1));
    }

    @Test
    public void should_return_the_winner_in_case_of_more_than_six_set(){

        // Given
        String playerName1 = "Player 1";
        String playerName2 = "Player 2";
        game.startGame(playerName1, playerName2);

        // when
        winGameForThePlayer(playerName1);
        winGameForThePlayer(playerName2);

        winGameForThePlayer(playerName1);
        winGameForThePlayer(playerName2);

        winGameForThePlayer(playerName1);
        winGameForThePlayer(playerName2);

        winGameForThePlayer(playerName1);
        winGameForThePlayer(playerName2);

        winGameForThePlayer(playerName1);
        winGameForThePlayer(playerName2);

        winGameForThePlayer(playerName1);
        winGameForThePlayer(playerName1);

        // Then
        assertEquals(game.getSet(playerName1), 7);
        assertEquals(game.getSet(playerName2), 5);
        assertEquals(game.winner(), String.format("%s win the game", playerName1));
    }
    @Test
    public void should_not_return_the_winner(){

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
        assertEquals(game.winner(), null);
    }

    @Test
    public void should_return_ADVANTAGE_as_value_of_score() {
        // Given
        String playerName1 = "Player 1";
        String playerName2 = "Player 2";
        game.startGame(playerName1, playerName2);

        // when
        game.scorePoint(playerName1);
        game.scorePoint(playerName1);
        game.scorePoint(playerName1);

        game.scorePoint(playerName2);
        game.scorePoint(playerName2);
        game.scorePoint(playerName2);

        game.scorePoint(playerName1);

        // Then
        assertEquals(game.getScore(playerName1), ADVANTAGE.getValue());

    }

    @Test
    public void the_player_should_win_the_game_after_ADVANTAGE() {
        // Given
        String playerName1 = "Player 1";
        String playerName2 = "Player 2";
        game.startGame(playerName1, playerName2);

        // when
        game.scorePoint(playerName1);
        game.scorePoint(playerName1);
        game.scorePoint(playerName1);

        game.scorePoint(playerName2);
        game.scorePoint(playerName2);
        game.scorePoint(playerName2);

        game.scorePoint(playerName1);
        assertEquals(game.getScore(playerName1), ADVANTAGE.getValue());
        game.scorePoint(playerName1);

        // Then
        assertEquals(game.getScore(playerName1), ZERO.getValue());
        assertEquals(game.getSet(playerName1), 1);
        assertEquals(game.getSet(playerName2), 0);
    }

    @Test
    public void should_return_to_DEUCE_after_ADVANATGE() {
        // Given
        String playerName1 = "Player 1";
        String playerName2 = "Player 2";
        game.startGame(playerName1, playerName2);

        // when
        game.scorePoint(playerName1);
        game.scorePoint(playerName1);
        game.scorePoint(playerName1);

        game.scorePoint(playerName2);
        game.scorePoint(playerName2);
        game.scorePoint(playerName2);

        game.scorePoint(playerName1);
        game.scorePoint(playerName2);

        // Then
        assertEquals(game.getScore(playerName1), FORTY.getValue());
        assertEquals(game.getScore(playerName2), FORTY.getValue());

    }


    private void winGameForThePlayer(String playerName) {
        game.scorePoint(playerName);
        game.scorePoint(playerName);
        game.scorePoint(playerName);
        game.scorePoint(playerName);
    }
}
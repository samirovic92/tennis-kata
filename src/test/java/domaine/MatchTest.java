package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static domaine.Scores.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchTest {

    private Match match;
    @BeforeEach
    public void setup() {
        match = new Match();
    }

    @Test
    public void should_start_Game() {
        // Given
        String playerName1 = "Player 1";
        String playerName2 = "Player 2";

        // when
        match.startGame(playerName1, playerName2);

        // Then
        assertEquals(match.getPoint(playerName1), ZERO.getValue());
        assertEquals(match.getPoint(playerName2), ZERO.getValue());
        assertEquals(match.getGameScore(playerName2), 0);
        assertEquals(match.getGameScore(playerName2), 0);
    }

    @Test
    public void add_new_point_to_player() {
        // Given
        String playerName1 = "Player 1";
        String playerName2 = "Player 2";
        match.startGame(playerName1, playerName2);

        // when
        match.scorePoint(playerName1);
        match.scorePoint(playerName1);
        match.scorePoint(playerName1);

        match.scorePoint(playerName2);

        // Then
        assertEquals(match.getPoint(playerName1), FORTY.getValue());
        assertEquals(match.getPoint(playerName2), FIFTEEN.getValue());
    }

    @Test
    public void should_return_the_winner(){

        // Given
        String playerName1 = "Player 1";
        String playerName2 = "Player 2";
        match.startGame(playerName1, playerName2);

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
        assertEquals(match.getGameScore(playerName1), 6);
        assertEquals(match.getGameScore(playerName2), 4);
        assertEquals(match.winner(), String.format("%s win the game", playerName1));
    }

    @Test
    public void should_return_the_winner_in_case_of_more_than_six_set(){

        // Given
        String playerName1 = "Player 1";
        String playerName2 = "Player 2";
        match.startGame(playerName1, playerName2);

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
        assertEquals(match.getGameScore(playerName1), 7);
        assertEquals(match.getGameScore(playerName2), 5);
        assertEquals(match.winner(), String.format("%s win the game", playerName1));
    }
    @Test
    public void should_not_return_the_winner(){

        // Given
        String playerName1 = "Player 1";
        String playerName2 = "Player 2";
        match.startGame(playerName1, playerName2);

        // when
        match.scorePoint(playerName1);
        match.scorePoint(playerName1);
        match.scorePoint(playerName1);

        match.scorePoint(playerName2);

        // Then
        assertEquals(match.getPoint(playerName1), FORTY.getValue());
        assertEquals(match.getPoint(playerName2), FIFTEEN.getValue());
        assertEquals(match.winner(), null);
    }

    @Test
    public void should_return_ADVANTAGE_as_value_of_score() {
        // Given
        String playerName1 = "Player 1";
        String playerName2 = "Player 2";
        match.startGame(playerName1, playerName2);

        // when
        match.scorePoint(playerName1);
        match.scorePoint(playerName1);
        match.scorePoint(playerName1);

        match.scorePoint(playerName2);
        match.scorePoint(playerName2);
        match.scorePoint(playerName2);

        match.scorePoint(playerName1);

        // Then
        assertEquals(match.getPoint(playerName1), ADVANTAGE.getValue());

    }

    @Test
    public void the_player_should_win_the_game_after_ADVANTAGE() {
        // Given
        String playerName1 = "Player 1";
        String playerName2 = "Player 2";
        match.startGame(playerName1, playerName2);

        // when
        match.scorePoint(playerName1);
        match.scorePoint(playerName1);
        match.scorePoint(playerName1);

        match.scorePoint(playerName2);
        match.scorePoint(playerName2);
        match.scorePoint(playerName2);

        match.scorePoint(playerName1);
        assertEquals(match.getPoint(playerName1), ADVANTAGE.getValue());
        match.scorePoint(playerName1);

        // Then
        assertEquals(match.getPoint(playerName1), ZERO.getValue());
        assertEquals(match.getGameScore(playerName1), 1);
        assertEquals(match.getGameScore(playerName2), 0);
    }

    @Test
    public void should_return_to_DEUCE_after_ADVANATGE() {
        // Given
        String playerName1 = "Player 1";
        String playerName2 = "Player 2";
        match.startGame(playerName1, playerName2);

        // when
        match.scorePoint(playerName1);
        match.scorePoint(playerName1);
        match.scorePoint(playerName1);

        match.scorePoint(playerName2);
        match.scorePoint(playerName2);
        match.scorePoint(playerName2);

        match.scorePoint(playerName1);
        match.scorePoint(playerName2);

        // Then
        assertEquals(match.getPoint(playerName1), FORTY.getValue());
        assertEquals(match.getPoint(playerName2), FORTY.getValue());

    }


    private void winGameForThePlayer(String playerName) {
        match.scorePoint(playerName);
        match.scorePoint(playerName);
        match.scorePoint(playerName);
        match.scorePoint(playerName);
    }
}
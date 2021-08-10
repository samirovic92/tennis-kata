package domaine;

import domaine.exception.ChangeScoreNotAuthorized;
import org.junit.jupiter.api.Test;

import static domaine.Scores.*;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {


    @Test
    public void should_add_New_Point() {
        // Given
        Player player = new Player("player 1");

        // When
        player.addNewPoint();

        // Then
        assertEquals(player.getScore(), FIFTEEN.getValue());
    }

    @Test
    public void should_not_add_New_Point_if_player_win_set() {
        // Given
        Player player = new Player("player 1");

        // When
        player.addNewPoint();
        player.addNewPoint();
        player.addNewPoint();
        player.addNewPoint();
        assertEquals(player.getScore(), WIN_GAME.getValue());
        player.addNewPoint();

        // Then
        assertEquals(player.getScore(), WIN_GAME.getValue());
    }

    @Test
    public void should_return_advantage_in_case_of_DEUCE() {
        // Given
        Player player = new Player("player 1");

        // When
        player.addNewPoint();
        player.addNewPoint();
        player.addNewPoint();
        player.addNewPoint();
        assertEquals(player.getScore(), WIN_GAME.getValue());
        player.addNewPoint();

        // Then
        assertEquals(player.getScore(), WIN_GAME.getValue());
    }

    @Test
    public void should_verify_if_the_player_is_a_winner() {
        // Given
        Player player = new Player("player 1");

        // When
        player.addNewPoint();
        player.addNewPoint();
        player.addNewPoint();
        player.addNewPoint();

        // Then
        assertTrue(player.isTheWinner());
    }

    @Test
    public void should_verify_DEUCE_rule() {
        // Given
        Player player1 = new Player("player 1");
        Player player2 = new Player("player 2");

        // When
        player1.addNewPoint();
        player1.addNewPoint();
        player1.addNewPoint();

        player2.addNewPoint();
        player2.addNewPoint();
        player2.addNewPoint();

        // Then
        assertTrue(player1.isDeuce(player2));
        assertTrue(player2.isDeuce(player2));
    }

    @Test
    public void should_change_the_score_to_ADVANTAGE() {
        // Given
        Player player1 = new Player("player 1");

        // When
        player1.addNewPoint();
        player1.addNewPoint();
        player1.addNewPoint();
        player1.advantage();

        // Then
        assertEquals(player1.getScore(), ADVANTAGE.getValue());
    }

    @Test
    public void should_not_change_the_score_to_ADVANTAGE() {
        // Given
        Player player1 = new Player("player 1");

        // When
        player1.addNewPoint();

        // Then
        assertThrows(ChangeScoreNotAuthorized.class, player1::advantage);
    }

    @Test
    public void the_score_should_equal_in_ADVANTAGE_case() {
        // Given
        Player player1 = new Player("player 1");
        Player player2 = new Player("player 2");

        // When
        player1.addNewPoint();
        player1.addNewPoint();
        player1.addNewPoint();

        player2.addNewPoint();
        player2.addNewPoint();
        player2.addNewPoint();

        player1.advantage();
        player1.backToEquality();

        // Then
        assertEquals(player1.getScore(), FORTY.getValue());
        assertEquals(player2.getScore(), FORTY.getValue());
    }
}
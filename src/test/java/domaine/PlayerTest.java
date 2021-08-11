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
        assertEquals(player.getPoint(), FIFTEEN.getValue());
    }

    @Test
    public void should_add_New_tie_Break_Point_() {
        // Given
        Player player = new Player("player 1");

        // When
        player.addNewTieBreakPoint();

        // Then
        assertEquals(player.getTieBreakPoint(), 1);
    }

    @Test
    public void should_not_add_New_Point_if_player_win_the_game() {
        // Given
        Player player = new Player("player 1");

        // When
        player.addNewPoint();
        player.addNewPoint();
        player.addNewPoint();
        player.addNewPoint();
        assertEquals(player.getPoint(), WIN_GAME.getValue());
        player.addNewPoint();

        // Then
        assertEquals(player.getPoint(), WIN_GAME.getValue());
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
        assertEquals(player.getPoint(), WIN_GAME.getValue());
        player.addNewPoint();

        // Then
        assertEquals(player.getPoint(), WIN_GAME.getValue());
    }

    @Test
    public void should_verify_if_the_player_is_a_winner() {
        // Given
        Player player1 = new Player("player 1");
        Player player2 = new Player("player 2");

        // When
        for (int i =1; i <=6; i++) {
            player1.addNewPoint();
            player1.addNewPoint();
            player1.addNewPoint();
            player1.addNewPoint();
            player1.winNewGame();
        }

        // Then
        assertTrue(player1.wonTheMatch(player2));
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
        assertEquals(player1.getPoint(), ADVANTAGE.getValue());
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
        player1.backToDeuce();

        // Then
        assertEquals(player1.getPoint(), FORTY.getValue());
        assertEquals(player2.getPoint(), FORTY.getValue());
    }

    @Test
    public void should_increment_the_set_and_initialize_the_score_for_the_winner() {
        // Given
        Player player1 = new Player("player 1");

        // When
        player1.addNewPoint();
        player1.addNewPoint();
        player1.addNewPoint();
        player1.addNewPoint();
        player1.winNewGame();

        //Then
        assertEquals(player1.getPoint(), ZERO.getValue());
        assertEquals(player1.getGame(), 1);
    }

}
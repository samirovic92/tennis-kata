package domaine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static domaine.Scores.FIFTEEN;
import static domaine.Scores.WIN_GAME;
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
    public void should_verify_if_the_player_is_a_winner() {
        // Given
        Player player = new Player("player 1");

        // When
        player.addNewPoint();
        player.addNewPoint();
        player.addNewPoint();
        player.addNewPoint();

        // Then
        assertEquals(player.isTheWinner(), true);
    }
}
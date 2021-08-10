package domaine;

import static domaine.Scores.WIN_GAME;

public class Game {
    private Player player1;
    private Player player2;

    public void startGame(String playerName1, String playerName2) {
        this.player1 = new Player(playerName1);
        this.player2 = new Player(playerName2);
    }

    public void scorePoint(String playerName) {
        if(isEnded())
            return;
        if (isPlayer1(playerName))
            player1.addNewPoint();
        else
            player2.addNewPoint();
    }

    public String winner() {
        String winner = "%s win the game";
        if(player1.isTheWinner())
            return String.format(winner, player1.getName());
        if(player2.isTheWinner())
            return String.format(winner, player2.getName());
        return null;
    }

    // ------------------

    public String getScore(String playerName) {
        if (isPlayer1(playerName))
            return player1.getScore();
        return player2.getScore();
    }

    public Integer getSet(String playerName) {
        if (isPlayer1(playerName))
            return player1.getSet();
        return player2.getSet();
    }

    private boolean isEnded() {
        return player1.isTheWinner() || player2.isTheWinner();
    }

    private boolean isPlayer1(String playerName) {
        return playerName.equals(player1.getName());
    }

}
package domaine;

public class Match {
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
            addNewPointProcess(player1, player2);
        else
            addNewPointProcess(player2, player1);
    }

    private void addNewPointProcess(Player p1, Player p2) {
        if (p1.isDeuce(p2)) {
            p1.advantage();
        } else if (p2.hasAdvantage()) {
            p2.backToDeuce();
        } else {
            p1.addNewPoint();
            if(p1.wonTheGame()) {
                p1.winNewGame();
                p2.loseGame();
            }
        }
    }

    public String winner() {
        String winner = "%s win the game";
        if(player1.wonTheMatch(player2))
            return String.format(winner, player1.getName());
        if(player2.wonTheMatch(player1))
            return String.format(winner, player2.getName());
        return null;
    }

    // ------------------

    public String getPoint(String playerName) {
        if (isPlayer1(playerName))
            return player1.getPoint();
        return player2.getPoint();
    }

    public Integer getGameScore(String playerName) {
        if (isPlayer1(playerName))
            return player1.getGame();
        return player2.getGame();
    }

    private boolean isEnded() {
        return player1.wonTheMatch(player2) || player2.wonTheMatch(player1);
    }

    private boolean isPlayer1(String playerName) {
        return playerName.equals(player1.getName());
    }

}

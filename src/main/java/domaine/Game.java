package domaine;

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
            addNewPointProcess(player1, player2);
        else
            addNewPointProcess(player2, player1);
    }

    private void addNewPointProcess(Player p1, Player p2) {
        if (p1.isDeuce(p2)) {
            p1.advantage();
        } else if (p2.isAdvantage()) {
            p2.backToEquality();
        } else {
            p1.addNewPoint();
            if(p1.wonTheGame()) {
                p1.winNewSet();
                p2.loseSet();
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
        return player1.wonTheMatch(player2) || player2.wonTheMatch(player1);
    }

    private boolean isPlayer1(String playerName) {
        return playerName.equals(player1.getName());
    }

}

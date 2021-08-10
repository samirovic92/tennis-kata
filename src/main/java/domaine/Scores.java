package domaine;

public enum Scores {

    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    WIN_GAME("Win Game"),
    ADVANTAGE("ADV");

    private final String value;

    Scores(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

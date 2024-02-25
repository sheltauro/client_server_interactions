package org.example.proto;

// Demo class for the ProtoParser to generate.
public class Tennis {
    // Field num = 1.
    private String name;

    // Field num = 2.
    private Integer score;

    // Field num = 3.
    private Double doubleScore;

    // Field num = 4.
    private String[] players;

    public Tennis(String name, Integer score, Double doubleScore, String[] players) {
        this.name = name;
        this.score = score;
        this.doubleScore = doubleScore;
        this.players = players;
    }

    public KVPair<Class<?>, Object> get(int i) {
        switch (i) {
            case 1:
                return new KVPair<>(name.getClass(), name);
            case 2:
                return new KVPair<>(score.getClass(), score);
            case 3:
                return new KVPair<>(doubleScore.getClass(), doubleScore);
            case 4:
                return new KVPair<>(players.getClass(), players);
            default:
                throw new IllegalArgumentException("Invalid field number");
        }
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public double getDoubleScore() {
        return doubleScore;
    }

    public String[] getPlayers() {
        return players;
    }
}

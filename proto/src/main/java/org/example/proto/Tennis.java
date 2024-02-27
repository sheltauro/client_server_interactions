package org.example.proto;

import java.util.*;

// Demo class for the ProtoParser to generate.
// Pending: Keep a list of all populated fields with their proto number.
//          While setting a field, add it to the list.
//          While encoding, only encode the fields that are populated.
public class Tennis {
    // Field num = 1. REQUIRED.
    private String name;

    // Field num = 2. REQUIRED.
    private Integer score;

    // Field num = 3. OPTIONAL.
    private Double doubleScore;

    // Field num = 4. REPEATED.
    private String[] players;

    // Store the id
    private Set<Integer> populatedFields = null;

    public Tennis(String name, Integer score, Double doubleScore, String[] players) {
        this.name = name;
        this.score = score;
        this.doubleScore = doubleScore;
        this.players = players;
        populatedFields = new HashSet<Integer>(Arrays.asList(1, 2, 3, 4));
    }

    public Tennis() {
        populatedFields = new HashSet<Integer>();
    }

    // Builder for Tennis class.
    public static class Builder {
        private String name = null;
        private Integer score = null;
        private Double doubleScore = 0.0;
        private String[] players = new String[0];

        public Builder() {}

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setScore(Integer score) {
            this.score = score;
            return this;
        }

        public Builder setDoubleScore(Double doubleScore) {
            this.doubleScore = doubleScore;
            return this;
        }

        public Builder setPlayers(String[] players) {
            this.players = players;
            return this;
        }

        // Ensure that all required fields are present.
        public Tennis build() {
            Tennis tennis = new Tennis();
            Set<Integer> popFields = new HashSet<>();
            if (name == null) {
                throw new IllegalArgumentException("Field name is required");
            } else {
                popFields.add(1);
                tennis.name = name;
            }
            if (score == null) {
                throw new IllegalArgumentException("Field score is required");
            } else {
                popFields.add(2);
                tennis.score = score;
            }
            if (doubleScore != 0.0) {
                popFields.add(3);
            }
            tennis.doubleScore = doubleScore;

            if (players.length > 0) {
                popFields.add(4);
            }
            tennis.players = players;
            return tennis;
        }
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

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setDoubleScore(Double doubleScore) {
        this.doubleScore = doubleScore;
    }

    public void setPlayers(String[] players) {
        this.players = players;
    }

    public void setPopulatedFields(Set<Integer> populatedFields) {
        this.populatedFields = populatedFields;
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

    public byte[] encode() {
        byte[] nameBytes = Encoder.encodeString(name);
        byte[] scoreBytes = Encoder.encodeInt32(score);
        byte[] doubleScoreBytes = Encoder.encodeDouble(doubleScore);
        return nameBytes;
    }

    public static Tennis decode(byte[] bytes) {
        return new Tennis();
    }
}

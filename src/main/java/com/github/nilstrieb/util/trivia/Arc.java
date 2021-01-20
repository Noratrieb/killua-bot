package com.github.nilstrieb.util.trivia;

public enum Arc {
    EXAM(0), ZOLDYCK_FAMILY(1), HEAVENS_ARENA(2), YORKNEW_CITY(3),
    GREED_ISLAND(4), CHIMERA_ANT(5), ELECTION(6);

    private final int number;
    Arc(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

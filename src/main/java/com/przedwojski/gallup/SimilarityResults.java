package com.przedwojski.gallup;

import java.util.List;

public class SimilarityResults {
    private final Person hero;
    private final List<Person> twins;
    private final List<Person> almostTwins;
    private final List<Person> sameTopThree;

    public SimilarityResults(
            Person hero, List<Person> twins, List<Person> almostTwins, List<Person> sameTopThree) {
        this.hero = hero;
        this.twins = twins;
        this.almostTwins = almostTwins;
        this.sameTopThree = sameTopThree;
    }

    public Person getHero() {
        return hero;
    }

    public List<Person> getTwins() {
        return twins;
    }

    public List<Person> getAlmostTwins() {
        return almostTwins;
    }

    public List<Person> getSameTopThree() {
        return sameTopThree;
    }
}

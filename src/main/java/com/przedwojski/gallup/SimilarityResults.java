package com.przedwojski.gallup;

import java.util.List;

public class SimilarityResults {
    private final Person hero;
    private final List<Person> twins;
    private final List<Person> almostTwins;

    public SimilarityResults(Person hero, List<Person> twins, List<Person> almostTwins) {
        this.hero = hero;
        this.twins = twins;
        this.almostTwins = almostTwins;
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
}

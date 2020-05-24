package com.przedwojski.gallup;

import java.util.Arrays;
import java.util.List;

class Person {
    private final String name;
    private final String description;
    private final TopFive topFive;

    public Person(String name, String description, TopFive topFive) {
        this.name = name;
        this.description = description;
        this.topFive = topFive;
    }

    public TopFive getTopFive() {
        return this.topFive;
    }

    public List<Talent> getTopThree() {
        Talent[] talents = this.topFive.get();
        Talent[] topThree = Arrays.copyOfRange(talents, 0, 3);
        return Arrays.asList(topThree);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", description='" + description + '\'' + ", topFive=" + topFive + '}';
    }
}

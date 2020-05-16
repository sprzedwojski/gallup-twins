package com.przedwojski.gallup;

class Person {
    private final String name;
    private final String description;
    private final TopFive topFive;

    public Person(String name, String description, TopFive topFive) {
        this.name = name;
        this.description = description;
        this.topFive = topFive;
    }

    TopFive getTopFive() {
        return this.topFive;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", description='" + description + '\'' + ", topFive=" + topFive + '}';
    }
}

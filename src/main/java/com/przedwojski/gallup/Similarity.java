package com.przedwojski.gallup;

import java.util.*;
import java.util.stream.Collectors;

class Similarity {
    private final Person person;
    private final List<Person> allPeople;

    Similarity(Person person, List<Person> allPeople) {
        this.person = person;
        this.allPeople = allPeople;
    }

    SimilarityResults findSimilarPeople() {
        Set<Talent> heroTopFive = this.person.getTopFive().asSet();
        List<Talent> heroTopFiveList = Arrays.asList(this.person.getTopFive().get());
        List<Talent> heroTopThree = this.person.getTopThree();
        List<Person> peopleWithoutHero = allPeople.stream()
                                                 .filter(p -> !p.getName().equals(person.getName())) // exclude the hero
                                                 .collect(Collectors.toList());

        List<Person> twins = new ArrayList<>();
        List<Person> almostTwins = new ArrayList<>();
        List<Person> sameTopThree = new ArrayList<>();

        for (Person person : peopleWithoutHero) {
            Set<Talent> personTopFive = person.getTopFive().asSet();
            List<Talent> personTopThree = person.getTopThree();
            if (isSameTopThree(personTopThree, heroTopThree)) {
                sameTopThree.add(person);
            }
            if (personTopFive.equals(heroTopFive)) {
                twins.add(person);
            } else {
                for (Talent heroTalent : heroTopFiveList) {
                    Set<Talent> heroFourFromTopFive = heroTopFiveList.stream().filter(t -> t != heroTalent).collect(Collectors.toSet());
                    if (personTopFive.containsAll(heroFourFromTopFive)) {
                        almostTwins.add(person);
                    }
                }
            }
        }

        return new SimilarityResults(this.person, twins, almostTwins, sameTopThree);
    }

    private boolean isSameTopThree(List<Talent> personTopThree, List<Talent> heroTopThree) {
        if (personTopThree.get(0) == heroTopThree.get(0))
            if (personTopThree.get(1) == heroTopThree.get(1))
                return personTopThree.get(2) == heroTopThree.get(2);
        return false;
    }
}

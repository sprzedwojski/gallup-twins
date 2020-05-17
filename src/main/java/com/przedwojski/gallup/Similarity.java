package com.przedwojski.gallup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Similarity {
    private final Person person;
    private final List<Person> allPeople;

    Similarity(Person person, List<Person> allPeople) {
        this.person = person;
        this.allPeople = allPeople;
    }

    SimilarityResults findSameTopFive() {
        Set<Talent> heroTopFive = this.person.getTopFive().asSet();
        List<Talent> heroTopFiveList = Arrays.asList(this.person.getTopFive().get());

        List<Person> peopleWithoutHero = allPeople.stream()
                                                 .filter(p -> !p.getName().equals(person.getName())) // exclude the hero
                                                 .collect(Collectors.toList());

        List<Person> twins = new ArrayList<>();
        List<Person> almostTwins = new ArrayList<>();

        for (Person person : peopleWithoutHero) {
            Set<Talent> personTopFive = person.getTopFive().asSet();
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

        return new SimilarityResults(this.person, twins, almostTwins);
    }
}

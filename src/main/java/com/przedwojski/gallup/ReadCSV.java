package com.przedwojski.gallup;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ReadCSV {
    static Talent[] allTalents = new Talent[] {
            Talent.ACHIEVER,
            Talent.ARRANGER,
            Talent.BELIEF,
            Talent.CONSISTENCY,
            Talent.DELIBERATIVE,
            Talent.DISCIPLINE,
            Talent.FOCUS,
            Talent.RESPONSIBILITY,
            Talent.RESTORATIVE,
            Talent.ACTIVATOR,
            Talent.COMMAND,
            Talent.COMMUNICATION,
            Talent.COMPETITION,
            Talent.MAXIMIZER,
            Talent.SELF_ASSURANCE,
            Talent.SIGNIFICANCE,
            Talent.WOO,
            Talent.ADAPTABILITY,
            Talent.CONNECTEDNESS,
            Talent.DEVELOPER,
            Talent.EMPATHY,
            Talent.HARMONY,
            Talent.INCLUDER,
            Talent.INDIVIDUALIZATION,
            Talent.POSITIVITY,
            Talent.RELATOR,
            Talent.ANALYTICAL,
            Talent.CONTEXT,
            Talent.FUTURISTIC,
            Talent.IDEATION,
            Talent.INPUT,
            Talent.INTELLECTION,
            Talent.LEARNER,
            Talent.STRATEGIC
};

    List<Person> read() {
        List<Person> people = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("gallup.tsv"))) {
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                List<String> cells = getRecordFromLine(nextLine);
                String name = cells.get(0);
                String description = cells.get(1);
                TopFive topFive = new TopFive();
                int OFFSET = 3;
                for (int i = 0; i <= 33; i++) {
                    String value = cells.get(i + OFFSET);
                    if (!value.equals("")) {
                        try {
                            int talentPosition = Integer.parseInt(value);
                            if (talentPosition <= 5) {
                                Talent talent = allTalents[i];
                                topFive.setTalent(talent, talentPosition);
                            }
                        } catch (NumberFormatException e) {
                            // continue
                        }
                    }
                }
                Person person = new Person(name, description, topFive);
                people.add(person);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return people;
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter("\t");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public SimilarityResults getFor(String name) {
        if (name == null || "".equals(name))
            throw new IllegalArgumentException();
        List<Person> allPeople = new ReadCSV().read();
        Optional<Person> maybeHero =
                allPeople.stream().filter(p -> p.getName().equals(name)).findFirst();
        if (maybeHero.isEmpty())
            throw new IllegalArgumentException();
        Person hero = maybeHero.get();
        Similarity similarity = new Similarity(hero, allPeople);
        return similarity.findSimilarPeople();
    }
}

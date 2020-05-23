package com.przedwojski.gallup;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.przedwojski.gallup.Talent.UNDEFINED;

class TopFive {
    private Talent[] talents = new Talent[] {
            UNDEFINED,
            UNDEFINED,
            UNDEFINED,
            UNDEFINED,
            UNDEFINED,
    };

    void setTalent(Talent talent, int position) {
        talents[position - 1] = talent;
    }

    Talent[] get() {
        return talents.clone();
    }

    Set<Talent> asSet() {
        return new HashSet<>(Arrays.asList(talents));
    }

    @Override
    public String toString() {
        return Arrays.stream(talents).map(Enum::name).collect(Collectors.joining(", "));
    }
}

package com.przedwojski.gallup;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class TopFive {
    private Talent[] talents = new Talent[5];

    void setTalent(Talent talent, int position) {
        talents[position - 1] = talent;
    }

    Talent[] get() {
        return talents.clone();
    }

    Set<Talent> asSet() {
        return new HashSet<Talent>(Arrays.asList(talents));
    }

//    @Override
//    public String toString() {
//        return "TopFive{" + "talents=" + Arrays.toString(talents) + '}';
//    }


    @Override
    public String toString() {
        return Arrays.toString(talents);
    }
}

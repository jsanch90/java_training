package com.talos.javatraining.lesson3;

import java.util.List;

import static java.util.Arrays.asList;

public interface JawlessFish extends Fish {
    @Override
    default List<String> getCharacteristics() {
        List<String> res  = Fish.super.getCharacteristics();
        res.add("They don't have jaw");
        return res;
    }
}

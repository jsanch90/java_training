package com.talos.javatraining.lesson3;

import java.util.List;

import static java.util.Arrays.asList;

public interface Bird extends Animal {
    @Override
    default List<String> getCharacteristics() {
        return asList("Coat of feathers", "Warm-blooded metabolisms");
    }
}

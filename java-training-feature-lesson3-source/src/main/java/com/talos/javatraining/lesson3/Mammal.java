package com.talos.javatraining.lesson3;

import java.util.List;

import static java.util.Arrays.asList;

public interface Mammal extends Animal {
    @Override
    default List<String> getCharacteristics() {
        return asList("They have hair or fur", "They suckle milk when they are young");
    }
}

package com.talos.javatraining.lesson3;

import java.util.List;

import static java.util.Arrays.asList;

public interface Reptile extends Animal {
    @Override
    default List<String> getCharacteristics() {
        return asList("They ruled the earth for over 150 million years", "They can lay some distance away from bodies of water");
    }
}

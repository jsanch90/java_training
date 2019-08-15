package com.talos.javatraining.lesson3;

import java.util.List;

import static java.util.Arrays.asList;

public interface Invertebrate extends Animal {
    @Override
    default List<String> getCharacteristics() {
        return asList("Lack of backbones and internal skeletons", "Simple anatomy");
    }


}

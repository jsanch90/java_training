package com.talos.javatraining.lesson3;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public interface Fish extends Animal {
    @Override
    default List<String> getCharacteristics() {
        ArrayList<String> res = new ArrayList<>();
        res.add("They breathe using gills");
        res.add("They have dominated the world's oceans, lakes and rivers");
        return res;
    }
}

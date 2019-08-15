package com.talos.javatraining.lesson3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

public interface BonyFish extends Fish {
    @Override
    default List<String> getCharacteristics() {
        List<String> res = Fish.super.getCharacteristics();
        res.add("They have skeletons primarily composed of bone tissue");
        return res;
    }
}

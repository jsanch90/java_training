package com.talos.javatraining.lesson3;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public interface CartilaginousFish extends Fish {
    @Override
    default List<String> getCharacteristics() {
        List<String> res = Fish.super.getCharacteristics();
        res.add("They have skeleton made of cartilage rather than bone");
        return res;
    }
}

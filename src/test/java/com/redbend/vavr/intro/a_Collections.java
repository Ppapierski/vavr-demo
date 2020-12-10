package com.redbend.vavr.intro;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashSet;
import io.vavr.collection.List;

public class a_Collections {

    @Test
    void tuple() {
        final var pair = Pair.of(2, "Pac");

        // tuples everywhere!
        final var drink = new Tuple2<>(4, "fiter")
                .map1(i -> "for");

        System.out.println(drink.append(" w gardło "));


        // what can i do with it?
        final var tyWiesz = drink.apply((integer, s) -> "" + integer + s);
        System.out.println(tyWiesz);
    }

    @Test
    void list() {
        // Core Java immutability
        final var javaStringsList = java.util.List.of("one", "two", "three");

        //yup try me
//        javaStringsList.add("four");

        // vavr immutability
        final var list = List.of("one", "two", "three");

        // try me!
        final var four = list.append("four");
        System.out.println(four);

        // heads or tails?
        System.out.println(list.head());

        System.out.println(list.zipWithIndex());


        //and print me out!
        System.out.println(list);
    }

    @Test
    void map() {
        // some prepared map:
        final var map = HashMap.of(1, "one", 2, "one", 3, "three");


        // vavr specific operations
        System.out.println(map.filterValues("one"::equals));
        System.out.println(map.filterKeys(k -> k % 2 == 1));
    }

    @Test
    void set() {
        // nothing new here actually...
        HashSet.empty();
    }
}

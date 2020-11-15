package com.redbend.vavr.intro;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import operation.client.model.Client;
import io.vavr.concurrent.Future;
import io.vavr.control.Option;

import static io.vavr.collection.Stream.ofAll;

public class b_JavaLookAlikes {


    @Test
    void option() {
        //our pojo
        final var bobby = new Client("Bobby", "Smith", "12345");

        // java Optional we know and love
        Optional.ofNullable(bobby);

        // the Option 
        Option.of("42");
        Option.of(bobby);
    }

    @Test
    void stream() {
        // some fancy Streams they want... for loop ftw!
        Stream.of(1, 2, 3, 4, 5);
        IntStream.iterate(1, i -> i + 1);


        //vavr
        io.vavr.collection.Stream.of(1, 2, 3, 4, 5);

        System.out.println(io.vavr.collection.Stream.iterate(1, i -> i + 1));

    }

    @Test
    void st1() {
        IntStream.iterate(1, i -> i + 1)
                .limit(100000)
                .mapToObj(i -> RandomStringUtils.randomAlphabetic(10))
                .sorted(Comparator.naturalOrder())
                .reduce((s, s2) -> s).get();
    }

    @Test
    void st2() {
        io.vavr.collection.Stream.iterate(1, i -> i + 1)
                .take(100000)
                .map(i -> RandomStringUtils.randomAlphabetic(10))
                .sorted(Comparator.naturalOrder())
                .reduce((s, s2) -> s);
    }

    @Test
    void st3() {
        ofAll(IntStream.iterate(1, i -> i + 1).boxed())
                .take(100000)
                .map(i -> RandomStringUtils.randomAlphabetic(10))
                .sorted(Comparator.naturalOrder())
                .reduce((s, s2) -> s);
    }

    @Test
    void future() {
        Future.of(() -> "kiedyś tu będe");


    }

}

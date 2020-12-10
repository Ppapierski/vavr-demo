package com.redbend.vavr.intro;

import com.redbend.vavr.intro.client.Client;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import io.vavr.concurrent.Future;
import io.vavr.control.Option;

public class b_JavaLookalikes {

    @Test
    void option() {
        //our pojo
        final var bobby = new Client("Bobby", "Smith", "12345");

        // java Optional we know and love
        Optional.ofNullable(bobby)
                .map(Client::getMiddleName)
                .map(String::length)
                .orElse(0);

        // the Option 
        Option.of("42");
        Option.of(bobby)
                .flatMap(client -> Option.of(client.getMiddleName()))
                .map(String::length);
    }

    @Test
    void stream() {
        // some fancy Streams they want... for loop ftw!
        Stream.of(1, 2, 3, 4, 5);
        System.out.println(IntStream.iterate(1, i -> i + 1)
        .limit(5)
        );


        //vavr
        io.vavr.collection.Stream.of(1, 2, 3, 4, 5);

        System.out.println(io.vavr.collection.Stream.iterate(1, i -> i + 1));

    }

    @Test
    void future() {
        Future.of(() -> "kiedyś tu będe");
    }

}

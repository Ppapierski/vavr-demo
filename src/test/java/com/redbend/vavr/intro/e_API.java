package com.redbend.vavr.intro;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;

import operation.client.model.Model;
import operation.client.model.ModelPatterns;
import io.vavr.API;
import io.vavr.Patterns;
import io.vavr.Predicates;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import io.vavr.control.Option;
import io.vavr.control.Try;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.For;
import static io.vavr.API.Map;
import static io.vavr.API.Match;
import static io.vavr.API.TODO;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class e_API {

    @Test
    void unchecked() {
        Stream.iterate(0, i -> i + 1);
    }

    @Test
    Stream<?> toImplement() {
        return TODO();
    }

    @Test
    void forComp() {
        // with Option
        final var op1 = Option.of("4546547964231649465165498641231682");
        final var op2 = Option.of("2411278945416549831654465165845465");

        For(op1, op2)
                .yield((s, s2) -> new BigInteger(s).add(new BigInteger(s2)));


        //with try
        final Try<String> success = Try.success("success ");
        final Try<String> failure = Try.failure(new RuntimeException());
        API.For(success, failure)
                .yield((s, s2) -> s + s2);

        //with lists
        final var list1 = List.of(1, 2, 3);
        final var list2 = List.of("1", "2", "3");
        For(list1, list2)
                .yield((integer, s) -> s + integer);


        //with map
        final var map1 = Map("one", 1, "two", 2, "three", 3);
        final var map2 = Map("one", 1, "two", 2, "four", 3);

        For(map1, map2)
                .yield((stringIntegerTuple2, stringIntegerTuple22) -> stringIntegerTuple2.concat(stringIntegerTuple22))
                .toList();
    }


    @Test
    void patternMatching() {
        final Try<String> success = Try.success("");
        final Try<String> failure = Try.failure(new RuntimeException());

        failure.mapFailure(
                Case($(Predicates.instanceOf(IOException.class)), e -> new RuntimeException()));

        Match(success).of(
                Case(Patterns.$Success($()), (String val) -> true),
                Case(Patterns.$Failure($(Predicates.instanceOf(RuntimeException.class))), () -> false)
        );

        Model.Employee person = new Model.Employee("Carl", "EMP01");


        String result = Match(person).of(
                Case(ModelPatterns.$Employee($("Carl"), $()),
                        (name, id) -> "Carl has employee id " + id),
                Case($(),
                        () -> "not found"));

        final var list = List.of("1", "2", "3", "4");
        Match(list).of(
                Case(Patterns.$Cons($(i -> i.equals("1")), $()), () -> "ŁAN is FIRST")
        );


    }

    private int thrower(int i) throws IOException {
        throw new IOException();
    }
}

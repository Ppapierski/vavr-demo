package com.redbend.vavr.intro;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function4;

public class c_FunctionsEverywhere {


    @Test
    void composition() {
        //in Pure Java
        Function<Integer, Integer> add2 = i -> i + 2;
        Function<Integer, Integer> multiplyBy3 = i -> i * 3;
        add2.andThen(multiplyBy3).apply(1);

        //in Vavr
        Function1<Integer, Integer> add3 = i -> i + 3;
        Function1<Integer, Integer> multiplyBy2 = i -> i * 2;
        add3.andThen(multiplyBy2).apply(1);
    }

    @Test
    void lifting() {
        BiFunction<Integer, Integer, Integer> divideBi = (a, b) -> a / b;
        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
        System.out.println(Function2.lift(divideBi).apply(4, 0));
    }


    @Test
    void partial() {
        Function4<String, String, String, String, String> concat =
                (s, s2, s3, s4) -> s + s2 + s3 + s4;

        concat.apply("1");

    }


    @Test
    void memoization() {
        Function2<String, String, Integer> getSize =
                (s, s2) -> {
                    System.out.println("Counting combined size of strings " + s + " and " + s2);
                    return s.length() + s2.length();
                };

        final var memoized = getSize.memoized();
        memoized.apply("Zuza nie zadaje pytań!", "wcale");
        memoized.apply("Zuza nie zadaje pytań!", "wcale");
        memoized.apply("Zuza nie zadaje pytań!", "wcale");
        memoized.apply("Zuza nie zadaje pytań!", "wcale");
        memoized.apply("Zuza nie zadaje pytań!", "wcale");
        memoized.apply("Zuza nie zadaje pytań!", "wcale");
        memoized.apply("Zuza nie zadaje pytań!", "wcale");
        memoized.apply("Zuza nie zadaje pytań!", "wcale");
        memoized.apply("Zuza nie zadaje pytań!", "wcale a wcale");
    }
}

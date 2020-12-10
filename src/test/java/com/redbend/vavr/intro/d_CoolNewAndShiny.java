package com.redbend.vavr.intro;

import com.redbend.vavr.intro.client.Model;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.http.HttpConnectTimeoutException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;

import io.vavr.Lazy;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Try;
import io.vavr.control.Validation;

public class d_CoolNewAndShiny {

    @Test
    void either() {
        final var right = Either.right("I'm right");
        final var left = Either.left("I'm wrong");
    }

    @Test
    void tryy() {
        Try.of(this::veryAnnoyingThrowingMethod)
                .onFailure(FileNotFoundException.class, e -> e.printStackTrace())
                .recover(URISyntaxException.class, e -> "")
                .recoverWith(URISyntaxException.class, e -> Try.failure(new RuntimeException()))
                .get();
    }

    String veryAnnoyingThrowingMethod() throws FileNotFoundException,
            URISyntaxException,
            HttpConnectTimeoutException,
            InvalidAlgorithmParameterException,
            NoSuchAlgorithmException,
            FuckThisShitImOutException {
        return "return";
    }

    private static class EmployeeValidator {

        Validation<Seq<String>, Model.Employee> validate(String name, String id) {
            return Validation.combine(validateName(name), validateId(id)).ap(Model.Employee::new);
        }

        Validation<String, String> validateName(String name) {
            return StringUtils.isNotBlank(name)?
                    Validation.valid(name):
                    Validation.invalid("Cannot be empty");
        }

        Validation<String, String> validateId(String id) {
            return StringUtils.isNotBlank(id)?
                    Validation.valid(id):
                    Validation.invalid("Cannot be empty");
        }

    }

    @Test
    void validation() {
        System.out.println(new EmployeeValidator().validate("John", "abc1"));
        System.out.println(new EmployeeValidator().validate("", ""));
    }

    @Test
    void lazy() {
        final var answerToTheUltimateQuestionOfLifeTheUniverseAndEverything = Lazy.of(() -> 42).get();
    }

    private static class FuckThisShitImOutException extends Exception {
    }
}

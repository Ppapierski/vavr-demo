package com.redbend.vavr.intro;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.http.HttpConnectTimeoutException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;

import operation.client.model.Model;
import io.vavr.Lazy;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Validation;

public class d_CoolNewAndShiny {

    @Test
    void either() {
        final var right = Either.right("I'm right");
        final var left = Either.left("I'm wrong");

    }

    @Test
    void tryy() {
//        final var uri = new URI("");
//        new FileOutputStream(new File(uri));
//
//
//        Try.of(() -> new URL(""));

    }

    void veryAnnoyingThrowingMethod() throws FileNotFoundException,
            URISyntaxException,
            HttpConnectTimeoutException,
            InvalidAlgorithmParameterException,
            NoSuchAlgorithmException,
            FuckThisShitImOutException{

    }
    private static class EmployeeValidator {


        Validation<Seq<String>, Model.Employee> validate(String name, String id) {
            return null;
        }

        Validation<String, String> validateName(String name) {
            return null;
        }
        Validation<String, String> validateId(String id) {
            return null;
        }

    }

    @Test
    void validation() {

    }

    @Test
    void lazy() {

        final var answerToTheUltimateQuestionOfLifeTheUniverseAndEverything = Lazy.of(() -> 42);

    }















    private static class FuckThisShitImOutException extends Exception{}




}

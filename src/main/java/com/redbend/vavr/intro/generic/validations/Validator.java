package com.redbend.vavr.intro.generic.validations;

import com.redbend.vavr.intro.generic.api.ErrorCode;
import com.redbend.vavr.intro.generic.api.ErrorResponse;

import java.util.stream.Collectors;

import io.vavr.Tuple2;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Validation;

public interface Validator<T, R> {

    Validation<Seq<String>, R> validate(T input);

    static <T, R> Either<ErrorResponse, R> validating(T input, Validator<T, R> validator) {
        return validator.validate(input)
                .toEither()
                .mapLeft(s -> s.collect(Collectors.joining(" ")))
                .mapLeft(s -> new Tuple2<>(ErrorCode.VALIDATION_ERROR, String.format(ErrorCode.VALIDATION_ERROR.getMessage(), s)))
                .mapLeft(t -> t.apply(ErrorResponse::of));
    }

    static <T, R> Either<ErrorResponse, R> validating(T input, Validator<T, R> datavalidator, Validator<R, R > postValidator) {
        return datavalidator.validate(input)
                .toEither()
                .map(postValidator::validate)
                .flatMap(Validation::toEither)
                .mapLeft(s -> s.collect(Collectors.joining(" ")))
                .mapLeft(s -> new Tuple2<>(ErrorCode.VALIDATION_ERROR, String.format(ErrorCode.VALIDATION_ERROR.getMessage(), s)))
                .mapLeft(t -> t.apply(ErrorResponse::of));
    }

}

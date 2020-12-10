package com.redbend.vavr.intro.client;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.With;

@Getter
@Builder
public class Operation {

    @With
    private final String id;

    private final String intoId;
    private final String fromId;
    private final String title;

    private final BigDecimal amount;
    private final LocalDateTime timestamp;

}

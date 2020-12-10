package com.redbend.vavr.intro.report.generate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.redbend.vavr.intro.client.Operation;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.ToString;

import static com.redbend.vavr.intro.report.generate.ReportData.DATE;
import static com.redbend.vavr.intro.report.generate.ReportData.TIME;
import static com.redbend.vavr.intro.report.generate.ReportData.TRANSACTION_TITLE;
import static com.redbend.vavr.intro.report.generate.ReportData.TRANSACTION_TYPE;
import static com.redbend.vavr.intro.report.generate.ReportData.TransactionType.OUTGOING;
import static com.redbend.vavr.intro.report.generate.ReportData.VALUE;


@Getter
@ToString
@JsonPropertyOrder({DATE, TIME, TRANSACTION_TYPE, TRANSACTION_TITLE, VALUE})
public class ReportData {

    static final String DATE = "Transaction time";
    static final String TIME = "Transaction day";
    static final String TRANSACTION_TYPE = "Transaction type";
    static final String TRANSACTION_TITLE = "Title";
    static final String VALUE = "Transaction amount";

    @JsonProperty(value = DATE)
    private final String date;

    @JsonProperty(value = TIME)
    private final String time;

    @JsonProperty(value = TRANSACTION_TYPE)
    private final TransactionType type;

    @JsonProperty(value = TRANSACTION_TITLE)
    private final String title;

    @JsonProperty(value = VALUE)
    private final BigDecimal value;

    public ReportData(Operation operation) {
        this.date = operation.getTimestamp().toLocalDate().toString();
        this.time = operation.getTimestamp().toLocalTime().toString();
        this.type = operation.getAmount().compareTo(BigDecimal.ZERO) >= 0 ? TransactionType.INCOMING : OUTGOING;
        this.title = operation.getTitle();
        this.value = operation.getAmount();
    }

    public enum TransactionType {
        INCOMING,
        OUTGOING
    }
}

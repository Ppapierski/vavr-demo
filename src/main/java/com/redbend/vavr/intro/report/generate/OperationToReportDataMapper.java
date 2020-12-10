package com.redbend.vavr.intro.report.generate;

import com.redbend.vavr.intro.client.Operation;

import io.vavr.Function1;

public class OperationToReportDataMapper implements Function1<Operation, ReportData> {

    @Override
    public ReportData apply(Operation operation) {
        return new ReportData(operation);
    }
}

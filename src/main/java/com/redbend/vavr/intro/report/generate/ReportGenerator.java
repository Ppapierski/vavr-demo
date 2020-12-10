package com.redbend.vavr.intro.report.generate;

import com.redbend.vavr.intro.generic.api.ErrorCode;
import com.redbend.vavr.intro.generic.api.ErrorResponse;
import com.redbend.vavr.intro.generic.csv.CsvCreatorTemplate;

import java.nio.file.Path;
import java.nio.file.Paths;

import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import static java.io.File.separator;
import static java.nio.file.Paths.get;

@Slf4j
public class ReportGenerator {

    private static final String REPORT_PATH = "/tmp/reports";
    private static final String FILE_NAME = "Operation-com.redbend.vavr.intro.report-";
    private static final String FILE_SUFFIX = ".csv";

    private static final CsvCreatorTemplate<ReportData> CSV_TEMPLATE = CsvCreatorTemplate.createFor(ReportData.class);
    private final Path path;
    private final List<ReportData> dataList;

   public Either<ErrorResponse, Path> generate() {
        return createFileAndAddRow(dataList.head(), path)
                .andThenTry(() -> dataList.tail().forEach(data -> addRow(data, path)))
                .toEither()
                .map(o -> path)
                .mapLeft(e -> ErrorCode.REPORT_CREATION_FAILURE)
                .mapLeft(code -> ErrorResponse.of(code, path.toString()));
    }

    private Try<Void> createFileAndAddRow(ReportData reportData, Path pathToReport) {
        final var error_while_creating_operationReport = CSV_TEMPLATE.createFileWithHeaders(pathToReport)
                .onFailure(ex -> log.error("Error while creating OperationReport", ex))
                .flatMap(t -> CSV_TEMPLATE.addRow(reportData, pathToReport));
        return error_while_creating_operationReport;
    }

    private Try<Void> addRow(ReportData reportData, Path pathToReport) {
        return CSV_TEMPLATE.addRow(reportData, pathToReport);
    }

    public ReportGenerator(String fileIdent, List<ReportData> dataList) {
        this.dataList = dataList;
        this.path = Paths.get(REPORT_PATH + separator + FILE_NAME + fileIdent + FILE_SUFFIX);

        Path reportPath = get(REPORT_PATH);
        if (reportPath.toFile().mkdirs()) {
            log.info("Created reporting directory {}.", reportPath.toAbsolutePath().toString());
        }
    }
}

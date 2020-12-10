package com.redbend.vavr.intro.report.example;

import com.redbend.vavr.intro.generic.api.ErrorCode;
import com.redbend.vavr.intro.generic.api.ErrorResponse;
import com.redbend.vavr.intro.generic.csv.CsvCreatorTemplate;
import com.redbend.vavr.intro.report.generate.ReportData;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import static java.io.File.separator;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.nio.file.Paths.get;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Slf4j
public class ReportGenerator2 {

    private static final String REPORT_PATH = "/tmp/reports";
    private static final String FILE_NAME = "Operation-com.redbend.vavr.intro.report-";
    private static final String FILE_SUFFIX = ".csv";

    private static final CsvCreatorTemplate<ReportData> CSV_TEMPLATE = CsvCreatorTemplate.createFor(ReportData.class);
    private final Path path;
    private final List<ReportData> dataList;

    public Either<ErrorResponse, Path> generate() {
        var results = CSV_TEMPLATE.createFileWithHeaders(path)
                .onFailure(ex -> log.error("Failed creating Full Report.", ex))
                .map(t -> CSV_TEMPLATE.addRows(dataList.toJavaStream(), path))
                .map(s -> s.collect(groupingBy(Try::isSuccess, counting())))
                .toEither()
                .mapLeft(t -> ErrorResponse.of(ErrorCode.REPORT_CREATION_FAILURE, t.getMessage()));

        results.peek(this::logResults);
        return results.map(map -> path);
    }

    private void logResults(Map<Boolean, Long> results) {
        log.info("Generated temporary full com.redbend.vavr.intro.report: {}. Added {} batches successfully, failed for {} batches. ",
                path,
                results.getOrDefault(TRUE, 0L),
                results.getOrDefault(FALSE, 0L)
        );
    }

    public ReportGenerator2(String fileIdent, List<ReportData> dataList) {
        this.dataList = dataList;
        this.path = Paths.get(REPORT_PATH + separator + FILE_NAME + fileIdent + FILE_SUFFIX);

        Path reportPath = get(REPORT_PATH);
        if (reportPath.toFile().mkdirs()) {
            log.info("Created reporting directory {}.", reportPath.toAbsolutePath().toString());
        }
    }
}

package operation.report;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import operation.client.model.Operation;
import io.vavr.collection.List;

class ReportGeneratorTest {

    private static final Operation OPERATION1 = Operation.builder()
            .timestamp(LocalDateTime.now())
            .amount(new BigDecimal("-10"))
            .title("Małpka")
            .build();

    private static final Operation OPERATION2 = Operation.builder()
            .timestamp(LocalDateTime.now())
            .amount(new BigDecimal("14999.99"))
            .title("Programista prawie 15K")
            .build();



    @Test
    void createsCsv() {
        final var operations = List.of(OPERATION1, OPERATION2);

        final var generate = new ReportGenerator("test", operations.map(ReportData::new)).generate();

        // given
        // when
        // then
    }
}
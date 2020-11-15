package operation.report;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import operation.client.model.Operation;
import io.vavr.collection.List;

import static io.vavr.collection.List.*;

public class OperationRepositoryAdapter {


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

    private static final List<Operation> operations = of(OPERATION1, OPERATION2);

    public static List<Operation> getOperations() {
        return operations;
    }


}

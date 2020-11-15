package operation.client.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import io.vavr.collection.List;
import lombok.Builder;
import lombok.Getter;
import lombok.With;

@With
@Getter
@Builder
public class Account {

    private final String id;

    private final BigInteger number;
    private final Client owner;
    private final BigDecimal balance;
    private final List<Operation> operations;

}

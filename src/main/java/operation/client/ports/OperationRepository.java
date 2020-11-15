package operation.client.ports;

import operation.client.model.Operation;
import io.vavr.collection.List;

public interface OperationRepository {

    Operation addOperation(Operation operation);

    List<Operation> getOperationsById(String accountId);
}

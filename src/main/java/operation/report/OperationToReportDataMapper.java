package operation.report;

import operation.client.model.Operation;
import io.vavr.Function1;

public class OperationToReportDataMapper implements Function1<Operation, ReportData> {

    @Override
    public ReportData apply(Operation operation) {
        return new ReportData(operation);
    }
}

package operation.client.model;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.match.annotation.Patterns;
import io.vavr.match.annotation.Unapply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Patterns
public class Model {
    @Unapply
    public static Tuple3<String, String, String> Client(Client client) {
        return Tuple.of(client.getFirstName(), client.getMiddleName(), client.getLastName());
    }

    @Unapply
    static Tuple2<String, String> Employee(Employee Employee) {
        return Tuple.of(Employee.getName(), Employee.getId());
    }
















    @Getter
    @Setter
    @AllArgsConstructor
    public static class Employee {
        private String name;
        private String id;
    }
}

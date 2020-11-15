package operation.client.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.With;

@With
@Getter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Client {

    private String id;
    private final String firstName;
    private String middleName;
    private final String lastName;
    private final String idNumber;

    public Client(String firstName, String middleName, String lastName, String idNumber) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.idNumber = idNumber;
    }
}

package operation.client.model;

import io.vavr.collection.CharSeq;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

import static io.netty.util.internal.StringUtil.isNullOrEmpty;

public class ClientValidator implements Validator<CreateClientRequest, Client>{
    private static final String VALID_NAME_CHARS = "[a-ząćęłńóźżA-ZĄĆĘŁŃÓŚŹŻ]";
    private static final String VALID_ID_CHARS = "[0-9a-zA-Z]";

    public Validation<Seq<String>, Client> validate(CreateClientRequest request) {
        return Validation.combine(
                validateName(request.getFirstName(), "First name"),
                validateMiddleName(request.getMiddleName()),
                validateName(request.getLastName(), "Last name"),
                validateId(request.getIdNumber()))
                .ap(Client::new);
    }

    private Validation<String, String> validateName(String name, String nameType) {
        return isNullOrEmpty(name) ? Validation.invalid("Property " + nameType + " cannot be empty.")
                : CharSeq.of(name).replaceAll(VALID_NAME_CHARS, "")
                .transform(seq -> seq.isEmpty()
                        ? Validation.valid(name)
                        : Validation.invalid(nameType + " contains invalid characters: '"
                        + seq.distinct().sorted() + "'"));
    }

    private Validation<String, String> validateMiddleName(String name) {
        return isNullOrEmpty(name) ? Validation.valid(name) : validateName(name, "Middle name");
    }

    private Validation<String, String> validateId(String id) {
        return isNullOrEmpty(id) ? Validation.invalid("Property idNumber cannot be empty.") :
                CharSeq.of(id).replaceAll(VALID_ID_CHARS, "").transform(seq -> seq.isEmpty()
                        ? Validation.valid(id)
                        : Validation.invalid("ID number contains invalid characters: '"
                        + seq.distinct().sorted() + "'"));
    }
}

package operation.client.model;

import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

public class ClientFraudValidator implements Validator<Client, Client> {

    private static final String ZIOBRO_INVALID = "Przestań mi rodzine prześladować!";
    private static final String SASIN_INVALID = "100 ton makulatury i 70mln dalej...";
    private static final String KACZOR_INVALID = "A po co mu konto?!";


    @Override
    public Validation<Seq<String>, Client> validate(Client client) {
//        return null;
        return Match(client).of(
                Case(ModelPatterns.$Client($("Zbigniew"), $("Tadeusz"), $("Ziobro")),
                        (fn, mn, ln) -> Validation.invalid(List.of(ZIOBRO_INVALID))),
                Case(ModelPatterns.$Client($("Jacek"), $(), $()),
                        (fn, mn, ln) -> Validation.invalid(List.of(SASIN_INVALID))),
                Case(ModelPatterns.$Client($("Jarosław"), $(), $("Kaczyński")),
                        (fn, mn, ln) -> Validation.invalid(List.of(KACZOR_INVALID))),
                Case($(), () -> Validation.valid(client))


        );
    }
}

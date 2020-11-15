
package operation.client.adapter;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonException;
import javax.json.JsonObject;

import operation.client.model.ClientFraudValidator;
import operation.client.model.ClientValidator;
import operation.client.model.CreateClientRequest;
import io.helidon.common.http.Http;
import io.helidon.webserver.Handler;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;
import lombok.RequiredArgsConstructor;

import static operation.client.model.Validator.validating;
import static io.helidon.common.http.Http.Status.ACCEPTED_202;

/**
 * A simple service to greet you. Examples:
 * <p>
 * Get default greeting message: curl -X GET http://localhost:8080/greet
 * <p>
 * Get greeting message for Joe: curl -X GET http://localhost:8080/greet/Joe
 * <p>
 * Change greeting curl -X PUT -H "Content-Type: application/json" -d '{"greeting" : "Howdy"}'
 * http://localhost:8080/greet/greeting
 * <p>
 * The message is returned as a JSON object
 */

@RequiredArgsConstructor
public class BankingService implements Service {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    private static final Logger LOGGER = Logger.getLogger(BankingService.class.getName());


    /**
     * A service registers itself by updating the routing rules.
     *
     * @param rules the routing rules.
     */
    @Override
    public void update(Routing.Rules rules) {
        rules
                .post("/client", Handler.create(CreateClientRequest.class, this::createClientHandler));
    }

    private void createClientHandler(ServerRequest request, ServerResponse response, CreateClientRequest body) {
        final var clientEither = validating(body, new ClientValidator(), new ClientFraudValidator())
                .fold(errorResponse -> response.status(Http.Status.BAD_REQUEST_400).send(errorResponse),
                        whatever -> response.status(ACCEPTED_202).send());
    }

    private static <T> T processErrors(Throwable ex, ServerRequest request, ServerResponse response) {

        if (ex.getCause() instanceof JsonException) {

            LOGGER.log(Level.FINE, "Invalid JSON", ex);
            JsonObject jsonErrorObject = JSON.createObjectBuilder()
                    .add("error", "Invalid JSON")
                    .build();
            response.status(Http.Status.BAD_REQUEST_400).send(jsonErrorObject);
        } else {

            LOGGER.log(Level.FINE, "Internal error", ex);
            JsonObject jsonErrorObject = JSON.createObjectBuilder()
                    .add("error", "Internal error")
                    .build();
            response.status(Http.Status.INTERNAL_SERVER_ERROR_500).send(jsonErrorObject);
        }

        return null;
    }
}
package operation.client.ports;

import operation.client.model.Client;
import io.vavr.control.Option;

public interface ClientRepository {

    Client addClient(Client client);

    Option<Client> getClient(String id);

}

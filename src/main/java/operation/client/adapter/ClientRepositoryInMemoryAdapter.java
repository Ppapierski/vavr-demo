package operation.client.adapter;

import java.util.UUID;

import operation.client.model.Client;
import operation.client.ports.ClientRepository;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;

public class ClientRepositoryInMemoryAdapter implements ClientRepository {

    private Map<String, Client> repo = HashMap.empty();
    @Override
    public Client addClient(Client client) {
        final var client1 = client.withId(UUID.randomUUID().toString());
        repo = repo.put(client1.getId(), client);
        return client;
    }

    @Override
    public Option<Client> getClient(String id) {
       return repo.get(id);
    }
}

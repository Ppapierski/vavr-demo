package operation.client.adapter;

import org.junit.jupiter.api.Test;

import io.vavr.collection.HashMap;

class ClientRepositoryInMemoryAdapterTest {


    @Test
    void name() {

        final var map = HashMap.of("1", "1");
        System.out.println(map);
        final var map1 = map.put("2", "2");
        System.out.println(map);
        System.out.println(map1);

        // given
        // when
        // then
    }
}
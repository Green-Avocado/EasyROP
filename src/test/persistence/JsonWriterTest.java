package persistence;

import model.Payload;
import model.RopChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonWriterTest {

    @Test
    void testWriteObjectPayload() throws TypeMismatchException, IOException {
        Payload payload = new Payload();
        new JsonReader("./data/payloads/testReadPayload.json").payloadFromFile(payload);

        JsonWriter jsonWriter = new JsonWriter("./data/payloads/testWritePayload.json");
        jsonWriter.writeObject(payload);

        assertEquals(
                String.join("\n", Files.readAllLines(Paths.get("./data/payloads/testReadPayload.json"))),
                String.join("\n", Files.readAllLines(Paths.get("./data/payloads/testWritePayload.json")))
        );

        JsonWriter jsonWriterIllegal = new JsonWriter("./data");
        assertThrows(IOException.class, () -> jsonWriterIllegal.writeObject(new Payload()));
    }

    @Test
    void testWriteObjectRopChain() throws TypeMismatchException, IOException {
        RopChain ropChain = new RopChain();
        new JsonReader("./data/ropchains/testReadRopChain.json").ropChainFromFile(ropChain);

        JsonWriter jsonWriter = new JsonWriter("./data/ropchains/testWriteRopChain.json");
        jsonWriter.writeObject(ropChain);

        assertEquals(
                String.join("\n", Files.readAllLines(Paths.get("./data/ropchains/testReadRopChain.json"))),
                String.join("\n", Files.readAllLines(Paths.get("./data/ropchains/testWriteRopChain.json")))
        );

        JsonWriter jsonWriterIllegal = new JsonWriter("./data");
        assertThrows(IOException.class, () -> jsonWriterIllegal.writeObject(new RopChain()));
    }
}

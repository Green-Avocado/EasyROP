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
    private JsonWriter jsonWriter;
    private JsonWriter jsonWriterIllegal;

    @BeforeEach
    void runBefore() {
        jsonWriter = new JsonWriter("./data/testWrite.json");
        jsonWriterIllegal = new JsonWriter("./data");
    }

    @Test
    void testWriteObjectPayload() throws TypeMismatchException, IOException {
        Payload payload = new Payload();
        new JsonReader("./data/testReadPayload.json").payloadFromFile(payload);
        jsonWriter.writeObject(payload);

        assertEquals(
                String.join("\n", Files.readAllLines(Paths.get("./data/testReadPayload.json"))),
                String.join("\n", Files.readAllLines(Paths.get("./data/testWrite.json")))
        );
        assertThrows(IOException.class, () -> jsonWriterIllegal.writeObject(new Payload()));
    }

    @Test
    void testWriteObjectRopChain() throws TypeMismatchException, IOException {
        RopChain ropChain = new RopChain();
        new JsonReader("./data/testReadRopChain.json").ropChainFromFile(ropChain);
        jsonWriter.writeObject(ropChain);

        assertEquals(
                String.join("\n", Files.readAllLines(Paths.get("./data/testReadRopChain.json"))),
                String.join("\n", Files.readAllLines(Paths.get("./data/testWrite.json")))
        );
        assertThrows(IOException.class, () -> jsonWriterIllegal.writeObject(new RopChain()));
    }
}

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
        jsonWriterIllegal = new JsonWriter("/etc/passwd");
    }

    @Test
    void testWriteObject() throws TypeMismatchException, IOException {
        Payload payload = new JsonReader("./data/testReadPayload.json").payloadFromFile();
        RopChain ropChain = new JsonReader("./data/testReadRopChain.json").ropChainFromFile();
        String oldFile;
        String newFile;

        jsonWriter.writeObject(payload);
        oldFile = String.join("\n", Files.readAllLines(Paths.get("./data/testReadPayload.json")));
        newFile = String.join("\n", Files.readAllLines(Paths.get("./data/testWrite.json")));
        assertEquals(oldFile, newFile);

        jsonWriter.writeObject(ropChain);
        oldFile = String.join("\n", Files.readAllLines(Paths.get("./data/testReadRopChain.json")));
        newFile = String.join("\n", Files.readAllLines(Paths.get("./data/testWrite.json")));
        assertEquals(oldFile, newFile);

        assertThrows(IOException.class, () -> jsonWriterIllegal.writeObject(new Payload()));
    }
}

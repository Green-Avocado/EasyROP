package persistence;

import model.Payload;
import model.RopChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonReaderTest {
    private JsonReader jsonReaderPayload;
    private JsonReader jsonReaderRopChain;
    private JsonReader jsonReaderNoFile;
    private JsonReader jsonReaderRopChainMalformed;

    @BeforeEach
    void runBefore() {
        jsonReaderPayload = new JsonReader("./data/testReadPayload.json");
        jsonReaderRopChain = new JsonReader("./data/testReadRopChain.json");
        jsonReaderRopChainMalformed = new JsonReader("./data/testReadRopChainMalformed.json");
        jsonReaderNoFile = new JsonReader("FileDoesNotExist");
    }

    @Test
    void testPayloadFromFile() throws UnsupportedOperationException, IOException {
        Payload payload = jsonReaderPayload.payloadFromFile();
        assertEquals("testReadPayload", payload.getName());

        assertThrows(UnsupportedOperationException.class, () -> jsonReaderRopChain.payloadFromFile());
        assertThrows(UnsupportedOperationException.class, () -> jsonReaderRopChain.payloadFromFile());
        assertThrows(IOException.class, () -> jsonReaderNoFile.payloadFromFile());
    }

    @Test
    void testRopChainFromFile() throws UnsupportedOperationException, IOException {
        RopChain ropChain = jsonReaderRopChain.ropChainFromFile();
        assertEquals("testReadRopChain", ropChain.getName());

        assertThrows(UnsupportedOperationException.class, () -> jsonReaderPayload.ropChainFromFile());
        assertThrows(UnsupportedOperationException.class, () -> jsonReaderRopChainMalformed.ropChainFromFile());
        assertThrows(IOException.class, () -> jsonReaderNoFile.ropChainFromFile());
    }
}

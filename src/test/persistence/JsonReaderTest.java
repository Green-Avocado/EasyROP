package persistence;

import model.ExploitObjectType;
import model.Payload;
import model.RopChain;
import model.gadgets.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

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
    void testPayloadFromFile() throws TypeMismatchException, IOException {
        Payload payload = new Payload();
        jsonReaderPayload.payloadFromFile(payload);

        assertEquals("testReadPayload", payload.getName());
        assertEquals(1, payload.getLength());

        assertEquals("testReadRopChain", payload.get(0).getName());
        assertEquals(ExploitObjectType.ROP_CHAIN, payload.get(0).getExploitObjectType());

        assertEquals(
                "Payload cannot be of type ROP_CHAIN",
                assertThrows(
                        TypeMismatchException.class,
                        () -> jsonReaderRopChain.payloadFromFile(payload)
                ).getMessage()
        );

        assertThrows(IOException.class, () -> jsonReaderNoFile.payloadFromFile(payload));
    }

    @Test
    void testRopChainFromFile() throws TypeMismatchException, IOException {
        RopChain ropChain = new RopChain();
        jsonReaderRopChain.ropChainFromFile(ropChain);
        assertEquals("testReadRopChain", ropChain.getName());
        assertEquals(10, ropChain.getLength());

        assertEquals(ExploitObjectType.PADDING, ropChain.get(0).getExploitObjectType());
        assertEquals(ExploitObjectType.PADDING, ropChain.get(1).getExploitObjectType());
        assertEquals("8", ((Padding) ropChain.get(0)).getLength());
        assertEquals("0x16", ((Padding) ropChain.get(1)).getLength());

        assertEquals(ExploitObjectType.ADDRESS_GADGET, ropChain.get(2).getExploitObjectType());
        assertEquals(ExploitObjectType.ADDRESS_GADGET, ropChain.get(3).getExploitObjectType());
        assertEquals("exe.address", ((AddressGadget) ropChain.get(2)).getBase());
        assertEquals("libc.address", ((AddressGadget) ropChain.get(3)).getBase());
        assertEquals("0", ((AddressGadget) ropChain.get(2)).getOffset());
        assertEquals("0x1e6", ((AddressGadget) ropChain.get(3)).getOffset());

        assertEquals(ExploitObjectType.INSTRUCTIONS_GADGET, ropChain.get(4).getExploitObjectType());
        assertEquals(ExploitObjectType.INSTRUCTIONS_GADGET, ropChain.get(5).getExploitObjectType());
        assertEquals("exe", ((InstructionsGadget) ropChain.get(4)).getBase());
        assertEquals("libc", ((InstructionsGadget) ropChain.get(5)).getBase());
        assertEquals(Collections.singletonList("ret"), ((InstructionsGadget) ropChain.get(4)).getInstructions());
        assertEquals(Arrays.asList("pop rdi", "ret"), ((InstructionsGadget) ropChain.get(5)).getInstructions());

        assertEquals(ExploitObjectType.STRING_GADGET, ropChain.get(6).getExploitObjectType());
        assertEquals(ExploitObjectType.STRING_GADGET, ropChain.get(7).getExploitObjectType());
        assertEquals("exe", ((StringGadget) ropChain.get(6)).getBase());
        assertEquals("libc", ((StringGadget) ropChain.get(7)).getBase());
        assertEquals("/bin/sh\\x00", ((StringGadget) ropChain.get(6)).getString());
        assertEquals("cat flag.txt", ((StringGadget) ropChain.get(7)).getString());

        assertEquals(ExploitObjectType.SYMBOL_GADGET, ropChain.get(8).getExploitObjectType());
        assertEquals(ExploitObjectType.SYMBOL_GADGET, ropChain.get(9).getExploitObjectType());
        assertEquals("exe", ((SymbolGadget) ropChain.get(8)).getBase());
        assertEquals("libc", ((SymbolGadget) ropChain.get(9)).getBase());
        assertEquals("sym", ((SymbolGadget) ropChain.get(8)).getType());
        assertEquals("plt", ((SymbolGadget) ropChain.get(9)).getType());
        assertEquals("main", ((SymbolGadget) ropChain.get(8)).getSymbol());
        assertEquals("puts", ((SymbolGadget) ropChain.get(9)).getSymbol());

        assertEquals(
                "RopChain cannot be of type PAYLOAD",
                assertThrows(
                        TypeMismatchException.class,
                        () -> jsonReaderPayload.ropChainFromFile(new RopChain())
                ).getMessage()
        );
        assertEquals(
                "Gadget cannot be of type PAYLOAD",
                assertThrows(
                        TypeMismatchException.class,
                        () -> jsonReaderRopChainMalformed.ropChainFromFile(new RopChain())
                ).getMessage()
        );

        assertThrows(IOException.class, () -> jsonReaderNoFile.ropChainFromFile(new RopChain()));
    }
}

package model.gadgets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PaddingTest {
    private Padding padding;

    @BeforeEach
    void runBefore() {
        padding = new Padding();
    }

    @Test
    void testConstructor() {
        assertNull(padding.getLength());

        for (int i = 1; i <= 16; i++) {
            assertEquals(String.valueOf(i), new Padding(String.valueOf(i)).getLength());

            padding.setLength("0x" + i);
            assertEquals("0x" + i, new Padding("0x" + i).getLength());
        }
    }

    @Test
    void testFromList() {
        for (int i = 1; i <= 16; i++) {
            padding.fromList(Collections.singletonList(String.valueOf(i)));
            assertEquals(String.valueOf(i), padding.getLength());

            padding.fromList(Collections.singletonList("0x" + i));
            assertEquals("0x" + i, padding.getLength());
        }
    }

    @Test
    void testGetScript() {
        for (int i = 1; i <= 16; i++) {
            padding.setLength(String.valueOf(i));
            assertEquals("b'a' * " + i, padding.getScript());

            padding.setLength("0x" + (i));
            assertEquals("b'a' * 0x" + i, padding.getScript());
        }
    }

    @Test
    void testGetName() {
        for (int i = 1; i <= 16; i++) {
            padding.setLength(String.valueOf(i));
            assertEquals("Padding (" + i + ")", padding.getName());

            padding.setLength("0x" + i);
            assertEquals("Padding (0x" + i + ")", padding.getName());
        }
    }

    @Test
    void testSetGetLength() {
        for (int i = 1; i <= 16; i++) {
            padding.setLength(String.valueOf(i));
            assertEquals(String.valueOf(i), padding.getLength());

            padding.setLength("0x" + i);
            assertEquals("0x" + i, padding.getLength());
        }
    }
}

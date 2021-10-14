package model;

import model.gadgets.Padding;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaddingTest {

    @Test
    void testGetScript() {
        for (int i = 1; i <= 16; i++) {
            assertEquals("b'a' * " + i, new Padding(i).getScript());
        }
    }

    @Test
    void testGetName() {
        for (int i = 1; i <= 16; i++) {
            assertEquals("Padding (" + i + ")", new Padding(i).getName());
        }
    }
}

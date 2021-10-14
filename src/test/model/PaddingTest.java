package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PaddingTest {
    private Padding padding;

    @BeforeEach
    void runBefore() {
        padding = new Padding();
    }

    @Test
    void getScriptTest() {
        padding.setLength(0);
        assertEquals("", padding.getScript());

        for(int i = 1; i <= 16; i++) {
            String string = "b'a' * " + i;
            padding.setLength(i);
            assertEquals(string, padding.getScript());
        }
    }

    @Test
    void getNameTest() {
        for(int i = 0; i <= 16; i++) {
            padding.setLength(i);
            assertEquals("Padding (" + i + ")", padding.getName());
        }
    }

    @Test
    void setLengthTest() {
        for(int i = 0; i <= 16; i++) {
            padding.setLength(i);
            assertEquals(i, padding.getLength());
        }
    }
}

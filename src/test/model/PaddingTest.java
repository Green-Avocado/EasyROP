package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaddingTest {
    private Padding padding;

    @BeforeEach
    void runBefore() {
        padding = new Padding();
    }

    @Test
    void getScriptTest() {
        String string = "";

        for(int i = 0; i <= 16; i++) {
            padding.setLength(i);
            assertEquals(string, padding.getScript());
            string = string.concat("a");
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

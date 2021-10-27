package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeMismatchExceptionTest {
    TypeMismatchException typeMismatchException;

    @BeforeEach
    void runBefore() {
        typeMismatchException = new TypeMismatchException("testMessage");
    }

    @Test
    void testConstructor() {
        assertEquals("testMessage", typeMismatchException.getMessage());
    }
}

package com.github.natche.jrobohash.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the {@link JRoboHashException}.
 */
 public class JRoboHashExceptionTest {
    /**
     * Creates a new instance of this class for testing purposes.
     */
    JRoboHashExceptionTest() {}

    /**
     * Tests for creation of exceptions.
     */
    @Test
    @SuppressWarnings({"ThrowableNotThrown", "ConstantValue"})
    void testCreation() {
        String nullString = null;
        assertThrows(IllegalArgumentException.class, () -> new JRoboHashException(""));
        assertThrows(NullPointerException.class, () -> new JRoboHashException(nullString));
        assertDoesNotThrow(() -> new JRoboHashException("Exception"));
        assertDoesNotThrow(() -> new JRoboHashException(new Exception("Exception")));
    }
}

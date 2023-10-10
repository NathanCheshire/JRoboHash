package com.github.natche.jrobohash.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
    void testCreation() {
        assertDoesNotThrow(() -> new JRoboHashException("Exception"));
        assertDoesNotThrow(() -> new JRoboHashException(new Exception("Exception")));
    }
}

package com.github.natche.jrobohash.exceptions;

import com.google.common.base.Preconditions;

/**
 * An exception thrown by methods of the JRoboHash API.
 */
public final class JRoboHashException extends RuntimeException {
    /**
     * Creates a new {@link JRoboHashException} with the provided message.
     *
     * @param message the message
     * @throws NullPointerException if the provided message is null
     * @throws IllegalArgumentException if the provided message is empty
     */
    public JRoboHashException(String message) {
        super(performMessageChecks(message));
    }

    /**
     * Creates a new {@link JRoboHashException} from the provided exception.
     *
     * @param exception the exception
     * @throws NullPointerException if the provided exception is null
     */
    public JRoboHashException(Exception exception) {
        super(Preconditions.checkNotNull(exception).getMessage());
    }

    /**
     * Validates the provided exception message.
     *
     * @param message the message provided
     * @return the original message
     * @throws NullPointerException if the provided message is null
     * @throws IllegalArgumentException if the provided message is empty
     */
    private static String performMessageChecks(String message) {
        Preconditions.checkNotNull(message);
        Preconditions.checkArgument(!message.trim().isEmpty());
        return message;
    }
}
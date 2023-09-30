package com.github.natche.jrobohash.exceptions;

import com.google.common.base.Preconditions;

/**
 * An exception thrown by public methods of the JRoboHash API.
 */
public final class JRoboHashException extends RuntimeException {
    /**
     * Creates a new {@link JRoboHashException} with the provided message.
     *
     * @param message the message
     * @throws NullPointerException if the provided message is null
     */
    public JRoboHashException(String message) {
        super(Preconditions.checkNotNull(message));
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
}
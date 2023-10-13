package com.github.natche.jrobohash.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the {@link UseGravatar} states accepted by RoboHash.
 */
public class UseGravatarTest {
    /**
     * Constructs a new instance of this class for testing purposes.
     */
    UseGravatarTest() {}

    /**
     * Tests for the construct URL parameter method.
     */
    @Test
    void testConstructUrlParameter() {
        assertEquals("?gravatar=yes", UseGravatar.YES.constructUrlParameter(true));
        assertEquals("&gravatar=yes", UseGravatar.YES.constructUrlParameter(false));
        assertEquals("?gravatar=hashed", UseGravatar.HASHED.constructUrlParameter(true));
        assertEquals("&gravatar=hashed", UseGravatar.HASHED.constructUrlParameter(false));
        assertEquals("", UseGravatar.NO.constructUrlParameter(true));
        assertEquals("", UseGravatar.NO.constructUrlParameter(false));
    }
}

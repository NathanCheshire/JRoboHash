package com.github.natche.jrobohash.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the {@link BackgroundSet}s supported by RoboHash.
 */
public class BackgroundSetTest {
    /**
     * Constructs a new instance of this class for testing purposes.
     */
    BackgroundSetTest() {}

    /**
     * Tests for constructing the URL parameter.
     */
    @Test
    void testConstructUrlParameter() {
        assertEquals("?bgset=bg1", BackgroundSet.OUTSIDE.constructUrlParameter(true));
        assertEquals("&bgset=bg1", BackgroundSet.OUTSIDE.constructUrlParameter(false));
        assertEquals("?bgset=bg2", BackgroundSet.SPIRAL_AND_PATTERNS.constructUrlParameter(true));
        assertEquals("&bgset=bg2", BackgroundSet.SPIRAL_AND_PATTERNS.constructUrlParameter(false));
        assertEquals("?bgset=any", BackgroundSet.ANY.constructUrlParameter(true));
        assertEquals("&bgset=any", BackgroundSet.ANY.constructUrlParameter(false));
    }
}

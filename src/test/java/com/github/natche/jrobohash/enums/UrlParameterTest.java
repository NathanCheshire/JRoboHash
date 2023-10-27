package com.github.natche.jrobohash.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the {@link UrlParameter}s used by RoboHash.
 */
public class UrlParameterTest {
    /**
     * Constructs a new instance of this class for testing purposes.
     */
    UrlParameterTest() {}

    /**
     * Tests for the get URL parameter method.
     */
    @Test
    void testGetUrlParameterName() {
        assertEquals("", UrlParameter.IMAGE_EXTENSION.getUrlParameterName());
        assertEquals("gravatar", UrlParameter.USE_GRAVATAR.getUrlParameterName());
        assertEquals("ignoreext", UrlParameter.IGNORE_EXTENSION.getUrlParameterName());
        assertEquals("set", UrlParameter.IMAGE_SET.getUrlParameterName());
        assertEquals("sets", UrlParameter.IMAGE_SETS.getUrlParameterName());
        assertEquals("bgset", UrlParameter.BACKGROUND_SET.getUrlParameterName());
        assertEquals("size", UrlParameter.SIZE.getUrlParameterName());
    }

    /**
     * Tests for the encode URL parameter method.
     */
    @Test
    void testEncodeUrlParameter() {
        assertThrows(NullPointerException.class, () -> UrlParameter.USE_GRAVATAR.encodeUrlParameter(null, false));
        assertThrows(IllegalArgumentException.class, () -> UrlParameter.USE_GRAVATAR.encodeUrlParameter("", false));
        assertThrows(IllegalArgumentException.class, () -> UrlParameter.USE_GRAVATAR.encodeUrlParameter("   ", false));
        assertThrows(IllegalArgumentException.class, () -> UrlParameter.USE_GRAVATAR.encodeUrlParameter("   ", false));

        assertEquals("?gravatar=hashed", UrlParameter.USE_GRAVATAR.encodeUrlParameter("hashed", true));
        assertEquals("&gravatar=hashed", UrlParameter.USE_GRAVATAR.encodeUrlParameter("hashed", false));

        assertEquals("?ignoreext=true", UrlParameter.IGNORE_EXTENSION.encodeUrlParameter("true", true));
        assertEquals("&ignoreext=true", UrlParameter.IGNORE_EXTENSION.encodeUrlParameter("true", false));
        assertEquals("?ignoreext=false", UrlParameter.IGNORE_EXTENSION.encodeUrlParameter("false", true));
        assertEquals("&ignoreext=false", UrlParameter.IGNORE_EXTENSION.encodeUrlParameter("false", false));

        assertEquals("?set=set1", UrlParameter.IMAGE_SET.encodeUrlParameter("set1", true));
        assertEquals("&set=set1", UrlParameter.IMAGE_SET.encodeUrlParameter("set1", false));

        assertEquals("?sets=1,2,3", UrlParameter.IMAGE_SETS.encodeUrlParameter("1,2,3", true));
        assertEquals("&sets=1,2,3", UrlParameter.IMAGE_SETS.encodeUrlParameter("1,2,3", false));

        assertEquals("?bgset=bg1", UrlParameter.BACKGROUND_SET.encodeUrlParameter("bg1", true));
        assertEquals("&bgset=any", UrlParameter.BACKGROUND_SET.encodeUrlParameter("any", false));

        assertEquals("?size=300x300", UrlParameter.SIZE.encodeUrlParameter("300x300", true));
        assertEquals("&size=200x200", UrlParameter.SIZE.encodeUrlParameter("200x200", false));

        assertThrows(NullPointerException.class, () -> UrlParameter.SIZE.encodeUrlParameter(null));
        assertThrows(IllegalArgumentException.class, () -> UrlParameter.SIZE.encodeUrlParameter(""));
        assertThrows(IllegalArgumentException.class, () -> UrlParameter.SIZE.encodeUrlParameter("     "));
        assertEquals("&size=300x300", UrlParameter.SIZE.encodeUrlParameter("300x300"));
    }
}
